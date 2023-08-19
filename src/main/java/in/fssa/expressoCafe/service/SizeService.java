package in.fssa.expressoCafe.service;

import java.util.List;

import in.fssa.expressoCafe.dao.SizeDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Size;
import in.fssa.expressoCafe.util.IntUtil;

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
			IntUtil.rejectIfInvalidInt(sizeId, "sizeId");

			SizeDAO sizeDAO = new SizeDAO();
			sizeDAO.doesSizeIdExists(sizeId);
			size = sizeDAO.getSizeById(sizeId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

		return size;
	}

}
