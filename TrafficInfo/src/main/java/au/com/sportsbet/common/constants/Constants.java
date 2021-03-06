package au.com.sportsbet.common.constants;

import au.com.sportsbet.traffic.organiser.TrafficDataOrganiser;

public interface Constants {

	public interface Strings {
		static final String UTF_8 = "UTF-8";
		public static final String SPEED_DELTA = "speed.delta";
		static final String NL = "\n";
		static final String TAB = "\t";
		static final String TAB_SPACE = "    ";
		static final String DIRECTION_A = "A";
		static final String DIRECTION_B = "B";
		public static final String LABEL_ZERO = "00";
		public static final String TWO_DIGITS = "%02d";
		public static final String LABEL_THIRTY = "30";
	}
	
	public interface Numeral {
		static final int ZERO = 0;
		static final int ONE = 1;
		static final int THREE = 3;
		static final int SIX = 6;
		static final int TWELVE = 12;
		static final int EIGHTEEN = 18;
		static final int TWENTY_ONE = 21;
		static final Integer TWO = 2;
		public static final int THIRTY = 30;
		public static final int TWENTY_FOUR = 24;
		public static final Long DAY_IN_MILLISECOND = TrafficDataOrganiser.HOUR_IN_MILLISECOND * 24;
	}

}
