package in.fssa.expressoCafe.validator;

import java.util.List;

import in.fssa.expressoCafe.dao.CategoryDAO;
import in.fssa.expressoCafe.dao.ProductDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ServiceException;

import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.service.ProductService;
import in.fssa.expressoCafe.util.IntUtil;
import in.fssa.expressoCafe.util.StringUtil;

public class ProductValidator {
	// validate for the product object
/**
 * 
 * @param product
 * @throws ValidationException
 */
	public static void Validate(Product product) throws ValidationException {

		ProductValidator.Validate1(product);
		StringUtil.rejectIfInvalidString(product.getName(), "Product Name");
		// StringUtil.rejectIfInvalidString(product.getDescription(), "Product
		// Description");
		IntUtil.validatePriceListRelationships(product.getPriceList());
	}
/**
 * 
 * @param product
 * @throws ValidationException
 */
	public static void Validate1(Product product) throws ValidationException {

		if (product == null) {
			throw new ValidationException("Product is null.");
		}
	}
	/**
	 * 
	 * @param product
	 * @throws ValidationException
	 * @throws ServiceException
	 */

	// validate whether the product already exists
	public static void ValidateProductNameAlreadyExists(Product product) throws ValidationException, ServiceException {
		try {
			ProductService prodservice = new ProductService();
			List<String> productName = prodservice.getAllProductName();
			
			if (productName.contains(product.getName())) {
			
				throw new ValidationException("Product name already Exists");
				
			}
			System.out.println("heloo everyone");
		} catch (ValidationException e) {
			throw new ValidationException("Product name already Exists");
		} catch (ServiceException e) {
			throw new ValidationException("Product name already Exists");
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
			// do it later
			// IntUtil.rejectIfInvalidInt(Productid, "ProductId");
			productdao.doesProductExist(productid);
			System.out.print(true);
		} catch (PersistanceException e) {
			throw new ValidationException("Invalid ProductId");
		}
	}

}
