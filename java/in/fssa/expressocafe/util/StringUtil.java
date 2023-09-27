package in.fssa.expressocafe.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Product;
 
public class StringUtil {

	public static void rejectIfInvalidString(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
		 
	    String regexPattern = "^[A-Za-z][A-Za-z\\\\s]*$"; 
	    Pattern pattern = Pattern.compile(regexPattern);
	    Matcher matcher = pattern.matcher(input);
	    if (!matcher.matches()) {
	        throw new ValidationException(inputName.concat(" contains invalid characters"));
	    }	    
	}
	 
	public static void rejectIfInvalidString1(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		} 
	}
	
	public static void rejectIfInvalidStringWithoutPattern(String input, String inputName) throws ValidationException {
		if (input == null || "".equals(input.trim())) {
			
			throw new ValidationException(inputName.concat(" cannot be null or empty"));
		}
}
	

	public static void rejectIfInvalidEmail(String email) throws ValidationException {
		String regexPattern =  "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.matches()) {
			throw new ValidationException("Invalid email");
		}
	}

	public static void rejectIfIvalidPassword(String password) throws ValidationException {

		String regexPattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			throw new ValidationException("Password doesn't matches with pattern");
		}
	}
	
	public static void rejectIfIvalidName(String name) throws ValidationException {

		String regexPattern ="^[A-Za-z]+(?: [A-Za-z]+)*$";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(name);
		if (!matcher.matches()) {
			throw new ValidationException("Name doesn't matches with pattern");
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