package in.fssa.expressocafe.model;

import java.sql.Timestamp;

public class OrderItems extends Product{

	private Product product;
	private Order order;
	private int sizeId;
	private int quantity;
	private boolean status;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order; 
	}


	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getSizeId() {
		return sizeId;
	}
	
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	
	

	
}
