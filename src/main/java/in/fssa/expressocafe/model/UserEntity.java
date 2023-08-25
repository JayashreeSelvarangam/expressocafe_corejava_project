package in.fssa.expressocafe.model;

public class UserEntity implements Comparable<UserEntity>{
		private int userId;
		private String name;
	    private long phoneNumber;
	    private String email;
	    private String password;
	    private String address;
	    private boolean status;
	    
	    public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(long phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		@Override
		public String toString() {
			return "UserEntity [userId=" + userId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email="
					+ email + ", password=" + password + ", address=" + address + ", status=" + status + "]";
		
	}
		// Compare by name
	    public int compareByName(UserEntity otherUser) {
	        return this.name.compareTo(otherUser.getName());
	    }

	    // Compare by userId
	    @Override
	    public int compareTo(UserEntity otherUser) {
	        if (this.userId == otherUser.getUserId()) {
	            return 0;
	        } else {
	            return Integer.compare(this.userId, otherUser.getUserId());
	        }
	    }
		


}
