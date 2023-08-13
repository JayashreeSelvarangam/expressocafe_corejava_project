package in.fssa.expressoCafe.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Price {
	private int priceId ;
	private int productId;
	private int price;
	private int sizeId;
	private Timestamp startDate;
	private Timestamp endDate;
	
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp timestamp) {
		this.startDate = timestamp;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Price [priceId=" + priceId + ", price=" + price + "]";
	}
	public void setProductId(int productId) {
		// TODO Auto-generated method stub
		this.productId = productId;
	}
	public int getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}
}
