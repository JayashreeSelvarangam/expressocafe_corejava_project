package in.fssa.expressoCafe;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.service.PriceService ;
import in.fssa.expressoCafe.dao.PriceDAO;
import in.fssa.expressoCafe.dao.UserDAO;
import in.fssa.expressoCafe.model.Category;
import in.fssa.expressoCafe.model.Product;
import in.fssa.expressoCafe.model.SizeEnum;
//import in.fssa.expressoCafe.model.Size.SizeEnum;
//import in.fssa.expressoCafe.model.CategoryEntity;
import in.fssa.expressoCafe.model.User;
import in.fssa.expressoCafe.service.CategoryService;
import in.fssa.expressoCafe.service.ProductService;
import in.fssa.expressoCafe.service.UserService;
import java.util.ArrayList;
public class App {

	private static final SizeEnum SMALL = null;
	private static final SizeEnum MEDIUM = null;
	private static final SizeEnum LARGE = null;

	public static void main(String[] args) throws Exception {

		UserService userService1 = new UserService(new UserDAO());

		User user1 = new User();
		user1.setName("J");
		user1.setEmail("js0637468@gmail.com");
		user1.setPhoneNumber(9494387297l);
		user1.setPassword("99937765262");
		user1.setAddress("jhds");

		userService1.createUser(user1);

		CategoryService category = new CategoryService();
		List<Category> cat = new ArrayList<>();
		cat = category.getAllCategories();
		System.out.print(cat);		
		
		   Map<SizeEnum, Double> priceMap = new TreeMap<>();
		  
	        
	        priceMap.put(SizeEnum.LARGE, 20.0);
	        priceMap.put(SizeEnum.MEDIUM, 15.0);
	        priceMap.put(SizeEnum.SMALL, 10.0);
	        System.out.print(priceMap);
		
	        Product product3 = new Product();
	        //product3.getDescription();
	        product3.setName("Coffee");
	        product3.setDescription("Coffee Description");
	        product3.setPriceMap(priceMap);
	        product3.setCategoryId(10);
	        ProductService prodService = new ProductService();
	        prodService.createProduct(product3);

//			Map<SizeEnum, Double> priceMap1 = new TreeMap<>();
//	        priceMap1.put(SizeEnum.LARGE, 200.0);
//	        priceMap1.put(SizeEnum.MEDIUM, 150.0);
//	        priceMap1.put(SizeEnum.SMALL, 100.0);
//	        System.out.print(priceMap1);
//	        Product product5 = new Product();
//	        //product3.getDescription();
//	        product5.setProduct_id(3);
//	        product5.setName("cappucino");
//	        product5.setDescription("cappucino Description");
//	        product5.setCategoryId(11);

//		ProductService prodService3 = new ProductService();
//		// System.out.print(prodService3.getAllProducts());
//		/// required to view the products
//		List<Product> products = prodService3.getAllproductswithCategoryId(11);
//
//		for (Product product : products) {
//			System.out.println("Product ID: " + product.getProduct_id());
//			System.out.println("Name: " + product.getName());
//			System.out.println("Description: " + product.getDescription());
//
//			Map<SizeEnum, Double> priceMap = product.getPriceMap();
//			for (Map.Entry<SizeEnum, Double> entry : priceMap.entrySet()) {
//				SizeEnum size = entry.getKey();
//				double price = entry.getValue();
//				System.out.println(size + " Price: " + price);
//			}
//
//			System.out.println("Category ID: " + product.getCategoryId());
//			System.out.println("------------------------");
//		}
		
//		ProductService productService = new ProductService();
//		Product product = new Product();
//		product = productService.findProductWithProductId(11);
//		System.out.print(product.toString());
		
//		PriceService price = new PriceService();
//		price.updatePrice(11, 2, 725);
		
		  	PriceService priceDAO = new PriceService();
	        int productId =  11; // Replace with the actual product ID
	        int sizeId = 2;
	        List<Price> priceHistory = priceDAO.getHistoryOfProuctWithUniqueSize(productId,sizeId);

	        for (Price priceEntry : priceHistory) {
	            System.out.println("Price ID: " + priceEntry.getPriceId());
	            System.out.println("Product ID: " + priceEntry.getProductId());
	            System.out.println("Size ID: " + priceEntry.getSizeId());
	            System.out.println("Price: " + priceEntry.getPrice());
	            System.out.println("Start Date: " + priceEntry.getStartDate());
	            System.out.println("End Date: " + priceEntry.getEndDate());
	            System.out.println("-----------");
	        }	
		
	}
}
