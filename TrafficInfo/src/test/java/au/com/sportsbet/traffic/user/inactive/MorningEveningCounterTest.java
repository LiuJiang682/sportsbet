package au.com.sportsbet.traffic.user.inactive;

import org.junit.Test;

import au.com.sportsbet.common.constants.Constants.Strings;

public class MorningEveningCounterTest {

	@Test
	public void testPrintMorningEveningCount() {
		MorningEveningCounter.printMorningEveningCount(3000, 2889, 2228, 3089, 4009, 3023, 2811, 1236, 3899, 5677, Strings.DIRECTION_A);
	}
}
