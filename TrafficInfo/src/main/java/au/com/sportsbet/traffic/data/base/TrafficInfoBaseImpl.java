package au.com.sportsbet.traffic.data.base;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import au.com.sportsbet.common.CollectionUtils;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class TrafficInfoBaseImpl implements TrafficInfoBase {

	private static final Logger LOGGER = Logger
			.getLogger(TrafficInfoBaseImpl.class.getName());

	private List<TrafficRecord> recordsForDirA = new ArrayList<>();
	private List<TrafficRecord> recordsForDirB = new ArrayList<>();

	@Override
	public void addTrafficRecord(String direction, TrafficRecord record) {
		List<TrafficRecord> records = this.getDirectionRecord(direction);

		if (null != records) {
			records.add(record);
		}
	}

	@Override
	public List<TrafficRecord> getTrafficRecords(String direction) {
		List<TrafficRecord> records = new ArrayList<>();
		
		List<TrafficRecord> directionRecords = this.getDirectionRecord(direction);
		if (CollectionUtils.isNotEmpty(directionRecords)) {
			records.addAll(directionRecords);
		}
		
		return records;
	}

	List<TrafficRecord> getDirectionRecord(final String direction) {
		List<TrafficRecord> records = null;
		
		switch (direction) {
		case Strings.DIRECTION_A:
			records = this.recordsForDirA;
			break;
		case Strings.DIRECTION_B:
			records = this.recordsForDirB;
			break;
		default:
			LOGGER.warning("Unknown direction " + direction);
		}
		
		return records;
	}
}
