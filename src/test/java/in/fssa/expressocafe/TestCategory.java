package in.fssa.expressocafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.expressocafe.dao.CategoryDAO;
import in.fssa.expressocafe.exception.ServiceException;
import in.fssa.expressocafe.exception.ValidationException;
import in.fssa.expressocafe.service.CategoryService;

public class TestCategory {
	
	@Test
	public void testFindMenuByIdWithValidCategoryId() {
		CategoryService categoryservice = new CategoryService();
		
		assertDoesNotThrow(() -> {
			categoryservice.getCategoryById(1);
		});
	}
	
	
	@Test
	public void testFindMenuByIdWithCategoryIdZero() {
		CategoryService categoryservice = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			categoryservice.getCategoryById(0);
		});
		String expectedMessage = "Category ID must be non-negative";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	@Test
	public void testFindMenuByIdWithNegativeCategoryId() {
		CategoryService categoryservice = new CategoryService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			categoryservice.getCategoryById(-1);
		});
		String expectedMessage = "Category ID must be non-negative";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindMenuByIdWithInvalidCategoryId() {
		CategoryService categoryservice = new CategoryService();
		
		ValidationException exception = assertThrows(ValidationException.class, () ->{
			categoryservice.getCategoryById(7);
		});
		String expectedMessage = "Category Id doesnot exists in the database";
		String actualMessage = exception.getMessage();

		assertTrue(expectedMessage.equals(actualMessage));
	}
	// isCategoryIdValid
	@Test
	public void testisCategoryIdValid() {
		CategoryService categoryservice = new CategoryService();
		
		ValidationException exception = assertThrows(ValidationException.class, () ->{
			categoryservice.isCategoryIdValid(6);
		});
		String expectedMessage = "Category Id doesnot exists in the database";
		String actualMessage = exception.getMessage();
		
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testisCategoryIdValidforcateid() {
		CategoryService categoryservice = new CategoryService();
		
		ValidationException exception = assertThrows(ValidationException.class, () ->{
			categoryservice.isCategoryIdValid(0);
		});
		String expectedMessage = "Category ID must be non-negative";
		String actualMessage = exception.getMessage();
		System.out.print(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	
	}
	
}
