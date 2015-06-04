package au.com.sportsbet.traffic.organiser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.com.sportsbet.common.CollectionUtils;
import au.com.sportsbet.common.WeekDay;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.data.base.TrafficInfoBaseImpl;
import au.com.sportsbet.traffic.dto.TrafficRecord;
import au.com.sportsbet.traffic.dto.TrafficRecordImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TrafficInfoBaseImpl.class, TrafficDataOrganiser.class })
public class TrafficDataOrganiserTest {

	@Mock
	private TrafficInfoBaseImpl mockBase;

	@Test
	public void testIsNewRecordSameString() {
		String time = "12345";
		String previous = time;
		assertTrue(TrafficDataOrganiser.isNewRecord(time, previous));
	}

	@Test
	public void testIsNewRecordCurrentGreater() {
		String time = "123456";
		String previous = "12345";
		assertTrue(TrafficDataOrganiser.isNewRecord(time, previous));
	}

	@Test
	public void testIsNewRecordCurrentInRange() {
		String time = "123456";
		String previous = "123331";
		assertFalse(TrafficDataOrganiser.isNewRecord(time, previous));

		previous = "123231";
		assertFalse(TrafficDataOrganiser.isNewRecord(time, previous));
	}

	@Test
	public void testIsNewRecordCurrentInRangeWithProperties() {
		System.setProperty(Strings.SPEED_DELTA, "50");
		String time = "123456";
		String previous = "123256";
		assertFalse(TrafficDataOrganiser.isNewRecord(time, previous));
		System.clearProperty(Strings.SPEED_DELTA);
	}

	/**
	 * Simple test case -- Direction A and B has one record each.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDoDataOrganise() throws Exception {
		List<String> trafficDetails = new ArrayList<>();
		trafficDetails.add("A268981");
		trafficDetails.add("A269123");
		trafficDetails.add("B604960");
		trafficDetails.add("B605132");

		PowerMockito.whenNew(TrafficInfoBaseImpl.class).withNoArguments()
				.thenReturn(mockBase);

		TrafficDataOrganiser.doDataOrganise(trafficDetails);

		verify(mockBase).addTrafficRecord(Matchers.eq(Strings.DIRECTION_A), Matchers.any(TrafficRecord.class));
		verify(mockBase).addTrafficRecord(Matchers.eq(Strings.DIRECTION_B), Matchers.any(TrafficRecord.class));
	}

	/**
	 * Simple test case -- Direction A and B has one record each.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDoDataOrganiseSingleCarHitBothDirections() throws Exception {
		List<String> trafficDetails = new ArrayList<>();

		trafficDetails.add("A582668");
		trafficDetails.add("B582671");
		trafficDetails.add("A582787");
		trafficDetails.add("B582789");

		PowerMockito.whenNew(TrafficInfoBaseImpl.class).withNoArguments()
				.thenReturn(mockBase);

		TrafficDataOrganiser.doDataOrganise(trafficDetails);

		ArgumentCaptor<TrafficRecord> recordCaptor = ArgumentCaptor
				.forClass(TrafficRecord.class);
		ArgumentCaptor<String> directionCaptor = ArgumentCaptor.forClass(String.class);
		verify(mockBase).addTrafficRecord(directionCaptor .capture(), recordCaptor.capture());
		
		List<String> capturedDirections = directionCaptor.getAllValues();
		assertTrue(CollectionUtils.isNotEmpty(capturedDirections));
		assertTrue(capturedDirections.size() == 1);
		assertEquals(Strings.DIRECTION_A, capturedDirections.iterator().next());
		TrafficRecord capturedRecord = recordCaptor.getValue();
		TrafficRecordImpl realRecord = (TrafficRecordImpl) capturedRecord;
		assertEquals(WeekDay.Monday, realRecord.getDay());
	}

	/**
	 * Over midnight test case -- Direction A has 2 records and B has one
	 * record.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDoDataOrganiseDayOver() throws Exception {
		List<String> trafficDetails = new ArrayList<>();
		trafficDetails.add("A268981");
		trafficDetails.add("A269123");
		trafficDetails.add("A11268981");
		trafficDetails.add("A11269123");
		trafficDetails.add("B604960");
		trafficDetails.add("B605132");

		PowerMockito.whenNew(TrafficInfoBaseImpl.class).withNoArguments()
				.thenReturn(mockBase);

		TrafficDataOrganiser.doDataOrganise(trafficDetails);

		ArgumentCaptor<String> directionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<TrafficRecord> recordCaptor = ArgumentCaptor
				.forClass(TrafficRecord.class);
		verify(mockBase, times(3)).addTrafficRecord(directionCaptor.capture(), recordCaptor.capture());
		
		List<String> capturedDirections = directionCaptor.getAllValues();
		assertTrue(CollectionUtils.isNotEmpty(capturedDirections));
		assertTrue(capturedDirections.size() == 3);
		assertEquals(Strings.DIRECTION_A, capturedDirections.get(0));
		assertEquals(Strings.DIRECTION_A, capturedDirections.get(1));
		assertEquals(Strings.DIRECTION_B, capturedDirections.get(2));

		List<TrafficRecord> capturedRecords = recordCaptor.getAllValues();
		assertTrue(CollectionUtils.isNotEmpty(capturedRecords));
		assertTrue(capturedRecords.size() == 3);
		assertEquals(WeekDay.Tuesday, ((TrafficRecordImpl)capturedRecords.get(2)).getDay());
	}

	/**
	 * Over midnight test case -- Direction A has 2 records and B has one
	 * record.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testDoDataOrganise5DayOver() throws Exception {
		List<String> trafficDetails = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			trafficDetails.add("A268981");
			trafficDetails.add("A269123");
			trafficDetails.add("A11268981");
			trafficDetails.add("A11269123");
			trafficDetails.add("B604960");
			trafficDetails.add("B605132");
		}

		trafficDetails.add("A268981");
		trafficDetails.add("A269123");
		trafficDetails.add("A111268981");
		trafficDetails.add("A111269123");

		PowerMockito.whenNew(TrafficInfoBaseImpl.class).withNoArguments()
				.thenReturn(mockBase);

		TrafficDataOrganiser.doDataOrganise(trafficDetails);

		ArgumentCaptor<String> directionCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<TrafficRecord> recordCaptor = ArgumentCaptor
				.forClass(TrafficRecord.class);
		verify(mockBase, times(14)).addTrafficRecord(directionCaptor.capture(), recordCaptor.capture());

		boolean mondayFound = false;
		boolean tuesdayFound = false;
		boolean wednesdayFound = false;
		boolean thursdayFound = false;
		boolean fridayFound = false;
		List<TrafficRecord> records = recordCaptor.getAllValues();
		for (TrafficRecord r : records) {
			switch (((TrafficRecordImpl) r).getDay()) {
			case Monday:
				mondayFound = true;
				break;
			case Tuesday:
				tuesdayFound = true;
				break;

			case Wednesday:
				wednesdayFound = true;
				break;

			case Thursday:
				thursdayFound = true;
				break;

			case Friday:
				fridayFound = true;
				break;
			
			default:
				break;
			}
		}
		
		assertTrue(mondayFound && tuesdayFound && wednesdayFound
				&& thursdayFound && fridayFound);
	}
}
