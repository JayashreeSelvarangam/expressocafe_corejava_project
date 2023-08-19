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

public class TestDeleteProduct {
	// validData
	
	@Test
	void testDeleteProductWithValidId() {
		int productId = 53;
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> productService.deleteProduct(productId));
	}
	
	
	@Test
	void testDeleteProductWithNonExistingProductId() {
		int productId = 900;
		ProductService productService = new ProductService();
		ServiceException exception = assertThrows(ServiceException.class,
				() -> productService.deleteProduct(productId));
		assertEquals("product does not exist", exception.getMessage());
	}
	
	@Test

	void testDeleteProductWithInvalidProductId() {
		int productId = -9;
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.deleteProduct(productId));
		assertEquals("Invalid productId", exception.getMessage());
	}
	// needed 
//	@Test
//
//	void testDeleteProductWithInactiveProductId() {
//		int productId = 48;
//		ProductService productService = new ProductService();
//		ServiceException exception = assertThrows(ServiceException.class,
//				() -> productService.deleteProduct(productId));
//		assertEquals("Product is inactive", exception.getMessage());
//	}
	
}
