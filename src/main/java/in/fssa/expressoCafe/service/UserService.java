package in.fssa.expressoCafe.service;
import in.fssa.expressoCafe.dao.UserDAO;
import in.fssa.expressoCafe.interfaces.UserServiceInterface;
import in.fssa.expressoCafe.model.UserEntity;
import in.fssa.expressoCafe.validator.UserValidator;
public class UserService implements UserServiceInterface{
	 private UserDAO userDAO;
	    public  UserService(UserDAO userDAO) {
	        this.userDAO = userDAO;
	    }
	    @Override
	    public boolean doesEmailExist(String email) {
	        // Implement logic using userDAO to check if the email exists in the database
	        // Return true if email exists, false otherwise
	        return userDAO.doesEmailExist(email);
	    }
	    @Override
	    public boolean doesPhoneNumberExist(long phoneNumber) {
	        // Implement logic using userDAO to check if the phone number exists in the database
	        // Return true if phone number exists, false otherwise
	        return userDAO.doesPhoneNumberExist(phoneNumber);
	    }
	
		@Override
		public UserEntity createUser(UserEntity user) {
			// TODO Auto-generated method stub
			if (UserValidator.validateUser(user)) {
	            if (!doesEmailExist(user.getEmail()) && !doesPhoneNumberExist(user.getPhoneNumber())) {
	                return userDAO.createUser(user);
	            }
	        }
	         // Return null if validation fails or user already exists
			return null;
		}
}


























