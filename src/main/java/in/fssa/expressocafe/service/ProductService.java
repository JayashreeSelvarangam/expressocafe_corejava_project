package in.fssa.expressocafe.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.dao.CategoryDAO;
import in.fssa.expressocafe.dao.PriceDAO;
import in.fssa.expressocafe.dao.ProductDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.model.SizeEnum;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.util.StringUtil;
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
			
			// create product details in product Table
			Product prod = productDAO.createProduct(product);
			
			// set product id in the product object
			product.setProduct_id(prod.getProduct_id());
			
			for (Price price1 : priceList) {
				price1.setProduct(product);
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
			// include modified at 
			ProductDAO productDAO = new ProductDAO();
			CategoryDAO cate = new CategoryDAO();
			// form validation of productName and productDescription
			
			ProductValidator.validate(product);
			ProductValidator.rejectIfInvalidInt(product.getProduct_id(), "productId");
			ProductValidator.isProductIdValid(product.getProduct_id());
			ProductValidator.validateProductNameAlreadyExists(product);
		
			// category validation

			CategoryValidator.validateCategory(product.getCategory());
			CategoryValidator.isCategoryIdValid((product.getCategory().getCategoryId()));
		
			// should add modified at DAO
			if (productDAO.doesProductExist(product.getProduct_id())) {
				productDAO.updateProduct(product.getProduct_id(), product.getName(), product.getDescription(),
						product.getCategory().getCategoryId());
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
			
			ProductDAO productDao = new ProductDAO();
			PriceDAO priceDao = new PriceDAO();
			
			products = productDao.getAllProducts();	
			
			for (Product product : products) {
				List<Price> prices = priceDao.getPricesForProduct(product.getProduct_id());
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
			ProductDAO productDao = new ProductDAO();
			PriceDAO priceDao = new PriceDAO();
			// validation for category id
			CategoryValidator.validateCategoryId(categoryId);
			CategoryValidator.isCategoryIdValid(categoryId);
			
			products = productDao.getAllproductswithCategoryId(categoryId);
			
			for (Product product : products) {
				List<Price> prices = priceDao.getPricesForProduct(product.getProduct_id());
				product.setPriceList(prices);
			}
		}
		catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return products;
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
			ProductDAO productDao = new ProductDAO();
			PriceDAO priceDao = new PriceDAO();
			// validation for product id
			ProductValidator.rejectIfInvalidInt(productId,"ProductId");
			ProductValidator.isProductIdValid(productId);
					
			product = productDao.findProductWithProductId(productId);
			
			if(product != null) {
				List<Price> prices = priceDao.getPricesForProduct(product.getProduct_id());
				product.setPriceList(prices);
			}
		}
		catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return product;
	}
//	// get all product name
/**
 * 
 * @return
 * @throws ServiceException
 */
	public List<String> getAllProductName() throws ServiceException {
		try {
			ProductDAO productDao = new ProductDAO();
			return productDao.getAllProductName();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
