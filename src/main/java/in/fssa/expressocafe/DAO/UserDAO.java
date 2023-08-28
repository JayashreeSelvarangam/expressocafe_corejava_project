package in.fssa.expressocafe.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressocafe.interfaces.UserDAOInterface;
import in.fssa.expressocafe.model.UserEntity;
import in.fssa.expressocafe.util.ConnectionUtil;
public class UserDAO {
	
	
	
	
	
	
	
	
	
	
	
//    /**
//     * Creates a new user in the database.
//     *
//     * @param user The UserEntity containing user information.
//     * @return The created UserEntity if successful, null if creation fails.
//     */
//	@Override
//    public UserEntity createUser(UserEntity user) {
//		
//        String insertQuery = "INSERT INTO users (name, phone_number, email, password, address) VALUES (?, ?, ?, ?, ?)";
//        Connection connection = null;
//        PreparedStatement ps = null;
//        try {
//            connection = ConnectionUtil.getConnnetion();
//            ps = connection.prepareStatement(insertQuery);
//            ps.setString(1, user.getName());
//            ps.setLong(2, user.getPhoneNumber());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getPassword());
//            ps.setString(5, user.getAddress());
//            int rowsAffected = ps.executeUpdate();
//            if (rowsAffected > 0) {
//                System.out.println("User created successfully");
//                return user;
//            } else {
//                System.out.println("User creation failed");
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            ConnectionUtil.close(connection, ps);
//        }
//    }
//	 /**
//     * Checks if an email address exists in the database.
//     *
//     * @param email The email address to be checked.
//     * @return true if the email address exists, false otherwise.
//     */
//	 @Override
//	    public boolean doesEmailExist(String email) {
//	        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
//	        Connection connection = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//	        try {
//	            connection = ConnectionUtil.getConnnetion();
//	            ps = connection.prepareStatement(query);
//	            ps.setString(1, email);
//	            rs = ps.executeQuery();
//	            if (rs.next()) {
//	                int count = rs.getInt(1);
//	                return count > 0;
//	            }
//	            return false;
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            throw new RuntimeException(e);
//	        } finally {
//	            ConnectionUtil.close(connection, ps, rs);
//	        }
//	    }
//	 @Override
//	    public boolean doesPhoneNumberExist(long phoneNumber) {
//	        String query = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
//	        Connection connection = null;
//	        PreparedStatement ps = null;
//	        ResultSet rs = null;
//	        try {
//	            connection = ConnectionUtil.getConnnetion();
//	            ps = connection.prepareStatement(query);
//	            ps.setLong(1, phoneNumber);
//	            rs = ps.executeQuery();
//	            if (rs.next()) {
//	                int count = rs.getInt(1);
//	                return count > 0;
//	            }
//	            return false;
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            throw new RuntimeException(e);
//	        } finally {
//	            ConnectionUtil.close(connection, ps, rs);
//	        }
//	    }
//	@Override
//	public boolean doesEmailExist1(String email) {
//		// TODO Auto-generated method stub
//		return false;
//	}
	 
	 
//	    @Override
//	    public UserEntity updateUser(UserEntity user) {
//	        String updateQuery = "UPDATE users SET name = ?, phone_number = ?, address = ?, password = ? WHERE emai; = ?";
//	        Connection connection = null;
//	        PreparedStatement ps = null;
//	        try {
//	            connection = ConnectionUtil.getConnnetion();
//	            ps = connection.prepareStatement(updateQuery);
//	            ps.setString(1, user.getName());
//	            ps.setLong(2, user.getPhoneNumber());
//	            ps.setString(3, user.getAddress());
//	            ps.setString(4, user.getPassword());
//	            ps.setString(5, user.getEmail());
//	            int rowsAffected = ps.executeUpdate();
//	            if (rowsAffected > 0) {
//	                System.out.println("User updated successfully");
//	                return user;
//	            } else {
//	                System.out.println("User update failed");
//	                return null;
//	            }
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            throw new RuntimeException(e);
//	        } finally {
//	            ConnectionUtil.close(connection, ps);
//	        }
//	    }
	    
	   

	
	}