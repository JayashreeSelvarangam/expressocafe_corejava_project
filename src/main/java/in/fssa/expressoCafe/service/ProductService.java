package in.fssa.expressoCafe.service;

import java.util.List;

import in.fssa.expressoCafe.dao.CategoryDAO;
import in.fssa.expressoCafe.dao.PriceDAO;
import in.fssa.expressoCafe.dao.ProductDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.util.IntUtil;
import in.fssa.expressoCafe.util.StringUtil;
import in.fssa.expressoCafe.validator.CategoryValidator;
import in.fssa.expressoCafe.validator.ProductValidator;

public class ProductService {

	public void createProduct(Product product) throws ServiceException, ValidationException, com.google.protobuf.ServiceException {
		try {
		ProductDAO productDAO = new ProductDAO();
		// product Validation
		ProductValidator.Validate(product);
		//ProductValidator.ValidateProductNameAlreadyExists(product);
		// category validation 
		CategoryValidator.isCategoryIdValid(product.getCategoryId());
		// create product details in product Table
		Product product1 = productDAO.createProduct(product);
		//create price for that product
		PriceDAO priceDAO = new PriceDAO();
		// check for the valid price
		IntUtil.validatePriceRelationships(product.getPriceMap());
		priceDAO.createProductPrices(product1);
		}catch(PersistanceException e){
		throw new ServiceException(e.getMessage()) ;
		} 
	}
	
	
	public void updateProduct(int product_id ,String productName , String productDescription ,  int cate_id) throws ServiceException, ValidationException {
		try {
		ProductDAO productDAO = new ProductDAO();	
		// form validation of productName and productDescription
		StringUtil.rejectIfInvalidString(productDescription,"productDescription");
		StringUtil.rejectIfInvalidString(productName,"productName");
		
		// form validation for product id and category id
		IntUtil.rejectIfInvalidInt(cate_id,"CategoryId");
		IntUtil.rejectIfInvalidInt(product_id,"productId");
		
		// category validation
		CategoryValidator.isCategoryIdValid(cate_id);
		// product validation
		if (productDAO.doesProductExist(product_id)) {
			productDAO.updateProduct( product_id , productName ,  productDescription , cate_id );
		   }
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage()) ;
		}
	}
	
	public void deleteProduct(int product_id) throws ServiceException, ValidationException{
		try {
		ProductDAO productDAO = new ProductDAO();	
		IntUtil.rejectIfInvalidInt(product_id,"productId");
		if (productDAO.isActive(product_id)) {
			productDAO.deleteProduct(product_id);
		    }
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage()) ;
		}
	}
	
	public List<Product> getAllProducts1() throws ServiceException{
		List<Product> product = null;
		try {
			
		ProductDAO productDAO = new ProductDAO();
		List<Product> product1 =  productDAO.getAllProducts();
		PriceDAO priceDAO = new PriceDAO();
		product = priceDAO.getAllprices(product1);
		}
		catch(PersistanceException e) {
			throw new ServiceException(e.getMessage()) ;
		}
		return product;
	}

//	public List<Product> getAllProducts() throws Exception{
//		ProductDAO productDAO = new ProductDAO();
//		List<Product> product1 =  productDAO.getAllproducts();
//		return product1;
//	}
	
	public List<Product> getAllproductswithCategoryId(int category_id) throws ServiceException{
		List<Product> products = null ; 
		try{
		CategoryDAO category =  new CategoryDAO();
		if(category.doesCategoryExist(category_id)) {
		ProductDAO productDAO = new ProductDAO();
		List<Product> product1 =  productDAO.getAllproductswithCategoryId(category_id);
		PriceDAO priceDAO = new PriceDAO();
		products =  priceDAO.getAllprices(product1);
		}
		}
		catch(PersistanceException e) {
			throw new ServiceException(e.getMessage()) ;
		}	
		return products;
	}
	
	public Product findProductWithProductId(int productId) throws ServiceException {
		Product product = null; 
		try {
		ProductDAO productDAO = new ProductDAO();
		Product product1 =  productDAO.findProductWithProductId(productId);
		PriceDAO priceDAO = new PriceDAO();
		product = priceDAO.getpriceswithProductId(product1);
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage()) ;
		}
		return product;
	}

	// get all product name
	public List<String> getAllProductName() throws ServiceException{
		try{
			ProductDAO productDao = new ProductDAO();
			return productDao.getAllProductName();
		}catch(PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		}


	
	
}
