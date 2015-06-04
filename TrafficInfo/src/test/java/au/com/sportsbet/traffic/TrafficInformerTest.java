package au.com.sportsbet.traffic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import au.com.sportsbet.traffic.data.base.TrafficInfoBase;
import au.com.sportsbet.traffic.file.reader.FileReader;
import au.com.sportsbet.traffic.fixture.FileFixture;
import au.com.sportsbet.traffic.organiser.TrafficDataOrganiser;
import au.com.sportsbet.traffic.user.UserInteractive;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FileReader.class, TrafficInformer.class, TrafficDataOrganiser.class, UserInteractive.class})
public class TrafficInformerTest {

	@Mock
	private FileReader mockFileReader;
	@Mock
	private List<String> mockList;
	@Mock
	private TrafficInfoBase mockTrafficInfoBase;
	@Mock
	private UserInteractive mockUserInteractive;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testConstructor() throws Exception {
		givenFileReader();
		
		assertNotNull(new TrafficInformer(FileFixture.TEST_DATA_FILE_NAME));
	}

	private void givenFileReader() throws Exception {
		PowerMockito.whenNew(FileReader.class).withArguments(FileFixture.TEST_DATA_FILE_NAME).thenReturn(mockFileReader);
		when(mockFileReader.read()).thenReturn(mockList);
	}
	
	@Test
	public void testDoTrafficInfoAnalyseNoList() {
		TrafficInformer mockTrafficInformer = Mockito.mock(TrafficInformer.class);
		when(mockTrafficInformer.getTrafficDetails()).thenReturn(null);
		
		mockTrafficInformer.doTrafficInfoAnalyse();
		
		verify(mockTrafficInformer, never()).doDataSorting(Matchers.anyList());
		verify(mockTrafficInformer, never()).doUserInteractive(Matchers.any(TrafficInfoBase.class));
	}
	
	@Test
	public void testDoTrafficInfoAnalyse() throws Exception {
		TrafficInformer mockTrafficInformer = PowerMockito.mock(TrafficInformer.class);
		PowerMockito.when(mockTrafficInformer.getTrafficDetails()).thenReturn(this.mockList);
		PowerMockito.doCallRealMethod().when(mockTrafficInformer, "doTrafficInfoAnalyse");
		mockTrafficInformer.doTrafficInfoAnalyse();
		
		verify(mockTrafficInformer).doDataSorting(Matchers.anyList());
		verify(mockTrafficInformer).doUserInteractive(Matchers.any(TrafficInfoBase.class));
	}
	
	@Test
	public void testDoDataSorting() throws Exception {
		PowerMockito.mockStatic(TrafficDataOrganiser.class);
		PowerMockito.doReturn(mockTrafficInfoBase).when(TrafficDataOrganiser.class, "doDataOrganise", this.mockList);
		givenFileReader();
		
		TrafficInformer trafficInformer = new TrafficInformer(FileFixture.TEST_DATA_FILE_NAME);
		assertEquals(this.mockTrafficInfoBase, trafficInformer.doDataSorting(mockList));
		
		PowerMockito.verifyStatic();
		TrafficDataOrganiser.doDataOrganise(mockList);
	}
	
	@Test
	public void testDoUserInteractive() throws Exception {
		PowerMockito.whenNew(UserInteractive.class).withArguments(mockTrafficInfoBase).thenReturn(mockUserInteractive);
		
		TrafficInformer trafficInformer = new TrafficInformer(FileFixture.TEST_DATA_FILE_NAME);
		trafficInformer.doUserInteractive(mockTrafficInfoBase);
		
		verify(mockUserInteractive).start();
	}
}
