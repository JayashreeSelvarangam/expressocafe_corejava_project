package in.fssa.expressoCafe.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import in.fssa.expressoCafe.dao.PriceDAO;
import in.fssa.expressoCafe.dao.ProductDAO;
import in.fssa.expressoCafe.dao.SizeDAO;
import in.fssa.expressoCafe.exception.PersistanceException;
import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.util.ConnectionUtil;
import in.fssa.expressoCafe.util.IntUtil;

public class PriceService {

	public List<Price> getHistoryOfProuct(int productId) throws ServiceException {
		List<Price> priceList = null;
		try {
			ProductDAO prod = new ProductDAO();
			prod.doesProductExist(productId);
			PriceDAO price = new PriceDAO();
			priceList = price.getAllPriceHistory(productId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return priceList;
	}

	public List<Price> getHistoryOfProuctWithUniqueSize(int productId, int sizeId) throws ServiceException {
		List<Price> priceList = null;
		try {
			ProductDAO prod = new ProductDAO();
			prod.doesProductExist(productId);
			PriceDAO price = new PriceDAO();
			priceList = price.getAllPriceHistoryWithSizeID(productId, sizeId);
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return priceList;
	}

	
	
	 

	// DOUBT
	public void updatePrice(int productId, int size_id, int price) throws ValidationException, ServiceException {
		
		// product exists?
		// size exists?
		// current price exists?
		// current price DAO? => price_id
		// update product price => (price_id, newPrice)
		// validator -> current product price id
		
		try {
			PriceDAO price1 = new PriceDAO();
			// form validation for product id and category id
			IntUtil.rejectIfInvalidInt(size_id,"CategoryId");
			IntUtil.rejectIfInvalidInt(productId,"productId");
			
			// check product id  already exists
			ProductDAO productDAO = new ProductDAO();
			productDAO.doesProductExist(productId);
			
			// check size_id already exists
			SizeDAO size = new SizeDAO();
			size.doesSizeIdExists(size_id);
			
			//check whether the price needed to be updated is same as its previous price 
			int Storedprice = price1.findPriceByProductIdAndSizeId(productId, size_id);
			
			// check
			// validate whether the price is appropriate 
			// I will do it later
			
			if(Storedprice == price) {
			int priceId = price1.checkIfPriceExistForProductWithUniqueSize(productId, size_id);
			LocalDateTime date = LocalDateTime.now();
			java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(date);

			price1.UpdatePrice(priceId, dateTime);
			price1.SetNewPrice(productId, size_id, price, dateTime);
			}
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
	}
}
