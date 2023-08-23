package in.fssa.expressoCafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.model.SizeEnum;
import in.fssa.expressoCafe.service.ProductService;

 public class TestGetProduct {
	 
		@Test

		void testGetProductWithExistingProductId() {

			int productId = 11;
			ProductService productService = new ProductService();
			assertDoesNotThrow(() -> productService.findProductWithProductId(productId));
		}
		
		@Test
	    void testGetProductWithExistingCategoryId() {
			int cateId = 4;
	        ProductService productService =  new ProductService();
	        assertDoesNotThrow(() -> productService.getAllproductswithCategoryId(cateId));
	    }

	@Test

	void testGetProductWithNonExistingProductId() {

		int productId = 90;
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.findProductWithProductId(productId));
		assertEquals("Invalid ProductId: no product exists in this product id", exception.getMessage());
	}
	
	@Test
    void testGetProductWithNonExistingCategoryId() {
		int cateId = 907;
        ProductService productService =  new ProductService();
        ValidationException exception = assertThrows(ValidationException.class,
                () -> productService.getAllproductswithCategoryId(cateId));
        assertEquals("Category Id doesnot exists in the database", exception.getMessage());
    }
	
	@Test

	void testGetProductWithInvalidProductId() {

		int productId = -9;
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.findProductWithProductId(productId));
		assertEquals("Invalid ProductId should be greater than 0.", exception.getMessage());
	}
	@Test
    void testGetProductWithInvalidCategoryId() {
		int cateId = -7;
        ProductService productService =  new ProductService();
        ValidationException exception = assertThrows(ValidationException.class,
                () -> productService.getAllproductswithCategoryId(cateId));
        assertEquals("Category ID must be non-negative", exception.getMessage());
    }
	
}
