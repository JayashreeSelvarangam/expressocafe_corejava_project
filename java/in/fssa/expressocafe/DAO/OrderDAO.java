package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.Order;
import in.fssa.expressocafe.model.OrderItem;
import in.fssa.expressocafe.model.OrderItems;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.model.Size;
import in.fssa.expressocafe.util.ConnectionUtil;

public class OrderDAO {

	
	public int createOrder(Order model) throws PersistanceException {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet generatedKeys = null;
	    int orderId = -1;

	    try {
	        // Establish a database connection
	        connection = ConnectionUtil.getConnnetion();
	        // Define the SQL query for inserting a new order
	        // add packaging type
	        String insertOrderQuery = "INSERT INTO orders (user_id, total_cost ,delivery_at,address_id,order_code) VALUES (?, ?, ?,?,?)";
	        
	        ps = connection.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, model.getUserId());
	        ps.setDouble(2, model.getTotalCost());
	        ps.setTimestamp(3, model.getDeliveryDate());
	        ps.setInt(4, model.getAddressId());
	        ps.setString(5, model.getOrderCode());
	        
	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected <= 0) {
	            System.out.print("Order creation failed");
	            throw new PersistanceException("Order creation failed");
	        }
	        // Retrieve the generated order ID
	        generatedKeys = ps.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            orderId = generatedKeys.getInt(1);
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(connection, ps, generatedKeys);
	    }
	    return orderId;
	}

	
	public List<Order> userOrders(int userId) throws PersistanceException {
	    List<Order> orderList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        connection = ConnectionUtil.getConnnetion();
	        String query = "SELECT o.order_code, o.order_id, o.user_id, o.total_cost, o.ordered_at, o.delivery_at, oi.product_id, oi.price_id, oi.quantity, oi.size_id " +
	            "FROM orders o " +
	            "INNER JOIN order_items oi ON o.order_id = oi.order_id " +
	            "WHERE o.user_id = ? " +
	            "ORDER BY o.order_id DESC";

	        ps = connection.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        int currentOrderId = -1;
	        Order currentOrder = null;

	        while (rs.next()) {
	            int orderId = rs.getInt("order_id");

	            if (orderId != currentOrderId) {
	                currentOrder = new Order();
	                currentOrder.setOrderId(orderId);
	                currentOrder.setUserId(userId);
	                currentOrder.setTotalCost(rs.getDouble("total_cost"));
	                currentOrder.setOrderedDate(rs.getTimestamp("ordered_at"));
	                currentOrder.setDeliveryDate(rs.getTimestamp("delivery_at"));
	                currentOrder.setOrderCode(rs.getString("order_code"));
	                
	                List< OrderItems> orderItemList = new ArrayList<>();
	                currentOrder.setOrderItems(orderItemList);

	                orderList.add(currentOrder);
	                currentOrderId = orderId;
	            }

	            OrderItems orderItem = new OrderItems();
	            Product product = new Product();
	            Price price = new Price();

	            product.setProduct_id(rs.getInt("product_id"));
	            
	            orderItem.setProduct(product);
	            
	            price.setPriceId(rs.getInt("price_id"));
	            product.setPriceObj(price);
	            
	            orderItem.setQuantity(rs.getInt("quantity"));
	            orderItem.setSizeId(rs.getInt("size_id"));

	            currentOrder.getOrderItems().add(orderItem);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException("Cannot get all ordered products");
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new PersistanceException("Cannot get all ordered products");
	    } finally {
	        ConnectionUtil.close(connection, ps, rs);
	    }

	    return orderList;
	}

	
	public List<Order> userOrders1(int userId) throws PersistanceException {
	    List<Order> orderList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        connection = ConnectionUtil.getConnnetion();
	        String query = "SELECT o.order_code, o.order_id, o.user_id, o.total_cost, o.ordered_at, o.delivery_at, oi.product_id, oi.price_id, oi.quantity, oi.size_id " +
	            "FROM orders o " +
	            "INNER JOIN order_items oi ON o.order_id = oi.order_id " +
	            "WHERE o.user_id = ? " +
	            "ORDER BY o.order_id DESC";

	        ps = connection.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            Order order = new Order();
	            Price price = new Price();

	            order.setOrderId(rs.getInt("order_id"));
	            order.setOrderCode(rs.getString("order_code"));
	            order.setProduct_id(rs.getInt("product_id"));

	            price.setPriceId(rs.getInt("price_id"));
	            order.setPriceObj(price);

	            order.setSizeId(rs.getInt("size_id"));
	            order.setQuantity(rs.getInt("quantity"));
	            order.setTotalCost(rs.getDouble("total_cost"));
	            order.setOrderedDate(rs.getTimestamp("ordered_at"));
	            order.setDeliveryDate(rs.getTimestamp("delivery_at"));

	            orderList.add(order);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException("Cannot get all ordered products");
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	        throw new PersistanceException("Cannot get all ordered products");
	    } finally {
	        ConnectionUtil.close(connection, ps, rs);
	    }

	    return orderList;
	}




	
	
//
//	public List<List<Order>> userOrders(int userId) throws PersistanceException {
//		
//	    List<List<Order>> orderLists = new ArrayList<>();
//	    Connection connection = null;
//	    PreparedStatement ps = null;
//	    ResultSet rs = null;
//	    
//	    try {
//	        connection = ConnectionUtil.getConnnetion();
//	        String query = "SELECT o.order_code,o.order_id, o.user_id, o.total_cost, o.ordered_at, o.delivery_at, oi.product_id, oi.price_id, oi.quantity, oi.size_id " +
//	            "FROM orders o " +
//	            "INNER JOIN order_items oi ON o.order_id = oi.order_id " +
//	            "WHERE o.user_id = ? " +
//	            "ORDER BY o.order_id DESC";
//
//	        ps = connection.prepareStatement(query);
//	        ps.setInt(1, userId);
//	        rs = ps.executeQuery();
//
//	        int currentOrderId = -1;
//	        List<Order> currentOrderList = null;
//
//	        while (rs.next()) {
//	            int orderId = rs.getInt("order_id");
//
//	            if (orderId != currentOrderId) {
//	                // Start a new order list for a different order ID
//	                currentOrderList = new ArrayList<>();
//	                orderLists.add(currentOrderList);
//	                currentOrderId = orderId;
//	            }
//
//	            Order order = new Order();
//	            Price price = new Price();
//
//	            order.setOrderId(orderId);
//	            order.setOrderCode(rs.getString("order_code"));
//	            order.setProduct_id(rs.getInt("product_id"));
//	            
//	            price.setPriceId(rs.getInt("price_id"));
//	            order.setPriceObj(price);
//	            
//	            order.setSizeId(rs.getInt("size_id"));
//	            order.setQuantity(rs.getInt("quantity"));
//	            order.setTotalCost(rs.getDouble("total_cost"));
//	            order.setOrderedDate(rs.getTimestamp("ordered_at"));
//	            order.setDeliveryDate(rs.getTimestamp("delivery_at"));
//
//	            currentOrderList.add(order);
//	        }
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        System.out.println(e.getMessage());
//	        throw new PersistanceException("Cannot get all ordered products");
//	    } catch (Exception e) {
//	        System.out.println(e.getMessage());
//	        throw new PersistanceException("Cannot get all ordered products");
//	    } finally {
//	        ConnectionUtil.close(connection, ps, rs);
//	    }
//
//	    return orderLists;
//	}

	
	public boolean cancelOrder(int orderId) throws PersistanceException {
	    Connection connection = null;
	    PreparedStatement ps = null;

	    try {
	        // Establish a database connection
	        connection = ConnectionUtil.getConnnetion();
	        // Define the SQL query to cancel an order
	        String cancelOrderQuery = "UPDATE orders SET status = 0 WHERE order_id = ?";	        
	        ps = connection.prepareStatement(cancelOrderQuery);
	        ps.setInt(1, orderId);
	        int rowsAffected = ps.executeUpdate();
	        // Check if the order was canceled successfully
	        if (rowsAffected > 0) {
	            return true;
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(connection, ps);
	    }
	    return false; // Return false if the order was not canceled
	}
	
	public boolean checkWhetherOrderisCancelledOrNot(int orderId) {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        // Establish a database connection
	        connection = ConnectionUtil.getConnnetion();
	        // Define the SQL query to check the status of an order
	        String checkOrderStatusQuery = "SELECT status FROM orders WHERE order_id = ?";
	        ps = connection.prepareStatement(checkOrderStatusQuery);
	        ps.setInt(1, orderId);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            int status = rs.getInt("status");
	            return status == 1; 
	        }
	    }catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        ConnectionUtil.close(connection, ps, rs);
	    }
	    return false; 
	}

	
	
//	public List<Order> userOrders(int userId) throws PersistanceException {
//	    List<Order> list = new ArrayList<>();
//	    Connection connection = null;
//	    PreparedStatement ps = null;
//	    ResultSet rs = null;
//	    try {
//	    	connection = ConnectionUtil.getConnnetion();
//	       String query = "SELECT o.order_id, o.user_id, o.total_cost, o.orderedDate, o.deliveredDate, oi.product_id, oi.price_id, oi.quantity, oi.size_id " +
//	                "FROM orders o " +
//	                "INNER JOIN order_items oi ON o.order_id = oi.order_id " +
//	                "WHERE o.user_id = ? " +
//	                "ORDER BY o.order_id DESC";
//
//	       ps = connection.prepareStatement(query);
//	       ps.setInt(1, userId);
//	       rs = ps.executeQuery();
//	        
//	        while (rs.next()) {
//	            Order order = new Order();
//	          
//	            // Extract order details
//	            order.setOrderId(rs.getInt("order_id"));
//	            order.setProductId(rs.getInt("product_id"));
//	            order.setPriceId(rs.getInt("price_id"));
//	            order.setSizeId(rs.getInt("size_id"));
//	            order.setQuantity(rs.getInt("quantity"));
//	            order.setTotalCost(rs.getDouble("total_cost"));
//	            order.setOrderedDate(rs.getTimestamp("orderedDate"));
//	            order.setDeliveryDate(rs.getTimestamp("deliveredDate"));
//	            
//	            // Get product details
//	            // it will be displayed in the service
////	            Product product = productDao.getSingleProduct(order.getId());
////	            order.setName(product.getName());
////	            order.setCategory(product.getCategory());
//	            
//	            list.add(order);
//	        }
//	        
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        System.out.println(e.getMessage());
//	        throw new PersistanceException("Cannot get all ordered products");
//	    } catch (Exception e) {
//	    	  System.out.println(e.getMessage());
//		        throw new PersistanceException("Cannot get all ordered products");
//	    }
//	    finally {
//	    	 ConnectionUtil.close(connection, ps, rs);
//	    }
//	    return list;
//	}

  
	
//    public void cancelOrder(int id) {
//        //boolean result = false;
//        try {
//            query = "delete from orders where o_id=?";
//            pst = this.con.prepareStatement(query);
//            pst.setInt(1, id);
//            pst.execute();
//            //result = true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.print(e.getMessage());
//        }
//        //return result;
//    }
//    
}
