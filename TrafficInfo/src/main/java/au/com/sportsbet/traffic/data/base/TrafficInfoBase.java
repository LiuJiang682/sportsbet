package au.com.sportsbet.traffic.data.base;

import au.com.sportsbet.traffic.dto.TrafficRecord;

public interface TrafficInfoBase {

	void addDirectionARecord(TrafficRecord record);

	void addDirectionBRecord(TrafficRecord record);

}
