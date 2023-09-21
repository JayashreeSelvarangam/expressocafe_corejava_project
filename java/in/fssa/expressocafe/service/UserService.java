package in.fssa.expressocafe.service;

import java.util.HashSet;
import java.util.Set;

import com.google.protobuf.ServiceException;

import in.fssa.expressocafe.DAO.UserDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.interfaces.UserServiceInterface;
import in.fssa.expressocafe.model.User;
import in.fssa.expressocafe.model.UserEntity;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.util.StringUtil;
import in.fssa.expressocafe.validator.UserValidator;

/**
 * This class implements the UserServiceInterface and provides methods to interact with user data.
 */
public class UserService {

	/**
	 * Retrieves a set of all active users from the database.
	 *
	 * This method queries the database to retrieve a set of all users who are
	 * marked as active. The retrieved user information is encapsulated in User
	 * objects and added to the returned set.
	 * 
	 * @return A set containing all active users retrieved from the database.
	 * @throws RuntimeException If an error occurs while querying the database.
	 */
	public Set<User> getAllUsers() {
		UserDAO userDAO = new UserDAO();
		Set<User> userList = new HashSet<>();
		try {
			userList = userDAO.findAll();
			for (User user : userList) {
				System.out.println(user);
			}
		} catch (PersistanceException e) {
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * Creates a new user in the database.
	 *
	 * This method is responsible for creating a new user in the database based on
	 * the provided User object. The provided user information is validated before
	 * attempting to create the user in the database.
	 * 
	 * @param newUser The User object containing the details of the new user to be
	 *                created.
	 * @throws Exception If the provided user information is invalid or an error
	 *                   occurs during database interaction.
	 */
	public void createUser(User newUser) throws ValidationException, ServiceException {
		UserDAO userDAO = new UserDAO();
		try {
			UserValidator.validate(newUser);
			boolean check = userDAO.isUserEmailPresent(newUser.getEmail());
			
			if(check) {
				throw new ServiceException("An account with the email already exists");
			}
			userDAO.create(newUser);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Updates an existing user's information in the database.
	 *
	 * This method is responsible for updating the information of an existing user
	 * in the database based on the provided user ID and updated User object. The
	 * provided user ID and updated information are validated before attempting to
	 * update the user's information in the database.
	 * 
	 * @param id          The ID of the user whose information needs to be updated.
	 * @param updatedUser The User object containing the updated details of the
	 *                    user.
	 * @throws ValidationException, ServiceException 
	 * @throws Exception If the provided user ID is invalid, the user does not
	 *                   exist, the updated user information is invalid, or an error
	 *                   occurs during database interaction.
	 */
	public void updateUser(int id, User updatedUser) throws ValidationException, ServiceException{

		try {
			UserDAO userDAO = new UserDAO();
			IntUtil.rejectIfInvalidInt(id,"userId");
			boolean check = UserDAO.isUserPresent(id);
			
			if (!check) {
				throw new ServiceException("User does not exist");
			}
			UserValidator.validate(updatedUser);
			userDAO.update(id, updatedUser);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}


	}

	/**
	 * Deletes a user from the database.
	 *
	 * This method is responsible for deleting a user from the database based on the
	 * provided user ID. The provided user ID is validated before attempting to
	 * delete the user.
	 * 
	 * @param userId The ID of the user to be deleted.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws PersistanceException 
	 * @throws RuntimeException    If the user does not exist or an error occurs
	 *                             during database interaction.
	 */
	public void deleteUser(int userId) throws ValidationException, ServiceException {

		try {
			IntUtil.rejectIfInvalidInt(userId,"userId");
			
			boolean check = UserDAO.isUserPresent(userId);
			
			if (!check) {
				throw new ServiceException("User does not exist");
			}
			UserDAO userDAO = new UserDAO();
			userDAO.delete(userId);

		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Retrieves a user from the database based on the provided user ID.
	 *
	 * This method is responsible for retrieving a user from the database using the
	 * provided user ID. The provided user ID is validated before attempting to
	 * retrieve the user.
	 * 
	 * @param userId The ID of the user to be retrieved.
	 * @return The retrieved user.
	 * @throws ValidationException If the provided user ID is invalid.
	 * @throws PersistanceException 
	 * @throws RuntimeException    If the user does not exist or an error occurs
	 *                             during database interaction.
	 */ 
	public User findByUserId(int userId) throws ValidationException, ServiceException {
		try {
			IntUtil.rejectIfInvalidInt(userId,"userId");
			
			boolean check = UserDAO.isUserPresent(userId);
			
			if (!check) {
				throw new ServiceException("User does not exist");
			}
			
			UserDAO userDAO = new UserDAO();
			User user = null;
			user =  userDAO.findById(userId);
			return user;
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
	}

	public int loginUser(String email, String password) throws ValidationException, ServiceException {
		int userId = -1 ;
		try {
			StringUtil.rejectIfInvalidEmail(email);
			StringUtil.rejectIfIvalidPassword(password);
			
			User user = UserDAO.findByEmail(email);
			userId = user.getId();
			UserDAO.passwordChecker(email, password);
		}
		catch (PersistanceException e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return userId ; 
	}
	
	
	public User findByEmail(String email) throws ValidationException, ServiceException {
 
		User user = null;
		try {
			StringUtil.rejectIfInvalidEmail(email);
			user = UserDAO.findByEmail(email);
		} 
		catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Failed to findById");
		}
		System.out.println(user);
		return user;
	}
}
























