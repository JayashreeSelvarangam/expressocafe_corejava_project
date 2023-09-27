package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.DeliveryAddresses;
import in.fssa.expressocafe.model.User;
import in.fssa.expressocafe.service.DeliverAddressService;

public class TestUpdateAddress {
	
	
	  @Test
	    public void testUpdateAddressWithValidInput() {
	        // Arrange
	        DeliverAddressService deliveryAddress = new DeliverAddressService();
	        DeliveryAddresses address = new DeliveryAddresses();
	        address.setTitle("Home");
	        address.setAddress("Updated Street");
	        address.setLandmark("Updated Landmark");
	        address.setCity("Updated City");
	        address.setPincode(620016);

			User user = new User();
			user.setId(5);
			address.setUser(user);
	        int existingAddressId = 1; // Replace with an actual existing address ID
	        // Act and Assert
	        assertDoesNotThrow(() -> {
	            deliveryAddress.updateAddress(existingAddressId, address);
	        });
	    }

	    @Test
	    public void testUpdateAddressWithInvalidInput() {
	        // Arrange
	        DeliverAddressService deliveryAddress = new DeliverAddressService();
	        int invalidAddressId = -1; // Replace with an invalid address ID
	        DeliveryAddresses address = null;

	        // Act and Assert
	        ValidationException exception = assertThrows(ValidationException.class, () -> {
	            deliveryAddress.updateAddress(invalidAddressId, address);
	        });

	        assertEquals("Invalid address Id", exception.getMessage());
	    }

	    @Test
	    public void testUpdateAddressWithNonexistentAddressId() {
	        // Arrange
	        DeliverAddressService deliveryAddress = new DeliverAddressService();
	        int nonexistentAddressId = 1000; // Replace with a nonexistent address ID
	        DeliveryAddresses address = new DeliveryAddresses();
	        address.setTitle("Home");
	        address.setAddress("Updated Street");
	        address.setLandmark("Updated Landmark");
	        address.setCity("Updated City");
	        address.setPincode(620016);
	    	User user = new User();
			user.setId(5);
			address.setUser(user);
	        // Act and Assert
	        ValidationException exception = assertThrows(ValidationException.class, () -> {
	            deliveryAddress.updateAddress(nonexistentAddressId, address);
	        });

	        assertEquals("Address with ID 1000 not found", exception.getMessage());
	    }

	    @Test
	    public void testUpdateAddressWithInvalidTitle() {
	        // Arrange
	        DeliverAddressService deliveryAddress = new DeliverAddressService();
	        int existingAddressId = 1; // Replace with an actual existing address ID
	        DeliveryAddresses address = new DeliveryAddresses();
	        address.setTitle(""); // Invalid title
	        address.setAddress("Updated Street");
	        address.setLandmark("Updated Landmark");
	        address.setCity("Updated City");
	        address.setPincode(620016);

	        // Act and Assert
	        ValidationException exception = assertThrows(ValidationException.class, () -> {
	            deliveryAddress.updateAddress(existingAddressId, address);
	        });

	        assertEquals("Title cannot be null or empty", exception.getMessage());
	    }

	
	
	    @Test
		public void testUpdateAddressTitletWithNull() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle(null);
			address.setAddress("maha street");
			address.setLandmark("nera park");
			address.setCity("Kanchipuram");
			address.setPincode(620015);
			User user = new User();
			user.setId(5);
			address.setUser(user);
			  int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});
			String exceptedMessage = "Title cannot be null or empty";
			String actualMessage = exception.getMessage();
			assertTrue(exceptedMessage.equals(actualMessage));

		}
	
	
		@Test
		public void testUpdateAddresstWithAlreadyCreatedAddress() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress("maha street");
			address.setLandmark("nera park");
			address.setCity("Kanchipuram");
			address.setPincode(620015);
			
			User user = new User();
			user.setId(5);
			address.setUser(user);
			 int existingAddressId = 1;
			ValidationException exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);
			});

			String exceptedMessage = "This address details is already exists";
			String actualMessage = exception.getMessage();
			assertEquals(exceptedMessage,actualMessage);
		}

	
	
		@Test
		public void testUpdateAddressStreetNameWithEmptyString() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress("");
			address.setLandmark("nera park");
			address.setCity("Kanchipuram");
			address.setPincode(620015);
			
			User user = new User();
			user.setId(5);
			address.setUser(user);
			int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});

			String exceptedMessage = "Address cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertEquals(exceptedMessage,actualMessage);
		}

		@Test
		public void testUpdateAddressStreetNameWithNull() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress(null);
			address.setLandmark("nera park");
			address.setCity("Kanchipuram");
			address.setPincode(620015);
			
			User user = new User();
			user.setId(5);
			address.setUser(user);
			int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});

			String exceptedMessage = "Address cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertTrue(exceptedMessage.equals(actualMessage));

		}
		

		@Test
		public void testUpdateAddresstLocationWithEmptyString() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress("jhghgf");
			address.setLandmark("");
			address.setCity("Kanchipuram");
			address.setPincode(620015);
			
			User user = new User();
			user.setId(5);
			address.setUser(user);
			int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});

			String exceptedMessage = "LandMark cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertTrue(exceptedMessage.equals(actualMessage));

		}

		@Test
		public void testUpdateAddresstLocationWithNull() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress("jhghgf");
			address.setLandmark(null);
			address.setCity("Kanchipuram");
			address.setPincode(620015);
			
			User user = new User();
			user.setId(5);
			address.setUser(user);
			int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});

			String exceptedMessage = "LandMark cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertTrue(exceptedMessage.equals(actualMessage));

		}

		@Test
		public void testUpdateAddressCityWithEmptyString() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress("jhghgf");
			address.setLandmark("hshjk");
			address.setCity("");
			address.setPincode(620015);
			
			User user = new User();
			user.setId(5);
			address.setUser(user);
			int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});
			String exceptedMessage = "City cannot be null or empty";
			String actualMessage = exception.getMessage();

			assertTrue(exceptedMessage.equals(actualMessage));
		}

		@Test
		public void testUpdateAddressCityWithNull() {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress("jhghgf");
			address.setLandmark("hshjk");
			address.setCity(null);
			address.setPincode(620015);

			User user = new User();
			user.setId(5);
			address.setUser(user);
			
			int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});

			String exceptedMessage = "City cannot be null or empty";
			String actualMessage = exception.getMessage();
			
			assertTrue(exceptedMessage.equals(actualMessage));
		}

		@Test
		public void testUpdateAddressPincodeWithInvalidPinCode1 () {
			DeliverAddressService deliveryAddress = new DeliverAddressService();
			DeliveryAddresses address = new DeliveryAddresses();
			address.setTitle("Home");
			address.setAddress("jhghgf");
			address.setLandmark("hshjk");
			address.setCity("hjk");
			address.setPincode(520001);
			User user = new User();
			user.setId(5);
			address.setUser(user);
			int existingAddressId = 1;
			Exception exception = assertThrows(ValidationException.class, () -> {
				deliveryAddress.updateAddress(existingAddressId,address);

			});

			String exceptedMessage = "Invalid Pincode. This pincode is not available for delivery.";
			String actualMessage = exception.getMessage();

			assertEquals(exceptedMessage,actualMessage);
			}

	
}
