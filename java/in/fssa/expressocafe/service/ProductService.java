package in.fssa.expressocafe.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.DAO.CategoryDAO;
import in.fssa.expressocafe.DAO.PriceDAO;
import in.fssa.expressocafe.DAO.ProductDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Cart;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.util.StringUtil;
//import in.fssa.expressocafe.model.SizeEnum;
//import in.fssa.expressocafe.util.IntUtil;
//import in.fssa.expressocafe.util.StringUtil;
import in.fssa.expressocafe.validator.CategoryValidator;
import in.fssa.expressocafe.validator.PriceValidator;
import in.fssa.expressocafe.validator.ProductValidator;

public class ProductService {
/**
 * 
 * @param product
 * @throws ServiceException
 * @throws ValidationException
 */
	public void createProduct(Product product) throws ServiceException, ValidationException {
		try {
			ProductDAO productDAO = new ProductDAO();
			PriceService priceService = new PriceService();
			CategoryDAO cate = new CategoryDAO(); 
			
			// product Validation 
			ProductValidator.validate(product);
			ProductValidator.validateProductNameAlreadyExists(product);

			// category validation
			CategoryValidator.validateCategory(product.getCategory());
			CategoryValidator.isCategoryIdValid(product.getCategory().getCategoryId());
			
			// doubt 
			//cate.doesCategoryExist(product.getCategory().getCategoryId());
			
			List<Price> priceList = new ArrayList<>();
			priceList = product.getPriceList();

			// check for the valid price
			PriceValidator.validatePriceList(product.getPriceList());
			
			// set Date
			LocalDateTime date = LocalDateTime.now(); 
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(date);
			product.setCreatedDate(dateTime);
			
			// create product details in product Table
			int prod_id = productDAO.createProduct(product);
			
			
			
			for (Price price1 : priceList) {
				price1.setProductId(prod_id);
				price1.setStartDate(dateTime);
				priceService.createPrice(price1);
			}
			
		}catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

/**
 * 
 * @param product
 * @throws ServiceException
 * @throws ValidationException
 */
	public void updateProduct(Product product) throws ServiceException, ValidationException {
		try {
			 
			ProductDAO productDAO = new ProductDAO();
			CategoryDAO cate = new CategoryDAO();
			// form validation of productName and productDescription
			
			ProductValidator.validate(product);
			ProductValidator.rejectIfInvalidInt(product.getProduct_id(), "productId");
			ProductValidator.isProductIdValid(product.getProduct_id());
			ProductValidator.validateProductNameAlreadyExists(product);
		
			// include modified at
			LocalDateTime date = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(date);
			// category validation

			CategoryValidator.validateCategory(product.getCategory());
			CategoryValidator.isCategoryIdValid((product.getCategory().getCategoryId()));
			// should add modified at DAO
			if (productDAO.doesProductExist(product.getProduct_id())) {
				productDAO.updateProduct(product.getProduct_id(), product.getName(), product.getDescription(),
						product.getCategory().getCategoryId(),dateTime);
			}
		}catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
/**
 * 
 * @param product_id
 * @throws ServiceException
 * @throws ValidationException 
 */
	// write testcases forisActive product 
	public void deleteProduct(int product_id) throws ServiceException, ValidationException {
		try {
			ProductDAO productDAO = new ProductDAO();
			ProductValidator.rejectIfInvalidInt(product_id, "productId");
			ProductValidator.isProductIdValid(product_id);
			if (productDAO.isActive(product_id)) {
			productDAO.deleteProduct(product_id);
			}
		}
		catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */ 

	public List<Product> getAllProducts() throws ServiceException {
		
		List<Product> products = null;
		try {
			
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();
			
			products = productDAO.getAllProducts();	
			System.out.println(products+"123");
			for (Product product : products) {
				
				List<Price> prices = priceDAO.getPricesForProduct(product.getProduct_id());
				
				product.setPriceList(prices);
			}
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return products;
	}
	/**
	 * 
	 * @param category_id
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException 
	 */
	public List<Product> getAllproductswithCategoryId(int categoryId) throws ServiceException, ValidationException{
		
		List<Product> products = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();
			
			// validation for category id
			CategoryValidator.validateCategoryId(categoryId);
			CategoryValidator.isCategoryIdValid(categoryId);
			
			products = productDAO.getAllproductswithCategoryId(categoryId);
			
			for (Product product : products) {
				List<Price> prices = priceDAO.getPricesForProduct(product.getProduct_id());
				product.setPriceList(prices);
			}
		}
		catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return products;
	}
	
	public void updateProductWithPrices(Product updatedProduct) throws ValidationException, ServiceException {	
			try {
			// Validate the updated product's fields
			ProductValidator.validate(updatedProduct);	
			int productId = updatedProduct.getProduct_id();
			
			// validation
			ProductValidator.rejectIfInvalidInt(updatedProduct.getProduct_id(), "productId");
			ProductValidator.isProductIdValid(updatedProduct.getProduct_id());
			ProductValidator.validateProductNameAlreadyExists(updatedProduct);
				
			// Validate the category associated with the updated product
			CategoryValidator.validateCategory(updatedProduct.getCategory());
			CategoryValidator.isCategoryIdValid(updatedProduct.getCategory().getCategoryId());
			
			// Get the current timestamp for product update
			LocalDateTime date = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(date);
			
			 // Initialize ProductDAO to update the product details
			ProductDAO productDAO = new ProductDAO();		
			productDAO.updateProduct(updatedProduct.getProduct_id(),updatedProduct.getName(),updatedProduct.getDescription(),updatedProduct.getCategory().getCategoryId(),dateTime);
			
			// validating the price in price service - update price()
			List<Price> priceList = updatedProduct.getPriceList();		
			PriceValidator.validatePriceList(priceList);
			   // Initialize PriceService and PriceDAO for price-related operations
			PriceService priceService = new PriceService();
			PriceDAO priceDAO = new PriceDAO();
			
			for (Price price : priceList) {	
			int sizeId = price.getSize().getSizeId();
			// Check if a price exists for the product and size
			Price pricefromProdIdAndSizeId = priceDAO.checkIfPriceExistForProductWithUniqueSize(productId, sizeId);
			
			if (pricefromProdIdAndSizeId != null) {
			if (pricefromProdIdAndSizeId.getPrice() != price.getPrice()) {
			int priceId = pricefromProdIdAndSizeId.getPriceId(); 
			// Update the price if it has changed
			priceService.updatePrice(productId, sizeId, price.getPrice());
			 }
		} 
	}			
			System.out.println("product and its prices updated successfully");
	} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException("Error updating product and prices: " + e.getMessage());
			
	     }
	 }
				
	
	/**
	 * 
	 * @param productId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException 
	 */
	public Product findProductWithProductId(int productId) throws ServiceException, ValidationException {
		Product product = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			PriceDAO priceDAO = new PriceDAO();
			// validation for product id
			ProductValidator.rejectIfInvalidInt(productId,"ProductId");
			ProductValidator.isProductIdValid(productId);
					
			product = productDAO.findProductWithProductId(productId);
			
			if(product != null) {
				List<Price> prices = priceDAO.getPricesForProduct(product.getProduct_id());
				product.setPriceList(prices);
			}
		}
		catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return product;
	}
	
	 
	 public int getProductIdByProductName(String productName) throws ServiceException, ValidationException {
		Product product = new Product();
		product.setName(productName);
		StringUtil.rejectIfInvalidString(productName, "Product Name");
		ProductValidator.validateProductNameAlreadyshouldExists(product);
		 int proId = 0;
	        try {
	        	ProductDAO p = new ProductDAO();
	        	proId= p.getProductIdByProductName(productName);			
			} catch (PersistanceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
	        return proId;
	    	}

	 	public Product getProductWithProductIdAndSizeId(int productId , int sizeId) throws ValidationException, ServiceException {
	 		
	 		Product product = new Product();
	 		
			ProductValidator.rejectIfInvalidInt(productId,"ProductId");
			ProductValidator.isProductIdValid(productId);
			
			ProductValidator.rejectIfInvalidInt(sizeId, "sizeId");
			
			ProductService prod = new ProductService();
			product = prod.findProductWithProductId(productId);

			try {
				PriceDAO price = new PriceDAO();
				Price price1 = new Price();
				price1 = price.checkIfPriceExistForProductWithUniqueSize(productId, sizeId);
				product.setPriceObj(price1);
			} catch (PersistanceException e) {
				e.printStackTrace();
				throw new ServiceException(e.getMessage());
			}
			return product;
	 	}
	 	
	 	public double getTotalCartPrice(List<Cart> cartList) {
	        double sum = 0;
	  
	            if (cartList.size() > 0) {
	                for (Cart item : cartList) {
	                	sum += item.getPrice()*item.getQuantity();
	                }
	            }	        
	        return sum;
	    }
	 	
	
	 	
	 	public List<Cart> getCartProducts(List<Cart> cartList) throws ServiceException {
	 	 // add validation for cartList 	
	 	
	 	 List<Cart> cartProducts = new ArrayList<>();
	 	 PriceDAO p = new PriceDAO();
	 	 ProductDAO pd = new ProductDAO();
	 	   Product product = null;
	 	   int price = 0 ;
	 	    try {
	 	        if (cartList.size() > 0) {
	 	            for (Cart item : cartList) {
	 	                // Find the product details by product ID
	 	                 product = pd.findProductWithProductId(item.getProduct_id());
	 	                if (product != null) {
	 	                    // Find the price for the product and size ID
	 	                     price = p.findPriceByProductIdAndSizeId(product.getProduct_id(), item.getSizeId());
	 	                    
	 	                    // Create a new Cart object and populate it
	 	                    Cart cart = new Cart();
	 	                    cart.setProduct_id(product.getProduct_id());
	 	                    cart.setName(product.getName());
	 	                    Category cate = new Category();
	 	                    cate.setCategoryId(product.getCategory().getCategoryId());
	 	                    cart.setCategory(cate);
	 	                    cart.setPrice(price * item.getQuantity());
	 	                    cart.setQuantity(item.getQuantity());
	 	                    
	 	                    // Add the Cart object to the list
	 	                    cartProducts.add(cart);
	 	                }
	 	            }
	 	        }
	 	    } catch (PersistanceException e) {
	 	        e.printStackTrace();
	 	        throw new ServiceException(e.getMessage());
	 	    }

	 	    return cartProducts;
	 	}

	 	
	 	
//	// get all product name
/**
 * 
 * @return
 * @throws ServiceException
 */
	public List<String> getAllProductName() throws ServiceException {
		try {
			ProductDAO productDAO = new ProductDAO();
			return productDAO.getAllProductName();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
