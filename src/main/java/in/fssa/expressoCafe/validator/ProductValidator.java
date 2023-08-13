package in.fssa.expressoCafe.validator;

import java.util.List;

import com.google.protobuf.ServiceException;

import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.service.ProductService;
import in.fssa.expressoCafe.util.IntUtil;
import in.fssa.expressoCafe.util.StringUtil;

public class ProductValidator {
	// validate for the product object
	public static void Validate(Product product) throws ValidationException {

		if (product == null) {
			throw new ValidationException("Product is null.");
		}
		StringUtil.rejectIfInvalidString(product.getName(), "Product Name");
		//StringUtil.rejectIfInvalidString(product.getDescription(), "Product Description");
		IntUtil.validatePriceRelationships(product.getPriceMap());
		}
	// validate whether the product already exists
	public static void ValidateProductNameAlreadyExists(Product product) throws ValidationException, ServiceException, in.fssa.expressoCafe.exception.ServiceException {
		try {
			ProductService prodservice = new ProductService();
			List<String> productName = prodservice.getAllProductName();
			if (productName.contains(product.getName())) {
				throw new ValidationException("Product name already Exists");
			}
		}catch (ValidationException e) {
			throw new ServiceException("Product name already Exists");
		}
	}
	
	
	
	
}
