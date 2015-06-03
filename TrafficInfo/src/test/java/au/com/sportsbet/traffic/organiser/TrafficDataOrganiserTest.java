package au.com.sportsbet.traffic.organiser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrafficDataOrganiserTest {

	@Test
	public void testIsNewRecordSameString() {
		String time = "12345";
		String previous = time;
		assertTrue(TrafficDataOrganiser.isNewRecord(time, previous));
	}
	
	@Test
	public void testIsNewRecordCurrentGreater() {
		String time = "123456";
		String previous = "12345";
		assertTrue(TrafficDataOrganiser.isNewRecord(time, previous));
	}
}
