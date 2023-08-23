package in.fssa.expressoCafe.validator;

import java.util.List;

import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.util.IntUtil;

public class PriceValidator {
	public static void validatePriceList(List<Price> priceList) throws ValidationException {
		IntUtil.validatePriceListRelationships(priceList);
	}
	public static void priceCheck(double price, String string) throws ValidationException {
		// TODO Auto-generated method stub
		IntUtil.priceCheck(price, "Price");
	}
}
