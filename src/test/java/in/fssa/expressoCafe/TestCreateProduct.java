package in.fssa.expressoCafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import in.fssa.expressoCafe.exception.ServiceException;

import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.model.SizeEnum;
import in.fssa.expressoCafe.service.ProductService;

public class TestCreateProduct {

	  @Test
	    void testCreateProductWithNullProduct() {
	        ProductService productService = new ProductService();
	        ValidationException exception = assertThrows(ValidationException.class, () -> productService.createProduct(null));
	        assertEquals("Product is null.", exception.getMessage());
	    }
	  
	
	@Test
	public void testCreateProductWithValidData() {

		Product prod = new Product();
		// ProductService productService = new ProductService();

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
        category.setCategoryId(11);

		Product product3 = new Product();

		product3.setName("StrawberryFrappucino");
		product3.setDescription("Feel refreshed");
		product3.setPriceList(priceList);
		product3.setCategory(category);
	//	product3.setCategory.setCategory_id(10);
		ProductService prodService = new ProductService();
		assertDoesNotThrow(() -> {
			prodService.createProduct(product3);
		});

	}
	
	@Test
    void testCreateProductWithExistingName() {
        
		ProductService prodService = new ProductService();
        Product product = new Product();
        product.setName("Frappy");
        product.setDescription("Test Description");

    	
        Category category = new Category();
        category.setCategoryId(11);

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

        ValidationException exception =assertThrows(ValidationException.class, () -> prodService.createProduct(product));
        assertEquals("Product name already Exists", exception.getMessage());
    }
	
	 @Test
	    void testCreateProductWithNullSize() {
	        Product product = new Product();
	        product.setName("cappu");
	        product.setDescription("cappu is good");

	        Price priceInvalidSize = new Price();
	        priceInvalidSize.setPrice(10.0);
	        priceInvalidSize.setSize(null); // Invalid size

	        List<Price> priceList = new ArrayList<>();
	        priceList.add(priceInvalidSize);

	        product.setPriceList(priceList);

	        ProductService productService = new ProductService();
			ValidationException exception = assertThrows(ValidationException.class, () -> productService .createProduct(product));
	        assertEquals("SizeEnum cannot be null", exception.getMessage());
	    }
	
	
	
	 @Test
	 void testCreateProductWithInvalidPrice() {
	     Product product = new Product();
	     product.setName("cappu");
	     product.setDescription("Test Description");

	     Price priceSmall = new Price();
	     priceSmall.setPrice(-5.0); // Invalid price
	     priceSmall.setSize(SizeEnum.SMALL);
	     
	     
	    
	     List<Price> priceList = new ArrayList<>();
	     priceList.add(priceSmall);

	     product.setPriceList(priceList);
	     	ProductService productService =  new ProductService();
	     	ValidationException exception = assertThrows(ValidationException.class, () -> productService.createProduct(product)
	            );
	     
	     String expectedMessage = "Invalid price: " + priceSmall.getPrice();
	     String actualMessage = exception.getMessage();

	     assertEquals(expectedMessage, actualMessage, "Exception message should match.");
	 }

	 
	    @Test
	    void testCreateProductWithNullName() {
	        Product product = new Product();
	        product.setDescription("Test Description");

	        Price priceSmall = new Price();
	        priceSmall.setPrice(10.0);
	        priceSmall.setSize(SizeEnum.SMALL);

	        List<Price> priceList = new ArrayList<>();
	        priceList.add(priceSmall);

	        product.setPriceList(priceList);
	        ProductService productService =  new ProductService();
	        ValidationException exception = assertThrows(ValidationException.class, () -> productService.createProduct(product));
	        assertEquals("Product Name cannot be null or empty", exception.getMessage());
	    }
	    
	    
	    
	    @Test
	    void testCreateProductWithEmptyPriceList() {
	        Product product = new Product();
	        product.setName("cappu");
	        product.setDescription("Test Description");

	        List<Price> priceList = new ArrayList<>(); // Empty price list

	        product.setPriceList(priceList);
	        ProductService productService =  new ProductService();
	        ValidationException exception = assertThrows(ValidationException.class, () -> productService.createProduct(product));
	        assertEquals("Prices for all sizes (small, medium, large) are required.", exception.getMessage());
	    }
	    
	    
	    @Test
	    void testCreateProductWithInvalidCategoryId() {
	        Product product = new Product();
	        product.setName("cap24343545pu");
	        product.setDescription("Test Description");

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
	        ProductService productService =  new ProductService();
	        ValidationException exception = assertThrows(ValidationException.class,
	                () -> productService.createProduct(product));
	        assertEquals("Category ID must be non-negative", exception.getMessage());
	    }

	    @Test
	    void testCreateProductWithNonExistingCategoryId() {
	        Product product = new Product();
	        product.setName("cappp122u");
	        product.setDescription("Test Description");

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
	        ProductService productService =  new ProductService();
	        ValidationException exception = assertThrows(ValidationException.class,
	                () -> productService.createProduct(product));
	        assertEquals("Invalid CategoryId", exception.getMessage());
	    }
	    
	    @Test
	    void testCreateProductWithCategoryIsNull() {
	        Product product = new Product();
	        product.setName("cappphhhu");
	        product.setDescription("Test Description");
	        List<Price> priceList = new ArrayList<>();
	        Category category = null; // Assuming category ID 10 does not exist
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
	        ProductService productService =  new ProductService();
	        ValidationException exception = assertThrows(ValidationException.class,
	                () -> productService.createProduct(product));
	        assertEquals("Category object cannot be null", exception.getMessage());
	    }
}
