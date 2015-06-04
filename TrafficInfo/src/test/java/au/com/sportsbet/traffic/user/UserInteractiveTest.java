package au.com.sportsbet.traffic.user;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.sportsbet.traffic.data.base.TrafficInfoBase;
import au.com.sportsbet.traffic.dto.TrafficRecord;

public class UserInteractiveTest {

	@Mock
	private TrafficInfoBase mockTrafficInfoBase;
	@Mock
	private List<TrafficRecord> mockList;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPrintFirstLevelMenu() {
		UserInteractive.printFirstLevelMenu();
	}
	
	@Ignore
	@Test
	public void testDoSummeryQuery() throws IOException {
		when(this.mockTrafficInfoBase.getTrafficRecords(Matchers.anyString())).thenReturn(mockList);
		when(this.mockList.size()).thenReturn(new Integer(300));
		UserInteractive ui = new UserInteractive(this.mockTrafficInfoBase);
		ui.doSummeryQuery(new Scanner(System.in));
	}
	
	@Ignore
	@Test
	public void testGetDirectionSelection() {
		UserInteractive ui = new UserInteractive(this.mockTrafficInfoBase);
		ui.getDirectionSelection(new Scanner(System.in));
	}
	
	@Ignore
	@Test
	public void testGetQueryTypeSelection() {
		UserInteractive ui = new UserInteractive(this.mockTrafficInfoBase);
		ui.getQueryTypeSelection(new Scanner(System.in));
	}
}
