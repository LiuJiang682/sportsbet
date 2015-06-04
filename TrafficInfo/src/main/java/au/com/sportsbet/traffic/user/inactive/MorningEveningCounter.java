package au.com.sportsbet.traffic.user.inactive;

import java.util.List;

import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class MorningEveningCounter extends BaseCounter {

	private List<TrafficRecord> records;
	private String direction;
	
	public MorningEveningCounter(List<TrafficRecord> records, final String direction) {
		this.records = records;
		this.direction = direction;
	}

	@Override
	public void countAndDisplay() {
			int mondayMorning = Numeral.ZERO;
			int tuesdayMorning = Numeral.ZERO;
			int wednesadyMorning = Numeral.ZERO;
			int thursdayMorning = Numeral.ZERO;
			int fridayMorning = Numeral.ZERO;
			
			int mondayEvening = Numeral.ZERO;
			int tuesdayEvening = Numeral.ZERO;
			int wednesadyEvening = Numeral.ZERO;
			int thursdayEvening = Numeral.ZERO;
			int fridayEvening = Numeral.ZERO;
			
			for (TrafficRecord record : records) {
				int hour = record.getHour();
				if ((Numeral.SIX < hour) && (hour < Numeral.TWELVE)) {
					switch (record.getDay()) {
					case Monday :
						++mondayMorning;
						break;
					case Tuesday :
						++tuesdayMorning;
						break;
					case Wednesday :
						++wednesadyMorning;
						break;
					case Thursday :
						++thursdayMorning;
						break;
					case Friday :
						++fridayMorning;
					}
				} else if ((Numeral.EIGHTEEN < hour) &&(hour < Numeral.TWENTY_ONE)) {
					switch (record.getDay()) {
					case Monday :
						++mondayEvening;
						break;
					case Tuesday :
						++tuesdayEvening;
						break;
					case Wednesday :
						++wednesadyEvening;
						break;
					case Thursday :
						++thursdayEvening;
						break;
					case Friday :
						++fridayEvening;
					}
				}
			}
			
			printMorningEveningCount(mondayMorning, tuesdayMorning,
					wednesadyMorning, thursdayMorning, fridayMorning,
					mondayEvening, tuesdayEvening, wednesadyEvening,
					thursdayEvening, fridayEvening, this.direction);
		}
	
		static void printMorningEveningCount(int mondayMorning, int tuesdayMorning,
				int wednesadyMorning, int thursdayMorning, int fridayMorning,
				int mondayEvening, int tuesdayEvening, int wednesadyEvening,
				int thursdayEvening, int fridayEvening, final String direction) {
			System.out.println(Strings.NL + Strings.NL + "====================Morning vs Evening result for direction " + direction + " =================" + Strings.NL);
			printWeekDayTitle();
			
			StringBuilder buf = new StringBuilder("Morning");
			buf.append(Strings.TAB);
			buf.append(mondayMorning);
			buf.append(Strings.TAB);
			buf.append(tuesdayMorning);
			buf.append(Strings.TAB);
			buf.append(wednesadyMorning);
			buf.append(Strings.TAB);
			buf.append(thursdayMorning);
			buf.append(Strings.TAB);
			buf.append(fridayMorning);
			System.out.println(buf.toString());
			
			buf = new StringBuilder("Evening");
			buf.append(Strings.TAB);
			buf.append(mondayEvening);
			buf.append(Strings.TAB);
			buf.append(tuesdayEvening);
			buf.append(Strings.TAB);
			buf.append(wednesadyEvening);
			buf.append(Strings.TAB);
			buf.append(thursdayEvening);
			buf.append(Strings.TAB);
			buf.append(fridayEvening);
			System.out.println(buf.toString());
		}
	
	
}
