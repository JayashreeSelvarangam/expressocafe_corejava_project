package in.fssa.expressoCafe;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.expressoCafe.exception.ServiceException;
import in.fssa.expressoCafe.exception.ValidationException;
import in.fssa.expressoCafe.service.SizeService;

public class TestSize {
	@Test
	public void testFindSizeByIdWithValidSizeId() {
		SizeService sizeservice = new SizeService();
		
		assertDoesNotThrow(() -> {
			sizeservice.getSizeById(1);
		});
	}
	
	
	@Test
	public void testFindSizeByIdWithSIzeIdZero() {
		SizeService sizeservice = new SizeService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			sizeservice.getSizeById(0);
		});
		String expectedMessage = "Size ID must be non-negative";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertTrue(expectedMessage.equals(actualMessage));
	}
	

	@Test
	public void testFindSizeByIdWithNegativeSizeId() {
		SizeService sizeservice = new SizeService();
		
		Exception exception = assertThrows(ValidationException.class, () ->{
			sizeservice.getSizeById(-1);
		});
		String expectedMessage = "Size ID must be non-negative";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
	
	@Test
	public void testFindSizeByIdWithInvalidSizeId() {
		SizeService sizeservice = new SizeService();
		
		Exception exception = assertThrows(ServiceException.class, () ->{
			sizeservice.getSizeById(5);
		});
		String expectedMessage = "size id does not exist";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);
		assertTrue(expectedMessage.equals(actualMessage));
	}
}
