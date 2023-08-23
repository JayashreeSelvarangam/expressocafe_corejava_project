package in.fssa.expressoCafe.service;

import java.util.List;

import in.fssa.expressoCafe.exception.ServiceException;

import in.fssa.expressoCafe.dao.CategoryDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.CategoryEntity;
import in.fssa.expressoCafe.util.IntUtil;
import in.fssa.expressoCafe.validator.CategoryValidator;

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
		  CategoryValidator category = new CategoryValidator(); // creating category Validator
		  
		  category.validateCategoryId(categoryId); // passing the id
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
