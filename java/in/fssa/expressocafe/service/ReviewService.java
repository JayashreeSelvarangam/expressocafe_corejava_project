package in.fssa.expressocafe.service;

import java.util.List;

import in.fssa.expressocafe.DAO.ReviewDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.model.Order;
import in.fssa.expressocafe.model.Review;
import in.fssa.expressocafe.model.User;

public class ReviewService {

	/**
	 * 
	 * @param userId
	 * @param orderId
	 * @param reviewStar
	 * @param reviewMessage
	 * @throws ServiceException
	 */
	public void createReviewService(int userId, int orderId, int reviewStar, String reviewMessage) throws ServiceException {
	    try {
	        // Create an instance of the Review model
	    	Order order = new Order();
	    	order.setOrderId(orderId);
	    	
	    	User user = new User();
	    	user.setId(userId);
	    	
	        Review review = new Review();
	        review.setUser(user);
	        review.setOrder(order);
	        review.setReviewStar(reviewStar);
	        review.setReviewMessage(reviewMessage);
	        
	        // Call the createReview method to insert the review into the database
	        ReviewDAO r = new ReviewDAO();
			int reviewId = r.createReview(review);
			
	        // Optionally, you can perform additional actions or validations here

	    } catch (PersistanceException e) {
	        // Handle database-related exceptions, log the error, and throw a custom ServiceException with the error message
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}
	
	public Review getReviewByUserIdAndOrderIdService(int userId, int orderId) throws ServiceException {
	    try {
	        // Call the data access layer method to retrieve the review
	    	ReviewDAO reviewDAO = new ReviewDAO();
	        return reviewDAO.getReviewByUserIdAndOrderId(userId, orderId);
	    } catch (PersistanceException e) {
	        // Handle database-related exceptions, log the error, and throw a custom ServiceException with the error message
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}

	public boolean hasReviewBeenSubmittedService(int userId, int orderId) throws ServiceException {
	    try {
	        // Call the data access layer method to check if a review has been submitted
	    	ReviewDAO reviewDAO = new ReviewDAO();
	        return reviewDAO.hasReviewBeenSubmitted(userId, orderId);
	    } catch (PersistanceException e) {
	        // Handle database-related exceptions, log the error, and throw a custom ServiceException with the error message
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}

	public List<Review> getAllReviewsService() throws ServiceException {
	    try {
	        // Call the data access layer method to retrieve all reviews
	    	ReviewDAO reviewDAO = new ReviewDAO();
	        return reviewDAO.getAllReviews();
	    } catch (PersistanceException e) {
	        // Handle database-related exceptions, log the error, and throw a custom ServiceException with the error message
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}


}
