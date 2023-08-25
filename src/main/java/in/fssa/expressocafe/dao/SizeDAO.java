package in.fssa.expressocafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.Size;
import in.fssa.expressocafe.util.ConnectionUtil;

public class SizeDAO {
	/**
	 * 
	 * @return
	 */
	public List<Size> getAllSizes() {
		String query = "SELECT * FROM sizes";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Size> sizes = new ArrayList<>();

		try {
			connection = ConnectionUtil.getConnnetion();
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Size size = new Size();
				size.setSizeId(rs.getInt("size_id"));
				size.setSizeName(rs.getString("size_name"));
				sizes.add(size);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}

		return sizes;
	}
/**
 * 
 * @param sizeId
 * @return
 * @throws PersistanceException 
 */
	public Size getSizeById(int sizeId) throws PersistanceException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Size size = null;

		try {
			connection = ConnectionUtil.getConnnetion();
			String query = "SELECT * FROM size WHERE size_id = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, sizeId);
			rs = ps.executeQuery();

			if (rs.next()) {
				size = new Size();
				size.setSizeId(rs.getInt("size_id"));
				size.setSizeName(rs.getString("size_name"));

			}

			// Size not found
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException("Size does not exists");
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}
		return size;
	}
/**
 * 
 * @param sizeId
 * @return
 * @throws PersistanceException
 */
	public boolean doesSizeIdExists(int sizeId) throws PersistanceException {
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        boolean value = true;
	        try {
	            connection = ConnectionUtil.getConnnetion();
	            String query = "SELECT * FROM size WHERE size_id = ?";
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, sizeId);
	            rs = ps.executeQuery();

	            if (!rs.next()) {
	             
	                System.out.print("size id does not exist");
	                throw new Exception("size id does not exist");
	                }
	             }
	        	catch(SQLException e){
	        		e.printStackTrace();
	        		System.out.print(e.getMessage());
	        		throw new PersistanceException("size id does not exist");
	        	}
	        catch(Exception e) {
	        	e.printStackTrace();
        		System.out.print(e.getMessage());
        		throw new PersistanceException(e.getMessage());
	        }
	        finally{
	        		ConnectionUtil.close(connection, ps, rs);
	        }
	        return value;
}

}
