package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Cart;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.service.OrderService;

public class TestCreateOrder {

	/**
	 * Test case to validate the creation of an order with valid data.
	 */
	@Test
	public void testCreateOrderWithValidData() {
		// Arrange
		OrderService orderService = new OrderService();

		// Create a list of items to add to the order
		List<Cart> cartList = new ArrayList<>();
		Cart cartItem = new Cart();

		// Set valid data for the cart item
		cartItem.setProduct_id(1); // Set valid product_id
		Price price = new Price();
		price.setPriceId(121);
		cartItem.setPriceObj(price); // Set a valid Price object
		cartItem.setQuantity(2); // Set a valid quantity
		Category cate = new Category();
		cate.setCategoryId(3);
		cartItem.setCategory(cate); // Set a valid category
		cartItem.setSizeId(1); // Set a valid sizeId

		// Add the cart item to the cart list
		cartList.add(cartItem);

		int userId = 5; // Set a valid userId
		int addressId = 1; // Set a valid addressId
		double totalCost = 100.0; // Set a valid totalCost

		// Act and Assert
		assertDoesNotThrow(() -> {
			// Attempt to create an order and ensure no exceptions are thrown
			orderService.CreateOrder(cartList, userId, addressId, totalCost);
		});
	}
	
	/**
	 * Test case to validate the behavior of creating an order when the cart list is null.
	 */
	@Test
	public void testCreateOrderWithNullCartList() {
	    // Arrange
	    OrderService orderService = new OrderService();
	    // Prepare test data
	    List<Cart> cartList = null; // Set the cartList to null for testing.
	    int userId = 6; // Set a valid userId
	    int addressId = 1; // Set a valid addressId
	    double totalCost = 100.0; // Set a valid totalCost
	    // Act and Assert
	    ValidationException exception = assertThrows(ValidationException.class, () -> {
	        // Attempt to create an order with a null cart list and capture any thrown exception.
	        orderService.CreateOrder(cartList, userId, addressId, totalCost);
	    });
	    // Verify that a ValidationException is thrown with the expected error message.
	    assertEquals("Cart list is empty or null.", exception.getMessage());
	}
	
	

	
	

}
