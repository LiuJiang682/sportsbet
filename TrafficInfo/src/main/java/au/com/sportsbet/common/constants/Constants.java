package au.com.sportsbet.common.constants;

public interface Constants {

	public interface Strings {
		static final String UTF_8 = "UTF-8";
	}
	
	public interface Numeral {
		static final int ZERO = 0;
		static final int ONE = 1;
		static final int THREE = 3;
		
		/**
		 * The second axle hit the rubber hoses time
		 * is calculated by distant(2.5m) divided by
		 * speed (60000 m divided by 3600000 milliseconds) 
		 */
//		static final long DEFAULT_2ND_AXLE_TIME = new Double(2.5 / (60000 / 3600000)).longValue();
	}

}
