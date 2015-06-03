package au.com.sportsbet.traffic.organiser;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import au.com.sportsbet.common.NumberUtils;
import au.com.sportsbet.common.TimeUtils;
import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.traffic.data.base.TrafficInfoBase;
import au.com.sportsbet.traffic.data.base.TrafficInfoBaseImpl;
import au.com.sportsbet.traffic.dto.TrafficRecord;
import au.com.sportsbet.traffic.dto.TrafficRecordImpl;

public class TrafficDataOrganiser {

	private static final Logger LOGGER = Logger.getLogger(TrafficDataOrganiser.class.getName());
	
	private static final String DIRECTION_A = "A";
	
	private TrafficDataOrganiser() {
	}
	
	public static TrafficInfoBase doDataOrganise(List<String> trafficDetails) {
		TrafficInfoBase trafficInfoBase = new TrafficInfoBaseImpl();
		String previousTimeStr = null;
		//Started on Monday.
		int day = Numeral.ONE;
		
		for (String details : trafficDetails) {
			String direction = details.substring(Numeral.ZERO, Numeral.ONE);
			String timeStr = details.substring(Numeral.ONE);
			LOGGER.info("Time string is: " + timeStr);
			Long seconds = NumberUtils.convertToSecond(timeStr);
			String hhmmssStr = TimeUtils.convertSecondsToHMmSs(seconds);
			
			if (previousTimeStr == null) {
				// First time. No question ask.
				previousTimeStr = timeStr;
			} else if (timeStr.length() < previousTimeStr.length()) {
				// Sudden drop of time string means the day
				// changed as stated at requirement.
				day++;
			} 
			
			if (isNewRecord(timeStr, previousTimeStr)) {
				TrafficRecord record = new TrafficRecordImpl(direction, day, hhmmssStr, details);
				if (DIRECTION_A.equalsIgnoreCase(direction)) {
					trafficInfoBase.addDirectionARecord(record);
				} else {
					trafficInfoBase.addDirectionBRecord(record);
				}
			}
		}
		
		return trafficInfoBase;
	}

	static boolean isNewRecord(String timeStr, String previousTimeStr) {
		if (timeStr.equals(previousTimeStr)) {
			// First time.
			return true;
		} else {
			Long current = Long.parseLong(timeStr);
			Long previous = Long.parseLong(previousTimeStr);
//			if ((current - previous) <= Numeral.DEFAULT_2ND_AXLE_TIME) {
			Long diff = current - previous;
			if (diff <= getDefaultTime())  {
			return false;
			}
		}
		
		return true;
	}

	private static Long getDefaultTime() {
		Long s = new Long(60000);
		Long m = new Long(3600000);
		Double d = ((double)s) / m;
		Double r = 2.5 / d;
		return r.longValue();
//		BigDecimal s = new BigDecimal(60000);
//		BigDecimal m = new BigDecimal(3600000);
//		BigDecimal sp = s.divide(m);
//		BigDecimal d = new BigDecimal(2.5);
//		return d.divide(sp).longValue();
	}

//	static String doConverting(String timeStr) {
//		Long seconds = NumberUtils.convertToSecond(timeStr);
//		return TimeUtils.convertSecondsToHMmSs(seconds);
//	}
}
