package in.fssa.expressocafe.validator;

import java.util.List;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.util.IntUtil;

public class PriceValidator {
	public static void validatePriceList(List<Price> priceList) throws ValidationException {
		IntUtil.validatePriceListRelationships(priceList);
	}
	public static void priceCheck(double price, String string) throws ValidationException {
		// TODO Auto-generated method stub
		IntUtil.priceCheck(price, "Price");
	}
}
 