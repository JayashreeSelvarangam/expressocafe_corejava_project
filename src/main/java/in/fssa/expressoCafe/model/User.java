package in.fssa.expressoCafe.model;

public class User extends UserEntity{

	public User(int id, String name,  String email, String password,long phno,String Adddress, boolean isActive) {

		super.setUserId(id);
		super.setStatus(isActive);
		super.setEmail(email);
		super.setName(name);
		super.setPassword(password);
		super.setPhoneNumber(phno);
		super.setAddress(Adddress);
		
	}
	public User() {
		super();
	}

	
}
