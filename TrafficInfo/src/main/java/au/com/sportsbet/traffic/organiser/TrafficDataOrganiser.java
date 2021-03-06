package au.com.sportsbet.traffic.organiser;

import java.util.List;
import java.util.logging.Logger;

import au.com.sportsbet.common.NumberUtils;
import au.com.sportsbet.common.TimeUtils;
import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.data.base.TrafficInfoBase;
import au.com.sportsbet.traffic.data.base.TrafficInfoBaseImpl;
import au.com.sportsbet.traffic.dto.TrafficRecord;
import au.com.sportsbet.traffic.dto.TrafficRecordImpl;

public class TrafficDataOrganiser {

	private static final Logger LOGGER = Logger
			.getLogger(TrafficDataOrganiser.class.getName());

	private static final Long DEFAULT_CAR_SPEED = new Long(60000);
	public static final Long HOUR_IN_MILLISECOND = new Long(3600000);
	private static final Double DEFAULT_SPEED_IN_MILLISECOND = ((double) DEFAULT_CAR_SPEED)
			/ HOUR_IN_MILLISECOND;
	private static final Long DEFAULT_2ND_AXLE_TIME = new Double(
			2.5d / DEFAULT_SPEED_IN_MILLISECOND).longValue();
	private static final Long DEFAULT_DELTA = new Long(100);
	private static final String DELTA_STR = System.getProperty(Strings.SPEED_DELTA);
	private static final Long CURRENT_DELTA = NumberUtils.isInteger(DELTA_STR) ? new Integer(DELTA_STR) : DEFAULT_DELTA;

	private TrafficDataOrganiser() {
	}

	public static TrafficInfoBase doDataOrganise(List<String> trafficDetails) {
		TrafficInfoBase trafficInfoBase = new TrafficInfoBaseImpl();
		String previousTimeStr = null;

		// Started on Monday.
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
				// What happen to the situation the car's
				// first axle hit sensor on 23:59:59.995,
				// the second axle hit the sensor on 00:00:00.095?
				day++;
			}

			/**
			 * Check the time gap regardless the direction of the car taken due
			 * to the requirement stated is possible the sensor at both
			 * directions recorded the traffic.
			 */
			if (isNewRecord(timeStr, previousTimeStr)) {
				TrafficRecord record = new TrafficRecordImpl(direction, day,
						hhmmssStr, details);

				trafficInfoBase.addTrafficRecord(direction, record);

				// Update the previous time string.
				previousTimeStr = timeStr;
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
			if (current < previous) {
				if (isWithDelta(previous)) {
					// Different day. Handle the first axle hit sensor
					// on 23:59:59.995,
					// the second axle hit the sensor on 00:00:00.095? case.
					return false;
				} else {
					return true;
				}
			} else {
				// Same day
				Long diff = current - previous;
				/**
				 * Consider the car's speed vary, thus, add a delta for the
				 * car's 2nd axle. This delta set to 100 millisecond for
				 * default. User can set it via -D properties. It could further
				 * externalize it into a properties file.
				 */
				if (diff <= (DEFAULT_2ND_AXLE_TIME + CURRENT_DELTA)) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	// Different day. Handle the first axle hit sensor
	// on 23:59:59.995,
	// the second axle hit the sensor on 00:00:00.095? case.
	private static boolean isWithDelta(Long previous) {
		Long diff = Numeral.DAY_IN_MILLISECOND - previous;
		if (diff < CURRENT_DELTA)
			return true;

		return false;
	}

}
