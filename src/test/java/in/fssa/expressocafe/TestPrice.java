package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Random;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.service.PriceService;
import in.fssa.expressocafe.service.ProductService;

public class TestPrice {
	 @Test
	    void testUpdatePriceValidData1() {
	        int min = 1; // Minimum value for the random number
	        int max = 999; // Maximum value for the random number

	        Random rand = new Random();
	        int randomNumber = rand.nextInt(max - min + 1) + min;
	        PriceService priceService = new PriceService();
	        // Act and Assert
	        assertDoesNotThrow(() -> priceService.updatePrice(36, 3, randomNumber));
	    }
	@Test 
	void testUpdatePriceInvalidProductId() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class, () -> priceService.updatePrice(-1, 3, 9.99));
		assertEquals("Invalid ProductId should be greater than 0.", exception.getMessage());
	} 

	@Test
	void testUpdatePriceInvalidSizeId() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.updatePrice(11, -1, 9.99));
		assertEquals("Invalid SizeId should be greater than 0.", exception.getMessage());
	}

	@Test
	void testUpdatePriceInvalidPrice() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.updatePrice(999, 2, -9.99));
		assertEquals("Invalid Price should be greater than 0.", exception.getMessage());
	}

	@Test
	void testUpdatePriceNonExistentProductId() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.updatePrice(999, 1, 9.99));
		assertEquals("Invalid ProductId: no product exists in this product id", exception.getMessage());
	}

	@Test
	void testUpdatePriceNonExistentSizeId() {

		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.updatePrice(36, 999, 9.99));
		assertEquals("Invalid SizeId: no size exists in this product id", exception.getMessage());
	}

//    @Test
//    void testUpdatePriceForUpdatingSamePrize() {
//    	PriceService priceService = new PriceService();
//
//    	ServiceException exception = assertThrows(ServiceException.class, () -> priceService.updatePrice(345, 999, 9.99));
//    	assertEquals("Price Updation is unsuccessful", exception.getMessage());
//    }
//   
//    @Test
//    void testUpdatePriceForNonExistentProdIdAndSizeId() {
//    	PriceService priceService = new PriceService();
//
//    	ServiceException exception = assertThrows(ServiceException.class, () -> priceService.updatePrice(345, 999, 9.99));
//    	assertEquals("productprice does not exists", exception.getMessage());
//    }

//    // get all price for size and product

	@Test
	void testGetAllPriceSizeValidData() {
		PriceService priceService = new PriceService();

		// Act and Assert
		assertDoesNotThrow(() -> priceService.getHistoryOfProuctWithUniqueSize(36, 1));
	}

	@Test
	void testGetPriceSizeInvalidProductId() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.getHistoryOfProuctWithUniqueSize(-1, 1));
		assertEquals("Invalid ProductId should be greater than 0.", exception.getMessage());
	}

	@Test
	void testGetPriceSizeInvalidSizeId() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.getHistoryOfProuctWithUniqueSize(41, -1));
		assertEquals("Invalid SizeId should be greater than 0.", exception.getMessage());
	}

	@Test
	void testGetPriceSizeNonExistentProductId() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.getHistoryOfProuctWithUniqueSize(999, 1));
		assertEquals("Invalid ProductId: no product exists in this product id", exception.getMessage());
	}

	@Test
	void testGetPriceSizeNonExistentSizeId() {

		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class,
				() -> priceService.getHistoryOfProuctWithUniqueSize(36, 999));
		assertEquals("Invalid SizeId: no size exists in this product id", exception.getMessage());
	}

//    // get all prices for product id

	@Test
	void testGetAllPriceValidData() {
		PriceService priceService = new PriceService();
 
		assertDoesNotThrow(() -> priceService.getHistoryOfProuct(36));

	}

	@Test
	void testGetPriceInvalidProductId() {
		PriceService priceService = new PriceService();
		ValidationException exception = assertThrows(ValidationException.class, () -> priceService.getHistoryOfProuct(-1));
		assertEquals("Invalid ProductId should be greater than 0.", exception.getMessage());
	}

	@Test
	void testGetPriceNonExistentProductId() {
		PriceService priceService = new PriceService();

		ValidationException exception = assertThrows(ValidationException.class, () -> priceService.getHistoryOfProuct(999));
		assertEquals("Invalid ProductId: no product exists in this product id", exception.getMessage());
	}
}
