package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.service.DeliverAddressService;

public class TestAddress{
	
	

    @Test
    void testGetAddressesByUserIdAndStatusWithValidInput() {
        DeliverAddressService addressService = new DeliverAddressService();
        int userId = 5;
        int status = 1;

        assertDoesNotThrow(() -> {
            addressService.getAddressesByUserIdAndStatus(userId, status);
        });
    }

    @Test
    void testHasActiveAddressForUserWithValidInput() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = 5;

        assertDoesNotThrow(() -> {
            addressService.hasActiveAddressForUser(userId);
        });
    }

    @Test
    void testGetAddressByUserIdAndStatusWithValidInput() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = 5;
        int status = 1;

        assertDoesNotThrow(() -> {
            addressService.getAddressByUserIdAndStatus(userId, status);
        });
    }

    @Test
    void testUpdateAddressStatusWithValidInput() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int addressId = 1;
        int newStatus = 0;

        assertDoesNotThrow(() -> {
            addressService.updateAddressStatus(addressId, newStatus);
        });
    }

    @Test
    void testGetAllAddress() {
    	DeliverAddressService addressService = new DeliverAddressService();
        assertDoesNotThrow(() -> {
            addressService.getAllAddress();
        });
    }

    @Test
    void testFindAllAddressesByUserEmailWithValidInput() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = 5;
        assertDoesNotThrow(() -> {
            addressService.findAllAddressesByUserEmail(userId);
        });
    }

    @Test
    void testFindAddressByIdWithValidInput() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int addressId = 1;

        assertDoesNotThrow(() -> {
            addressService.findAddressById(addressId);
        });
    }
    
    
    @Test
    void testHasActiveAddressForUserWithValidUserId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = 1;

        assertDoesNotThrow(() -> {
            addressService.hasActiveAddressForUser(userId);
        });
    }

    @Test
    void testHasActiveAddressForUserWithInvalidUserId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = -1; // Invalid user ID

        assertThrows(ValidationException.class, () -> {
            addressService.hasActiveAddressForUser(userId);
        });
    }


    @Test
    void testGetAddressByUserIdAndStatusWithInvalidUserId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = -1; // Invalid user ID
        int status =0;
        assertThrows(ValidationException.class, () -> {
            addressService.getAddressByUserIdAndStatus(userId, status);
        });
    }

    @Test
    void testGetAddressByUserIdAndStatusWithInvalidStatus() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = 1;
        int status = 999; // Invalid status

        assertThrows(ValidationException.class, () -> {
            addressService.getAddressByUserIdAndStatus(userId, status);
        });
    }
    
 

    @Test
    void testUpdateAddressStatusWithInvalidAddressId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int addressId = -1; // Invalid address ID
        int newStatus = 2;

        assertThrows(ValidationException.class, () -> {
            addressService.updateAddressStatus(addressId, newStatus);
        });
    }

    @Test
    void testUpdateAddressStatusWithInvalidStatus() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int addressId = 1;
        int newStatus = 999; // Invalid status

        assertThrows(ValidationException.class, () -> {
            addressService.updateAddressStatus(addressId, newStatus);
        });
    }



    @Test
    void testFindAllAddressesByUserEmailWithInvalidUserId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = -1; // Invalid user ID

        assertThrows(ValidationException.class, () -> {
            addressService.findAllAddressesByUserEmail(userId);
        });
    }

    @Test
    void testFindAddressByIdWithValidAddressId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int addressId = 1;

        assertDoesNotThrow(() -> {
            addressService.findAddressById(addressId);
        });
    }

    @Test
    void testFindAddressByIdWithInvalidAddressId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int addressId = -1; // Invalid address ID

        assertThrows(ValidationException.class, () -> {
            addressService.findAddressById(addressId);
        });
    }
	
    @Test
    void testGetAddressesByUserIdAndStatusWithInvalidUserId() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = -1; // Invalid user ID

        assertThrows(ValidationException.class, () -> {
            addressService.getAddressesByUserIdAndStatus(userId, 0);
        });
    }

    @Test
    void testGetAddressesByUserIdAndStatusWithInvalidStatus() {
    	DeliverAddressService addressService = new DeliverAddressService();
        int userId = 1;
        int status = 999; // Invalid status

        assertThrows(ValidationException.class, () -> {
            addressService.getAddressesByUserIdAndStatus(userId, status);
        });
    }

}