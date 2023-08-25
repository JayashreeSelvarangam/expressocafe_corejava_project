package in.fssa.expressocafe.service;

import java.util.List;

import in.fssa.expressocafe.dao.SizeDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Size;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.validator.SizeValidator;

public class SizeService {
	/**
	 * 
	 * @return
	 */
	public List<Size> getAllSizes() {
		SizeDAO sizeDAO = new SizeDAO();
		return sizeDAO.getAllSizes();
	}

	/**
	 * 
	 * @param sizeId
	 * @return
	 * @throws PersistanceException
	 * @throws ServiceException
	 * @throws ValidationException
	 */
	public Size getSizeById(int sizeId) throws ServiceException, ValidationException {
		Size size = new Size();
		try {
			
			SizeValidator.validateSizeId(sizeId);
			SizeDAO sizeDAO = new SizeDAO();
			sizeDAO.doesSizeIdExists(sizeId);
			size = sizeDAO.getSizeById(sizeId);
			
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

		return size;
	}

}
