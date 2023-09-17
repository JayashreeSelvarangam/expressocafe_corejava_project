package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.CategoryEntity;
import in.fssa.expressocafe.util.ConnectionUtil;

public class CategoryDAO {
/**
 * 
 * @param categoryId
 * @return
 * @throws ValidationException
 * @throws PersistanceException 
 */
	public Category getCategoryById(int categoryId) throws ValidationException, PersistanceException {
		 String query = "SELECT cate_id,name  FROM categories WHERE cate_id = ?";
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
	public boolean doesCategoryExist(int cateId) throws PersistanceException {
	
		Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean value = false;
	    
	    try {
	        connection = ConnectionUtil.getConnnetion();
	        String query = "SELECT 1 FROM categories WHERE cate_id = ?";
	        ps = connection.prepareStatement(query);
	        ps.setInt(1, cateId);
	        rs = ps.executeQuery();
	        	        
	        if(rs.next()) { 
	        	  value = true;
	        }else {
	        	throw new PersistanceException("category does not exists");
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
/**
 * 
 * @return
 * @throws PersistanceException 
 */
	
	public List<Category> getAllCategories() throws PersistanceException {
        String query = "SELECT cate_id,name FROM categories";
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
	

