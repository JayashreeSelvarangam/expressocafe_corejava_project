package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.DeliveryAddresses;
import in.fssa.expressocafe.util.ConnectionUtil;

public class DeliveryAddressDAO {

	public void create(DeliveryAddresses newAddress) throws PersistanceException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet generatedKeys = null;

	    try {
	        String query = "INSERT INTO addresses (user_id, title, address, land_mark, city, pincode,status) VALUES (?,?,?,?,?,?,?)";
	        conn = ConnectionUtil.getConnnetion();
	        ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, newAddress.getUser().getId());
	        ps.setString(2, newAddress.getTitle());
	        ps.setString(3, newAddress.getAddress());
	        ps.setString(4, newAddress.getLandmark());
	        ps.setString(5, newAddress.getCity());
	        ps.setInt(6, newAddress.getPincode());
	        ps.setInt(7, newAddress.getStatus());

	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected > 0) {
	            generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int addressId = generatedKeys.getInt(1);
	                newAddress.setAddressId(addressId);
	                System.out.println("Address has been created successfully with ID: " + addressId);
	            }
	        } else {
	            System.out.println("Address creation failed");
	            throw new PersistanceException("Address creation failed");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(conn, ps, generatedKeys);
	    }
	}

	public void update(DeliveryAddresses address , int addressId) throws PersistanceException {
	    Connection conn = null;
	    PreparedStatement ps = null;

	    try {
	        String query = "UPDATE addresses SET title=?, address=?, land_mark=?, city=?, pincode=?, modified_at=CURRENT_TIMESTAMP WHERE address_id=?";
	        conn = ConnectionUtil.getConnnetion();
	        ps = conn.prepareStatement(query);
	        ps.setString(1, address.getTitle());
	        ps.setString(2, address.getAddress());
	        ps.setString(3, address.getLandmark());
	        ps.setString(4, address.getCity());
	        ps.setInt(5, address.getPincode());
	        ps.setInt(6, addressId);

	        int rowsUpdated = ps.executeUpdate();

	        if (rowsUpdated > 0) {
	            System.out.println("Address with ID " +addressId + " has been updated successfully.");
	        } else {
	            System.out.println("Address with ID " + addressId + " not found.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println(e.getMessage());
	        throw new PersistanceException(e.getMessage());
	    } finally {
	        ConnectionUtil.close(conn, ps);
	    }
	}


		public void Delete(int addressId) throws PersistanceException {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				String query = "UPDATE addresses SET status=? WHERE is_active=1 AND id=?";
				conn = ConnectionUtil.getConnnetion();
				ps = conn.prepareStatement(query);
				ps.setBoolean(1, false);
				ps.setInt(2, addressId);
				int rowsUpdated = ps.executeUpdate();
				if (rowsUpdated > 0) {
					System.out.println("Address with ID " + addressId + " has been deleted successfully.");
				} else {
					System.out.println("Address with ID " + addressId + " not found.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				throw new PersistanceException(e.getMessage());
			} finally {
				ConnectionUtil.close(conn, ps);
			}

		}
		
		
		public DeliveryAddresses findById(int id) throws PersistanceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    DeliveryAddresses address = null;
		    try {
		        String query = "SELECT user_id, title, address, land_mark, city, pincode, created_at, modified_at, status FROM addresses WHERE address_id=?";
		        conn = ConnectionUtil.getConnnetion();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, id);
		        rs = ps.executeQuery();

		        if (!rs.next()) {
		            throw new PersistanceException("Address with ID " + id + " not found");
		        }

		        address = new DeliveryAddresses();
		        address.setAddressId(id); // Set the provided ID
		        address.setUserId(rs.getInt("user_id"));
		        address.setTitle(rs.getString("title"));
		        address.setAddress(rs.getString("address"));
		        address.setLandmark(rs.getString("land_mark"));
		        address.setCity(rs.getString("city"));
		        address.setPincode(rs.getInt("pincode"));
		        address.setCreatedAt(rs.getTimestamp("created_at"));
		        address.setModifiedAt(rs.getTimestamp("modified_at"));
		        address.setStatus(rs.getInt("status"));

		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }
		    return address;
		}

		public List<DeliveryAddresses> listAllAddressesByUserUniqueId(int  userId) throws PersistanceException {
		    List<DeliveryAddresses> addressList = new ArrayList<DeliveryAddresses>();
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		        String query = "SELECT address_id, user_id, title, address, land_mark, city, pincode, created_at, modified_at, status FROM addresses WHERE  user_id=?";
		        conn = ConnectionUtil.getConnnetion();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, userId);
		        rs = ps.executeQuery();

		        while (rs.next()) {
		            DeliveryAddresses address = new DeliveryAddresses();
		            address.setAddressId(rs.getInt("address_id"));
		            address.setUserId(rs.getInt("user_id"));
		            address.setTitle(rs.getString("title"));
		            address.setAddress(rs.getString("address"));
		            address.setLandmark(rs.getString("land_mark"));
		            address.setCity(rs.getString("city"));
		            address.setPincode(rs.getInt("pincode"));
		            address.setCreatedAt(rs.getTimestamp("created_at"));
		            address.setModifiedAt(rs.getTimestamp("modified_at"));
		            address.setStatus(rs.getInt("status"));

		            addressList.add(address);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }

		    return addressList;
		}
		
		public boolean hasActiveAddressForUser(int userId) throws PersistanceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    boolean hasActiveAddress = false;

		    try {
		        String query = "SELECT COUNT(*) FROM addresses WHERE user_id = ? AND status = 1";
		        conn = ConnectionUtil.getConnnetion();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, userId);
		        rs = ps.executeQuery();

		        if (rs.next() && rs.getInt(1) > 0) {
		            // User has an active address
		            hasActiveAddress = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }

		    return hasActiveAddress;
		}

		
		public boolean isAddressDetailsExists(DeliveryAddresses address) throws PersistanceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try {
		        String query = "SELECT address_id FROM addresses WHERE user_id = ? AND title = ? AND address = ? AND land_mark = ? AND city = ? AND pincode = ? AND created_at = ? AND modified_at = ? AND status = ?";
		        conn = ConnectionUtil.getConnnetion();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, address.getUserId());
		        ps.setString(2, address.getTitle());
		        ps.setString(3, address.getAddress());
		        ps.setString(4, address.getLandmark());
		        ps.setString(5, address.getCity());
		        ps.setInt(6, address.getPincode());
		        ps.setTimestamp(7, address.getCreatedAt());
		        ps.setTimestamp(8, address.getModifiedAt());
		        ps.setInt(9, address.getStatus());

		        rs = ps.executeQuery();

		        return rs.next();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }
		}
		
		
		public List<DeliveryAddresses> findAll() throws PersistanceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    List<DeliveryAddresses> addressList = new ArrayList<DeliveryAddresses>();
		    ResultSet rs = null;
		    try {
		        String query = "SELECT address_id, user_id, title, address, land_mark, city, pincode, created_at, modified_at, status FROM addresses";
		        conn = ConnectionUtil.getConnnetion();
		        ps = conn.prepareStatement(query);
		        rs = ps.executeQuery();
		        while (rs.next()) {
		            DeliveryAddresses address = new DeliveryAddresses();

		            address.setAddressId(rs.getInt("address_id"));
		            address.setUserId(rs.getInt("user_id"));
		            address.setTitle(rs.getString("title"));
		            address.setAddress(rs.getString("address"));
		            address.setLandmark(rs.getString("land_mark"));
		            address.setCity(rs.getString("city"));
		            address.setPincode(rs.getInt("pincode"));
		            address.setCreatedAt(rs.getTimestamp("created_at"));
		            address.setModifiedAt(rs.getTimestamp("modified_at"));
		            address.setStatus(rs.getInt("status"));

		            addressList.add(address);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println(e.getMessage());
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }
		    return addressList;
		}
		
		public int getAddressesByUserIdAndStatus(int userId, int status) throws PersistanceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    
		    int addressId = 0 ;
		    try {
		        String query = "SELECT address_id FROM addresses WHERE user_id = ? AND status = ?";
		        conn = ConnectionUtil.getConnnetion();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, userId);
		        ps.setInt(2, status);
		        rs = ps.executeQuery();

		        while (rs.next()) {
		            // Populate the address object from the ResultSet
		        	addressId = rs.getInt("address_id");
		           
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(conn, ps, rs);
		    }

		    return addressId;
		}
		
		public void updateAddressStatus(int addressId, int newStatus) throws PersistanceException {
		    Connection conn = null;
		    PreparedStatement ps = null;
		  System.out.print("updateAddressStatus");
		    try {
		        String query = "UPDATE addresses SET status = ? WHERE address_id = ?";
		        conn = ConnectionUtil.getConnnetion();
		        ps = conn.prepareStatement(query);
		        ps.setInt(1, newStatus);
		        ps.setInt(2, addressId);

		        int rowsAffected = ps.executeUpdate();

		        if (rowsAffected <= 0) {
		            System.out.println("Address status update failed");
		            throw new PersistanceException("Address status update failed");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		        throw new PersistanceException(e.getMessage());
		    } finally {
		        ConnectionUtil.close(conn, ps);
		    }
		}


		
	}

