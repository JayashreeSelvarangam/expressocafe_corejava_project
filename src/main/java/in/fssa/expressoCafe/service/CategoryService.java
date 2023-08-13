package in.fssa.expressoCafe.service;

import java.util.List;

import com.google.protobuf.ServiceException;

import in.fssa.expressoCafe.dao.CategoryDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.CategoryEntity;
import in.fssa.expressoCafe.util.IntUtil;
import in.fssa.expressoCafe.validator.CategoryValidator;

public class CategoryService  {
	
	public List<Category> getAllCategories() {
	    	CategoryDAO categoryDAO = new CategoryDAO();
	    	return categoryDAO.getAllCategories();
	    }


	  public CategoryEntity getCategoryById(int categoryId) throws ServiceException, ValidationException {
		 
		  CategoryDAO categoryDAO = new CategoryDAO();
		  CategoryValidator category = new CategoryValidator(); // creating category Validator
		  
		  category.validateCategoryId(categoryId); // passing the id
	      return categoryDAO.getCategoryById(categoryId);
		  
	    }
	
	// this method check whether the category id exists but it does not return anything  
	  public void isCategoryIdValid(int category_id) throws ValidationException {
			CategoryValidator.isCategoryIdValid(category_id);
		}

	  
	  
}
