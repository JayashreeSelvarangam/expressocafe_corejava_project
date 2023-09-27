package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.User;
import in.fssa.expressocafe.service.UserService;

public class TestUpdtaeUser {
	@Test
	@Order(13)
	void testUpdateUserWithValidData() {
	    UserService userService = new UserService();
	    User updatedUser = new User();

	    updatedUser.setFirstName("UpdatedFirstName");
	    updatedUser.setLastName("UpdatedLastName");
	    updatedUser.setPhoneNo(9876543210L);

	    assertDoesNotThrow(() -> {
	        userService.updateUser(1, updatedUser);
	    });
	}

	@Test
	@Order(14)
	void testUpdateUserWithInvalidData() {
	    UserService userService = new UserService();
	    User updatedUser = null;

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.updateUser(1, updatedUser);
	    });

	    String expectedMessage = "Invalid user input";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(15)
	void testUpdateUserWithInvalidFirstName() {
	    UserService userService = new UserService();
	    User updatedUser = new User();

	    updatedUser.setFirstName(null);
	    updatedUser.setLastName("UpdatedLastName");
	    updatedUser.setPhoneNo(9876543210L);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.updateUser(1, updatedUser);
	    });

	    String expectedMessage = "First Name cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(16)
	void testUpdateUserWithEmptyFirstName() {
	    UserService userService = new UserService();
	    User updatedUser = new User();

	    updatedUser.setFirstName("");
	    updatedUser.setLastName("UpdatedLastName");
	    updatedUser.setPhoneNo(9876543210L);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.updateUser(1, updatedUser);
	    });

	    String expectedMessage = "First Name cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(17)
	void testUpdateUserWithInvalidLastName() {
	    UserService userService = new UserService();
	    User updatedUser = new User();

	    updatedUser.setFirstName("UpdatedFirstName");
	    updatedUser.setLastName(null);
	    updatedUser.setPhoneNo(9876543210L);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.updateUser(1, updatedUser);
	    });

	    String expectedMessage = "Last Name cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(18)
	void testUpdateUserWithEmptyLastName() {
	    UserService userService = new UserService();
	    User updatedUser = new User();

	    updatedUser.setFirstName("UpdatedFirstName");
	    updatedUser.setLastName("");
	    updatedUser.setPhoneNo(9876543210L);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.updateUser(1, updatedUser);
	    });

	    String expectedMessage = "Last Name cannot be null or empty";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}

	@Test
	@Order(19)
	void testUpdateUserWithInvalidPhoneNo() {
	    UserService userService = new UserService();
	    User updatedUser = new User();

	    updatedUser.setFirstName("UpdatedFirstName");
	    updatedUser.setLastName("UpdatedLastName");
	    updatedUser.setPhoneNo(12345L);

	    Exception exception = assertThrows(ValidationException.class, () -> {
	        userService.updateUser(1, updatedUser);
	    });

	    String expectedMessage = "Phone number is invalid";
	    String actualMessage = exception.getMessage();

	    assertTrue(expectedMessage.equals(actualMessage));
	}
}
