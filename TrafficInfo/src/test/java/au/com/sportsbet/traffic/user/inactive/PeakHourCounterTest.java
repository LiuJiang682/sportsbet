package au.com.sportsbet.traffic.user.inactive;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import au.com.sportsbet.common.constants.Constants.Strings;

public class PeakHourCounterTest {

	@Test
	public void testFindPeakHour() {
		Integer[] hours = {23, 30, 60, 68, 55, 200, 3008, 2007, 1005, 2007, 1005, 2007, 1005, 2007, 1005, 2822, 3198, 2811, 2317,2007, 1005, 1007, 896, 905 };
		int peakHour = PeakTimeCounter.findPeakHour(hours);
		assertEquals(16, peakHour);
	}
	
	@Test
	public void testDisplayResult() {
		PeakTimeCounter.displayResult(16, 7, 8, 7, 15, Strings.DIRECTION_A);
	}
}
