package in.fssa.expressocafe.validator;

import java.util.List;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Cart;
import in.fssa.expressocafe.util.IntUtil;

/**
 * A utility class for validating order-related data.
 */
public class OrderValidator {
	/**
	 * Validates the creation of an order.
	 *
	 * @param cartList  The list of items in the cart.
	 * @param userId    The user ID associated with the order.
	 * @param addressId The address ID for shipping.
	 * @param totalCost The total cost of the order.
	 * @throws ValidationException If any validation checks fail.
	 */
	public static void validateCreateOrder(List<Cart> cartList, int userId, int addressId, double totalCost)
			throws ValidationException {
		// Check if the cart list is empty or null
//		if (cartList == null || cartList.isEmpty()) {
//			throw new ValidationException("Cart list is empty or null.");
//		}
		// Validate the user ID
		IntUtil.rejectIfInvalidInt(userId, "userID");
		// Validate the address ID
		validateAddressId(addressId);
		// Check the total cost
		IntUtil.totalPriceCheck(totalCost, "TotalCost");
	}
	/**
	 * Validates an order ID.
	 *
	 * @param orderId The order ID to validate.
	 * @throws ValidationException If the order ID is invalid.
	 */
	public static void validateOrderId(int orderId) throws ValidationException {
		// Validate the order ID
		IntUtil.rejectIfInvalidInt(orderId, "Invalid order ID.");
	}
	/** 
	 * Validates an address ID.
	 * @param addressId The address ID to validate.
	 * @throws ValidationException If the address ID is invalid.
	 */
	public static void validateAddressId(int addressId) throws ValidationException {
		// Validate the address ID
		IntUtil.rejectIfInvalidInt(addressId, "Invalid address ID.");
	}


public static void validateUserId(int userId) throws ValidationException {
	// Validate the order ID
	IntUtil.rejectIfInvalidInt(userId, "Invalid userID");
}
}