package in.fssa.expressocafe.validator;

import in.fssa.expressocafe.dao.ProductDAO;
import in.fssa.expressocafe.dao.SizeDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Size;
import in.fssa.expressocafe.util.IntUtil;

public class SizeValidator {
	/**
	 * 
	 * @param size
	 * @throws ValidationException
	 */
	public static void validateSize(Size size) throws ValidationException {
        if (size == null) {
            throw new ValidationException("Size object cannot be null");
        }
        validateSizeId(size.getSizeId());
        validateSizeName(size.getSizeName());
    }
/**
 * 
 * @param sizeId
 * @throws ValidationException
 */
    public static void validateSizeId(int sizeId) throws ValidationException {
        if (sizeId <= 0) {
            throw new ValidationException("Size ID must be non-negative");
        }
    }
    public static void rejectIfInvalidInt (int productId, String message) throws ValidationException {
		IntUtil.rejectIfInvalidInt(productId, message);
	}

    public static void validateSizeName(String sizeName) throws ValidationException {
        if (sizeName == null || sizeName.isEmpty()) {
            throw new ValidationException("Size name cannot be null or empty");
        }
    }
    
/**
 * 
 * @param sizeId
 * @throws ValidationException
 */
	public static void isSizeIdValid(int sizeId) throws ValidationException {

		try {
			SizeDAO sizeDAO = new SizeDAO();
			// do it later
			// IntUtil.rejectIfInvalidInt(Productid, "ProductId");
			sizeDAO.doesSizeIdExists(sizeId);
			
		} catch (PersistanceException e) {
			throw new ValidationException("Invalid SizeId: no size exists in this product id");
		}
	
	}
}


