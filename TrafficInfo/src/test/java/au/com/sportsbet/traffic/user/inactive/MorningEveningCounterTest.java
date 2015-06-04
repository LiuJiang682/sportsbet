package au.com.sportsbet.traffic.user.inactive;

import org.junit.Test;

public class MorningEveningCounterTest {

	@Test
	public void testPrintMorningEveningCount() {
		new MorningEveningCounter(null).printMorningEveningCount(3000, 2889, 2228, 3089, 4009, 3023, 2811, 1236, 3899, 5677);
	}
}
