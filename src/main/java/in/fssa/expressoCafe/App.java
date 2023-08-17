package in.fssa.expressoCafe;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import in.fssa.expressoCafe.model.Price;
import in.fssa.expressoCafe.service.PriceService;
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

	public static void main(String[] args) throws Exception {

//		UserService userService1 = new UserService(new UserDAO());
//
//		User user1 = new User();
//		user1.setName("J");
//		user1.setEmail("js0637468@gmail.com");
//		user1.setPhoneNumber(9494387297l);
//		user1.setPassword("99937765262");
//		user1.setAddress("jhds");
//
//		userService1.createUser(user1);

//		CategoryService category = new CategoryService();
//		List<Category> cat = new ArrayList<>();
//		cat = category.getAllCategories();
//		System.out.print(cat);		

//		   Map<SizeEnum, Double> priceMap = new TreeMap<>();
//		  
//	        
//	        priceMap.put(SizeEnum.LARGE, 20.0);
//	        priceMap.put(SizeEnum.MEDIUM, 15.0);
//	        priceMap.put(SizeEnum.SMALL, 10.0);
//	        System.out.print(priceMap);
//		
//	        Product product3 = new Product();
//	        //product3.getDescription();
//	        product3.setName("Coffee");
//	        product3.setDescription("Coffee Description");
//	        product3.setPriceMap(priceMap);
//	        product3.setCategoryId(10);
//	        ProductService prodService = new ProductService();
//	        prodService.createProduct(product3);

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
		Product product =  new Product();
		PriceService price = new PriceService();
		product = price.updatePrice(10, 3,231.99);
		if(product!=null) {
		System.out.println("Product ID: " + product.getProduct_id());
		System.out.println("Name: " + product.getName());
		System.out.println("Description: " + product.getDescription());
		   List<Price> updatedPrices = product.getPriceList();
	        for (Price price1 : updatedPrices) {
	            System.out.println("Price ID: " + price1.getPriceId());
	            System.out.println("Price: $" + price1.getPrice());
	            System.out.println("Size: " + price1.getSize());
	            System.out.println("------------------------------");
	        }
		}

//		  	PriceService priceDAO = new PriceService();
//	        int productId =  11; // Replace with the actual product ID
//	        int sizeId = 2;
//	        List<Price> priceHistory = priceDAO.getHistoryOfProuctWithUniqueSize(productId,sizeId);
//
//	        for (Price priceEntry : priceHistory) {
//	            System.out.println("Price ID: " + priceEntry.getPriceId());
//	            System.out.println("Product ID: " + priceEntry.getProduct().getProduct_id());
//	            System.out.println("Size ID: " + priceEntry.getSize());
//	            System.out.println("Price: " + priceEntry.getPrice());
//	            System.out.println("Start Date: " + priceEntry.getStartDate());
//	            System.out.println("End Date: " + priceEntry.getEndDate());
//	            System.out.println("-----------");
//	        }	

		// create product
//        ProductService productService = new ProductService();
//        
//        // Create a Product instance
//        Product product = new Product();
//        product.setName("hello");
//        product.setDescription("Delicious coffee beverage");
//        
//        Category category = new Category();
//        category.setCategoryId(11);
//
//        List<Price> priceList = new ArrayList<>();
//
//        // Create Price instances for different sizes
//        Price priceSmall = new Price();
//        priceSmall.setPrice(3.99);
//        priceSmall.setSize(SizeEnum.SMALL);
//
//        Price priceMedium = new Price();
//        priceMedium.setPrice(4.99);
//        priceMedium.setSize(SizeEnum.MEDIUM);
//
//        Price priceLarge = new Price();
//        priceLarge.setPrice(5.99);
//        priceLarge.setSize(SizeEnum.LARGE);
//        
//        priceList.add(priceLarge);
//        priceList.add(priceMedium);
//        priceList.add(priceSmall);
//
//        // Add Price instances to the Product's priceList
//        product.setPriceList(priceList);
//        product.setCategory(category);
//        productService.createProduct(product); 

		// update [roduct
//        ProductService productService1 = new ProductService();
//        
//        // Create a Product instance
//        Product product1 = new Product();
//        product1.setName("Cofee");
//        product1.setDescription("jnfjwefjk");
//        product1.setProduct_id(26);
//        Category category1 = new Category();
//        category1.setCategoryId(11);
//
//       
		
//        // Add Price instances to the Product's priceList
//        
//        product1.setCategory(category1);
//        productService1.updateProduct(product1); 

		// product delete

//		ProductService productService1 = new ProductService();
//      productService1.deleteProduct(294454);

		// get all products with prices
//        ProductService productService = new ProductService();
//        List<Product> products = productService.getAllproductswithCategoryId(11);
//
//        for (Product product : products) {
//            System.out.println("Product Name: " + product.getName());
//            System.out.println("Product Description: " + product.getDescription());
//            System.out.println("Product id: " + product.getProduct_id());
//            System.out.println("Product category: " + product.getCategory().getCategoryId());
//            List<Price> prices = product.getPriceList();
//         //   System.out.println();
//            for (Price price : prices) {
//                System.out.println("Price ID: " + price.getPriceId());
//                System.out.println("Price Value: " + price.getPrice());
//                System.out.println("Price Size: " + price.getSize());
//                System.out.println("----------");
//            }
//
//            System.out.println("=========================");
//        }
		// get product with product id
//		ProductService productService = new ProductService();
//		Product product = productService.findProductWithProductId(90);
//		if (product != null) {
//			System.out.println("Product ID: " + product.getProduct_id());
//			System.out.println("Name: " + product.getName());
//			System.out.println("Description: " + product.getDescription());
//
//			Category category = product.getCategory();
//
//			System.out.println("Category ID: " + category.getCategoryId());
//
//			List<Price> priceList = product.getPriceList();
//			if (priceList != null && !priceList.isEmpty()) {
//				System.out.println("Price List:");
//				for (Price price : priceList) {
//					System.out.println("Price ID: " + price.getPriceId());
//					System.out.println("Value: " + price.getPrice());
//					System.out.println("Size: " + price.getSize());
//					System.out.println("---");
//				}
//			}
//	}

		}
	}

