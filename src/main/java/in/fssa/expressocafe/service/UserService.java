package in.fssa.expressocafe.service;

import in.fssa.expressocafe.dao.UserDAO;
import in.fssa.expressocafe.interfaces.UserServiceInterface;
import in.fssa.expressocafe.model.UserEntity;
import in.fssa.expressocafe.validator.UserValidator;

/**
 * This class implements the UserServiceInterface and provides methods to interact with user data.
 */
public class UserService implements UserServiceInterface {

    private UserDAO userDAO;

    /**
     * Constructs a UserService instance with the provided UserDAO.
     *
     * @param userDAO The UserDAO instance to be used for data access.
     */
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Checks if an email address exists in the database.
     *
     * @param email The email address to be checked.
     * @return true if the email address exists, false otherwise.
     */
    @Override
    public boolean doesEmailExist(String email) {
        return userDAO.doesEmailExist(email);
    }

    /**
     * Checks if a phone number exists in the database.
     *
     * @param phoneNumber The phone number to be checked.
     * @return true if the phone number exists, false otherwise.
     */
    @Override
    public boolean doesPhoneNumberExist(long phoneNumber) {
        return userDAO.doesPhoneNumberExist(phoneNumber);
    }

    /**
     * Creates a new user if the provided UserEntity is valid and the email/phone number doesn't already exist.
     *
     * @param user The UserEntity to be created.
     * @return The created UserEntity if successful, null if validation fails or user already exists.
     */
    @Override
    public UserEntity createUser(UserEntity user) {
        if (UserValidator.validateUser(user)) {
            if (!doesEmailExist(user.getEmail()) && !doesPhoneNumberExist(user.getPhoneNumber())) {
                return userDAO.createUser(user);
            }
        }
        // Return null if validation fails or user already exists
        return null;
    }
}

























