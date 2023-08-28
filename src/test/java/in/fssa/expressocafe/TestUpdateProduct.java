package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.model.SizeEnum;
import in.fssa.expressocafe.service.ProductService;
// testCases for pattern 
public class TestUpdateProduct {
	
	@Test
	void testUpdateProductWithValidData() {
		Product product = new Product();
		product.setName(generateRandomProductName());
		product.setDescription("Coffee Description");
		
		Category category = new Category();
		category.setCategoryId(1);
		product.setCategory(category);
		
		product.setProduct_id(11);
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> productService.updateProduct(product));
	}
	// string empty
	@Test
	void testUpdateProductWithNullProduct() {
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(null));
		assertEquals("Product Object is null.", exception.getMessage());
	}
	@Test
	void testUpdateProductWithNullProductName() {
		Product product = new Product();
		product.setDescription("feel refreshed");
		
		Category category = new Category();
		category.setCategoryId(1);
		product.setCategory(category);
		
		product.setProduct_id(11);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(product));
		assertEquals("Product Name cannot be null or empty", exception.getMessage());
	}

	@Test
	void testUpdateProductWithInvalidProductId() {
		Product product = new Product();
		product.setName("doppio");
		product.setDescription("feel refreshed");
		
		Category category = new Category();
		category.setCategoryId(1);
		product.setCategory(category);
		
		product.setProduct_id(-1);
		
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(product));
		System.out.println(exception.getMessage());
		assertEquals("Invalid productId should be greater than 0.", exception.getMessage(), "Exception message should match.");
	}
	
	@Test
	void testUpdateProductWithNonExistingCategoryId() {
		Product product = new Product();
		product.setName("cappp122u");
		product.setDescription("feel refreshed");
		product.setProduct_id(11);
		
		Category category = new Category();
		category.setCategoryId(90); 
		product.setCategory(category);
		
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class, () -> productService.updateProduct(product));
		assertEquals("Category Id doesnot exists in the database", exception.getMessage());
	}
	// doubt
	@Test
	void testUpdateProductWithCategoryIsNull() {
		Product product = new Product();
		product.setName("ca343ppphhhu");
		product.setDescription("Test Description");

		Category category = null;
		product.setCategory(category);
		
		product.setProduct_id(11);
		
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(product));
		assertEquals("Category object cannot be null", exception.getMessage());
	}
	

	@Test
	void testUpdateProductWithEmptyName() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		product.setName("");
		product.setDescription("Feel refreshed");

		Category category = new Category();
		category.setCategoryId(4);
		product.setCategory(category);
		
		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.createProduct(product));
		assertEquals("Product Name cannot be null or empty", exception.getMessage());
	}
	
	@Test
	void testUpdateProductWithEmptyDescription() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		product.setName("Cappu");
		product.setDescription("");

		Category category = new Category();
		category.setCategoryId(4);
		product.setCategory(category);
		
		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.updateProduct(product));
		assertEquals("Product Description cannot be null or empty", exception.getMessage());
	}
	@Test
	void testUpdateProductWithNullDescription() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		product.setName("Cappu");
		product.setDescription(null);

		Category category = new Category();
		category.setCategoryId(4);

		product.setCategory(category);

		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.updateProduct(product));
		assertEquals("Product Description cannot be null or empty", exception.getMessage());
	}
	
	@Test
	void testUpdateProductWithInvalidCategoryId() {
		Product product = new Product();
		product.setName("cap24343545pu");
		product.setDescription("Test Description");

		Category category = new Category();
		category.setCategoryId(-1); // Invalid category ID
		product.setCategory(category);
		product.setProduct_id(11);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(product));
		assertEquals("Category ID must be non-negative", exception.getMessage());
	}

	@Test
	void testUpdateProductWithExistingName() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		
		product.setName("JavaChip");
		product.setDescription("feel refreshed");

		Category category = new Category();
		category.setCategoryId(2);
		product.setCategory(category);
		
		product.setProduct_id(11);
		
		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.updateProduct(product));
		assertEquals("Product name already exists , Create product with different name.", exception.getMessage());
	}

	@Test
	void testUpdateProductWithNonExistingProductId() {
		Product product = new Product();
		product.setName("capppuj122u");
		product.setDescription("Test Description");

		Category category = new Category();
		category.setCategoryId(3); 
		product.setCategory(category);
		
		product.setProduct_id(79);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class, () -> productService.updateProduct(product));
		assertEquals("Invalid ProductId: no product exists in this product id", exception.getMessage());
	}
	
	  private String generateRandomProductName() {
	        String alphabet = "abcdefghijklmnopqrstuvwxyz";
	        StringBuilder dishName = new StringBuilder();

	        for (int i = 0; i < 10; i++) {
	            int index = (int) (Math.random() * alphabet.length());
	            char randomChar = alphabet.charAt(index);
	            dishName.append(Character.toUpperCase(randomChar));
	        }
	        return dishName.toString();
	    }
	  
//    @Test
//    void testUpdateProductWithInvalidCategoryId() {
//        Product product = new Product();
//        product.setName("Invalid");
//        product.setDescription("Test Description");
//        Category category = new Category();
//        category.setCategoryId(-1);
//        product.setCategory(category);
//        product.setProduct_id(1);
//        ProductService productService =  new ProductService();
//        ServiceException exception = assertThrows(ServiceException.class,
//                () -> productService.updateProduct(product));
//        assertEquals("Invalid CategoryId", exception.getMessage());
//    }
	
}
