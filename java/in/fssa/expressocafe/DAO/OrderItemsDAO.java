package in.fssa.expressocafe.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.Order;
import in.fssa.expressocafe.model.OrderItems;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.util.ConnectionUtil;

public class OrderItemsDAO {
	 /**
     * Creates a new order item in the database.
     *
     * @param orderId   The unique identifier of the order to which the item belongs.
     * @param productId The unique identifier of the product associated with the item.
     * @param priceId   The unique identifier of the price associated with the item.
     * @param sizeId    The unique identifier of the size associated with the item.
     * @param quantity  The quantity of the item.
     * @throws PersistanceException If an error occurs while creating the order item.
     */
    public void createOrderItem(int orderId, int productId, int priceId , int sizeId,int quantity) throws PersistanceException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            // Establish a database connection
           connection = ConnectionUtil.getConnnetion();

            // Define the SQL query for inserting a new order item
            String insertOrderItemQuery = "INSERT INTO order_items (order_id, product_id, price_id,size_id,quantity) VALUES (?, ?, ?,?,?)";
            ps = connection.prepareStatement(insertOrderItemQuery);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, priceId);
            ps.setInt(4, sizeId);
            ps.setInt(5,quantity);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected <= 0) {
                System.out.print("Order item creation failed");
                throw new PersistanceException("Order item creation failed");
            }
        } catch (SQLException e) {
            throw new PersistanceException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps);
        }
    }
    
    /**
     * Retrieves a list of order items based on the provided order ID.
     *
     * @param orderId The unique identifier of the order for which to retrieve items.
     * @return A list of OrderItems objects representing the items associated with the order.
     * @throws PersistanceException If an error occurs during the retrieval process.
     */
    public List<OrderItems> GetOrderItemByOrderId(int orderId) throws PersistanceException {
        List<OrderItems> orderItemsList1 = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null; 
        ResultSet rs = null;
        try {
            // Establish a database connection
            connection = ConnectionUtil.getConnnetion();
            // Define the SQL query to retrieve order items by orderId
            String selectOrderItemsQuery = "SELECT order_id,product_id,price_id,size_id,quantity FROM order_items WHERE order_id = ?";
            ps = connection.prepareStatement(selectOrderItemsQuery);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderItems orderItem = new OrderItems();
                Order order = new Order();
                Product product = new Product();
                Price price = new Price();
                price.setPriceId(rs.getInt("price_id"));
                
                
                order.setOrderId(rs.getInt("order_id"));
                product.setProduct_id(rs.getInt("product_id"));
                product.setPriceObj(price);
                
                orderItem.setOrder(order);
                orderItem.setProduct(product);
                orderItem.setSizeId(rs.getInt("size_id"));
                orderItem.setQuantity(rs.getInt("quantity"));
                orderItemsList1.add(orderItem);
            }
        } catch (SQLException e) {
            throw new PersistanceException(e.getMessage());
        } finally {
            ConnectionUtil.close(connection, ps, rs);
        }
        return orderItemsList1;
    }
    
}
