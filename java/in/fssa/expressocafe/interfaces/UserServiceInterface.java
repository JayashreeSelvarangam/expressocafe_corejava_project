package in.fssa.expressocafe.interfaces;

import in.fssa.expressocafe.model.UserEntity;

public interface UserServiceInterface {
    UserEntity createUser(UserEntity user);
    boolean doesEmailExist(String email);
    boolean doesPhoneNumberExist(long phoneNumber);
}
