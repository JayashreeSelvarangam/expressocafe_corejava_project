package in.fssa.expressocafe.service;

import java.util.List;

import in.fssa.expressocafe.DAO.CategoryDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.CategoryEntity;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.validator.CategoryValidator;

public class CategoryService  {
	/**
	 * 
	 * @return
	 * @throws PersistanceException 
	 * @throws ServiceException 
	 */
	public List<Category> getAllCategories() throws  ServiceException {
		List <Category> cate = null ;
		try {
	    	CategoryDAO categoryDAO = new CategoryDAO();
	    	cate = categoryDAO.getAllCategories();
	    	}
	    	catch(PersistanceException e) {
				 throw new ServiceException(e.getMessage());
			 }
		return cate;
	    }

/**
 * 
 * @param categoryId
 * @return
 * @throws ServiceException
 * @throws ValidationException
 */
	  public CategoryEntity getCategoryById(int categoryId) throws ServiceException, ValidationException {
		  CategoryEntity cate = null ;
		  try {
		  CategoryDAO categoryDAO = new CategoryDAO();
		  //  category Validator
		  CategoryValidator.validateCategoryId(categoryId); 
		  CategoryValidator.isCategoryIdValid(categoryId);
		  cate =  categoryDAO.getCategoryById(categoryId);
		  
		 }catch(PersistanceException e) {
			 throw new ServiceException(e.getMessage());
		 }
		return cate;
		  
	    }
	/**
	 * 
	 * @param category_id
	 * @throws ValidationException
	 */
	// this method check whether the category id exists but it does not return anything  
	  public void isCategoryIdValid(int category_id) throws ValidationException {
		  CategoryValidator.validateCategoryId(category_id);
		  CategoryValidator.isCategoryIdValid(category_id);
		}

	  
	  
}
