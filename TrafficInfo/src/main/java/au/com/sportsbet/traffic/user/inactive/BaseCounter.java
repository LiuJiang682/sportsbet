package au.com.sportsbet.traffic.user.inactive;

import java.util.Map;

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
	
	protected static void addCount(Map<String, Integer> map, final String lable) {
		Integer count = map.get(lable);
		++count;
		map.put(lable, count);
	}
	
	protected static String buildData(final String prefix, final String suffix, Map<String, Integer> mondayMap, Map<String, Integer> tuesdayMap, Map<String, Integer> wednesdayMap, Map<String, Integer> thursdayMap, Map<String, Integer> fridayMap) {
		StringBuilder buf = new StringBuilder();
		String label = prefix + suffix;
		buf.append(prefix);
		buf.append(":");
		buf.append(suffix);
		buf.append(Strings.TAB);
		buf.append(mondayMap.get(label));
		buf.append(Strings.TAB);
		buf.append(tuesdayMap.get(label));
		buf.append(Strings.TAB);
		buf.append(wednesdayMap.get(label));
		buf.append(Strings.TAB);
		buf.append(thursdayMap.get(label));
		buf.append(Strings.TAB);
		buf.append(fridayMap.get(label));
		buf.append(Strings.NL);
		
		return buf.toString();
	}
	
	public abstract void countAndDisplay();
}
