package au.com.sportsbet.traffic.user.inactive;

import java.util.List;

import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class HourCounter extends BaseCounter {

	private List<TrafficRecord> records;
	private String direction;
	
	protected Integer[] mondayHours = initArray();
	protected Integer[] tuesdayHours = initArray();
	protected Integer[] wednesdayHours = initArray();
	protected Integer[] thursdayHours = initArray();
	protected Integer[] fridayHours = initArray();

	public HourCounter(List<TrafficRecord> records, final String direction) {
		this.records = records;
		this.direction = direction;
	}

	@Override
	public void countAndDisplay() {

		doCounting();

		displayResult(mondayHours, tuesdayHours, wednesdayHours, thursdayHours, fridayHours, this.direction);
	}

	protected void doCounting() {
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
	}

	public List<TrafficRecord> getRecords() {
		return records;
	}

	public String getDirection() {
		return direction;
	}

	static void displayResult(Integer[] mondayHours, Integer[] tuesdayHours, Integer[] wednesdayHours, Integer[] thursdayHours, Integer[] fridayHours, final String direction) {
		System.out
				.println(Strings.NL
						+ Strings.NL
						+ "====================== Hours Result for direction " + direction + " ==============================="
						+ Strings.NL);
		printWeekDayTitle();
		
		StringBuilder buf = new StringBuilder();
		for (int i = Numeral.ZERO; i < Numeral.TWENTY_FOUR; i++) {
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
		Integer[] hours = new Integer[Numeral.TWENTY_FOUR];
		for (int i = 0; i < Numeral.TWENTY_FOUR; i++) {
			hours[i] = new Integer(Numeral.ZERO);
		}

		return hours;
	}
}
