package in.fssa.expressocafe.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Price {

	private Timestamp startDate;
	private Timestamp endDate;
	private int priceId;
   
	private double price; 
    private SizeEnum size;
    private int product_id;
	
	
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) { 
		this.endDate = endDate;
	}
	public int getPriceId() {
		return priceId;
	}
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public SizeEnum getSize() {
		return size;
	}
	public void setSize(SizeEnum size) {
		this.size = size;
	}
	public int getProductId() {
		return product_id;
	}
	public void setProductId(int product_id) {
		this.product_id = product_id;
	}
}
