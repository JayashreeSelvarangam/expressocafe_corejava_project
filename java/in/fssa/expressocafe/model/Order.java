package in.fssa.expressocafe.model;

import java.sql.Timestamp;
import java.util.List;

public class Order extends Product {

	private int userId ;
	private int orderId;
	private int sizeId;
	private int quantity;
	private double totalCost;
	private String orderCode;
	private boolean deliveryStatus;
	private List<OrderItems> orderItems;
	private OrderItems orderItem;
	private Timestamp orderedDate;
	private Timestamp cancelDate;
	private Timestamp deliveryDate;
	private int addressId;

	public Timestamp getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}
	public OrderItems getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItems orderItem) {
		this.orderItem = orderItem;
	}
	public List<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getSizeId() {
		return sizeId;
	}
	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Timestamp getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(Timestamp orderedDate) {
		this.orderedDate = orderedDate;
	}
	public Timestamp getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Timestamp deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public boolean getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
