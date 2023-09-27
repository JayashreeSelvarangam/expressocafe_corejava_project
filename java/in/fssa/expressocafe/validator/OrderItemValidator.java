package in.fssa.expressocafe.validator;

import java.util.List;

import in.fssa.expressocafe.DAO.ProductDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.OrderItems;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.service.ProductService;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.util.StringUtil;

public class OrderItemValidator {

    /**
     * Validates an order item, checking various properties for correctness.
     *
     * @param orderItem The order item to validate.
     * @throws ValidationException If there are validation errors in the order item.
     */
    public static void validate(OrderItems orderItem) throws ValidationException {
        validateNotNull(orderItem);
        rejectIfInvalidInt(orderItem.getProduct().getProduct_id(), "The product Id in the orderItem object is invalid");
        rejectIfInvalidInt(orderItem.getSizeId(), "The size Id in the orderItem object is invalid");
        rejectIfInvalidInt(orderItem.getPriceObj().getPriceId(), "The price Id in the orderItem object is invalid");
        IntUtil.totalPriceCheck(orderItem.getPriceObj().getPrice(), "Price");
        StringUtil.rejectIfInvalidStringWithoutPattern(orderItem.getName(), "Product Name");
    }

    /**
     * Validates that the order item is not null.
     *
     * @param orderItem The order item to validate.
     * @throws ValidationException If the order item is null.
     */
    public static void validateNotNull(OrderItems orderItem) throws ValidationException {
        if (orderItem == null) {
            throw new ValidationException("orderItem Object is null.");
        }
    }

    /**
     * Rejects an integer value if it is invalid.
     *
     * @param value   The integer value to validate.
     * @param message The error message to use if the value is invalid.
     * @throws ValidationException If the integer value is invalid.
     */
    public static void rejectIfInvalidInt(int value, String message) throws ValidationException {
        IntUtil.rejectIfInvalidInt(value, message);
    }
}
