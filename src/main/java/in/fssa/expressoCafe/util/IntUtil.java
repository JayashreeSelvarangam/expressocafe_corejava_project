package in.fssa.expressoCafe.util;

import java.util.Map;

import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.SizeEnum;

public class IntUtil {

	public static void rejectIfInvalidPhoneNumber(long phoneNumber) throws ValidationException {

		if (phoneNumber < 6000000000L || phoneNumber > 9999999999L) {
			throw new ValidationException("Invalid phone number");
		}

	}
	
	public static void rejectIfInvalidInt(int input, String inputName) throws ValidationException {
		if(input <=0 ) {
			throw new ValidationException("Invalid ".concat(inputName));
		}
	}

//	public static void priceCheck(int input, String inputName) throws ValidationException {
//		if(input < 0 || input > 1000) {
//			throw new ValidationException("Invalid ".concat(inputName));
//		}
//	}
	
	 public static void validatePriceRelationships(Map<SizeEnum, Double> priceMap) throws ValidationException {
	        Double smallPrice = priceMap.get(SizeEnum.SMALL);
	        Double mediumPrice = priceMap.get(SizeEnum.MEDIUM);
	        Double largePrice = priceMap.get(SizeEnum.LARGE);

	        if(smallPrice>1000) {
	        	throw new ValidationException("Small price should not be greater that 1000");
	        }
	        if(mediumPrice>1000) {
	        	throw new ValidationException("mediumPrice should not be greater that 1000");
	        }
	        if(largePrice>1000) {
	        	throw new ValidationException("largePrice should not be greater that 1000");
	        }
	        
	        
	        if (smallPrice <= 0 || mediumPrice < 0 || smallPrice > mediumPrice) {
	            throw new ValidationException("Small Price should be less than  Medium Price");
	        }

	        if (mediumPrice <= 0 || largePrice <= 0  || mediumPrice > largePrice) {
	            throw new ValidationException("Medium Price should be less than  Large Price");
	        }

	        if (smallPrice <= 0 || largePrice <= 0 || smallPrice > largePrice) {
	            throw new ValidationException("Small Price should be less than  Large Price");
	        }
	    }
}
