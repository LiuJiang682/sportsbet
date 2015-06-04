package au.com.sportsbet.traffic.data.base;

import java.util.List;

import au.com.sportsbet.traffic.dto.TrafficRecord;

public interface TrafficInfoBase {

	void addTrafficRecord(final String direction, final TrafficRecord record);
	
	List<TrafficRecord> getTrafficRecords(final String direction);

}
