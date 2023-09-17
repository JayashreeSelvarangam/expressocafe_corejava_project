package in.fssa.expressocafe.model;

public interface OrderItem {
	   int getProductId();
	    void setProductId(int productId);

	    int getOrderId();
	    void setOrderId(int orderId);

	    int getSizeId();
	    void setSizeId(int sizeId);

	    int getPriceId();
	    void setPriceId(int priceId);

	    int getQuantity();
	    void setQuantity(int quantity);

	    boolean isStatus();
	    void setStatus(boolean status);
}
