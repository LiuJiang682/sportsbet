package au.com.sportsbet.traffic.user.inactive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class FifteenMinutesCounter extends BaseCounter {

	private static final int FIFTEEN = 15;
	private static final String LABLE_FIFTEEN = "15";
	private static final int NINETY_SIX = 96;
	private static final String LABEL_FORTY_FIVE = "45";

	private List<TrafficRecord> records;

	public FifteenMinutesCounter(List<TrafficRecord> records) {
		this.records = records;
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
			if (minuts < FIFTEEN) {
				label = label + Strings.LABEL_ZERO;
			} else if (minuts < Numeral.THIRTY) {
				label = label + LABLE_FIFTEEN;
			} else if (minuts < 45) {
				label = label + Strings.LABEL_THIRTY;
			} else {
				label = label + LABEL_FORTY_FIVE;
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
				fridayMap);

	}

	private void displayResult(Map<String, Integer> mondayMap,
			Map<String, Integer> tuesdayMap, Map<String, Integer> wednesdayMap,
			Map<String, Integer> thursdayMap, Map<String, Integer> fridayMap) {
		System.out
				.println(Strings.NL
						+ Strings.NL
						+ "====================== Fifteen minutes Result ==============================="
						+ Strings.NL);
		printWeekDayTitle();

		StringBuilder buf = new StringBuilder();
		int hour = Numeral.ZERO;
		int index = Numeral.ZERO;
		for (int i = Numeral.ZERO; i < NINETY_SIX; i++) {
			String label = String.format(Strings.TWO_DIGITS, hour);
			if (Numeral.ZERO == index) {
				buf.append(buildData(label, Strings.LABEL_ZERO, mondayMap, tuesdayMap, wednesdayMap, thursdayMap, fridayMap));
				++index;
			} else if (Numeral.ONE == index) {
				buf.append(buildData(label, LABLE_FIFTEEN, mondayMap, tuesdayMap, wednesdayMap, thursdayMap, fridayMap));
				++index;
			} else if (Numeral.TWO == index) {
				buf.append(buildData(label, Strings.LABEL_THIRTY, mondayMap, tuesdayMap, wednesdayMap, thursdayMap, fridayMap));
				++index;
			} else {
				buf.append(buildData(label, LABEL_FORTY_FIVE, mondayMap, tuesdayMap, wednesdayMap, thursdayMap, fridayMap));
				++hour;
				index = Numeral.ZERO;
			}
		}
		
		System.out.println(buf.toString());
	}

	public static Map<String, Integer> initMap() {
		HashMap<String, Integer> map = new HashMap<>();
		int hour = Numeral.ZERO;
		int index = Numeral.ZERO;
		for (int i = Numeral.ZERO; i < NINETY_SIX; i++) {
			String label = String.format(Strings.TWO_DIGITS, hour);
			if (Numeral.ZERO == index) {
				map.put(label + Strings.LABEL_ZERO, new Integer(Numeral.ZERO));
				++index;
			} else if (Numeral.ONE == index) {
				map.put(label + LABLE_FIFTEEN, new Integer(Numeral.ZERO));
				++index;
			} else if (Numeral.TWO == index) {
				map.put(label + Strings.LABEL_THIRTY, new Integer(Numeral.ZERO));
				++index;
			} else {
				map.put(label + LABEL_FORTY_FIVE, new Integer(Numeral.ZERO));
				++hour;
				index = Numeral.ZERO;
			}
		}
		return map;
	}
}
