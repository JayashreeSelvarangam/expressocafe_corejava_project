package in.fssa.expressoCafe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Product;

public class StringUtil {

	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
		
	    String regexPattern = "^[a-zA-Z0-9_]*$"; // Add your desired regex pattern here
	    Pattern pattern = Pattern.compile(regexPattern);
	    Matcher matcher = pattern.matcher(input);
	    if (!matcher.matches()) {
	        throw new ValidationException(inputName.concat(" contains invalid characters"));
	    }
	    
	}
	
	public static void rejectIfInvalidStringWithoutPattern(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
	}

	public static void rejectIfInvalidEmail(String email) throws ValidationException {
		String regexPattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new ValidationException("Invalid email");
		}
	}

	public static void rejectIfIvalidPassword(String password) throws ValidationException {

		String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(password);

		if (!matcher.matches()) {

			throw new ValidationException("Password doesn't matches with pattern");
		}
	}

	public static boolean isValidString(String newString) {

		if (newString == null || "".equals(newString.trim())) {

			return false;
		}
		return true;

	}

	public static boolean isInvalidString(String newString) {

		if (!isValidString(newString)) {

			return true;
		}
		return false;

	}

	

}