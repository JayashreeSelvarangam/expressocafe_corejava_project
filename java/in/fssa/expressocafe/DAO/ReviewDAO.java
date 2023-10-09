package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.Order;
import in.fssa.expressocafe.model.Review;
import in.fssa.expressocafe.model.User;
import in.fssa.expressocafe.util.ConnectionUtil;

public class ReviewDAO {

	/**
	 * 
	 * @param model
	 * @return
	 * @throws PersistanceException
	 */
	public int createReview(Review model) throws PersistanceException { 
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet generatedKeys = null; 
	    int reviewId = -1; 

	    try {
	        // Establish a database connection
	        connection = ConnectionUtil.getConnnetion(); // Get a database connection
	        String insertReviewQuery = "INSERT INTO reviews (user_id, order_id, review_star, review_message) VALUES (?, ?, ?, ?)";
	        // Prepare the SQL statement for inserting a new review and retrieving generated keys
	        ps = connection.prepareStatement(insertReviewQuery, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, model.getUser().getId()); // Set the user ID
	        ps.setInt(2, model.getOrder().getOrderId()); // Set the order ID
	        ps.setInt(3, model.getReviewStar()); // Set the review star rating
	        ps.setString(4, model.getReviewMessage()); // Set the review message

	        // Execute the SQL statement and check if any rows were affected
	        int rowsAffected = ps.executeUpdate();
	        if (rowsAffected <= 0) {
	            System.out.print("Review creation failed");
	            throw new PersistanceException("Review creation failed");
	        }
	        // Retrieve the generated review ID
	        generatedKeys = ps.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            reviewId = generatedKeys.getInt(1);
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        // Close the database connection and resources
	        ConnectionUtil.close(connection, ps, generatedKeys);
	    }
	    return reviewId; // Return the ID of the newly created review
	}

	
	public Review getReviewByUserIdAndOrderId(int userId, int orderId) throws PersistanceException {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Review review = null;

	    try {
	        // Establish a database connection
	        connection = ConnectionUtil.getConnnetion(); // Get a database connection
	        String selectReviewQuery = "SELECT review_id,order_id,user_id,review_star,review_message  FROM reviews WHERE user_id = ? AND order_id = ?";
	        
	        // Prepare the SQL statement for retrieving the review
	        ps = connection.prepareStatement(selectReviewQuery);
	        ps.setInt(1, userId); // Set the user ID
	        ps.setInt(2, orderId); // Set the order ID

	        // Execute the SQL statement
	        rs = ps.executeQuery();

	        // Check if a review was found
	        if (rs.next()) {
	            // Create a Review object and populate it with data from the database
	            review = new Review();
	            review.setReviewId(rs.getInt("review_id"));
	            User user = new User();
	            user.setId(rs.getInt("user_id"));
	            review.setUser(user);
	  
	            Order order = new Order();
	            order.setOrderId(rs.getInt("order_id"));
	            review.setOrder(order);

	            review.setReviewStar(rs.getInt("review_star"));
	            review.setReviewMessage(rs.getString("review_message"));
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        // Close the database connection and resources
	        ConnectionUtil.close(connection, ps, rs);
	    }
	    return review; // Return the Review object or null if not found
	}

	
	public boolean hasReviewBeenSubmitted(int userId, int orderId) throws PersistanceException {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean reviewSubmitted = false;

	    try {
	        // Establish a database connection
	        connection = ConnectionUtil.getConnnetion(); // Get a database connection
	        String selectReviewQuery = "SELECT 1 FROM reviews WHERE user_id = ? AND order_id = ?";
	        
	        // Prepare the SQL statement to check for the existence of a review
	        ps = connection.prepareStatement(selectReviewQuery);
	        ps.setInt(1, userId); // Set the user ID
	        ps.setInt(2, orderId); // Set the order ID

	        // Execute the SQL statement
	        rs = ps.executeQuery();

	        // Check if a review was found
	        if (rs.next()) {
	            reviewSubmitted = true; // Review found, set to true
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        // Close the database connection and resources
	        ConnectionUtil.close(connection, ps, rs);
	    }
	    
	    return reviewSubmitted; // Return true if a review exists, false otherwise
	}

	public List<Review> getAllReviews() throws PersistanceException {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<Review> reviews = new ArrayList<>();

	    try {
	        // Establish a database connection
	        connection = ConnectionUtil.getConnnetion(); // Get a database connection
	        String selectAllReviewsQuery = "SELECT review_id, order_id, user_id, review_star, review_message FROM reviews";
	        
	        // Prepare the SQL statement for retrieving all reviews
	        ps = connection.prepareStatement(selectAllReviewsQuery);

	        // Execute the SQL statement
	        rs = ps.executeQuery();

	        // Loop through the result set and create Review objects
	        while (rs.next()) {
	            Review review = new Review();
	            review.setReviewId(rs.getInt("review_id"));
	            
	            User user = new User();
	            user.setId(rs.getInt("user_id"));
	            review.setUser(user);

	            Order order = new Order();
	            order.setOrderId(rs.getInt("order_id"));
	            review.setOrder(order);

	            review.setReviewStar(rs.getInt("review_star"));
	            review.setReviewMessage(rs.getString("review_message"));

	            reviews.add(review);
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        // Close the database connection and resources
	        ConnectionUtil.close(connection, ps, rs);
	    }
	    
	    return reviews; // Return a list of Review objects
	}

	

}
