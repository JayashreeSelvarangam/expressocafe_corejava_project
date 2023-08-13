package in.fssa.expressoCafe.interfaces;

import in.fssa.expressoCafe.model.UserEntity;

public interface UserServiceInterface {
    UserEntity createUser(UserEntity user);
    boolean doesEmailExist(String email);
    boolean doesPhoneNumberExist(long phoneNumber);
}
