package au.com.sportsbet.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeUtilsTest {

	@Test
	public void testConvertSecondsToHMmSs() {
		assertEquals("00:00:01", TimeUtils.convertSecondsToHMmSs(1));
		assertEquals("00:00:38", TimeUtils.convertSecondsToHMmSs(38));
		assertEquals("00:01:27", TimeUtils.convertSecondsToHMmSs(87));
		assertEquals("00:11:27", TimeUtils.convertSecondsToHMmSs(687));
		assertEquals("01:00:27", TimeUtils.convertSecondsToHMmSs(3627));
		assertEquals("01:01:01", TimeUtils.convertSecondsToHMmSs(3661));
		assertEquals("13:01:01", TimeUtils.convertSecondsToHMmSs(46861));
	}
}
