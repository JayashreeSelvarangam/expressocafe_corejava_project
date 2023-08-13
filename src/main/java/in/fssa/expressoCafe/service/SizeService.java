package in.fssa.expressoCafe.service;

import java.util.List;

import in.fssa.expressoCafe.dao.SizeDAO;
import in.fssa.expressoCafe.model.Size;

public class SizeService {
	  public List<Size> getAllSizes() {
		  SizeDAO sizeDAO = new SizeDAO();
	        return sizeDAO.getAllSizes();
	    }

	    public Size getSizeById(int sizeId) {
	    	 SizeDAO sizeDAO = new SizeDAO();
	        return sizeDAO.getSizeById(sizeId);
	    }
	
	
}
