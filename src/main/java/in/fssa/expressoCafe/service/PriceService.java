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
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.util.ConnectionUtil;
import in.fssa.expressoCafe.util.IntUtil;
import in.fssa.expressoCafe.validator.ProductValidator;
import in.fssa.expressoCafe.validator.SizeValidator;

public class PriceService {

	public void createPrice(Price price) throws ServiceException, ValidationException {
		try {

			IntUtil.priceCheck(price.getPrice(), "Price"); // Validate the price before creating

			PriceDAO priceDao = new PriceDAO();
			priceDao.createProductPrices(price);

			System.out.println("Price created successfully");
		} catch (PersistanceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Price> getHistoryOfProuct(int productId) throws ServiceException {
		List<Price> priceList = null;
		try {
			ProductDAO prod = new ProductDAO();
			IntUtil.rejectIfInvalidInt(productId);
			ProductValidator.isProductIdValid(productId);
			PriceDAO price = new PriceDAO();
			priceList = price.getAllPriceHistory(productId);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return priceList;
	}

	public List<Price> getHistoryOfProuctWithUniqueSize(int productId, int sizeId) throws ServiceException {
		List<Price> priceList = null;
		try {
			ProductDAO prod = new ProductDAO();
			SizeDAO sizeDAO = new SizeDAO();
			IntUtil.rejectIfInvalidInt(productId);
			IntUtil.rejectIfInvalidInt(sizeId);
			// check product id already exists
			ProductValidator.isProductIdValid(productId);

			// check size_id already exists
			SizeValidator.isSizeIdValid(sizeId);
			PriceDAO price = new PriceDAO();

			priceList = price.getAllPriceHistoryWithSizeID(productId, sizeId);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}
		return priceList;
	}

	public Product updatePrice(int productId, int size_id, double price) throws ValidationException, ServiceException {
		Product product = new Product();
		try {

			PriceDAO price1 = new PriceDAO();
			ProductService productser = new ProductService();
			// form validation for product id and category id
			IntUtil.rejectIfInvalidInt(size_id, "SizeId");
			IntUtil.rejectIfInvalidInt(productId, "ProductId");
			IntUtil.priceCheck(price, "Price");
			// check product id already exists
			ProductValidator.isProductIdValid(productId);

			// check size_id already exists
			SizeValidator.isSizeIdValid(size_id);

			// check whether the price needed to be updated is same as its previous price
			int Storedprice = price1.findPriceByProductIdAndSizeId(productId, size_id);

			// check
			// validate whether the price is appropriate
			// I will do it later

			if (Storedprice != price) {
				int priceId = price1.checkIfPriceExistForProductWithUniqueSize(productId, size_id);
				LocalDateTime date = LocalDateTime.now();
				java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(date);
				price1.UpdatePrice(priceId, dateTime);
				price1.SetNewPrice(productId, size_id, price, dateTime);
				// get all prices for the product
			} 
			else {
				throw new ServiceException("Product price should not be same");
			}

			product = productser.findProductWithProductId(productId);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (PersistanceException e) {
			throw new ServiceException(e.getMessage());
		}

		return product;
	}
}
