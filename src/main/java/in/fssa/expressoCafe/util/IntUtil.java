package in.fssa.expressoCafe.util;

import java.util.List;
import java.util.Map;

import com.google.protobuf.Internal.ListAdapter;

import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.SizeEnum;

public class IntUtil {

	public static void rejectIfInvalidPhoneNumber(long phoneNumber) throws ValidationException {

		if (phoneNumber < 6000000000L || phoneNumber > 9999999999L) {
			throw new ValidationException("Invalid phone number");
		}

	}

	public static void rejectIfInvalidInt(double input, String inputName) throws ValidationException {
		System.out.print(false);
		if (input <= 0) {
			throw new ValidationException("Invalid ".concat(inputName));
		}
		System.out.print(false);
	}

	public static void priceCheck(int input, String inputName) throws ValidationException {
		if(input < 0 || input > 1000) {
			throw new ValidationException("Invalid ".concat(inputName));
		}
	}

	public static void validatePriceListRelationships(List<Price> priceList) throws ValidationException {
		double smallPrice = 0;
		double mediumPrice = 0;
		double largePrice = 0;

		if (priceList == null) {
			throw new ValidationException("Prices for all sizes (small, medium, large) are required.");
		}

		for (Price price : priceList) {

			if (price.getPrice() <= 0) {
				throw new ValidationException("Invalid price: " + price.getPrice());
			}

			if (price.getSize() == null) {
				throw new ValidationException("SizeEnum cannot be null");
			}

			if (price.getSize() == SizeEnum.SMALL) {
				smallPrice = price.getPrice();
			} else if (price.getSize() == SizeEnum.MEDIUM) {
				mediumPrice = price.getPrice();
			} else if (price.getSize() == SizeEnum.LARGE) {
				largePrice = price.getPrice();
			}
		}

		if (smallPrice == 0 || mediumPrice == 0 || largePrice == 0) {
			throw new ValidationException("Prices for all sizes (small, medium, large) are required.");
		}

		if (smallPrice > 1000) {
			throw new ValidationException("Small price should not be greater than 1000");
		}
		if (mediumPrice > 1000) {
			throw new ValidationException("Medium price should not be greater than 1000");
		}
		if (largePrice > 1000) {
			throw new ValidationException("Large price should not be greater than 1000");
		}

		if (smallPrice <= 0) {
			throw new ValidationException("Small Price should be less than zero");
		}

		if (mediumPrice <= 0) {
			throw new ValidationException("Medium Price should be less than zero");
		}

		if (largePrice <= 0) {
			throw new ValidationException("Small Price should be less than zero");
		}
	}

	public static void rejectIfInvalidInt(double price) {
		// TODO Auto-generated method stub

	}

	public static void priceCheck(double price,String inputNmae) throws ValidationException {
		// TODO Auto-generated method stub
		if(price < 0 || price > 1000) {
			throw new ValidationException("Invalid ".concat(inputNmae));
		}
	}

	
}
