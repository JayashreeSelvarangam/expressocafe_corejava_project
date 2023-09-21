package in.fssa.expressocafe.validator;

import in.fssa.expressocafe.DAO.DeliveryAddressDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.DeliveryAddresses;
import in.fssa.expressocafe.util.StringUtil;

//public class AddressValidator {
//	public static void validateCreate(DeliveryAddresses newAddress) throws ValidationException {
//
//		if (newAddress == null) {
//			throw new ValidationException("Invalid Address input");
//		}
//	
//		StringUtil.rejectIfInvalidString(newAddress.getStreetName(), "Street Name");
//		StringUtil.rejectIfInvalidString(newAddress.getAddressTitle(), "Address Title");
//
//		StringUtil.rejectIfInvalidString(newAddress.getUserEmail(), "User Email");
//		StringUtil.rejectIfInvalidString(newAddress.getLocation(), "Location");
//
//		if (newAddress.getCity() == null || "".equals(newAddress.getCity().trim())) {
//			throw new ValidationException("Please Select Your Delivery City");
//		}
//
//		if (newAddress.getPersonName() == null || "".equals(newAddress.getPersonName().trim())) {
//			throw new ValidationException("Please Enter Your Name in Specific field");
//		}
//
//		if (newAddress.getState() == null || "".equals(newAddress.getState().trim())) {
//			throw new ValidationException("Please Select Your Delivery State");
//		}
//
//		if (!(newAddress.getMobileNumber() >= 6000000001l && newAddress.getMobileNumber() <= 9999999999l)) {
//
//			throw new ValidationException(
//					"Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.");
//		}
//
//		if (!(newAddress.getPincode() >= 600001 && newAddress.getPincode() <= 643253)) {
//
//			throw new ValidationException("Invalid Pincode. This pincode is not available for delivery.");
//		}
//
//		try {
//			DeliveryAddressDAO deliveryAddressDAO = new DeliveryAddressDAO();
//			boolean isAddressExists = deliveryAddressDAO.isAddressDetailsExists(newAddress);
//			if (isAddressExists) {
//				throw new ValidationException("This address details is already exists");
//			}
//		} catch (PersistanceException e) {
//			throw new ValidationException(e.getMessage());
//		}
//
//	}
//
//	public static void validateEmail(String userEmail) throws ValidationException {
//
//		if (userEmail == null || "".equals(userEmail.trim())) {
//			throw new ValidationException("Invalid User email. Email cannot be null or empty");
//		}
//
//	}
//
//	public static void validateId(int addressId) throws ValidationException {
//		if (addressId <= 0) {
//			throw new ValidationException("Invalid address Id");
//		}
//
//		DeliveryAddressDAO deliveryAddressDAO = null;
//		try {
//			deliveryAddressDAO = new DeliveryAddressDAO();
//			deliveryAddressDAO.findById(addressId);
//		} catch (PersistanceException e) {
//			throw new ValidationException(e.getMessage());
//		}
//
//	}
//
//	public static void validateUpdate(int addressId, DeliveryAddresses newAddress) throws ValidationException {
//		if (addressId <= 0) {
//			throw new ValidationException("Invalid address Id");
//		}
//		if (newAddress == null) {
//			throw new ValidationException("Invalid Address input");
//		}
//		StringUtil.rejectIfInvalidString(newAddress.getStreetName(), "Street Name");
//		StringUtil.rejectIfInvalidString(newAddress.getAddressTitle(), "Address Title");
//
//		StringUtil.rejectIfInvalidString(newAddress.getUserEmail(), "User Email");
//		StringUtil.rejectIfInvalidString(newAddress.getLocation(), "Location");
//
//		if (newAddress.getCity() == null || "".equals(newAddress.getCity().trim())) {
//			throw new ValidationException("Please Select Your Delivery City");
//		}
//
//		if (newAddress.getPersonName() == null || "".equals(newAddress.getPersonName().trim())) {
//			throw new ValidationException("Please Enter Your Name in Specific field");
//		}
//
//		if (newAddress.getState() == null || "".equals(newAddress.getState().trim())) {
//			throw new ValidationException("Please Select Your Delivery State");
//		}
//
//		if (!(newAddress.getMobileNumber() >= 6000000001l && newAddress.getMobileNumber() <= 9999999999l)) {
//
//			throw new ValidationException(
//					"Invalid phone number. The phone number must be in the range of 6000000001 to 9999999999.");
//		}
//
//		if (!(newAddress.getPincode() >= 600001 && newAddress.getPincode() <= 643253)) {
//
//			throw new ValidationException("Invalid Pincode. This pincode is not available for delivery.");
//		}
//		DeliveryAddressDAO deliveryAddressDAO = null;
//		try {
//			deliveryAddressDAO = new DeliveryAddressDAO();
//			if ((deliveryAddressDAO.findById(addressId)) == null) {
//				throw new ValidationException("This address Id is not available in address list");
//			}
//		} catch (PersistanceException e) {
//			throw new ValidationException(e.getMessage());
//		}
//
//		try {
//			deliveryAddressDAO = new DeliveryAddressDAO();
//			boolean isAddressExists = deliveryAddressDAO.isAddressDetailsExists(newAddress);
//			if (isAddressExists) {
//				throw new ValidationException("This address details is already exists");
//			}
//		} catch (PersistanceException e) {
//			throw new ValidationException(e.getMessage());
//		}
//
//	}
//
//	public static void validateDelete(int addressId) throws ValidationException {
//		
//		if (addressId <= 0) {
//			throw new ValidationException("Invalid address Id");
//		}
//		DeliveryAddressDAO deliveryAddressDAO = null;
//		try {
//			deliveryAddressDAO = new DeliveryAddressDAO();
//			if ((deliveryAddressDAO.findById(addressId)) == null) {
//				throw new ValidationException("This address Id is not available in address list");
//			}
//		} catch (PersistanceException e) {
//			throw new ValidationException(e.getMessage());
//		}
//	}
//
//}
