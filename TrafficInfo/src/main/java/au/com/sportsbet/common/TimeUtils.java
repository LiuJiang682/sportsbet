package au.com.sportsbet.common;

public class TimeUtils {

	private TimeUtils() {
	}
	
	public static String convertSecondsToHMmSs(long seconds) {
	    long s = seconds % 60;
	    long m = (seconds / 60) % 60;
	    long h = (seconds / (60 * 60)) % 24;
	    return String.format("%02d:%02d:%02d", h,m,s);
	}
}
