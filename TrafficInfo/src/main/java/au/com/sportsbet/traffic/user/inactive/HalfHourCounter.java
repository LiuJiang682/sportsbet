package au.com.sportsbet.traffic.user.inactive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.sportsbet.common.NumberUtils;
import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class HalfHourCounter extends BaseCounter {

	private static final int FOURTY_EIGHT = 48;

	private List<TrafficRecord> records;
	private String direction;

	public HalfHourCounter(List<TrafficRecord> records, final String direction) {
		this.records = records;
		this.direction = direction;
	}

	@Override
	public void countAndDisplay() {
		Map<String, Integer> mondayMap = initMap();
		Map<String, Integer> tuesdayMap = initMap();
		Map<String, Integer> wednesdayMap = initMap();
		Map<String, Integer> thursdayMap = initMap();
		Map<String, Integer> fridayMap = initMap();

		for (TrafficRecord record : records) {
			int hour = record.getHour();
			String label = String.format(Strings.TWO_DIGITS, hour);
			int minuts = record.getMinuts();
			if (minuts < Numeral.THIRTY) {
				label = label + Strings.LABEL_ZERO;
			} else {
				label = label + Strings.LABEL_THIRTY;
			}

			switch (record.getDay()) {
			case Monday:
				addCount(mondayMap, label);
				break;
			case Tuesday:
				addCount(tuesdayMap, label);
				break;
			case Wednesday:
				addCount(wednesdayMap, label);
				break;
			case Thursday:
				addCount(thursdayMap, label);
				break;
			case Friday:
				addCount(fridayMap, label);
			}
		}

		displayResult(mondayMap, tuesdayMap, wednesdayMap, thursdayMap,
				fridayMap, this.direction);
	}

	static void displayResult(Map<String, Integer> mondayMap,
			Map<String, Integer> tuesdayMap, Map<String, Integer> wednesdayMap,
			Map<String, Integer> thursdayMap, Map<String, Integer> fridayMap, final String direction) {
		System.out
				.println(Strings.NL
						+ Strings.NL
						+ "====================== Half Hours Result for direction " + direction + " ==============================="
						+ Strings.NL);
		printWeekDayTitle();

		StringBuilder buf = new StringBuilder(buildData(Strings.LABEL_ZERO, Strings.LABEL_ZERO, mondayMap, tuesdayMap, wednesdayMap, thursdayMap, fridayMap));
		int hour = Numeral.ZERO;
		for (int i = Numeral.ONE; i < FOURTY_EIGHT; i++) {
			String label = String.format(Strings.TWO_DIGITS, hour);
			if (NumberUtils.isEven(i)) {
				buf.append(buildData(label, Strings.LABEL_ZERO, mondayMap, tuesdayMap, wednesdayMap, thursdayMap, fridayMap));
			} else {
				buf.append(buildData(label, Strings.LABEL_THIRTY, mondayMap, tuesdayMap, wednesdayMap, thursdayMap, fridayMap));
				++hour;
			}
		}
		
		System.out.println(buf.toString());
	}
	
	public static Map<String, Integer> initMap() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put(Strings.LABEL_ZERO + Strings.LABEL_ZERO, new Integer(Numeral.ZERO));
		int hour = Numeral.ZERO;
		for (int i = Numeral.ONE; i < FOURTY_EIGHT; i++) {
			String label = String.format(Strings.TWO_DIGITS, hour);
			if (NumberUtils.isEven(i)) {
				map.put(label + Strings.LABEL_ZERO, new Integer(Numeral.ZERO));
			} else {
				map.put(label + Strings.LABEL_THIRTY, new Integer(Numeral.ZERO));
				++hour;
			}
		}
		return map;
	}

}
