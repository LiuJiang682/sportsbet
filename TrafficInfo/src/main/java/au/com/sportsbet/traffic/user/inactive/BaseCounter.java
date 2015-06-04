package au.com.sportsbet.traffic.user.inactive;

import au.com.sportsbet.common.WeekDay;
import au.com.sportsbet.common.constants.Constants.Strings;

public abstract class BaseCounter {

	public static void printWeekDayTitle() {
		StringBuilder buf = new StringBuilder("Day    ");
		buf.append(Strings.TAB);
		buf.append(WeekDay.Monday);
		buf.append(Strings.TAB);
		buf.append(WeekDay.Tuesday);
		buf.append(Strings.TAB);
		buf.append(WeekDay.Wednesday);
		buf.append(Strings.TAB); 
		buf.append(WeekDay.Thursday);
		buf.append(Strings.TAB);
		buf.append(WeekDay.Friday); 
		buf.append(Strings.NL);
		System.out.println(buf.toString());
	}
	
	public abstract void countAndDisplay();
}
