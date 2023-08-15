package in.fssa.expressoCafe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.model.SizeEnum;
import in.fssa.expressoCafe.service.ProductService;

public class TestGetProduct {

	@Test

	void testGetProductWithNonExistingProductId() {

		int productId = 90;
		ProductService productService = new ProductService();
		ServiceException exception = assertThrows(ServiceException.class,
				() -> productService.findProductWithProductId(productId));
		assertEquals("Invalid ProductId", exception.getMessage());
	}
	@Test
    void testCreateProductWithNonExistingCategoryId() {
		int cateId = 907;
        ProductService productService =  new ProductService();
        ServiceException exception = assertThrows(ServiceException.class,
                () -> productService.getAllproductswithCategoryId(cateId));
        assertEquals("Invalid CategoryId", exception.getMessage());
    }
	
	@Test

	void testGetProductWithInvalidProductId() {

		int productId = -9;
		ProductService productService = new ProductService();
		ServiceException exception = assertThrows(ServiceException.class,
				() -> productService.findProductWithProductId(productId));
		assertEquals("Invalid ProductId", exception.getMessage());
	}
	@Test
    void testCreateProductWithInvalidCategoryId() {
		int cateId = -7;
        ProductService productService =  new ProductService();
        ServiceException exception = assertThrows(ServiceException.class,
                () -> productService.getAllproductswithCategoryId(cateId));
        assertEquals("Category ID must be non-negative", exception.getMessage());
    }
	
}
