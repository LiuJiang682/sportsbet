package au.com.sportsbet.traffic.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import au.com.sportsbet.traffic.data.base.TrafficInfoBase;

public class UserInteractiveTest {

	@Mock
	private TrafficInfoBase mockTrafficInfoBase;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPrintFirstLevelMenu() {
		UserInteractive.printFirstLevelMenu();
	}
	
}
