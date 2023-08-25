package in.fssa.expressocafe.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {	

	private int product_id;
	private String name;
	private String description;
	private boolean status;
	private SizeEnum size;
	private Category category;
	private List<Price> priceList = new ArrayList<>();
	private Timestamp createdDate;

	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public SizeEnum getSize() {
		return size;
	}
	public void setSize(SizeEnum size) {
		this.size = size;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<Price> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
	    StringBuilder stringBuilder = new StringBuilder();
	    stringBuilder.append("Product [product_id=").append(product_id)
	                 .append(", name=").append(name)
	                 .append(", description=").append(description)
	                 .append(", status=").append(status)
	                 .append(", size=").append(size)
	                 .append(", category=").append(category.getCategoryId());

	    // Append prices from priceList
	    stringBuilder.append(", prices=[");
	    if (priceList != null && !priceList.isEmpty()) {
	        for (Price price : priceList) {
	        	stringBuilder.append("{");
	            stringBuilder.append(price.getPrice()).append(", ");
	         
	            stringBuilder.append(price.getPriceId()).append(", ");
	           
	            stringBuilder.append(price.getSize()).append("} ");
	        }
	        // Remove the trailing ", " after the last price
	        stringBuilder.setLength(stringBuilder.length() - 2);
	    }
	    stringBuilder.append("]");

	    stringBuilder.append(", createdDate=").append(createdDate).append("]");

	    return stringBuilder.toString();
	}
	
}
