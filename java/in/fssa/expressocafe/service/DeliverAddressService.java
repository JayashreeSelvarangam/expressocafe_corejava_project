package in.fssa.expressocafe.service;

import java.util.List;

import in.fssa.expressocafe.DAO.DeliveryAddressDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.DeliveryAddresses;

public class DeliverAddressService {
 public void createDeliveryAddress(DeliveryAddresses newAddress) throws ValidationException, ServiceException {

		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
//		AddressValidator.validateCreate(newAddress);
		DeliverAddressService deliveryService = new DeliverAddressService();
		
		Boolean value = deliveryService.hasActiveAddressForUser(newAddress.getUser().getId());
		List<DeliveryAddresses> listOfAddress = deliveryService.findAllAddressesByUserEmail(newAddress.getUser().getId());
		
		if(value == false || listOfAddress == null ) {
			newAddress.setStatus(1);
		}else {
			newAddress.setStatus(0);
		}
		
		
		try {
			deliveryAddressDAO.create(newAddress);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());

		}

	}

	public List<DeliveryAddresses> getAllAddress() throws ServiceException   {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
		try {
			return deliveryAddressDAO.findAll();
		} catch (PersistanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public List<DeliveryAddresses> findAllAddressesByUserEmail(int userId)
			throws ValidationException, ServiceException {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
	//	AddressValidator.validateEmail(userEmail);
		try {
			return deliveryAddressDAO.listAllAddressesByUserUniqueId(userId);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	public DeliveryAddresses findAddressById(int addressId) throws ValidationException, ServiceException {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
	//	AddressValidator.validateId(addressId);
		try {
			return deliveryAddressDAO.findById(addressId);
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
// I have to some changes
	public void updateAddress(int addressId, DeliveryAddresses address) throws ValidationException, ServiceException {
		DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
	//	AddressValidator.validateUpdate(addressId, address);
		try {
			deliveryAddressDAO.update(address,addressId);
		
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	/*
	 * public void deleteAddress(int addressId) throws ValidationException,
	 * ServiceException { DeliveryAddressDAO deliveryAddressDAO = new
	 * DeliveryAddressDAO(); // AddressValidator.validateDelete(addressId); try {
	 * deliveryAddressDAO.update(addressId); } catch (PersistanceException e) {
	 * e.printStackTrace(); throw new ServiceException(e.getMessage()); } }
	 */
	
	public int  getAddressesByUserIdAndStatus(int userId, int status) throws ServiceException {
	    DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
	    try {
	        return deliveryAddressDAO.getAddressesByUserIdAndStatus(userId, status);
	    } catch (PersistanceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}

	public boolean hasActiveAddressForUser(int userId) throws ServiceException {
	    DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
	    try {
	        return deliveryAddressDAO.hasActiveAddressForUser(userId);
	    } catch (PersistanceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}
	
	public void updateAddressStatus(int addressId, int newStatus) throws ServiceException {
	    DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
	    System.out.println("updateAddressStatus+123");
	    try {
	        deliveryAddressDAO.updateAddressStatus(addressId, newStatus);
	    } catch (PersistanceException e) {
	        e.printStackTrace();
	        throw new ServiceException(e.getMessage());
	    }
	}

}