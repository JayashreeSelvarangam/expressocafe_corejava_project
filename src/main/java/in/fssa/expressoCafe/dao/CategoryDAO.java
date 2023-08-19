package in.fssa.expressoCafe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.CategoryEntity;
import in.fssa.expressoCafe.util.ConnectionUtil;

public class CategoryDAO {
/**
 * 
 * @param categoryId
 * @return
 * @throws ValidationException
 * @throws PersistanceException 
 */
	public Category getCategoryById(int categoryId) throws ValidationException, PersistanceException {
		 String query = "SELECT * FROM category WHERE cate_id = ?";
	        Connection connection = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        Category category = null;
	        
	        try {
	            connection = ConnectionUtil.getConnnetion();
	            ps = connection.prepareStatement(query);
	            ps.setInt(1, categoryId);
	            rs = ps.executeQuery();

	            if (rs.next()) {
	            	
	                category = new Category();
	                category.setCategoryId(rs.getInt("cate_id"));
	                category.setCategoryName(rs.getString("name"));
	             
	            }

	           
	        }catch (SQLException e) {
		        e.printStackTrace();
		        System.out.print(e.getMessage());
		        throw new PersistanceException("category does not exists");
		    } catch (Exception e) {
		        e.printStackTrace();
		        System.out.print(e.getMessage());
		        throw new PersistanceException("category does not exists"); 	
		    } finally {
	            ConnectionUtil.close(connection, ps, rs);
	        }
	        return category;
			
	    }
	/**
	 * 
	 * @param cate_id
	 * @return
	 * @throws PersistanceException
	 */
	public boolean doesCategoryExist(int cate_id) throws PersistanceException {
		Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean value = true;
	    
	    try {
	        connection = ConnectionUtil.getConnnetion();
	        String query = "SELECT * FROM category WHERE cate_id = ?";
	        ps = connection.prepareStatement(query);
	        ps.setInt(1, cate_id);
	        rs = ps.executeQuery();
	        if(!rs.next()) {
	        	  value = false;
	        	  System.out.print("category does not exists");
	        	  throw new Exception("category does not exists"); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print(e.getMessage());
	        throw new PersistanceException("category does not exists");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.print(e.getMessage());
	        throw new PersistanceException("category does not exists"); 	
	    }
	    finally {
	     ConnectionUtil.close(connection, ps, rs);
	    }
	    return value;
	}
/**
 * 
 * @return
 * @throws PersistanceException 
 */
	
	public List<Category> getAllCategories() throws PersistanceException {
        String query = "SELECT * FROM category";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> categories = new ArrayList<>();

        try {
            connection = ConnectionUtil.getConnnetion();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
            	Category category = new Category();
                category.setCategoryId(rs.getInt("cate_id"));
                category.setCategoryName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.print(e.getMessage());
	        throw new PersistanceException("category does not exists");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.print(e.getMessage());
	        throw new PersistanceException("category does not exists"); 	
	    } finally {
            ConnectionUtil.close(connection, ps, rs);
        }

        return categories;
    }
}
	

