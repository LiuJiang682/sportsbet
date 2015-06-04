package au.com.sportsbet.traffic.user.inactive;

import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.com.sportsbet.common.WeekDay;
import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.dto.TrafficRecord;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TrafficRecord.class})
public class HourCounterTest {

	@Mock
	private List<TrafficRecord> mockList;
	@Mock
	private Iterator<TrafficRecord> mockIterator;
	@Mock
	private TrafficRecord mockRecord1;
	@Mock
	private TrafficRecord mockRecord2;
	@Mock
	private TrafficRecord mockRecord3;
	@Mock
	private TrafficRecord mockRecord4;
	@Mock
	private TrafficRecord mockRecord5;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCountAndDisplay() {
		when(mockList.iterator()).thenReturn(mockIterator);
		when(mockIterator.hasNext()).thenReturn(true, true, true, true, true, false);
		when(mockIterator.next()).thenReturn(mockRecord1, mockRecord2, mockRecord3, mockRecord4, mockRecord5);
		when(mockRecord1.getDay()).thenReturn(WeekDay.Monday);
		when(mockRecord1.getHour()).thenReturn(Numeral.ZERO);
		when(mockRecord2.getDay()).thenReturn(WeekDay.Tuesday);
		when(mockRecord2.getHour()).thenReturn(Numeral.ONE);
		when(mockRecord3.getDay()).thenReturn(WeekDay.Wednesday);
		when(mockRecord3.getHour()).thenReturn(Numeral.THREE);
		when(mockRecord4.getDay()).thenReturn(WeekDay.Thursday);
		when(mockRecord4.getHour()).thenReturn(Numeral.SIX);
		when(mockRecord5.getDay()).thenReturn(WeekDay.Friday);
		when(mockRecord5.getHour()).thenReturn(Numeral.TWELVE);
		
		HourCounter h = new HourCounter(this.mockList, Strings.DIRECTION_A);
		h.countAndDisplay();
		
	}
}
