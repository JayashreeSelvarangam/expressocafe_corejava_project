package in.fssa.expressoCafe.service;

import java.util.ArrayList;
import java.util.List;

import in.fssa.expressoCafe.dao.CategoryDAO;
import in.fssa.expressoCafe.dao.PriceDAO;
import in.fssa.expressoCafe.dao.ProductDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.util.IntUtil;
import in.fssa.expressoCafe.util.StringUtil;
import in.fssa.expressoCafe.validator.CategoryValidator;
import in.fssa.expressoCafe.validator.ProductValidator;

public class ProductService {

	public void createProduct(Product product) throws ServiceException, ValidationException, Exception {
		try {
			ProductDAO productDAO = new ProductDAO();
			PriceService priceService = new PriceService();

			// product Validation
			ProductValidator.Validate(product);

			// do it later
			ProductValidator.ValidateProductNameAlreadyExists(product);

			// category validation
			CategoryValidator.validateCategory(product.getCategory());
			CategoryValidator.isCategoryIdValid(product.getCategory().getCategoryId());

			List<Price> priceList = new ArrayList<>();
			priceList = product.getPriceList();

			// create product details in product Table
			int productId = productDAO.createProduct(product);
			// check for the valid price
			IntUtil.validatePriceListRelationships(product.getPriceList());
			product.setProduct_id(productId);
			System.out.println(productId);
			for (Price price1 : priceList) {
				price1.setProduct(product);
				priceService.createPrice(price1);
			}
			System.out.println("c");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateProduct(Product product) throws ServiceException, ValidationException {
		try {
			ProductDAO productDAO = new ProductDAO();
			// form validation of productName and productDescription
			ProductValidator.Validate1(product);
			StringUtil.rejectIfInvalidString(product.getName(), "productName");
			ProductValidator.ValidateProductNameAlreadyExists(product);
			ProductValidator.isProductIdValid(product.getProduct_id());
			// category validation

			CategoryValidator.validateCategory1(product.getCategory());
			CategoryValidator.validateCategoryId(product.getCategory().getCategoryId());
			CategoryValidator.isCategoryIdValid(product.getCategory().getCategoryId());

			// form validation for product id and category id
			// IntUtil.rejectIfInvalidInt(product.getCategory().getCategoryId(),"CategoryId");
			IntUtil.rejectIfInvalidInt(product.getProduct_id(), "productId");
			// product validation
			if (productDAO.doesProductExist(product.getProduct_id())) {
				productDAO.updateProduct(product.getProduct_id(), product.getName(), product.getDescription(),
						product.getCategory().getCategoryId());
			}
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void deleteProduct(int product_id) throws ServiceException {
		try {
			ProductDAO productDAO = new ProductDAO();

			ProductValidator.isProductIdValid(product_id);
			IntUtil.rejectIfInvalidInt(product_id, "productId");
			if (productDAO.isActive(product_id)) {
				productDAO.deleteProduct(product_id);
			}
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Product> getAllProducts1() throws ServiceException {
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

	public List<Product> getAllproductswithCategoryId(int category_id) throws ServiceException{
		List<Product> products = null;
		try {
			ProductDAO productDao = new ProductDAO();
			PriceDAO priceDao = new PriceDAO();
			CategoryValidator.validateCategoryId(category_id);
			CategoryValidator.isCategoryIdValid(category_id);
			
			products = productDao.getAllproductswithCategoryId(category_id);
			
			for (Product product : products) {
				List<Price> prices = priceDao.getPricesForProduct(product.getProduct_id());
				product.setPriceList(prices);
			}

		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return products;
	}
	
	public Product findProductWithProductId(int productId) throws ServiceException {
		Product product = null;
		try {
			ProductDAO productDao = new ProductDAO();
			PriceDAO priceDao = new PriceDAO();
			
			IntUtil.rejectIfInvalidInt(productId);
			ProductValidator.isProductIdValid(productId);
						
			product = productDao.findProductWithProductId(productId);
			
			if(product != null) {
				List<Price> prices = priceDao.getPricesForProduct(product.getProduct_id());
				product.setPriceList(prices);
			}

		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return product;
	}

	


//	// get all product name

	public List<String> getAllProductName() throws ServiceException {
		try {
			ProductDAO productDao = new ProductDAO();
			return productDao.getAllProductName();
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
