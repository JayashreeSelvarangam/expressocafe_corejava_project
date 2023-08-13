package in.fssa.expressoCafe.interfaces;

import in.fssa.expressoCafe.model.UserEntity;

public interface UserDAOInterface {
	UserEntity createUser(UserEntity user);

	boolean doesPhoneNumberExist(long phoneNumber);

	boolean doesEmailExist(String email);

	//UserEntity updateUser(UserEntity user);

	// boolean doesEmailExist1(String email);
}
