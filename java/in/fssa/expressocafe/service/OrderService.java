package in.fssa.expressocafe.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.DAO.OrderDAO;
import in.fssa.expressocafe.DAO.OrderItemsDAO;
import in.fssa.expressocafe.DAO.PriceDAO;
import in.fssa.expressocafe.DAO.ProductDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Cart;
import in.fssa.expressocafe.model.Order;
import in.fssa.expressocafe.model.OrderItems;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.validator.OrderItemValidator;
import in.fssa.expressocafe.validator.OrderValidator;


public class OrderService {
	/**
	 * Creates a new order in the system based on the provided cart items and user information.
	 *
	 * @param cartList   The list of Cart items representing the products to be ordered.
	 * @param userId     The unique identifier of the user placing the order.
	 * @param addressId  The unique identifier of the delivery address for the order.
	 * @param totalCost  The total cost of the order.
	 * @throws ServiceException If an error occurs during the order creation process.
	 * @throws ValidationException 
	 */
	public void CreateOrder(List<Cart> cartList, int userId, int addressId, double totalCost ,String packageType) throws ServiceException, ValidationException {
	    try {
	    	// order validation
	    	OrderValidator.validateCreateOrder(cartList, userId, addressId, totalCost);
	        // Create instances of data access objects (DAOs) for orders and order items
	        OrderDAO orderDAO = new OrderDAO();
	        OrderItemsDAO orderItemDAO = new OrderItemsDAO();
	        // Create a new Order object to represent the order
	        Order order = new Order();
	        // Calculate the delivery date and timestamps
	        LocalDateTime currentDateTime = LocalDateTime.now();
	        LocalDateTime modifiedDateTime = currentDateTime.plus(20, ChronoUnit.MINUTES);
	        Timestamp deliveryTimestamp = Timestamp.valueOf(modifiedDateTime);
	        LocalDateTime modifiedDateTime1 = currentDateTime.plus(5, ChronoUnit.MINUTES);
	        Timestamp cancelTimestamp = Timestamp.valueOf(modifiedDateTime1);
	        // Set order attributes 
	        order.setDeliveryDate(deliveryTimestamp); 
	        order.setOrderCode(generateRandomProductName());
	        System.out.print("orderservice"+packageType);
	        order.setPackageType(packageType);
	        order.setUserId(userId);
	        order.setAddressId(addressId);
	        order.setTotalCost(totalCost);
	        order.setCancelDate(cancelTimestamp);
	        // Create the order and obtain the generated order ID
	        int orderId = orderDAO.createOrder(order);
	        // Create order items for each product in the cart
	        for (Cart cart : cartList) {
	            orderItemDAO.createOrderItem(orderId, cart.getProduct_id(), cart.getPriceObj().getPriceId(), cart.getSizeId(), cart.getQuantity());
	        }
	    } catch (PersistanceException e) {
	        // Handle database-related exceptions, log the error, and throw a custom ServiceException with the error message
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}
 
	/**
	 * Retrieves a list of orders for a specific user, including order details and
	 * associated products.
	 *
	 * @param userId The unique identifier of the user for whom orders are being
	 *               retrieved.
	 * @return A list of Order objects representing the user's orders with
	 *         associated order items.
	 * @throws ServiceException    If an error occurs during the retrieval process.
	 * @throws ValidationException If there's a validation error.
	 */
	public List<Order> GetAllOrder(int userId) throws ServiceException, ValidationException {
		List<Order> orderList = new ArrayList<>();
		// validate user id 
		OrderValidator.validateUserId(userId);
		try {
			OrderDAO orderDAO = new OrderDAO();
			OrderItemsDAO orderItemDAO = new OrderItemsDAO();
			ProductService productService = new ProductService();
			// Retrieve a list of orders for the given user
			orderList = orderDAO.userOrders(userId);
			for (Order order : orderList) {
				int orderId = order.getOrderId();
				OrderValidator.validateOrderId(orderId);
				
				// Retrieve order items for each order
				
				List<OrderItems> orderItems = orderItemDAO.GetOrderItemByOrderId(orderId);
				for (OrderItems orderItem : orderItems) {
					// Retrieve product details for each order item
					Product product = productService.getProductWithProductIdAndSizeId(
							orderItem.getProduct().getProduct_id(), orderItem.getSizeId());
					orderItem.setName(product.getName());
					orderItem.setCategory(product.getCategory());
					Price price = new Price();
					price.setPrice(product.getPriceObj().getPrice());
					price.setPriceId(product.getPriceObj().getPriceId());
					orderItem.setPriceObj(price);
					// validate each order Item 
					OrderItemValidator.validate(orderItem);
				}
				// Set the order items for the order
				order.setOrderItems(orderItems);
			}
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return orderList;
	}

	/**
	 * Cancels an order in the system by updating its status.
	 *
	 * @param orderId The unique identifier of the order to be canceled.
	 * @return true if the order was successfully canceled, false otherwise.
	 * @throws ServiceException If an error occurs during the cancellation process.
	 * @throws ValidationException 
	 */
	public boolean cancelOrder(int orderId) throws ServiceException, ValidationException {
		OrderDAO orderDAO = new OrderDAO();
		OrderValidator.validateOrderId(orderId);
		boolean isCanceled = false;
		try {
			// Attempt to cancel the order and check if it was canceled successfully
			isCanceled = orderDAO.cancelOrder(orderId);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		return isCanceled;
	}
	
	/**
	 * Checks whether an order has been canceled or not based on its status in the system.
	 *
	 * @param orderId The unique identifier of the order to be checked for cancellation.
	 * @return true if the order is not canceled (status = 1), false if canceled (status = 0) or not found.
	 * @throws ServiceException If an error occurs during the status check.
	 * @throws ValidationException 
	 */
	public boolean CheckCancelOrder(int orderId) throws ServiceException, ValidationException {
	    OrderDAO orderDAO = new OrderDAO();
	    OrderValidator.validateOrderId(orderId);
	    boolean isNotCanceled = false;
	    try {
	        // Check whether the order has been canceled or not
	        isNotCanceled = orderDAO.checkWhetherOrderisCancelledOrNot(orderId);
	    } catch (PersistanceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	    return isNotCanceled;
	}	
	/**
	 * Generates a random product name consisting of 10 uppercase letters.
	 *
	 * @return A randomly generated product name.
	 */
	  private String generateRandomProductName() {
	        String alphabet = "abcdefghijklmnopqrstuvwxyz";
	        StringBuilder dishName = new StringBuilder();

	        for (int i = 0; i < 10; i++) {
	            int index = (int) (Math.random() * alphabet.length());
	            char randomChar = alphabet.charAt(index);
	            dishName.append(Character.toUpperCase(randomChar));
	        }
	        return dishName.toString();
	    }


}