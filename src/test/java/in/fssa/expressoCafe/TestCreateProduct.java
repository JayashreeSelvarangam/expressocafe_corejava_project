package in.fssa.expressoCafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.model.SizeEnum;
import in.fssa.expressoCafe.service.ProductService;

public class TestCreateProduct {

	@Test
	public void testCreateProductWithValidData() {

		Product prod = new Product();
		// ProductService productService = new ProductService();

		Map<SizeEnum, Double> priceMap = new TreeMap<>();

		priceMap.put(SizeEnum.LARGE, 20.0);
		priceMap.put(SizeEnum.MEDIUM, 15.0);
		priceMap.put(SizeEnum.SMALL, 10.0);
		System.out.print(priceMap);

		Product product3 = new Product();

		product3.setName("Cappucino");
		product3.setDescription("Feel refreshed");
		product3.setPriceMap(priceMap);
		product3.setCategoryId(10);
		ProductService prodService = new ProductService();
		assertDoesNotThrow(() -> {
			prodService.createProduct(product3);
		});

	}

}
