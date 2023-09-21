
package in.fssa.expressocafe.util;

import java.util.List;
import java.util.Map;

import com.google.protobuf.Internal.ListAdapter;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.SizeEnum;

public class IntUtil {
/**
 * 
 * @param phoneNumber
 * @throws ValidationException
 */
	public static void rejectIfInvalidPhoneNumber(long phoneNumber) throws ValidationException {
		if (phoneNumber < 6000000000L || phoneNumber > 9999999999L) {
			throw new ValidationException("Invalid phone number");
		}
	}
/**
 * 
 * @param input
 * @param inputName
 * @throws ValidationException
 */
	public static void rejectIfInvalidInt(int input, String inputName) throws ValidationException {
		if (input <= 0) {
			throw new ValidationException("Invalid ".concat(inputName)+" should be greater than 0.");
		}
	}
/**
 * 
 * @param input
 * @param inputName
 * @throws ValidationException
 */
	public static void priceCheck(double input, String inputName) throws ValidationException {
		if(input <= 0 || input > 1000) {
			throw new ValidationException("Invalid ".concat(inputName)+" should be greater than 0.");
		}
	}
	
	public static void totalPriceCheck(double input, String inputName) throws ValidationException {
		if(input <= 0 ) {
			throw new ValidationException("Invalid ".concat(inputName)+" should be greater than 0.");
		}
	}
	
/**
 * 
 * @param priceList 
 * @throws ValidationException 
 */
	public static void validatePriceListRelationships(List<Price> priceList) throws ValidationException {
		
		if (priceList == null) { 
			throw new ValidationException("Prices for the product are required.");
		}		
		for (Price price : priceList) {
			if(price==null) {
				throw new ValidationException("price object cannot be null") ;
			}			
			if (price.getPrice() <= 0 || price.getPrice() >= 1000) {
				throw new ValidationException("Invalid price: price should be lesser than 1000 and greater than 0") ;
			}
			if (price.getSize() == null) {
				throw new ValidationException("SizeEnum cannot be null");
			}
			if (price.getSize().getSizeId() == 0) {
				throw new ValidationException("Sizes cannot be null");
			}
		}
	}

//	public static void rejectIfInvalidInt(double price) {
//
//	}

//	public static void priceCheck(double price,String inputNmae) throws ValidationException {
//		if(price < 0 || price > 1000) {
//			throw new ValidationException("Invalid ".concat(inputNmae));
//		}
//	}

	
}
