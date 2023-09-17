package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.Cart;
import in.fssa.expressocafe.util.ConnectionUtil;

public class CartDAO {

	public int createCart1(int productId , int sizeId , int userId ) {
		return 0;
	}
	
	 public void createCartProduct(int productId, int sizeId, int userId , int priceId , int quantity) throws PersistanceException {
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet generatedKeys = null;
	        int cartId = -1;
	        try {
	            // Establish a database connection
	            connection = ConnectionUtil.getConnnetion();

	            // Define the SQL query for inserting a new cart
	            String insertCartQuery = "INSERT INTO carts (product_id, size_id, user_id , price_id ,quantity ) VALUES (?, ?, ?,?)";
	            ps = connection.prepareStatement(insertCartQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	            ps.setInt(1, productId);
	            ps.setInt(2, sizeId);
	            ps.setInt(3, userId);
	            ps.setInt(4, priceId);
	            ps.setInt(5, quantity);

	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected > 0) {
	                generatedKeys = ps.getGeneratedKeys();
	                // Check if there are generated keys and retrieve the first one
	                if (generatedKeys.next()) {
	                    cartId = generatedKeys.getInt(1);
	                }
	            } else {
	                System.out.print("Cart creation failed");
	                throw new PersistanceException("Cart creation failed");
	            }
	        } catch (SQLException e) {
	            throw new PersistanceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(connection, ps, generatedKeys);
	        }
	   
	    }
	 
	 public void deleteCart(int cartId) throws PersistanceException {
	        Connection connection = null;
	        PreparedStatement ps = null;

	        try {
	            // Establish a database connection
	            connection = ConnectionUtil.getConnnetion();

	            // Define the SQL query for deleting a cart by cartId
	            String deleteCartQuery = "DELETE FROM carts WHERE user_id = ? AND end_date IS NULL A";
	            ps = connection.prepareStatement(deleteCartQuery);
	            ps.setInt(1, cartId);

	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected <= 0) {
	                System.out.print("Cart deletion failed");
	                throw new PersistanceException("Cart deletion failed");
	            }
	        } catch (SQLException e) {
	            throw new PersistanceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(connection, ps, null);
	        }
	    }
	 
	 
	 public List<Cart> getAllCartItemsWithUserId( int userId) throws PersistanceException {
	        List<Cart> cartItems = new ArrayList<>();
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            // Establish a database connection
	            connection = ConnectionUtil.getConnnetion();

	            // Define the SQL query for retrieving cart items with the given productId and userId
	            String selectCartItemsQuery = "SELECT * FROM carts WHERE  user_id = ? AND end_date IS NULL AND status = 1";
	            ps = connection.prepareStatement(selectCartItemsQuery);
	            ps.setInt(1, userId);

	            rs = ps.executeQuery();

	            while (rs.next()) {
	                Cart cartItem = new Cart();
	                cartItem.setCartId(rs.getInt("cart_id"));
	                cartItem.setProduct_id(rs.getInt("product_id"));
	                cartItem.setSizeId(rs.getInt("size_id"));
	              
	                cartItem.setEndDate(rs.getTimestamp("end_date"));
	                cartItem.setStatus(rs.getInt("status"));
	                cartItem.setPrice(rs.getInt("price_id"));
	                cartItem.setQuantity(rs.getInt("quantity"));
	                cartItems.add(cartItem);
	            }
	            
	        } catch (SQLException e) {
	            throw new PersistanceException("Cannot fetch the cartItems");
	        } finally {
	            ConnectionUtil.close(connection, ps, rs);
	        }

	        return cartItems;
	    }
	 
	 
	 
	 public void setEndDateAndStatusToCartItem(int cartId) throws PersistanceException {
		    Connection connection = null;
		    PreparedStatement ps = null;

		    try {
		        // Establish a database connection
		        connection = ConnectionUtil.getConnnetion();

		        // Define the SQL query to update the end_date and status for the specified cart item
		        String updateEndDateAndStatusQuery = "UPDATE carts SET end_date = ?, status = 0 WHERE cart_id = ?";
		        
		        // Set the end_date to the current timestamp (you may need to adjust this)
		        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		        ps = connection.prepareStatement(updateEndDateAndStatusQuery);
		        ps.setTimestamp(1, currentTimestamp);
		        ps.setInt(2, cartId);

		        int rowsAffected = ps.executeUpdate();
		        if (rowsAffected <= 0) {
		            System.out.print("Setting end date and status to cart item failed");
		            throw new PersistanceException("Setting end date and status to cart item failed");
		        }
		    } catch (SQLException e) {
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(connection, ps, null);
		    }
		}

	  public boolean isPresentCartItem(int productId, int sizeId, int userId) throws PersistanceException {
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        boolean value =  true;
	        try {
	            // Establish a database connection
	            connection = ConnectionUtil.getConnnetion();

	            // Define the SQL query to check if the combination exists in the cart
	            String checkExistenceQuery = "SELECT COUNT(*) FROM carts WHERE product_id = ? AND size_id = ? AND user_id = ?";
	            ps = connection.prepareStatement(checkExistenceQuery);
	            ps.setInt(1, productId);
	            ps.setInt(2, sizeId);
	            ps.setInt(3, userId);

	            rs = ps.executeQuery();

	            if (rs.next()) {
	                int rowCount = rs.getInt(1);
	                return rowCount > 0;
	            }
	            else {
	             value =  false;
	            }
	        } catch (SQLException e) {
	            throw new PersistanceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(connection, ps, rs);
	        }
	        return value;
	    }
	  
	  
	  
	  
	 
	   
	}

