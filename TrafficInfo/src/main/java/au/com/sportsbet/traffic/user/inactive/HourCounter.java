package au.com.sportsbet.traffic.user.inactive;

import java.util.List;

import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class HourCounter extends BaseCounter {

	private static final int TWENTY_FOUR = 24;
	private List<TrafficRecord> records;

	public HourCounter(List<TrafficRecord> records) {
		this.records = records;
	}

	@Override
	public void countAndDisplay() {
		Integer[] mondayHours = initArray();
		Integer[] tuesdayHours = initArray();
		Integer[] wednesdayHours = initArray();
		Integer[] thursdayHours = initArray();
		Integer[] fridayHours = initArray();

		for (TrafficRecord record : records) {
			int hour = record.getHour();
			switch (record.getDay()) {
			case Monday :
				++mondayHours[hour];
				break;
			case Tuesday :
				++tuesdayHours[hour];
				break;
			case Wednesday :
				++wednesdayHours[hour];
				break;
			case Thursday :
				++thursdayHours[hour];
				break;
			case Friday :
				++fridayHours[hour];
			}
		}

		displayResult(mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours);
	}

	static void displayResult(Integer[] mondayHours, Integer[] tuesdayHours, Integer[] wednesdayHours, Integer[] thursdayHours, Integer[] fridayHours) {
		System.out
				.println(Strings.NL
						+ Strings.NL
						+ "====================== Hours Result ==============================="
						+ Strings.NL);
		printWeekDayTitle();
		
		StringBuilder buf = new StringBuilder();
		for (int i = Numeral.ZERO; i < TWENTY_FOUR; i++) {
			buf.append(i);
			buf.append(Strings.TAB);
			buf.append(mondayHours[i]);
			buf.append(Strings.TAB);
			buf.append(tuesdayHours[i]);
			buf.append(Strings.TAB);
			buf.append(wednesdayHours[i]);
			buf.append(Strings.TAB);
			buf.append(thursdayHours[i]);
			buf.append(Strings.TAB);
			buf.append(fridayHours[i]);
			buf.append(Strings.NL);
		}
		
		System.out.println(buf.toString());

	}

	public static Integer[] initArray() {
		Integer[] hours = new Integer[TWENTY_FOUR];
		for (int i = 0; i < TWENTY_FOUR; i++) {
			hours[i] = new Integer(Numeral.ZERO);
		}

		return hours;
	}
}
