package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.model.SizeEnum;
import in.fssa.expressocafe.util.ConnectionUtil;

public class PriceDAO {
/**
 * 
 * @param price
 * @throws PersistanceException
 */
	public void createProductPrices(Price price) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			String query = "INSERT INTO prices (price, size_id, product_id,start_date) VALUES (?, ?, ?,?)";

			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			
			ps.setDouble(1, price.getPrice());
			ps.setInt(2, price.getSize().getSizeId()); 
			ps.setInt(3, price.getProductId());
			ps.setTimestamp(4, price.getStartDate());
			int rows = ps.executeUpdate();
			if(rows>0) {
			System.out.println("Product price created sucessfully");
			}
			else {
				throw new Exception("Price creation is unsuccessfull");
			}

			System.out.print("Product is successfully created with its prices");
		} catch (SQLException e) {
			System.out.print(e.getMessage());
			throw new PersistanceException("Product price creation is unsuccessfull");
		} catch (Exception e) {
			throw new PersistanceException("Product price creation is unsuccessfull");
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	/**
	 * 
	 * @param productId
	 * @return
	 * @throws PersistanceException
	 */
	/// method for getAllproducts for categoryId;
	
	 public List<Price> getPricesForProduct(int productId) throws PersistanceException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        List<Price> prices = new ArrayList<>();

	        try {
	            String query =  "SELECT price_id,size_id, price FROM prices WHERE product_id = ? AND end_date IS NULL";

	            con = ConnectionUtil.getConnnetion();
	            ps = con.prepareStatement(query);
	            ps.setInt(1, productId);
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                Price price = new Price();
	                price.setPriceId(rs.getInt("price_id"));
	                price.setPrice(rs.getDouble("price"));
	                price.setSize(SizeEnum.values()[rs.getInt("size_id") - 1]); // Convert to SizeEnum
	                
	                prices.add(price);
	            }

	      } 
	        catch (SQLException e) {
	            e.printStackTrace();
	            throw new PersistanceException(e.getMessage());
	        }
	        catch (Exception e) {
				throw new PersistanceException("Error while fetching prices for product");
			} 
	        finally {
	            ConnectionUtil.close(con, ps, rs);
	        }
	        return prices;
	    }
// another method for get all prices 
	
//	public List<Product> getAllprices(List<Product> product1) throws PersistanceException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = ConnectionUtil.getConnnetion();
//			for (Product product : product1) {
//				int productId = product.getProduct_id();
//				String query = "SELECT size_id, price FROM price WHERE product_id = ?";
//				ps = conn.prepareStatement(query);
//				ps.setInt(1, productId);
//				rs = ps.executeQuery();
//				Map<SizeEnum, Double> priceMap = new HashMap<>();
//				while (rs.next()) {
//					int sizeId = rs.getInt("size_id");
//					double price = rs.getDouble("price");
//					SizeEnum sizeEnum = SizeEnum.getSizeEnumById(sizeId);
//					priceMap.put(sizeEnum, price);
//				}
//				product.setPriceMap(priceMap);
//				ConnectionUtil.close(null, ps, rs);
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			throw new PersistanceException("Cannot get All prices");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			throw new PersistanceException("Cannot get All prices");
//		} finally {
//			ConnectionUtil.close(conn, ps, rs);
//		}
//		return product1;
//	}
	
	 /**
	  * 
	  * @param priceId
	  * @param dateTime
	  * @throws PersistanceException
	  */
	public void updatePrice(int priceId, Timestamp dateTime)throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE prices SET end_date = ? WHERE price_id = ? AND end_date IS NULL";
			
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			
			ps.setTimestamp(1, dateTime);
			ps.setInt(2, priceId);
			int rows = ps.executeUpdate();
			if(rows>0) {
			System.out.println("Product price created sucessfully");
			}
			else {
				throw new Exception("Price updation is unsuccessfull");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException("Price Updation is unsuccessful");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException("Price Updation is unsuccessful");
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
/**
 * 
 * @param productId
 * @param sizeId
 * @return
 * @throws PersistanceException
 */
	public int findPriceByProductIdAndSizeId(int productId, int sizeId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int price = -1;

		try {
			String query = "SELECT price FROM prices WHERE product_id=? AND size_id=? AND end_date IS NULL";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, sizeId);

			rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.print("productprice does not exists");
				throw new Exception("productprice does not exists");
			}
			price = rs.getInt("price");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException("Price Updation is unsuccessful");
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return price;
	}

	/**
	 * 
	 * @param productId
	 * @param sizeId
	 * @return
	 * @throws PersistanceException
	 */
	public int checkIfPriceExistForProductWithUniqueSize(int productId, int sizeId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		boolean value = true;
		int priceId = 0;
		
		try {
			String query = "SELECT size_id,price,start_date,price_id,product_id,end_date FROM prices WHERE product_id = ? AND size_id = ? AND  end_date IS NULL";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, sizeId);

			rs = ps.executeQuery();
			if (!rs.next()) {
				System.out.print("productprice does not exists");
				throw new Exception("productprice does not exists");
			}
			priceId = rs.getInt("price_id");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException("productprice does not exists");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException("productprice does not exists");
		} finally {
			ConnectionUtil.close(con, ps);
		}
		return priceId;
	}
/**
 * 
 * @param product_id
 * @param size_id
 * @param price
 * @param dateTime
 * @throws PersistanceException
 */
	public void setNewPrice(int productId, int sizeId, double price, Timestamp dateTime) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO prices(product_id,size_id,price,start_date) VALUES (?,?,?,?)";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, sizeId);
			ps.setDouble(3, price);
			ps.setTimestamp(4, dateTime);
			int rows = ps.executeUpdate();
			if(rows>0) {
			System.out.println("Product price created sucessfully");
			}
			else {
				throw new Exception("Price updation is unsuccessfull");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new PersistanceException("Price updation is unsuccessfull");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
/**
 * 
 * @param productId
 * @param sizeId
 * @return
 * @throws PersistanceException
 */
	public List<Price> getAllPriceHistoryWithSizeID(int productId, int sizeId) throws PersistanceException {
		List<Price> priceHistory = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT size_id,price,start_date,price_id,product_id,end_date FROM prices WHERE product_id = ? AND size_id = ? ORDER BY start_date ASC";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);
			ps.setInt(2, sizeId);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sizeId1 = rs.getInt("size_id");
				int price = rs.getInt("price");
				Timestamp startDate = rs.getTimestamp("start_date");

				Price priceEntry = new Price();
				Product product = new Product();
				
				priceEntry.setPrice(price);
				priceEntry.setPriceId(rs.getInt("price_id"));
				product.setProduct_id(rs.getInt("product_id"));
				priceEntry.setProductId(rs.getInt("product_id"));
				priceEntry.setSize(SizeEnum.values()[rs.getInt("size_id") - 1]);
				priceEntry.setStartDate(rs.getTimestamp("start_date"));
				priceEntry.setEndDate(rs.getTimestamp("end_date"));
				priceHistory.add(priceEntry);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException("Cannot get price history of all the product");
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistanceException("Cannot get price history of all the product");
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return priceHistory;
	}
/**
 * 
 * @param productId
 * @return
 * @throws PersistanceException
 */
	public List<Price> getAllPriceHistory(int productId) throws PersistanceException {
		List<Price> priceHistory = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT size_id,price,start_date,price_id,product_id,end_date FROM prices WHERE product_id = ? ORDER BY start_date ASC";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setInt(1, productId);

			rs = ps.executeQuery();
			while (rs.next()) {
				int sizeId1 = rs.getInt("size_id");
				int price = rs.getInt("price");
				Timestamp startDate = rs.getTimestamp("start_date");
				
				Product product = new Product();
				Price priceEntry = new Price();
				priceEntry.setPrice(price);
				priceEntry.setPriceId(rs.getInt("price_id"));
				priceEntry.setProductId(rs.getInt("product_id"));
				priceEntry.setSize(SizeEnum.values()[rs.getInt("size_id") - 1]);
				priceEntry.setStartDate(rs.getTimestamp("start_date"));
				priceEntry.setEndDate(rs.getTimestamp("end_date"));
				priceHistory.add(priceEntry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistanceException("Cannot get price history of the product");
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistanceException("Cannot get price history of the product");
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return priceHistory;
	}

}
