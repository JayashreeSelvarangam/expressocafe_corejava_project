package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Order;
import in.fssa.expressocafe.service.OrderService;

/**
 * Unit tests for the GetAllOrder method in the OrderService class.
 */
public class TestGetAllOrder {  
    /**
     * Test case to validate the retrieval of orders for a valid user ID.
     */
    @Test
    public void testGetAllOrderWithValidUserId() {
        // Arrange
        OrderService orderService = new OrderService();
        // Valid user ID
        int validUserId = 5;
        // Act and Assert
        assertDoesNotThrow(() -> {
            List<Order> orderList = orderService.GetAllOrder(validUserId);
            assertNotNull(orderList);
            assertFalse(orderList.isEmpty());
        });
    }
    /**
     * Test case to validate the behavior of GetAllOrder with an invalid user ID.
     * It should throw a ValidationException.
     */
    @Test
    public void testGetAllOrderWithInvalidUserId() {
        // Arrange
        OrderService orderService = new OrderService();
        // Invalid user ID
        int invalidUserId = -1;
        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            orderService.GetAllOrder(invalidUserId);
        });
        assertEquals("Invalid Invalid userID should be greater than 0.", exception.getMessage());
    }
    /**
     * Test case to validate the behavior of GetAllOrder when a user has no orders.
     * It should return an empty list.
     */
    @Test
    public void testGetAllOrderWithEmptyResult() {
        // Arrange
        OrderService orderService = new OrderService();
        // A user with no orders, this may result in an empty list
        int userIdWithNoOrders = 1;
        // Act and Assert
        assertDoesNotThrow(() -> {
            List<Order> orderList = orderService.GetAllOrder(userIdWithNoOrders);
            assertNotNull(orderList);
            assertTrue(orderList.isEmpty());
        });
    }
    @BeforeAll
    public static void setUp() {
        OrderService orderService = new OrderService();
    }

    /**
     * Test case to cancel an order with a valid order ID.
     */
    @Test
    public void testCancelOrderWithValidOrderId() {
        // Arrange
        int validOrderId = 57;

        // Act and Assert
        assertDoesNotThrow(() -> {
        	 OrderService orderService = new OrderService();
            boolean isCanceled = orderService.cancelOrder(validOrderId);
            assertTrue(isCanceled);
        });
    }
    /**
     * Test case to cancel an order with an invalid (negative) order ID.
     */
    @Test
    public void testCancelOrderWithInvalidOrderId() {
        // Arrange
        int invalidOrderId = -1;

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
        	 OrderService orderService = new OrderService();
            orderService.cancelOrder(invalidOrderId);
        });

        assertEquals("Invalid Invalid order ID. should be greater than 0.", exception.getMessage());
    }
    /**
     * Test case to cancel a non-existent order.
     */
    @Test
    public void testCancelOrderWithNonexistentOrderId() {
        // Arrange
        int nonexistentOrderId = 1000;

        // Act and Assert
        assertDoesNotThrow(() -> {
        	 OrderService orderService = new OrderService();
            boolean isCanceled = orderService.cancelOrder(nonexistentOrderId);
            assertFalse(isCanceled);
        });
    }
    /**
     * Test case to check if an order is not canceled with a valid order ID.
     */
    @Test
    public void testCheckCancelOrderWithValidOrderId() {
        // Arrange
        int validOrderId = 57;

        // Act and Assert
        assertDoesNotThrow(() -> {
        	 OrderService orderService = new OrderService();
            boolean isNotCanceled = orderService.CheckCancelOrder(validOrderId);
            assertFalse(isNotCanceled);
        });
    }
    /**
     * Test case to check if an order is not canceled with an invalid (negative) order ID.
     */
    @Test
    public void testCheckCancelOrderWithInvalidOrderId() {
        // Arrange
        int invalidOrderId = -1;

        // Act and Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
        	 OrderService orderService = new OrderService();
            orderService.CheckCancelOrder(invalidOrderId);
        });

        assertEquals("Invalid Invalid order ID. should be greater than 0.", exception.getMessage());
    }
    // write a method whether product id already exists
//    @Test
//    public void testCheckCancelOrderWithNonexistentOrderId() {
//        // Arrange
//        int nonexistentOrderId = 1000;
//        // Act and Assert
//        assertDoesNotThrow(() -> {
//        	 OrderService orderService = new OrderService();
//            boolean isNotCanceled = orderService.CheckCancelOrder(nonexistentOrderId);
//            assertTrue(isNotCanceled);
//        });
//    }
}
