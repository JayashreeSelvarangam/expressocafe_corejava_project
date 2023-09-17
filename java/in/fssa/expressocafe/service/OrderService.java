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


public class OrderService {
    /**
     * 
     * @param cartList
     * @param userId
     * @param addressId
     * @param totalCost
     * @throws ServiceException
     */
	public void CreateOrder(List<Cart> cartList , int userId , int addressId , double totalCost) throws ServiceException{
		try {
			
		OrderDAO orderDAO = new OrderDAO();
		OrderItemsDAO orderItem = new OrderItemsDAO();
		Order order = new Order();
		
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime modifiedDateTime = currentDateTime.plus(30, ChronoUnit.MINUTES);
        Timestamp sqlTimestamp = Timestamp.valueOf(modifiedDateTime);
        order.setDeliveryDate(sqlTimestamp);
        order.setOrderCode(generateRandomProductName());
	    order.setUserId(userId);
	    order.setAddressId(addressId);
	    order.setTotalCost(totalCost);
	    
	    int orderId = orderDAO.createOrder(order);
	    
		for(Cart cart : cartList){
		    orderItem.createOrderItem(orderId, cart.getProduct_id(), cart.getPriceObj().getPriceId(), cart.getSizeId(),cart.getQuantity());
			} 
		}catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}	
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ServiceException
	 * @throws ValidationException 
	 */

	public List<Order> GetAllOrder(int userId) throws ServiceException, ValidationException {	
	    List<Order> orderList = new ArrayList<>();
	    try {
	        OrderDAO orderDAO = new OrderDAO();
	        OrderItemsDAO orderItemDAO = new OrderItemsDAO();
	        ProductService productService = new ProductService();

	        orderList = orderDAO.userOrders(userId);

	        for (Order order : orderList) {
	            int orderId = order.getOrderId();
	            
	            List<OrderItems> orderItems = orderItemDAO.GetOrderItemByOrderId(orderId);
	            
	            for (OrderItems orderItem : orderItems) {
	                Product product = productService.getProductWithProductIdAndSizeId(orderItem.getProduct().getProduct_id(), orderItem.getSizeId());
	                orderItem.setName(product.getName());
	                orderItem.setCategory(product.getCategory());
	                Price price = new Price();
	                price.setPrice(product.getPriceObj().getPrice());
	                price.setPriceId(product.getPriceObj().getPriceId());
	                orderItem.setPriceObj(price);
	            }
	            
	            order.setOrderItems(orderItems);
	        }
	    } catch (PersistanceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	    
	    return orderList;
	}

	
	public boolean cancelOrder (int orderId) throws ServiceException { 
		OrderDAO orderDAO = new OrderDAO();
		boolean v = false;
		
		try {
			v = orderDAO.cancelOrder(orderId);
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
		
		return v;
		
	}
	
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