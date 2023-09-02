package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.google.protobuf.ServiceException;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.service.UserService;

public class TestDeleteUser {

		@Test
		@Order(1)
		public void deleteUserByValidId() {
			UserService userService = new UserService();

			int id = 1;

			assertDoesNotThrow(() -> {
				userService.deleteUser(id);
			});
		}

		@Test
		@Order(2)
		public void deleteUserByInvalidId() {
			UserService userService = new UserService();

			int id = -3;
			
			Exception exception = assertThrows(ValidationException.class, () -> {
				userService.deleteUser(id);
			});
			String expectedMessage = "Invalid Id";	
			String actualMessage = exception.getMessage();
		}
		
		@Test
		@Order(3)
		public void deleteUserByNonExistingId() {
			UserService userService = new UserService();

			int id = 29;
			
			ServiceException exception = assertThrows(ServiceException.class, () -> {
				userService.deleteUser(id);
			});

			String expectedMessage = "User does not exist";
			String actualMessage = exception.getMessage();
		}
		
	}

