package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.service.OrderService;

public class TestGetAllOrders {
	@Test
    public void testGetAllOrders() {
        OrderService orderService = new OrderService(); // Replace with your actual service class

        assertDoesNotThrow(() -> {
            int userId = 7;
            orderService.GetAllOrder(userId);
        }, "Exception occurred when getting all orders");
    }
}
