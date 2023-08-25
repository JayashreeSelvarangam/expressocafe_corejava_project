package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.model.SizeEnum;
import in.fssa.expressocafe.service.ProductService;

public class TestCreateProduct {

	// write testcases for pattern check
	
	@Test
	public void testCreateProductWithValidData() {
		// ProductService productService = new ProductService();

		Product product3 = new Product();

		product3.setName(generateRandomProductName());
		product3.setDescription("Feel refreshed");
		
		List<Price> priceList = new ArrayList<>();
		// Create Price instances for different sizes
		Price priceSmall = new Price();
		priceSmall.setPrice(3.99);
		priceSmall.setSize(SizeEnum.SMALL);

		Price priceMedium = new Price();
		priceMedium.setPrice(4.99);
		priceMedium.setSize(SizeEnum.MEDIUM);

		Price priceLarge = new Price();
		priceLarge.setPrice(5.99);
		priceLarge.setSize(SizeEnum.LARGE);

		priceList.add(priceSmall);
		priceList.add(priceMedium);
		priceList.add(priceLarge);

		Category category = new Category();
		category.setCategoryId(4);

		product3.setPriceList(priceList);
		product3.setCategory(category);

		ProductService prodService = new ProductService();
		assertDoesNotThrow(() -> { prodService.createProduct(product3);
		});
	}
	
	@Test
	void testCreateProductWithNullProduct() {
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,() -> productService.createProduct(null));
		assertEquals("Product Object is null.", exception.getMessage());
	}


	@Test
	void testCreateProductWithExistingName() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		product.setName("JavaChip");
		product.setDescription("Feel refreshed");

		Category category = new Category();
		category.setCategoryId(4);

		
		List<Price> priceList = new ArrayList<>();
		Price priceSmall = new Price();
		priceSmall.setPrice(10.0);
		priceSmall.setSize(SizeEnum.SMALL);

		Price priceMedium = new Price();
		priceMedium.setPrice(4.99);
		priceMedium.setSize(SizeEnum.MEDIUM);

		Price priceLarge = new Price();
		priceLarge.setPrice(5.99);
		priceLarge.setSize(SizeEnum.LARGE);
		
		priceList.add(priceSmall);
		priceList.add(priceMedium);
		priceList.add(priceLarge);
		
		product.setPriceList(priceList);
		product.setCategory(category);
		

		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.createProduct(product));
		assertEquals("Product name already exists , Create product with different name.", exception.getMessage());
	}

	@Test
	void testCreateProductWithEmptyName() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		product.setName("");
		product.setDescription("Feel refreshed");

		Category category = new Category();
		category.setCategoryId(4);

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

		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.createProduct(product));
		assertEquals("Product Name cannot be null or empty", exception.getMessage());
	}
	
	@Test
	void testCreateProductWithEmptyDescription() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		product.setName("Cappu");
		product.setDescription("");

		Category category = new Category();
		category.setCategoryId(4);

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

		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.createProduct(product));
		assertEquals("Product Description cannot be null or empty", exception.getMessage());
	}
	@Test
	void testCreateProductWithNullDescription() {

		ProductService prodService = new ProductService();
		Product product = new Product();
		product.setName("Cappu");
		product.setDescription(null);

		Category category = new Category();
		category.setCategoryId(4);

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

		ValidationException exception = assertThrows(ValidationException.class,
				() -> prodService.createProduct(product));
		assertEquals("Product Description cannot be null or empty", exception.getMessage());
	}
	
	@Test
	void testCreateProductWithNullSize() {
		Product product = new Product();
		product.setName("capplavage");
		product.setDescription("Feel refreshed");

		Price priceInvalidSize = new Price();
		priceInvalidSize.setPrice(10.0);
		priceInvalidSize.setSize(null); // Invalid size

		List<Price> priceList = new ArrayList<>();
		priceList.add(priceInvalidSize);

		product.setPriceList(priceList);

		Category category = new Category();
		category.setCategoryId(4);
		product.setCategory(category);
		
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.createProduct(product));
		assertEquals("SizeEnum cannot be null", exception.getMessage());
	}

	@Test
	void testCreateProductWithInvalidPrice() {
		Product product = new Product();
		product.setName("mochacoffee");
		product.setDescription("Feel refreshed");

		Price priceSmall = new Price();
		priceSmall.setPrice(-5.0); // Invalid price
		priceSmall.setSize(SizeEnum.SMALL);

		List<Price> priceList = new ArrayList<>();
		priceList.add(priceSmall);
		product.setPriceList(priceList);
		
		Category category = new Category();
		category.setCategoryId(4);
		product.setCategory(category);
		
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.createProduct(product));

		String expectedMessage = "Invalid price: price should be lesser than 1000 and greater than 0";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage, "Exception message should match.");
	}

	@Test
	void testCreateProductWithNullName() {
		Product product = new Product();
		product.setDescription("Feel refreshed");

		Price priceSmall = new Price();
		priceSmall.setPrice(10.0);
		priceSmall.setSize(SizeEnum.SMALL);

		List<Price> priceList = new ArrayList<>();
		priceList.add(priceSmall);

		Category category = new Category();
		category.setCategoryId(4);
		product.setCategory(category);
		
		product.setPriceList(priceList);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.createProduct(product));
		assertEquals("Product Name cannot be null or empty", exception.getMessage());
	}

	@Test
	void testCreateProductWithEmptyPriceList() {
		Product product = new Product();
		product.setName("capplavage");
		product.setDescription("Feel refreshed");
		
		Category category = new Category();
		category.setCategoryId(4);
		product.setCategory(category);
		
		List<Price> priceList = new ArrayList<>();
		product.setPriceList(null);
		
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.createProduct(product));
		assertEquals("Prices for the product are required.", exception.getMessage());
	}

	@Test
	void testCreateProductWithInvalidCategoryId() {
		Product product = new Product();
		product.setName("capplavage");
		product.setDescription("Feel refreshed");

		Category category = new Category();
		category.setCategoryId(-1); // Invalid category ID
		List<Price> priceList = new ArrayList<>();
		Price priceSmall = new Price();
		priceSmall.setPrice(3.99);
		priceSmall.setSize(SizeEnum.SMALL);

		Price priceMedium = new Price();
		priceMedium.setPrice(4.99);
		priceMedium.setSize(SizeEnum.MEDIUM);

		Price priceLarge = new Price();
		priceLarge.setPrice(5.99);
		priceLarge.setSize(SizeEnum.LARGE);

		priceList.add(priceSmall);
		priceList.add(priceMedium);
		priceList.add(priceLarge);

		product.setCategory(category);
		product.setPriceList(priceList);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.createProduct(product));
		assertEquals("Category ID must be non-negative", exception.getMessage());
	}

	@Test
	void testCreateProductWithNonExistingCategoryId() {
		Product product = new Product();
		product.setName("capplavage");
		product.setDescription("Feel refreshed");

		Category category = new Category();
		category.setCategoryId(90); // Assuming category ID 10 does not exist
		List<Price> priceList = new ArrayList<>();
		Price priceSmall = new Price();
		priceSmall.setPrice(3.99);
		priceSmall.setSize(SizeEnum.SMALL);

		Price priceMedium = new Price();
		priceMedium.setPrice(4.99);
		priceMedium.setSize(SizeEnum.MEDIUM);

		Price priceLarge = new Price();
		priceLarge.setPrice(5.99);
		priceLarge.setSize(SizeEnum.LARGE);

		priceList.add(priceSmall);
		priceList.add(priceMedium);
		priceList.add(priceLarge);
		product.setPriceList(priceList);

		product.setCategory(category);
		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.createProduct(product));
		assertEquals("Category Id doesnot exists in the database", exception.getMessage());
	}

	@Test
	void testCreateProductWithCategoryIsNull() {
		
		Product product = new Product();
		product.setName("capplavage");
		product.setDescription("Feel refreshed");
		
		Category category = null; // Assuming category ID 10 does not exist
		product.setCategory(category);
		
		List<Price> priceList = new ArrayList<>();
		
		Price priceSmall = new Price();
		priceSmall.setPrice(3.99);
		priceSmall.setSize(SizeEnum.SMALL);

		Price priceMedium = new Price();
		priceMedium.setPrice(4.99);
		priceMedium.setSize(SizeEnum.MEDIUM);

		Price priceLarge = new Price();
		priceLarge.setPrice(5.99);
		priceLarge.setSize(SizeEnum.LARGE);

		priceList.add(priceSmall);
		priceList.add(priceMedium);
		priceList.add(priceLarge);
		product.setPriceList(priceList);

		ProductService productService = new ProductService();
		ValidationException exception = assertThrows(ValidationException.class,
				() -> productService.createProduct(product));
		System.out.println(exception.getMessage());
		
		assertEquals("Category object cannot be null", exception.getMessage());
	}
	
	//// generate random strings 
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
