package in.fssa.expressoCafe.validator;

  
import in.fssa.expressoCafe.dao.CategoryDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.CategoryEntity;
import in.fssa.expressoCafe.util.IntUtil;

public class CategoryValidator {
    public static void validateCategory(CategoryEntity category) throws ValidationException {
   
        if (category == null) {
            throw new ValidationException("Category object cannot be null");
        }

        validateCategoryId(category.getCategoryId());
        validateCategoryName(category.getCategoryName());
      
    }

    public static void validateCategoryId(int categoryId) throws ValidationException {
        if (categoryId < 0) {
            throw new ValidationException("Category ID must be non-negative");
        }
    }

    public static void validateCategoryName(String categoryName) throws ValidationException {
 
        if (categoryName == null || categoryName.isEmpty()) {
            throw new ValidationException("Category name cannot be null or empty");
        }
    }
    
    public static void isCategoryIdValid(int category_id) throws ValidationException {

		try {
			CategoryDAO categorydao = new CategoryDAO();
			IntUtil.rejectIfInvalidInt(category_id, "Category Id");
			categorydao.doesCategoryExist(category_id);

		} catch(PersistanceException e) {
			throw new ValidationException("Invalid CategoryId");
		}
	}


}