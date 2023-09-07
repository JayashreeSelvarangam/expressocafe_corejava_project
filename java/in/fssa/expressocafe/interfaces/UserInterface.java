package in.fssa.expressocafe.interfaces;

import java.util.Set;

import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.model.User;


public interface UserInterface extends Base<User>{
	public abstract	Set<User> findAll() throws PersistanceException;
	public abstract User findById(int id) throws PersistanceException;
}
