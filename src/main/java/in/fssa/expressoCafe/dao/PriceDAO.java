package in.fssa.expressoCafe.dao;

import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.model.SizeEnum;
import in.fssa.expressoCafe.util.ConnectionUtil;

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

public class PriceDAO {

	public void createProductPrices(Price price) throws PersistanceException {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			
			String query = "INSERT INTO price (price, size_id, product_id) VALUES (?, ?, ?)";

			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setDouble(1, price.getPrice());
			ps.setInt(2, price.getSize().getSizeId()); // Assuming SizeEnum values match database values
			ps.setInt(3, price.getProduct().getProduct_id());
			ps.executeUpdate();

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

	

	/// method for getAllproducts for categoryId;
	 public List<Price> getPricesForProduct(int productId) throws PersistanceException {
	        Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;

	        List<Price> prices = new ArrayList<>();

	        try {
	            String query =  "SELECT price_id,size_id, price FROM price WHERE product_id = ? AND end_date IS NULL";

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
	
	public void UpdatePrice(int priceId, Timestamp dateTime)throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "UPDATE price SET end_date = ? WHERE price_id = ? AND end_date IS NULL";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, dateTime);
			ps.setInt(2, priceId);
			// ps.setTimestamp(3, dateTime);
			ps.executeUpdate();
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

	public int findPriceByProductIdAndSizeId(int productId, int sizeId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int price = -1;

		try {
			String query = "SELECT * FROM price WHERE product_id=? AND size_id =? AND end_date IS NULL";
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

	public int checkIfPriceExistForProductWithUniqueSize(int productId, int sizeId) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean value = true;
		int priceId = 0;
		try {
			String query = "Select * From price Where product_id = ? And size_id = ? And  end_date IS NULL";
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

	public void SetNewPrice(int product_id, int size_id, double price, Timestamp dateTime) throws PersistanceException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			String query = "INSERT INTO price(product_id,size_id,price,start_date) VALUES (?,?,?,?)";
			con = ConnectionUtil.getConnnetion();
			ps = con.prepareStatement(query);
			ps.setInt(1, product_id);
			ps.setInt(2, size_id);
			ps.setDouble(3, price);
			ps.setTimestamp(4, dateTime);
			ps.executeUpdate();
			System.out.println("Product price created sucessfully");
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

	public List<Price> getAllPriceHistoryWithSizeID(int productId, int sizeId) throws PersistanceException {
		List<Price> priceHistory = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM price WHERE product_id = ? AND size_id = ? ORDER BY start_date ASC";
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
				priceEntry.setProduct(product);
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

	public List<Price> getAllPriceHistory(int productId) throws PersistanceException {
		List<Price> priceHistory = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM price WHERE product_id = ? ORDER BY start_date ASC";
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
				product.setProduct_id(rs.getInt("product_id"));
				priceEntry.setProduct(product);
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

//	public Product getpriceswithProductId(Product product1) throws PersistanceException {
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		try {
//			conn = ConnectionUtil.getConnnetion();
//			int productId = product1.getProduct_id();
//			String query = "SELECT size_id, price FROM price WHERE product_id = ?";
//			ps = conn.prepareStatement(query);
//			ps.setInt(1, productId);
//			rs = ps.executeQuery();
//			Map<SizeEnum, Double> priceMap = new HashMap<>();
//			while (rs.next()) {
//				int sizeId = rs.getInt("size_id");
//				double price = rs.getDouble("price");
//				SizeEnum sizeEnum = SizeEnum.getSizeEnumById(sizeId);
//				priceMap.put(sizeEnum, price);
//			}
//			product1.setPriceMap(priceMap);
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			throw new PersistanceException("Cannot get Price for this product id");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			throw new PersistanceException("Cannot get Price for this product id");
//		} finally {
//			ConnectionUtil.close(conn, ps, rs);
//		}
//		return product1;
//	}

}
