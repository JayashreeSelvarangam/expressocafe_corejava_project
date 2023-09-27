package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.OrderItems;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.util.StringUtil;
import in.fssa.expressocafe.validator.OrderItemValidator;

public class TestOrderItemValidator {
    /**
     * Test case for validating an order item with valid data.
     * It asserts that the validation does not throw any exceptions.
     */
    @Test
    public void testValidateWithValidOrderItem() {
        // Arrange
        OrderItems orderItem = new OrderItems();
        orderItem.setProduct(new Product());
        orderItem.getProduct().setProduct_id(1); // Valid product ID
        orderItem.setSizeId(2);
        orderItem.setPriceObj(new Price());
        orderItem.getPriceObj().setPriceId(121);
        orderItem.getPriceObj().setPrice(10.0);
        orderItem.setName("ValidProductName");

        // Act and Assert
        assertDoesNotThrow(() -> {
            OrderItemValidator.validate(orderItem);
        });
    }

    /**
     * Test case for validating a null order item.
     * It asserts that the validation throws a ValidationException
     * with the message "orderItem Object is null."
     */
    @Test
    public void testValidateWithNullOrderItem() {
        // Arrange
        OrderItems orderItem = null;

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            OrderItemValidator.validate(orderItem);
        });
        assertEquals("orderItem Object is null.", exception.getMessage());
    }

    /**
     * Test case for validating an order item with an invalid product ID (negative ID).
     * It asserts that the validation throws a ValidationException
     * with an appropriate error message.
     */
    @Test
    public void testValidateWithInvalidProductId() {
        // Arrange
        OrderItems orderItem = new OrderItems();
        orderItem.setProduct(new Product());
        orderItem.getProduct().setProduct_id(-1); // Invalid product ID
        orderItem.setSizeId(2);
        orderItem.setPriceObj(new Price());
        orderItem.getPriceObj().setPriceId(3);
        orderItem.getPriceObj().setPrice(10.0);
        orderItem.setName("ValidProductName");

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            OrderItemValidator.validate(orderItem);
        });
        assertEquals("Invalid The product Id in the orderItem object is invalid should be greater than 0.", exception.getMessage());
    }
    /**
     * Test case for validating an order item with an invalid size ID (negative ID).
     * It asserts that the validation throws a ValidationException
     * with an appropriate error message.
     */
    @Test
    public void testValidateWithInvalidSizeId() {
        // Arrange
        OrderItems orderItem = new OrderItems();
        orderItem.setProduct(new Product());
        orderItem.getProduct().setProduct_id(1);
        orderItem.setSizeId(-1); // Invalid size ID
        orderItem.setPriceObj(new Price());
        orderItem.getPriceObj().setPriceId(3);
        orderItem.getPriceObj().setPrice(10.0);
        orderItem.setName("Valid Product Name");

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            OrderItemValidator.validate(orderItem);
        });
        assertEquals("Invalid The size Id in the orderItem object is invalid should be greater than 0.", exception.getMessage());
    }

    /**
     * Test case for validating an order item with an invalid price ID (negative ID).
     * It asserts that the validation throws a ValidationException
     * with an appropriate error message.
     */
    @Test
    public void testValidateWithInvalidPriceId() {
        // Arrange
        OrderItems orderItem = new OrderItems();
        orderItem.setProduct(new Product());
        orderItem.getProduct().setProduct_id(1);
        orderItem.setSizeId(2);
        orderItem.setPriceObj(new Price());
        orderItem.getPriceObj().setPriceId(-1); // Invalid price ID
        orderItem.getPriceObj().setPrice(10.0);
        orderItem.setName("ValidProductName");

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            OrderItemValidator.validate(orderItem);
        });
        assertEquals("Invalid The price Id in the orderItem object is invalid should be greater than 0.", exception.getMessage());
    }

    /**
     * Test case for validating an order item with a negative price.
     * It asserts that the validation throws a ValidationException
     * with an appropriate error message.
     */
    @Test
    public void testValidateWithNegativePrice() {
        // Arrange
        OrderItems orderItem = new OrderItems();
        orderItem.setProduct(new Product());
        orderItem.getProduct().setProduct_id(1);
        orderItem.setSizeId(2);
        orderItem.setPriceObj(new Price());
        orderItem.getPriceObj().setPriceId(3);
        orderItem.getPriceObj().setPrice(-1.0); // Negative price
        orderItem.setName("ValidProductName");

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            OrderItemValidator.validate(orderItem);
        });
        assertEquals("Invalid Price should be greater than 0.", exception.getMessage());
    }

    /**
     * Test case for validating an order item with an empty name.
     * It asserts that the validation throws a ValidationException
     * with an appropriate error message.
     */
    @Test
    public void testValidateWithInvalidName() {
        // Arrange
        OrderItems orderItem = new OrderItems();
        orderItem.setProduct(new Product());
        orderItem.getProduct().setProduct_id(1);
        orderItem.setSizeId(2);
        orderItem.setPriceObj(new Price());
        orderItem.getPriceObj().setPriceId(3);
        orderItem.getPriceObj().setPrice(10.0);
        orderItem.setName(""); // Empty name

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            OrderItemValidator.validate(orderItem);
        });
        assertEquals("Product Name cannot be null or empty", exception.getMessage());
    }

}
