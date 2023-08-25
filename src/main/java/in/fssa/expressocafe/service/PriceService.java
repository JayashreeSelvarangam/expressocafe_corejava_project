package in.fssa.expressocafe.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import in.fssa.expressocafe.dao.PriceDAO;
import in.fssa.expressocafe.dao.ProductDAO;
import in.fssa.expressocafe.dao.SizeDAO;
import in.fssa.expressocafe.exception.PersistanceException;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.model.Price;
import in.fssa.expressocafe.model.Product;
import in.fssa.expressocafe.util.ConnectionUtil;
import in.fssa.expressocafe.util.IntUtil;
import in.fssa.expressocafe.validator.PriceValidator;
import in.fssa.expressocafe.validator.ProductValidator;
import in.fssa.expressocafe.validator.SizeValidator;

public class PriceService {
/**
 * 
 * @param price
 * @throws ServiceException
 * @throws ValidationException
 */
	public void createPrice(Price price) throws ServiceException, ValidationException {
		try {

			IntUtil.priceCheck(price.getPrice(), "Price");
			PriceDAO priceDao = new PriceDAO();
			priceDao.createProductPrices(price);
			System.out.println("Price created successfully");
			
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
/**
 * 
 * @param productId
 * @return
 * @throws ServiceException
 */
	public List<Price> getHistoryOfProuct(int productId) throws ServiceException , ValidationException{
		List<Price> priceList = null;
		try {
			ProductDAO prod = new ProductDAO();
			ProductValidator.rejectIfInvalidInt(productId,"ProductId");
			ProductValidator.isProductIdValid(productId);
			
			PriceDAO price = new PriceDAO();
			priceList = price.getAllPriceHistory(productId);
		}  catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return priceList;
	}
/**
 * 
 * @param productId
 * @param sizeId
 * @return
 * @throws ServiceException
 */
	public List<Price> getHistoryOfProuctWithUniqueSize(int productId, int sizeId) throws ServiceException ,ValidationException {
		List<Price> priceList = null;
		try {
			ProductDAO prod = new ProductDAO();
			SizeDAO sizeDAO = new SizeDAO();
			ProductValidator.rejectIfInvalidInt(productId,"ProductId");
			SizeValidator.rejectIfInvalidInt(sizeId,"SizeId");
			
			// check product id already exists
			ProductValidator.isProductIdValid(productId);
			// check size_id already exists
			SizeValidator.isSizeIdValid(sizeId);
			
			PriceDAO price = new PriceDAO();
			priceList = price.getAllPriceHistoryWithSizeID(productId, sizeId);
			
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return priceList;
	}
/**
 * 
 * @param productId
 * @param size_id
 * @param price
 * @return
 * @throws ValidationException
 * @throws ServiceException
 */
	public Product updatePrice(int productId, int sizeId, double price) throws ValidationException, ServiceException {
		Product product = new Product();
		try {
			
			ProductDAO ProductDAO= new ProductDAO();
			SizeDAO sizeDAO = new SizeDAO();
			PriceDAO price1 = new PriceDAO();
			ProductService productser = new ProductService();
			
			// form validation for product id and category id
			ProductValidator.rejectIfInvalidInt(productId, "ProductId");
			SizeValidator.rejectIfInvalidInt(sizeId, "SizeId");
			
			PriceValidator.priceCheck(price, "Price");
			
			// check product id already exists
			ProductValidator.isProductIdValid(productId);
			
			// check size_id already exists
			SizeValidator.isSizeIdValid(sizeId);
			
			// check whether the price needed to be updated is same as its previous price
			int Storedprice = price1.findPriceByProductIdAndSizeId(productId, sizeId);

			if (Storedprice != price) {
				int priceId = price1.checkIfPriceExistForProductWithUniqueSize(productId, sizeId);
				LocalDateTime date = LocalDateTime.now();
				java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(date);
				price1.updatePrice(priceId, dateTime);
				price1.setNewPrice(productId, sizeId, price, dateTime);
				// get all prices for the product
			} 
			else {
				throw new ServiceException("Product price should not be same");
			}
			product = productser.findProductWithProductId(productId);
		}  catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return product;
	}
}
