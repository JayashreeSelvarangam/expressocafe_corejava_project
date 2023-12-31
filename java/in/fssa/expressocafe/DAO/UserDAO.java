package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.interfaces.UserInterface;
import in.fssa.expressocafe.model.User;
import in.fssa.expressocafe.util.ConnectionUtil;
import in.fssa.expressocafe.util.PasswordUtil;

public class UserDAO implements UserInterface {

	/**
	 * Creates a new user entry in the database.
	 *
	 * This method inserts a new user entry into the 'users' table in the database.
	 * The user information is obtained from the 'newUser' parameter, including
	 * first name, last name, email, password, and phone number. If the insertion is
	 * successful, a new user record is created. If any exception occurs during the
	 * process, it is caught, and a RuntimeException is thrown.
	 *
	 * @param newUser An instance of 'User' containing the user information to be
	 *                created.
	 * @throws PersistanceException 
	 * @throws RuntimeException If an error occurs during the database insertion
	 *                          process. The original exception is printed, and a
	 *                          RuntimeException is thrown.
	 */

	@Override 
	public void create(User newUser) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "INSERT INTO users (first_name, last_name, email, password, phone_no) VALUES (?,?,?,?,?)";
			conn = ConnectionUtil.getConnnetion();
			ps = conn.prepareStatement(query);

			ps.setString(1, newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setString(3, newUser.getEmail());
			ps.setString(4, newUser.getPassword());
			ps.setLong(5, newUser.getPhoneNo());
			ps.executeUpdate();

			System.out.println("User has been successfully created");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Updates an existing user entry in the database.
	 *
	 * This method updates an existing user entry in the 'users' table in the
	 * database. The user information is obtained from the 'updatedUser' parameter,
	 * including updated first name, last name, and phone number. The update is
	 * performed based on the provided 'id', and it only affects active user entries
	 * (where 'is_active' is 1). If the update is successful, the user record is
	 * modified. If any exception occurs during the process, it is caught, and a
	 * RuntimeException is thrown.
	 *
	 * @param id          The ID of the user to be updated.
	 * @param updatedUser An instance of 'User' containing the updated user
	 *                    information.
	 * @throws RuntimeException If an error occurs during the database update
	 *                          process. The original exception is printed, and a
	 *                          RuntimeException is thrown.
	 */
	@Override
	public void update(int id, User updatedUser) throws PersistanceException{
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET first_name = ? , last_name = ? , phone_no = ? WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnnetion();
			ps = conn.prepareStatement(query);

			ps.setString(1, updatedUser.getFirstName());
			ps.setString(2, updatedUser.getLastName());
			ps.setLong(3, updatedUser.getPhoneNo()); 
			ps.setInt(4, id);
			ps.executeUpdate();

			System.out.println("User has been successfully updated");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Deactivates an existing user entry in the database.
	 *
	 * This method deactivates an existing user entry in the 'users' table in the
	 * database. The deactivation is performed based on the provided 'userId'. The
	 * user entry is marked as inactive by setting the 'is_active' column to 0. If
	 * the deactivation is successful, the user record is deactivated. If any
	 * exception occurs during the process, it is caught, and a RuntimeException is
	 * thrown.
	 *
	 * @param userId The ID of the user to be deactivated.
	 * @throws PersistanceException 
	 * @throws RuntimeException If an error occurs during the database deactivation
	 *                          process. The original exception is printed, and a
	 *                          RuntimeException is thrown.
	 */
	@Override
	public void delete(int userId) throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			String query = "UPDATE users SET is_active = ? WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnnetion();
			ps = conn.prepareStatement(query);

			ps.setInt(1, 0);
			ps.setInt(2, userId);
			ps.executeUpdate();

			System.out.println("User has been successfully deactivated");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps);
		}
	}

	/**
	 * Retrieves all active user entries from the database.
	 *
	 * This method queries the database to retrieve all active user entries stored
	 * in the 'users' table that have 'is_active' set to 1. For each matching active
	 * user entry, a 'User' object is created, populated with the retrieved data,
	 * and added to a 'Set'. The set is then returned, containing details of all
	 * active user entries. If any exception occurs during the process, it is
	 * caught, and a RuntimeException is thrown.
	 *
	 * @return A 'Set' containing 'User' objects representing details of all active
	 *         user entries.
	 * @throws RuntimeException If an error occurs during the database retrieval
	 *                          process. The original exception is printed, and a
	 *                          RuntimeException is thrown.
	 * @throws PersistanceException 
	 */
	@Override
	public Set<User> findAll() throws PersistanceException {
		Connection conn = null;
		PreparedStatement ps = null;
		Set<User> setOfUser = new HashSet
				<>();
		ResultSet rs = null;

		try {
			String query = "SELECT id, first_name, last_name,is_active,password, email, phone_no FROM users WHERE is_active = 1";
			conn = ConnectionUtil.getConnnetion();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNo(rs.getLong("phone_no"));
				user.setActive(rs.getBoolean("is_active"));
				user.setPassword(rs.getString("password"));
				setOfUser.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return setOfUser;
	}

	/**
	 * Retrieves an active user entry from the database by its ID.
	 *
	 * This method queries the database to retrieve an active user entry stored in
	 * the 'users' table that matches the provided 'userId' and has 'is_active' set
	 * to 1. If a matching active user entry is found, a 'User' object is created,
	 * populated with the retrieved data, and returned. If no matching entry is
	 * found, the method returns null. If any exception occurs during the process,
	 * it is caught, and a RuntimeException is thrown.
	 *
	 * @param userId The ID of the user to be retrieved.
	 * @return A 'User' object representing the details of the retrieved user entry.
	 *         If no matching active entry is found, null is returned.
	 * @throws RuntimeException If an error occurs during the database retrieval
	 *                          process. The original exception is printed, and a
	 *                          RuntimeException is thrown.
	 * @throws PersistanceException 
	 */
	@Override
	public User findById(int userId) throws PersistanceException {

		Connection conn = null;
		PreparedStatement ps = null;
		User user = null;
		ResultSet rs = null;

		try {
			String query = "SELECT id, first_name,password, last_name,is_active, email, phone_no FROM users WHERE is_active = 1 AND id = ?";
			conn = ConnectionUtil.getConnnetion();
			ps = conn.prepareStatement(query);

			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNo(rs.getLong("phone_no"));
				user.setActive(rs.getBoolean("is_active"));
				user.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, ps, rs);
		}
		return user;
	}
	
	/**
	 * Checks if a user with the provided user ID exists in the database.
	 *
	 * This method determines whether a user with the given user ID exists in the
	 * database. It queries the database and returns a boolean value indicating the
	 * presence of the user.
	 * 
	 * @param id The ID of the user to be checked.
	 * @return True if the user with the provided ID exists, otherwise false.
	 * @throws PersistanceException 
	 * @throws RuntimeException If an error occurs during database interaction.
	 */
	public static boolean isUserPresent(int id) throws PersistanceException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionUtil.getConnnetion();
			String query = "SELECT 1 FROM users WHERE id = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}

			return false;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(conn, stmt, rs);
		}
	}
	
	
	/**
	 * Checks if a user with the provided user ID exists in the database.
	 *
	 * This method determines whether a user with the given user ID exists in the
	 * database. It queries the database and returns a boolean value indicating
	 * the presence of the user.
	 *
	 * @param id The ID of the user to be checked.
	 * @return {@code true} if the user with the provided ID exists in the database, {@code false} otherwise.
	 * @throws PersistanceException If an SQL exception occurs while interacting with the database.
	 * @throws RuntimeException If an error occurs during database interaction.
	 */
	public boolean isUserEmailPresent(String email) throws PersistanceException {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = ConnectionUtil.getConnnetion();
	        String query = "SELECT 1 FROM users WHERE email = ?";
	        stmt = conn.prepareStatement(query);
	        stmt.setString(1, email);

	        rs = stmt.executeQuery();

	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }

	        return false;

	    } catch (SQLException e) { 
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(conn, stmt, rs);
	    }
	}

	public User getUserByEmail(String email) throws PersistanceException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    User user = null;

	    try {
	        String query = "SELECT first_name, last_name, email, password, phone_no FROM users WHERE email = ? AND is_active=1";
	        conn = ConnectionUtil.getConnnetion();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, email);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            user = new User();
	            user.setFirstName(rs.getString("first_name"));
	            user.setLastName(rs.getString("last_name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setPhoneNo(rs.getLong("phone_no"));
	        }else {
	        	throw new PersistanceException("email doesnot exists in the dataase");
	        }
	        
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        
	    }catch(PersistanceException e) {
	    	throw new PersistanceException(e.getMessage());
	    }
	    finally {
	        ConnectionUtil.close(conn, ps, rs);
	    }
	    return user;
	}

	public static User findByEmail(String email) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		User user = null;

		try {
			 String query = "SELECT id,first_name, last_name, email, password, phone_no FROM users WHERE email = ? AND is_active=1";
		        con = ConnectionUtil.getConnnetion();
		        ps = con.prepareStatement(query);
		        ps.setString(1, email);
		        rs = ps.executeQuery();

		        if (rs.next()) { 
		            user = new User();
		            user.setFirstName(rs.getString("first_name"));
		            user.setLastName(rs.getString("last_name"));
		            user.setEmail(rs.getString("email"));
		            user.setPassword(rs.getString("password"));
		            user.setPhoneNo(rs.getLong("phone_no"));
		            user.setId(rs.getInt("id"));
		        }else {
		        	throw new PersistanceException("Email doesnot exists ");
		        }
		        
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		}catch(PersistanceException e){
			throw new PersistanceException(e.getMessage());
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return user;
	}
	
	public static void passwordChecker(String email, String password) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String query = "SELECT password FROM users WHERE email = ?";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			
			ps.setString(1, email);
		//	password = PasswordUtil.encodePassword(password);
		//	userDAO.passwordChecker(email, password);
			rs = ps.executeQuery();
			if(rs.next()) {
			String password1 = rs.getString("password");
			String password2 = PasswordUtil.decodePassword(password1);
			if((!password2.equals(password))) {
					throw new PersistanceException("Password mismatch");
			}
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
	}



}