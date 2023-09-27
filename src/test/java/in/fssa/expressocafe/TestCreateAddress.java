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


public class TestCreateAddress {
	@Test
	public void testCreateAddresstWithValideInput() {
		DeliverAddressService deliveryAddress = new DeliverAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setTitle("Home");
		address.setAddress("mannmha street");
		address.setLandmark("nera park");
		address.setCity("Kanchipuram");
		address.setPincode(620015);
		
		User user = new User();
		user.setId(5);
		address.setUser(user);
		
		assertDoesNotThrow(() -> {
			deliveryAddress.createDeliveryAddress(address);

		});
	}

	


	@Test
	public void testCreateProductWithInvalidInput() {
		DeliverAddressService deliveryAddress = new DeliverAddressService();
		Exception exception = assertThrows(Exception.class, () -> {
			deliveryAddress.createDeliveryAddress(null);
		});
		String expectedMessage = "Invalid Address input";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}


	@Test
	public void testCreateAddresstWithAlreadyCreatedAddress() {
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
		ValidationException exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});
		

		String exceptedMessage = "This address details is already exists";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);

	}


	@Test
	public void testCreateAddressTitletWithEmptyString() {
		DeliverAddressService deliveryAddress = new DeliverAddressService();
		DeliveryAddresses address = new DeliveryAddresses();
		address.setTitle("");
		address.setAddress("maha street");
		address.setLandmark("nera park");
		address.setCity("Kanchipuram");
		address.setPincode(620015);
		
		User user = new User();
		user.setId(5);
		address.setUser(user);
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Title cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
	}

	@Test
	public void testCreateAddressTitletWithNull() {
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
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Title cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressStreetNameWithEmptyString() {
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
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
	}

	@Test
	public void testCreateAddressStreetNameWithNull() {
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
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Address cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstLocationWithEmptyString() {
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
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "LandMark cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddresstLocationWithNull() {
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
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "LandMark cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));

	}

	@Test
	public void testCreateAddressCityWithEmptyString() {
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
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "City cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateAddressCityWithNull() {
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
		
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);
		});

		String exceptedMessage = "City cannot be null or empty";
		String actualMessage = exception.getMessage();
		
		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateAddressPincodeWithInvalidPinCode() {
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
		Exception exception = assertThrows(ValidationException.class, () -> {
			deliveryAddress.createDeliveryAddress(address);

		});

		String exceptedMessage = "Invalid Pincode. This pincode is not available for delivery.";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage,actualMessage);
		}
}


