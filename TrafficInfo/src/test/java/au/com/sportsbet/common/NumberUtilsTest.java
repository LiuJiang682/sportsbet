package au.com.sportsbet.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void testConvertToSecond() {
		String time = "1234";
		assertEquals(new Long(1), NumberUtils.convertToSecond(time));
		
		time = "123";
		assertEquals(new Long(0), NumberUtils.convertToSecond(time));
		
		time = "12";
		assertEquals(new Long(0), NumberUtils.convertToSecond(time));
		
		time = "12345";
		assertEquals(new Long(12), NumberUtils.convertToSecond(time));
	}
	
	@Test
	public void testIsNumbers() {
		assertFalse(NumberUtils.isInteger(null));
		assertFalse(NumberUtils.isInteger(""));
		assertFalse(NumberUtils.isInteger("ab"));
		assertTrue(NumberUtils.isInteger("1"));
		assertTrue(NumberUtils.isInteger("23"));
		assertFalse(NumberUtils.isInteger("12#"));
		assertFalse(NumberUtils.isInteger("12.3"));
		assertFalse(NumberUtils.isInteger("^12$"));
		assertFalse(NumberUtils.isInteger("0.003"));
		assertFalse(NumberUtils.isInteger("3 "));
		assertFalse(NumberUtils.isInteger(" 3"));
	}
	
	@Test
	public void testConvertToSecondNullString() {
		try {
			NumberUtils.convertToSecond(null);
		} 
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Illegal value: null", message);
		}
	}
	
	@Test
	public void testConvertToSecondEmptyString() {
		try {
			NumberUtils.convertToSecond("");
		} 
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Illegal value: ", message);
		}
	}
	
	@Test
	public void testConvertToSecondRubbishString() {
		try {
			NumberUtils.convertToSecond("^12$");
		} 
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Illegal value: ^12$", message);
		}
	}
	
	@Test
	public void testIsEven() {
		assertTrue(NumberUtils.isEven(new Integer(2)));
		assertFalse(NumberUtils.isEven(new Integer(1)));
		assertFalse(NumberUtils.isEven(new Integer(0)));
	}
}
