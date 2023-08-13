package in.fssa.expressoCafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.model.Size;
import in.fssa.expressoCafe.util.ConnectionUtil;

public class SizeDAO {
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

	    public Size getSizeById(int sizeId) {
	        String query = "SELECT * FROM sizes WHERE size_id = ?";
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        try {
	            connection = ConnectionUtil.getConnnetion();
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, sizeId);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                Size size = new Size();
	                size.setSizeId(rs.getInt("size_id"));
	                size.setSizeName(rs.getString("size_name"));
	                return size;
	            }

	            return null; // Size not found
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        } finally {
	            ConnectionUtil.close(connection, ps, rs);
	        }
	    }
	    
	    public boolean doesSizeIdExists(int sizeId) throws PersistanceException {
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        boolean value = false;
	        try {
	            connection = ConnectionUtil.getConnnetion();
	            String query = "SELECT COUNT(*) FROM size WHERE size_id = ?";
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, sizeId);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	                int count = rs.getInt(1);
	                if (count > 0) {
	                    System.out.print(" size id exists");
	                    value = true;
	                }else{
	                    System.out.print("size id does not exist");
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.print(e.getMessage());
	            throw new PersistanceException(e.getMessage());
	        } finally {
	            ConnectionUtil.close(connection, ps, rs);
	        }
	        return value;
	    	}

}
