package in.fssa.expressocafe;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.model.Cart;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.service.OrderService;

public class TestCreateOrder {

	  @Test
	    public void testCreateOrderWithValidData() {
	        // Arrange
	        OrderService orderService = new OrderService();
	        
	        
	        List<Cart> cartList = new ArrayList<>();
	        Cart cartItem = new Cart();
	        
	        
	        cartItem.setProduct_id(36); // Set valid product_id
	        Price price = new Price();
	        price.setPriceId(281);
	        cartItem.setPriceObj(price); // Set a valid Price object
	        cartItem.setQuantity(2); // Set a valid quantity
	        Category cate = new Category();
	        cate.setCategoryId(3);
	        cartItem.setCategory(cate); // Set a valid category
	        
	        cartItem.setSizeId(1); // Set a valid sizeId
	        cartList.add(cartItem);
	        int userId = 6; // Set a valid userId
	        int addressId = 1; // Set a valid addressId
	        int totalCost = 100; // Set a valid totalCost

	        // Act and Assert
	        Assertions.assertDoesNotThrow(() -> {
	            orderService.CreateOrder(cartList, userId, addressId, totalCost);
	        });
	    }
}
