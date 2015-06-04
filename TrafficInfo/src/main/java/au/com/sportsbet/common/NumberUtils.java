package au.com.sportsbet.common;

import au.com.sportsbet.common.constants.Constants.Numeral;

public class NumberUtils {

	private static final String REGEX_INTEGER = "^\\d+$";

	private NumberUtils() {
	}

	public static Long convertToSecond(String timeString) {
		if (!isInteger(timeString)) {
			throw new IllegalArgumentException("Illegal value: " + timeString);
		}
		
		int len = timeString.length();
		if (Numeral.THREE < len) {
			// filter out the milliseconds
			len = len - Numeral.THREE;
			String secondString = timeString.substring(Numeral.ZERO, len);
			Long second = Long.parseLong(secondString);
			return second;
		}

		return (long) Numeral.ZERO;
	}
	
	public static Boolean isInteger(final String string) {
		if ((null == string) || (string.length() == Numeral.ZERO) || (!string.matches(REGEX_INTEGER))) {
			return false;
		}
		
		return true;
	}
	
	public static Boolean isEven(final Integer num) {
		return ((num % Numeral.TWO) == Numeral.ZERO);
	}
}
