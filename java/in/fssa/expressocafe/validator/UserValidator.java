package in.fssa.expressocafe.validator;

import java.util.regex.Pattern;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.User;
import in.fssa.expressocafe.model.UserEntity;
import in.fssa.expressocafe.util.StringUtil;

public class UserValidator {

	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([a-zA-Z0-9_+\\-\\. ]*[a-zA-Z0-9]+)?@[a-zA-Z0-9]+([a-zA-Z0-9\\-\\.]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,}$"; 

	private static final Pattern pattern1 = Pattern.compile(EMAIL_PATTERN);

	private static final String PASSWORD_PATTERN = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

	private static final Pattern pattern2 = Pattern.compile(PASSWORD_PATTERN);

	private static final String NAME_PATTERN = "^[A-Za-z]+(?: [A-Za-z]+)*$";

	private static final Pattern pattern3 = Pattern.compile(NAME_PATTERN);

	/**
	 * Validates a User object to ensure its properties are valid.
	 *
	 * This method checks whether the provided User object is valid for processing.
	 * It ensures that the User object and its properties meet the required
	 * validation criteria. Specifically, it validates the user's email, password,
	 * first name, and phone number. If any validation check fails, a 
	 * ValidationException is thrown with an appropriate error message.
	 *
	 * @param user The User object to be validated.
	 * @throws ValidationException If the provided User object or its properties are
	 *                             invalid.
	 */
	public static void validate(User user) throws ValidationException { 

		if (user == null) {
			throw new ValidationException("Invalid user input");
		}

		StringUtil.rejectIfInvalidString1(user.getEmail(), "Email");
		StringUtil.rejectIfInvalidString1(user.getPassword(), "Password");
		StringUtil.rejectIfInvalidString1(user.getFirstName(), "First Name");
		StringUtil.rejectIfInvalidString1(user.getLastName(), "Last Name");


		if (!pattern1.matcher(user.getEmail()).matches()) {
			throw new ValidationException(
					"Invalid email format. Please provide an email address with the following conditions:\n"
							+ "- Should follow the standard email format (e.g., user@example.com)\n"
							+ "- Should contain only letters, numbers, and special characters (@, .)\n"
							+ "- Should have a valid domain name and top-level domain (e.g., .com, .org)");
		}

		if (!pattern2.matcher(user.getPassword()).matches()) {
			throw new ValidationException(
					"Invalid password format. Please provide a password with the following conditions:\n"
							+ "- Should be at least 8 characters long\n"
							+ "- Should contain at least one lowercase letter\n"
							+ "- Should contain at least one uppercase letter\n"
							+ "- Should contain at least one digit\n"
							+ "- Should contain at least one special character (@, $, !, %, *, ?, &)");
		}

		if (!pattern3.matcher(user.getFirstName()).matches()) {
			throw new ValidationException("Name should only contain alphabets and be seprated by only one space");
		}

		if (!pattern3.matcher(user.getLastName()).matches()) {
			throw new ValidationException("Name should only contain alphabets and be seprated by only one space");
		}

		if (user.getPhoneNo() < 6000000000l || user.getPhoneNo() > 9999999999l) {
			throw new ValidationException("Phone number is invalid");
		}

	}
	
	public static void validateUpdate(User user) throws ValidationException { 
		if (user == null) {
			throw new ValidationException("Invalid user input");
		}
		StringUtil.rejectIfInvalidString1(user.getFirstName(), "First Name");
		StringUtil.rejectIfInvalidString1(user.getLastName(), "Last Name");
		if (!pattern3.matcher(user.getFirstName()).matches()) {
			throw new ValidationException("Name should only contain alphabets and be seprated by only one space");
		}
		if (!pattern3.matcher(user.getLastName()).matches()) {
			throw new ValidationException("Name should only contain alphabets and be seprated by only one space");
		}
		if (user.getPhoneNo() < 6000000000l || user.getPhoneNo() > 9999999999l) {
			throw new ValidationException("Phone number is invalid");
		}

	}
}

