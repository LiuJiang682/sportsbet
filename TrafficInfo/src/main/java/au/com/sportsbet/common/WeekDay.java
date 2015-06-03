package au.com.sportsbet.common;

public enum WeekDay {
	Monday(1),
	Tuesday(2),
	Wednesday(3),
	Thursday(4),
	Friday(5);
	
	private int code;
	
	WeekDay(int code) {
		this.code = code;
	}
	
	public static WeekDay fromCode(int code) {
		for (WeekDay day : WeekDay.values()) {
			if (day.code == code) {
				return day;
			}
		}
		
		throw new IllegalArgumentException("Unknown weekday code: " + code);
	}
}
