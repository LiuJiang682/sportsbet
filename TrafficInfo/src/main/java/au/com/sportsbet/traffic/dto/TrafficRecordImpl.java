package au.com.sportsbet.traffic.dto;

import java.io.Serializable;

import au.com.sportsbet.common.WeekDay;
import au.com.sportsbet.common.constants.Constants.Numeral;

public class TrafficRecordImpl implements TrafficRecord, Serializable {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 8527066244223124970L;

	private static final String DEL = ":";

	private String direction;
	private WeekDay day;
	private String hhmmss;
	private String orginal;
	
	public TrafficRecordImpl(final String direction, final int day, final String hhmmss, final String orginal) {
		this.direction = direction;
		this.day = WeekDay.fromCode(day);
		this.hhmmss = hhmmss;
		this.orginal = orginal;
	}

	public String getDirection() {
		return direction;
	}

	@Override
	public WeekDay getDay() {
		return day;
	}

	public String getHhmmss() {
		return hhmmss;
	}

	public String getOrginal() {
		return orginal;
	}

	@Override
	public int getHour() {
		String[] times = this.getHhmmss().split(DEL); 
		return Integer.parseInt(times[Numeral.ZERO]);
	}
	
}
