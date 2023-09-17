package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import in.fssa.expressoCafe.service.ProductEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.interfaces.PriceDAOInterface;
import in.fssa.expressocafe.model.Cart;
import in.fssa.expressocafe.model.Category;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.model.SizeEnum;
import in.fssa.expressocafe.util.ConnectionUtil;

public class ProductDAO {
	/** 
	 * 
	 * @param product
	 * @return
	 * @throws PersistanceException
	 */
	public int createProduct(Product product) throws PersistanceException {
		Connection connection = null; 
		PreparedStatement ps = null;
		ResultSet generatedKeys = null;
		int productId = -1;
		try {
			 // Establish a database connection
			connection = ConnectionUtil.getConnnetion();
			// Define the SQL query for inserting a new product
			String insertProductQuery = "INSERT INTO products (name, description, category_id, created_date) VALUES (?, ?, ?, ?)";
			ps = connection.prepareStatement(insertProductQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, product.getName());
			ps.setString(2, product.getDescription());
			ps.setInt(3, product.getCategory().getCategoryId());	
			ps.setTimestamp(4, product.getCreatedDate());

			int rowsAffected = ps.executeUpdate(); 
			if (rowsAffected > 0) {
				generatedKeys = ps.getGeneratedKeys();
	            // Check if there are generated keys and retrieve the first one
				if (generatedKeys.next()) {
					productId = generatedKeys.getInt(1);
				}
			}else {
				System.out.print("Product creation failed");
				throw new PersistanceException("Product creation failed");
			}
		} catch (SQLException e) {
			throw new PersistanceException(e.getMessage());
		} finally {
			ConnectionUtil.close(connection, ps, generatedKeys);
		}
		return productId;
	}

	
	/**
	 * 
	 * @return
	 * @throws PersistanceException
	 */

	// this method get all product name
	public List<String> getAllProductName() throws PersistanceException {
	
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> productsName = new ArrayList<>();

		try {
			connection = ConnectionUtil.getConnnetion();
			 String query = "SELECT product_id, name FROM products";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String productName = rs.getString("name");
				productsName.add(productName);
			}
			System.out.print("All product names are recieved");
		} catch (SQLException e) {
			throw new PersistanceException("Cannot get all products Name");
		} catch (Exception e) {
			throw new PersistanceException("Cannot get all products Name");
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}
		return productsName;
	}

	/**
	 * 
	 * @param product_id
	 * @param productName
	 * @param productDescription
	 * @param cate_id
	 * @throws PersistanceException
	 */
	// add modified at
	public void updateProduct(int productId1, String productName, String productDescription, int cateId, Timestamp date)throws PersistanceException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try{
			connection = ConnectionUtil.getConnnetion();
			
			String insertProductQuery = "UPDATE products SET name = ? , description = ? , category_id = ? , modified_at =?  WHERE product_id = ? AND status = 1";
			ps = connection.prepareStatement(insertProductQuery);
			
			ps.setString(1, productName);
			ps.setString(2, productDescription);
			ps.setInt(3, cateId);
			ps.setTimestamp(4, date);
			ps.setInt(5,productId1 );
			int rowsAffected = ps.executeUpdate();
			
			System.out.println();
			if (rowsAffected > 0) {
				System.out.print("Product Updated");
			} else {
				System.out.print("Product Not Updated ");
				throw new Exception("Updating product failed");
			}
			
		}catch (SQLException e) {
			System.out.print(e.getMessage());
			throw new PersistanceException("Error while updating product");
		}catch (Exception e) {
			System.out.print(e.getMessage());
			throw new PersistanceException("Error while updating product");
		} finally {
			ConnectionUtil.close(connection, ps);
		}
	}

	/**
	 * 
	 * @param product_id
	 * @return
	 * @throws PersistanceException
	 */
	public boolean doesProductExist(int productId) throws PersistanceException {

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		boolean value = true;

		try {
			
			connection = ConnectionUtil.getConnnetion();
			String query = "SELECT product_id  FROM products WHERE product_id = ? AND status = 1";
			ps = connection.prepareStatement(query);
			
			ps.setInt(1, productId);
			rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.print("product does not exist");
				throw new PersistanceException("product does not exist");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException("product does not exist");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
			throw new PersistanceException("product does not exist");
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}
		return value;
	}

	/**
	 * 
	 * @param product_id
	 * @throws PersistanceException
	 */
	// write testcases for isActive
	public void deleteProduct(int productId) throws PersistanceException{
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = ConnectionUtil.getConnnetion();
			String deleteQuery = "UPDATE products SET status = 0 WHERE product_id = ? AND status = 1";
			ps = connection.prepareStatement(deleteQuery);
			
			ps.setInt(1, productId);
			
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Product deleted successfully");
			}else {
				System.out.println("Product deletion failed");
				throw new Exception("Product deletion failed");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException("Product deletion failed");
		}catch (Exception e) {
			e.printStackTrace();
			throw new PersistanceException("Product deletion failed");
		} finally {
			ConnectionUtil.close(connection, ps);
		}
	}

	/// this method is for checking whether the product is active or not
	/**
	 * 
	 * @param product_id
	 * @return
	 * @throws PersistanceException
	 */
	
	public boolean isActive(int productId) throws PersistanceException {
		String query = "SELECT status FROM products WHERE product_id = ?";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean value = false;
		try {
			connection = ConnectionUtil.getConnnetion();
			ps = connection.prepareStatement(query);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int status = rs.getInt("status");
				if (status == 1) {

					System.out.print("Product is active");
					value = true;
				} else {
					throw new PersistanceException("Product is inactive");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException("Product is inactive");
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistanceException("Product is inactive");
		} finally {
			ConnectionUtil.close(connection, ps, rs);
		}
		return value;
	}
	
	public void changeStatus(int productId , int status) throws PersistanceException {
		String query = "INSERT status VALUES (?) FROM products WHERE product_id = ? ";
		Connection connection = null;
		PreparedStatement ps = null;
		
		boolean value = false;
		try {
			connection = ConnectionUtil.getConnnetion();
			ps = connection.prepareStatement(query);
			ps.setInt(1, status);
			ps.setInt(2, productId);
			int row =  ps.executeUpdate();
			if (row>0) {
		
					System.out.print("Product status is updated");
					
				} else {
					throw new PersistanceException("Product status updation is failed");
				}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException("Product status updation is failed");
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistanceException("Product status updation is failed");
		} finally {
			ConnectionUtil.close(connection, ps);
		}
		
	
		}
		



	
	/**
	 * 
	 * @return
	 * @throws PersistanceException
	 */
	public List<Product> getAllProducts() throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Product> products = new ArrayList<>();

		try {
			con = ConnectionUtil.getConnnetion();
			String query = "SELECT product_id,name,description,category_id FROM products WHERE status=1";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				Category category = new Category();
				product.setProduct_id(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				category.setCategoryId(rs.getInt("category_id"));
				product.setCategory(category);
				products.add(product);

			}
		} catch (SQLException e) {
			throw new PersistanceException("Cannot get all products");
		} catch (Exception e) {
			throw new PersistanceException("Cannot get all products");
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return products;
	}

	/**
	 * 
	 * @param category_id
	 * @return
	 * @throws PersistanceException
	 */
	public List<Product> getAllproductswithCategoryId(int categoryId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Product> products = new ArrayList<>();

		try {
			con = ConnectionUtil.getConnnetion();
			String query = "SELECT product_id, name, description, category_id FROM products where category_id = ? AND status=1";
			ps = con.prepareStatement(query);
			ps.setInt(1, categoryId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				Category category = new Category();

				product.setProduct_id(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				category.setCategoryId(rs.getInt("category_id"));
				product.setCategory(category);
				products.add(product);
			}
		} catch (SQLException e) {
			throw new PersistanceException("Cannot get all products");
		} catch (Exception e) {
			throw new PersistanceException("Cannot get all products");
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return products;
	}

	/**
	 * 
	 * @param productId
	 * @return
	 * @throws PersistanceException
	 */
	public Product findProductWithProductId(int productId) throws PersistanceException {
	    Product product = new Product();
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	        connection = ConnectionUtil.getConnnetion();
	        String query = "SELECT product_id, name, description, category_id FROM products WHERE product_id = ?";
	        ps = connection.prepareStatement(query);
	        ps.setInt(1, productId);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            product = new Product();
	            Category category = new Category();

	            product.setProduct_id(rs.getInt("product_id"));
	            product.setName(rs.getString("name"));
	            product.setDescription(rs.getString("description"));
	            category.setCategoryId(rs.getInt("category_id"));
	            product.setCategory(category);
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException("Cannot get product detail");
	    } catch (Exception e) {
	        throw new PersistanceException("Cannot get product detail");
	    } finally {
	        ConnectionUtil.close(connection, ps, rs);
	    }
	    return product;
	}


	public  int getProductIdByProductName(String productName) throws PersistanceException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int productId = -1; // Initialize with a default value (-1) to indicate no match found.

	    try {
	        con = ConnectionUtil.getConnnetion(); // Correct the method name to getConnection()
	        String query = "SELECT product_id FROM products WHERE name = ? AND status = 1";
	        ps = con.prepareStatement(query);
	        ps.setString(1, productName);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            productId = rs.getInt("product_id");
	        }else {
	        	 throw new PersistanceException("Cannot get product ID by name");
	        }
	    } catch (SQLException e) {
	        throw new PersistanceException("Cannot get product ID by name");
	    }catch(PersistanceException e){
	    	throw new PersistanceException("Cannot get product ID by name");
	    }
	    finally {
	    	
	        ConnectionUtil.close(con, ps, rs);
	    }
	    return productId;
	}

	
//	public 	Product createProduct1(Product product) throws PersistanceException {
//
//		
//		Connection connection = null;
//		PreparedStatement ps = null;
//		ResultSet generatedKeys = null;
//	//	Product productObj = null ; 
//		int productId = -1;
//	
//
//		try {
//			connection = ConnectionUtil.getConnnetion();
//			String insertProductQuery = "INSERT INTO product (name, description,category_id) VALUES (?, ?,?)";
//			ps = connection.prepareStatement(insertProductQuery, PreparedStatement.RETURN_GENERATED_KEYS);
//			ps.setString(1, product.getName());
//			ps.setString(2, product.getDescription());
//			ps.setInt(3, product.getCategory().getCategoryId());
//			
//			int rowsAffected = ps.executeUpdate();
//			if (rowsAffected > 0) {
//				generatedKeys = ps.getGeneratedKeys();
//				if (generatedKeys.next()) {
//					productId = generatedKeys.getInt(1);
//					
//				} else {
//					throw new PersistanceException("Creating product failed, no ID obtained.");
//				}
//
//			} else {
//				System.out.print("Product creation failed");
//				throw new PersistanceException("Product creation failed");
//			}
//		} catch (SQLException e) {
//			throw new PersistanceException("Error while creating product");
//		} finally {
//			ConnectionUtil.close(connection, ps, generatedKeys);
//		}
//		return product;
//	}

	

//

}
