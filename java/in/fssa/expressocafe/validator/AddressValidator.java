package in.fssa.expressocafe.validator;

import in.fssa.expressocafe.DAO.DeliveryAddressDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.DeliveryAddresses;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.util.StringUtil;

public class AddressValidator {
	public static void validateCreate(DeliveryAddresses newAddress) throws ValidationException {

		if (newAddress == null) {
			throw new ValidationException("Invalid Address input");
		}
	
		StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getAddress(), "Address");
		StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getTitle(), "Title");
		StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getCity(), "City");
		StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getLandmark(), "LandMark");
		
		if (!(newAddress.getPincode() >= 600001 && newAddress.getPincode() <= 699999)) {
			throw new ValidationException("Invalid Pincode. This pincode is not available for delivery.");
		}

		try {
			if(newAddress.getStatus()>=0) {
			DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
			boolean isAddressExists = deliveryAddressDAO.isAddressDetailsExists(newAddress);
			if (isAddressExists) {
				throw new ValidationException("This address details is already exists");
			}
			}
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public static void validateId(int addressId) throws ValidationException {
		if (addressId <= 0) {
			throw new ValidationException("Invalid address Id");
		}
		DeliveryAddressDAO deliveryAddressDAO = null;
		try {
			deliveryAddressDAO = new DeliveryAddressDAO();
			deliveryAddressDAO.findById(addressId);
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	/** 
	 * Validates an address ID.
	 * @param addressId The address ID to validate.
	 * @throws ValidationException If the address ID is invalid.
	 */
		public static void validateAddressId(int addressId) throws ValidationException {
			// Validate the address ID
			IntUtil.rejectIfInvalidInt(addressId, "Invalid address ID.");
		}
		
		public static void validateStatus(int status) throws ValidationException {
			// Validate the address ID
			
			if(status!=0 && status!=1) {
				throw new ValidationException("status is invalid");
			}
	
		}
	
	
		public static void validateUserId(int userId) throws ValidationException {
			// Validate the order ID
			IntUtil.rejectIfInvalidInt(userId, "Invalid userID");
		}
		public static void validateUpdate(int addressId, DeliveryAddresses newAddress) throws ValidationException {
			if (addressId <= 0) {
				throw new ValidationException("Invalid address Id");
			}
			if (newAddress == null) {
				throw new ValidationException("Invalid Address input");
			}
	
			StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getAddress(), "Address");
			StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getTitle(), "Title");
			StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getCity(), "City");
			StringUtil.rejectIfInvalidStringWithoutPattern(newAddress.getLandmark(), "LandMark");
	
			if (!(newAddress.getPincode() >= 600001 && newAddress.getPincode() <= 699999)) {
	
				throw new ValidationException("Invalid Pincode. This pincode is not available for delivery.");
			}
			
			DeliveryAddressDAO deliveryAddressDAO = null;
			try {
				deliveryAddressDAO = new DeliveryAddressDAO();
				if ((deliveryAddressDAO.findById(addressId)) == null) {
					throw new ValidationException("This address Id is not available in address list");
				}
			} catch (PersistanceException e) {
				throw new ValidationException(e.getMessage());
			}
			
			try {
				deliveryAddressDAO = new DeliveryAddressDAO();
				boolean isAddressExists = deliveryAddressDAO.isAddressDetailsExists(newAddress);
				if (isAddressExists) {
					throw new ValidationException("This address details is already exists");
				}
			} catch (PersistanceException e) {
				throw new ValidationException(e.getMessage());
			}
		}
	
//	public

	public static void validateDefault(int addressId) throws ValidationException {
		if (addressId <= 0) {
			throw new ValidationException("Invalid address Id");
		}
		DeliveryAddressDAO deliveryAddressDAO = null;
		try {
			deliveryAddressDAO = new DeliveryAddressDAO();
			if((deliveryAddressDAO.findById(addressId)) == null) {
				throw new ValidationException("This address is null");
			}
		} catch (PersistanceException e) {
			throw new ValidationException(e.getMessage());
		}
	}
}
