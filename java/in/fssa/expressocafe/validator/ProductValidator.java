package in.fssa.expressocafe.validator;

import java.util.List;

import in.fssa.expressocafe.DAO.CategoryDAO;
import in.fssa.expressocafe.DAO.ProductDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.service.ProductService;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.util.StringUtil;

public class ProductValidator {
	// validate for the product object
/**
 * 
 * @param product
 * @throws ValidationException
 */
	public static void validate(Product product) throws ValidationException {
		ProductValidator.validate1(product);
		StringUtil.rejectIfInvalidString(product.getName(), "Product Name");
		StringUtil.rejectIfInvalidStringWithoutPattern(product.getDescription(), "Product Description");
	}
/**
 *  
 * @param product   
 * @throws ValidationException
 */
	public static void validate1(Product product) throws ValidationException {
		if (product == null) {
			throw new ValidationException("Product Object is null.");
		}
	}
	
	public static void rejectIfInvalidInt (int productId, String message) throws ValidationException {
		IntUtil.rejectIfInvalidInt(productId, message);
	}
	/**
	 * 
	 * @param product
	 * @throws ValidationException
	 * @throws ServiceException
	 */ 
	
	// validate whether the product already exists
	public static void validateProductNameAlreadyExists(Product product) throws ValidationException, ServiceException {
		try {
			ProductService prodservice = new ProductService();
			List<String> productName = prodservice.getAllProductName();
			if (productName.contains(product.getName())) {
				throw new ValidationException("Product name already exists , Create product with different name.");
			}
		}catch (ServiceException e) {
			throw new ValidationException("Product name already exists , Create product with different name.");
		}
	}
	
	public static void validateProductNameAlreadyshouldExists(Product product) throws ValidationException, ServiceException {
		try {
			ProductService prodservice = new ProductService();
			List<String> productName = prodservice.getAllProductName();
			if (!productName.contains(product.getName())) {
				throw new ValidationException("Product name should already exists");
			}
		}catch (ServiceException e) {
			throw new ValidationException("Product name already exists , Create product with different name.");
		}
	}
/**
 * 
 * @param productid
 * @throws ValidationException
 */
	public static void isProductIdValid(int productid) throws ValidationException {
		try {
			ProductDAO productdao = new ProductDAO();
			productdao.doesProductExist(productid);
		}catch (PersistanceException e) {
			throw new ValidationException("Invalid ProductId: no product exists in this product id");
		}
	}
	
	public static void validatePriceList(List<Price> priceList) throws ValidationException {
		IntUtil.validatePriceListRelationships(priceList);
	}

}
