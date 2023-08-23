package in.fssa.expressoCafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.expressoCafe.dao.ProductDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.model.SizeEnum;
import in.fssa.expressoCafe.service.ProductService;

public class TestDeleteProduct {
	@Test
	void testDeleteProductWithValidId()  {
		try {
		int productId = 21;
		ProductDAO pro = new ProductDAO();
		pro.changeStatus(productId, 1);
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> productService.deleteProduct(productId));
		
		
		
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Test
	void testDeleteProductWithNonExistingProductId() {
		int productId = 900;
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.deleteProduct(productId));
		assertEquals("Invalid ProductId: no product exists in this product id", exception.getMessage());
	}
	
	@Test
	void testDeleteProductWithInvalidProductId() {
		int productId = -9;
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.deleteProduct(productId));
		assertEquals("Invalid productId should be greater than 0.", exception.getMessage());
	}

	
//	@Test  
//	void testWhetherTheProductIsActive() {
//		int productId = 12;
//		ProductService productService = new ProductService();
//		ServiceException exception = assertThrows(ServiceException.class,
//				() -> productService.deleteProduct(productId));
//		System.out.println(exception.getMessage());
//		assertEquals("Product is inactive", exception.getMessage());
//	}
	}
	
	

