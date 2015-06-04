package au.com.sportsbet.traffic.user.inactive;

import java.util.List;

import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class PeakTimeCounter extends HourCounter {
	
	public PeakTimeCounter(List<TrafficRecord> records, String direction) {
		super(records, direction);
	}

	@Override
	public void countAndDisplay() {
		super.doCounting();
		
		int mondayPeakHour = findPeakHour(this.mondayHours);
		int tuesdayPeakHour = findPeakHour(this.tuesdayHours);
		int wednesdayPeakHour = findPeakHour(this.wednesdayHours);
		int thursdayPeakHour = findPeakHour(this.thursdayHours);
		int fridayPeakHour = findPeakHour(this.fridayHours);
		
		displayResult(mondayPeakHour, tuesdayPeakHour, wednesdayPeakHour, thursdayPeakHour, fridayPeakHour, this.getDirection());
	}

	static void displayResult(int mondayPeakHour, int tuesdayPeakHour,
			int wednesdayPeakHour, int thursdayPeakHour, int fridayPeakHour,
			String direction) {
		System.out.println(Strings.NL + Strings.NL + "==================== Peak hour for direction " + direction + " =================" + Strings.NL);
		printWeekDayTitle();
		
		StringBuilder buf = new StringBuilder();
		buf.append(Strings.TAB);
		buf.append(mondayPeakHour);
		buf.append(Strings.TAB);
		buf.append(tuesdayPeakHour);
		buf.append(Strings.TAB);
		buf.append(wednesdayPeakHour);
		buf.append(Strings.TAB);
		buf.append(thursdayPeakHour);
		buf.append(Strings.TAB);
		buf.append(fridayPeakHour);
		
		System.out.println(buf.toString());
	}

	static int findPeakHour(Integer[] dayHours) {
		int peakHourCount = dayHours[Numeral.ZERO];
		int peakHour = Numeral.ZERO;
		
		for (int i = Numeral.ONE; i < Numeral.TWENTY_FOUR; i++) {
			if (peakHourCount < dayHours[i]) {
				peakHourCount = dayHours[i];
				peakHour = i;
			}
		}
		return peakHour;
	}

}
