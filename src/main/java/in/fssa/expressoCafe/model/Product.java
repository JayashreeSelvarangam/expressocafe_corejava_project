package in.fssa.expressoCafe.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Product {

	private int product_id;

	public Product(int product_id, String name, String description, int priceSmall, int priceMedium, int priceLarge,
			boolean status, int categoryId, LocalDateTime createdDate, Map<SizeEnum, Double> priceMap) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.priceSmall = priceSmall;
		this.priceMedium = priceMedium;
		this.priceLarge = priceLarge;
		this.status = status;
		this.categoryId = categoryId;
		this.createdDate = createdDate;
		this.priceMap = priceMap;
	}

	private String name;
	private String description;
	private int priceSmall;
	private int priceMedium;
	private int priceLarge;
	private boolean status;
	private int categoryId;
	private LocalDateTime createdDate;
	private Map<SizeEnum, Double> priceMap = new HashMap<>();
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public Map<SizeEnum, Double> getPriceMap() {
		return priceMap;
	}
	
	public void setPriceMap(Map<SizeEnum, Double> priceMap) {
		this.priceMap = priceMap;
	}
	
	public Product(String name2, String description2, Map<in.fssa.expressoCafe.model.SizeEnum, Double> priceMap2,
			int category_id) {
		this.name = name;
		this.description = description;
		this.priceMap = priceMap;
		this.categoryId = category_id;
	}
	
	public Product() {

	}
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
		if (description == null) {
			System.out.println("object null");
		}
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getPriceSmall() {
		return priceSmall;
	}
	
	public void setPriceSmall(int priceSmall) {
		this.priceSmall = priceSmall;
	}
	
	public int getPriceMedium() {
		return priceMedium;
	}

	public void setPriceMedium(int priceMedium) {
		this.priceMedium = priceMedium;
	}
	public int getPriceLarge() {
		return priceLarge;
	}
	public void setPriceLarge(int priceLarge) {
		this.priceLarge = priceLarge;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setPriceForSize(SizeEnum size, double price) {
		priceMap.put(size, price);
	}

	public Double getPriceForSize(SizeEnum size) {
		return priceMap.get(size);
	}
	
//	@Override
//	public String toString() {
//		return "Product [product_id=" + product_id + ", name=" + name + ", description=" + description + ", priceSmall="
//				+ priceSmall + ", priceMedium=" + priceMedium + ", priceLarge=" + priceLarge + ", createdDate="
//				+ createdDate] };
//	
//	}
	
}
