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

	/**
	 * This method creates a new order record in the database.
	 *
	 * @param model The Order object representing the order to be created.
	 * @return The ID of the newly created order.
	 * @throws PersistanceException If there is an error while interacting with the
	 *                              database.
	 */
	public int createOrder(Order model) throws PersistanceException { 
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet generatedKeys = null; 
		int orderId = -1;

		try {
			// Establish a database connection
			connection = ConnectionUtil.getConnnetion(); // Get a database connection
			String insertOrderQuery = "INSERT INTO orders (user_id, total_cost, delivery_at, address_id, order_code, cancel_time , package_type ) VALUES (?, ?, ?, ?, ?, ?,?)";
			// Prepare the SQL statement for inserting a new order and retrieving generated
			// keys
			ps = connection.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, model.getUserId()); // Set the user ID
			ps.setDouble(2, model.getTotalCost()); // Set the total cost
			ps.setTimestamp(3, model.getDeliveryDate()); // Set the delivery date
			ps.setInt(4, model.getAddressId()); // Set the address ID
			ps.setString(5, model.getOrderCode()); // Set the order code
			ps.setTimestamp(6, model.getCancelDate()); // Set the cancel date
			ps.setString(7, model.getPackageType());

			// Execute the SQL statement and check if any rows were affected
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
			// Close the database connection and resources
			ConnectionUtil.close(connection, ps, generatedKeys);
		}
		return orderId; // Return the ID of the newly created order
	}

	/**
	 * Retrieves a list of orders for a given user from the database.
	 *
	 * @param userId The ID of the user for whom to retrieve orders.
	 * @return A list of Order objects representing the user's orders.
	 * @throws PersistanceException If there is an error while interacting with the database.
	 */
	public List<Order> userOrders(int userId) throws PersistanceException {
	    List<Order> orderList = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        connection = ConnectionUtil.getConnnetion(); // Get a database connection

	        // SQL query to retrieve user's orders and associated order items
	        String query = "SELECT o.package_type,o.order_code,o.address_id, o.order_id, o.user_id, o.total_cost, o.ordered_at, o.delivery_at, oi.product_id, oi.price_id, oi.quantity, oi.size_id, o.cancel_time " +
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

	            // Create a new Order object if the order ID has changed
	            if (orderId != currentOrderId) {
	                currentOrder = new Order();
	                currentOrder.setPackageType(rs.getString("package_type"));
	                currentOrder.setOrderId(orderId);
	                currentOrder.setAddressId(rs.getInt("address_id"));
	                currentOrder.setUserId(userId);
	                currentOrder.setTotalCost(rs.getDouble("total_cost"));
	                currentOrder.setOrderedDate(rs.getTimestamp("ordered_at"));
	                currentOrder.setDeliveryDate(rs.getTimestamp("delivery_at"));
	                currentOrder.setOrderCode(rs.getString("order_code"));
	                currentOrder.setCancelDate(rs.getTimestamp("cancel_time"));
	                List<OrderItems> orderItemList = new ArrayList<>();
	                currentOrder.setOrderItems(orderItemList);

	                orderList.add(currentOrder);
	                currentOrderId = orderId;
	            }

	            // Create an OrderItems object for the current row
	            OrderItems orderItem = new OrderItems();
	            Product product = new Product();
	            Price price = new Price();

	            product.setProduct_id(rs.getInt("product_id"));
	            orderItem.setProduct(product);

	            price.setPriceId(rs.getInt("price_id"));
	            product.setPriceObj(price);

	            orderItem.setQuantity(rs.getInt("quantity"));
	            orderItem.setSizeId(rs.getInt("size_id"));

	            // Add the OrderItems object to the current Order's list of order items
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
	    return orderList; // Return the list of Order objects representing user's orders
	}

	
	/**
	 * This method retrieves a list of orders for a specific user from a database.
	 *
	 * @param userId The unique identifier of the user whose orders are being
	 *               retrieved.
	 * @return A list of Order objects representing the user's orders.
	 * @throws PersistanceException If an error occurs while accessing the database.
	 */
	public List<Order> userOrders1(int userId) throws PersistanceException {
		// Create a list to store the retrieved orders.
		List<Order> orderList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// Get a database connection using a utility class (ConnectionUtil).
			connection = ConnectionUtil.getConnnetion();

			// SQL query to retrieve user orders and related information.
			String query = "SELECT o.order_code, o.order_id, o.user_id, o.total_cost, o.ordered_at, o.delivery_at, oi.product_id, oi.price_id, oi.quantity, oi.size_id "
					+ "FROM orders o " + "INNER JOIN order_items oi ON o.order_id = oi.order_id "
					+ "WHERE o.user_id = ? " + "ORDER BY o.order_id DESC";

			// Create a prepared statement and set the user ID parameter.
			ps = connection.prepareStatement(query);
			ps.setInt(1, userId);

			// Execute the SQL query and retrieve the result set.
			rs = ps.executeQuery();

			// Iterate through the result set and populate Order objects.
			while (rs.next()) {
				Order order = new Order();
				Price price = new Price();

				// Set order-related attributes from the result set.
				order.setOrderId(rs.getInt("order_id"));
				order.setOrderCode(rs.getString("order_code"));
				order.setProduct_id(rs.getInt("product_id"));

				// Set price-related attributes using a Price object.
				price.setPriceId(rs.getInt("price_id"));
				order.setPriceObj(price);

				order.setSizeId(rs.getInt("size_id"));
				order.setQuantity(rs.getInt("quantity"));
				order.setTotalCost(rs.getDouble("total_cost"));
				order.setOrderedDate(rs.getTimestamp("ordered_at"));
				order.setDeliveryDate(rs.getTimestamp("delivery_at"));

				// Add the populated Order object to the list.
				orderList.add(order);
			}
		} catch (SQLException e) {
			// Handle SQL exceptions, log the error, and throw a custom exception.
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException("Cannot get all ordered products");
		} catch (Exception e) {
			// Handle other exceptions, log the error, and throw a custom exception.
			System.out.println(e.getMessage());
			throw new PersistanceException("Cannot get all ordered products");
		} finally {
			// Close the database resources (connection, prepared statement, and result
			// set).
			ConnectionUtil.close(connection, ps, rs);
		}

		// Return the list of retrieved orders.
		return orderList; 
	}

	/**
	 * Cancels an order in the database by setting its status to '0'.
	 *
	 * @param orderId The unique identifier of the order to be canceled.
	 * @return true if the order was canceled successfully, false otherwise.
	 * @throws PersistanceException If an error occurs while accessing the database.
	 */
	public boolean cancelOrder(int orderId) throws PersistanceException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			// Establish a database connection
			connection = ConnectionUtil.getConnnetion();

			// Define the SQL query to cancel an order by setting its status to '0'
			String cancelOrderQuery = "UPDATE orders SET status = 0 WHERE order_id = ?";

			// Create a prepared statement and set the order ID parameter
			ps = connection.prepareStatement(cancelOrderQuery);
			ps.setInt(1, orderId);

			// Execute the SQL update statement and get the number of rows affected
			int rowsAffected = ps.executeUpdate();

			// Check if the order was canceled successfully (at least one row affected)
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			// Handle SQL exceptions and throw a custom PersistanceException with the error
			// message
			throw new PersistanceException(e.getMessage());
		} finally {
			// Close the database resources (connection and prepared statement)
			ConnectionUtil.close(connection, ps);
		}

		// Return false if the order was not canceled (no rows affected)
		return false;
	}

	/**
	 * Checks whether an order is canceled or not based on its status in the
	 * database.
	 *
	 * @param orderId The unique identifier of the order to be checked.
	 * @return true if the order is not canceled (status = 1), false if canceled
	 *         (status = 0) or not found.
	 * @throws PersistanceException If an error occurs while accessing the database.
	 */
	public boolean checkWhetherOrderisCancelledOrNot(int orderId) throws PersistanceException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// Establish a database connection
			connection = ConnectionUtil.getConnnetion();
			// Define the SQL query to check the status of an order
			String checkOrderStatusQuery = "SELECT status FROM orders WHERE order_id = ?";
			// Create a prepared statement and set the order ID parameter
			ps = connection.prepareStatement(checkOrderStatusQuery);
			ps.setInt(1, orderId);
			// Execute the SQL query and retrieve the result set
			rs = ps.executeQuery();
			if (rs.next()) {
				int status = rs.getInt("status");

				// Return true if the order is not canceled (status = 1), false if canceled
				// (status = 0)
				return status == 1;
			}
		} catch (SQLException e) {
			// Handle SQL exceptions, log the error, and throw a custom PersistanceException
			// with the error message
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			// Close the database resources (connection, prepared statement, and result set)
			ConnectionUtil.close(connection, ps, rs);
		}
		// Return false if the order was not found in the database
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
