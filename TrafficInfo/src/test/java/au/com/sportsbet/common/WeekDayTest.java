package au.com.sportsbet.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WeekDayTest {

	@Test
	public void testFromCode() {
		assertEquals(WeekDay.Monday, WeekDay.fromCode(1));
		assertEquals(WeekDay.Tuesday, WeekDay.fromCode(2));
		assertEquals(WeekDay.Wednesday, WeekDay.fromCode(3));
		assertEquals(WeekDay.Thursday, WeekDay.fromCode(4));
		assertEquals(WeekDay.Friday, WeekDay.fromCode(5));
	}
	
	@Test
	public void testFromCodeFail() {
		try {
			WeekDay.fromCode(0);
		}
		catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Unknown weekday code: 0", message);
		}
	}
}
