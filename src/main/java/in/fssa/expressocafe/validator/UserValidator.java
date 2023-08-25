package in.fssa.expressocafe.validator;

import java.util.regex.Pattern;

import in.fssa.expressocafe.model.UserEntity;

public class UserValidator {
	    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
	 // private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
	    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^[6-9]\\d{9}$");

	    public static boolean validateUser(UserEntity user) {
	        if (user == null) {
	            System.out.println("User object cannot be null");
	            return false;
	        }
	        
	        if (!validateEmail(user.getEmail())) {
	            return false;
	        }
	        
//	        if (!validatePassword(user.getPassword())) {
//	            return false;
//	        }
	        if (!validatePhoneNumber(user.getPhoneNumber())) {
	            return false;
	        }
	        return true; // All validations passed
	    }



	    private static boolean validateName(String name) {
	        if (name == null || name.isEmpty()) {
	            System.out.println("Name cannot be null or empty");
	            return false;
	        }
	        return true;
	    }

	    private static boolean validateEmail(String email) {
	        if (email == null || email.isEmpty() || !Pattern.matches(EMAIL_PATTERN, email)) {
	            System.out.println("Email should not be null or empty and should match the given pattern");
	            return false;
	        }
	        return true;
	    }

//	    private static boolean validatePassword(String password) {
//	        if (password == null || password.isEmpty() || !Pattern.matches(PASSWORD_PATTERN, password)) {
//	            System.out.println("Password should not be null or empty and should match the given pattern");
//	            return false;
//	        }
//	        return true;
//	    }

	    private static boolean validatePhoneNumber(long phoneNumber) {
	        if (!PHONE_NUMBER_PATTERN.matcher(String.valueOf(phoneNumber)).matches()) {
	            System.out.println("Phone number should match the given pattern");
	            return false;
	        }
	        return true;
	    }
	}


