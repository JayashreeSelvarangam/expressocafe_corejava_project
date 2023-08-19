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

public class TestUpdateProduct {
	// validData
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
		assertEquals("Product is null.", exception.getMessage());
	}
	@Test
	void testUpdateProductWithNullProductName() {
		Product product = new Product();
		product.setDescription("Test Description");
		Category category = new Category();
		category.setCategoryId(1);
		product.setCategory(category);
		product.setProduct_id(11);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(product));
		assertEquals("productName cannot be null or empty", exception.getMessage());
	}

	@Test
	void testUpdateProductWithInvalidProductId() {
		Product product = new Product();
		product.setName("doppio");
		product.setDescription("Test Description");
		Category category = new Category();
		category.setCategoryId(1);
		product.setCategory(category);
		product.setProduct_id(-1);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(product));
		System.out.println(exception.getMessage());
		assertEquals("Invalid productId", exception.getMessage(), "Exception message should match.");
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
	@Test
	void testUpdateProductWithNonExistingCategoryId() {
		Product product = new Product();
		product.setName("cappp122u");
		product.setDescription("Test Description");
		product.setProduct_id(11);
		Category category = new Category();
		category.setCategoryId(90); // Assuming category ID 90 does not exist

		product.setCategory(category);
		ProductService productService = new ProductService();
		ServiceException exception = assertThrows(ServiceException.class, () -> productService.updateProduct(product));
		assertEquals("category does not exists", exception.getMessage());
	}
	// doubt
	@Test
	void testUpdateProductWithCategoryIsNull() {
		Product product = new Product();
		product.setName("ca343ppphhhu");
		product.setDescription("Test Description");

		Category category = null;

		product.setProduct_id(11);
		product.setCategory(category);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.updateProduct(product));
		assertEquals("Category object cannot be null", exception.getMessage());
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
		product.setDescription("Test Description");

		Category category = new Category();
		category.setCategoryId(2);

		Price priceSmall = new Price();
		priceSmall.setPrice(10.0);
		priceSmall.setSize(SizeEnum.SMALL);

		List<Price> priceList = new ArrayList<>();
		priceList.add(priceSmall);

		Price priceMedium = new Price();
		priceMedium.setPrice(4.99);
		priceMedium.setSize(SizeEnum.MEDIUM);

		Price priceLarge = new Price();
		priceLarge.setPrice(5.99);
		priceLarge.setSize(SizeEnum.LARGE);

		priceList.add(priceMedium);
		priceList.add(priceLarge);
		product.setPriceList(priceList);
		product.setCategory(category);
		product.setProduct_id(11);
		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.updateProduct(product));
		assertEquals("Product name already Exists", exception.getMessage());
	}

	@Test
	void testUpdateProductWithNonExistingProductId() {
		Product product = new Product();
		product.setName("capppuj122u");
		product.setDescription("Test Description");

		Category category = new Category();
		category.setCategoryId(3); // Assuming category ID 10 does not exist

		product.setCategory(category);
		product.setProduct_id(79);
		ProductService productService = new ProductService();
		ServiceException exception = assertThrows(ServiceException.class, () -> productService.updateProduct(product));
		assertEquals("product does not exist", exception.getMessage());
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
}
