package in.fssa.expressoCafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.service.PriceService;
import in.fssa.expressoCafe.service.ProductService;

public class TestPrice {

    @Test
    void testUpdatePriceValidData1() {
       
    	PriceService priceService = new PriceService();

        // Act and Assert
        assertDoesNotThrow(() -> priceService.updatePrice(10, 3, 90));
    }

    @Test
    void testUpdatePriceInvalidProductId() {
    	PriceService priceService = new PriceService();
    	
       
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.updatePrice(-1, 1, 9.99));
    	assertEquals("Invalid ProductId", exception.getMessage());
    }

    @Test
    void testUpdatePriceInvalidSizeId() {
    	PriceService priceService = new PriceService();
    	
        
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.updatePrice(11, 999, 9.99));
    	assertEquals("Invalid SizeId", exception.getMessage());
    }

    @Test
    void testUpdatePriceInvalidPrice() {
	PriceService priceService = new PriceService();
    	
        
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.updatePrice(999, 1, -9.99));
    	assertEquals("Invalid Price", exception.getMessage());
    }

    @Test
    void testUpdatePriceNonExistentProductId() {
    	PriceService priceService = new PriceService();
    	
        
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.updatePrice(999, 1, 9.99));
    	assertEquals("Invalid ProductId", exception.getMessage());
    }
  
    @Test
    void testUpdatePriceNonExistentSizeId() {
        
    	PriceService priceService = new PriceService();

        
    	ServiceException exception = assertThrows(ServiceException.class, () -> priceService.updatePrice(1, 999, 9.99));
    	assertEquals("Invalid SizeId", exception.getMessage());
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
        assertDoesNotThrow(() -> priceService.getHistoryOfProuctWithUniqueSize(11, 1));
    }
    
    @Test
    void testGetPriceSizeInvalidProductId() {
    	PriceService priceService = new PriceService();
    	
       
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.getHistoryOfProuctWithUniqueSize(-1, 1));
    	assertEquals("Invalid ProductId", exception.getMessage());
    }

    @Test
    void testGetPriceSizeInvalidSizeId() {
    	PriceService priceService = new PriceService();
    	
        
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.getHistoryOfProuctWithUniqueSize(11, -1));
    	assertEquals("Invalid SizeId", exception.getMessage());
    }

  
    @Test
    void testGetPriceSizeNonExistentProductId() {
    	PriceService priceService = new PriceService();
    	
        
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.getHistoryOfProuctWithUniqueSize(999, 1));
    	assertEquals("Invalid ProductId", exception.getMessage());
    }
  
    @Test
    void testGetPriceSizeNonExistentSizeId() {
        
    	PriceService priceService = new PriceService();

        
    	ServiceException exception = assertThrows(ServiceException.class, () -> priceService.getHistoryOfProuctWithUniqueSize(11, 999));
    	assertEquals("Invalid SizeId", exception.getMessage());
    }
   
    

  
//    // get all prices for product id
    
    @Test
    void testGetAllPriceValidData() {
    	PriceService priceService = new PriceService();

        assertDoesNotThrow(() -> priceService.getHistoryOfProuct(11));
    
    }
    @Test
    void testGetPriceInvalidProductId() {
    	PriceService priceService = new PriceService();
    	
       
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.getHistoryOfProuct(-1));
    	assertEquals("Invalid ProductId", exception.getMessage());
    }
   
    @Test
    void testGetPriceNonExistentProductId() {
    	PriceService priceService = new PriceService();
    	
        
    	ServiceException exception =  assertThrows(ServiceException.class, () -> priceService.getHistoryOfProuct(999));
    	assertEquals("Invalid ProductId", exception.getMessage());
    }
   
   

 

}

