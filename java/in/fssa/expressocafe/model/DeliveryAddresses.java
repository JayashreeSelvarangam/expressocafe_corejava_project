package in.fssa.expressocafe.model;

import java.sql.Timestamp;

public class DeliveryAddresses {

	  	private int addressId;
	    private int userId;
	    private String title;
	    private String address;
	    private String landmark;
	    private String city;
	    private int pincode;
	    private Timestamp createdAt;
	    private Timestamp modifiedAt;
	    private int status;
	    private User user;

	    public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public int getAddressId() {
	        return addressId;
	    }

	    public void setAddressId(int addressId) {
	        this.addressId = addressId;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getLandmark() {
	        return landmark;
	    }

	    public void setLandmark(String landmark) {
	        this.landmark = landmark;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public int getPincode() {
	        return pincode;
	    }

	    public void setPincode(int pincode) {
	        this.pincode = pincode;
	    }

	    public Timestamp getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(Timestamp createdAt) {
	        this.createdAt = createdAt;
	    }

	    public Timestamp getModifiedAt() {
	        return modifiedAt;
	    }

	    public void setModifiedAt(Timestamp modifiedAt) {
	        this.modifiedAt = modifiedAt;
	    }

	    public int getStatus() {
	        return status;
	    }

	    public void setStatus(int status) {
	        this.status = status;
	    }

	    @Override
	    public String toString() {
	        return "Address [addressId=" + addressId + ", userId=" + userId + ", title=" + title + ", address=" + address
	                + ", landmark=" + landmark + ", city=" + city + ", pincode=" + pincode + ", createdAt=" + createdAt
	                + ", modifiedAt=" + modifiedAt + ", status=" + status + "]";
	    }
	}