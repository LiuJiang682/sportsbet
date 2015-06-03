package au.com.sportsbet.traffic.file.reader;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import au.com.sportsbet.common.CollectionUtils;
import au.com.sportsbet.traffic.FileFixture;
import au.com.sportsbet.traffic.file.reader.FileReader;


public class FileReaderTest {
	
	private FileReader testInstance;
	private String line;
	private List<String> trafficDetails;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		trafficDetails = new ArrayList<>();
		testInstance = new FileReader(FileFixture.TEST_DATA_FILE_NAME);
	}

	@After
	public void tearDown() {
		testInstance = null;
		line = null;
		trafficDetails = null;
	}
	
	@Test
	public void testAddLineToList() {
		line = "A123456";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isNotEmpty(trafficDetails));
		
		line = "a123456";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isNotEmpty(trafficDetails));
		
		line = "B123456";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isNotEmpty(trafficDetails));
		
		line = "b123456";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isNotEmpty(trafficDetails));
	}
	
	@Test
	public void testAddLineToListEmptyString() {
		line = "";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isEmpty(trafficDetails));
	}
	
	@Test
	public void testAddLineToListMultiCharacters() {
		String line = "ABC77777777";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isEmpty(trafficDetails));
	}
	
	@Test
	public void testAddLineToListNoCharacters() {
		String line = "77777777";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isEmpty(trafficDetails));
	}
	
	@Test
	public void testAddLineToListInvalidCharacters() {
		String line = "C77777777";
		this.testInstance.addLineToList(trafficDetails, line);
		assertTrue(CollectionUtils.isEmpty(trafficDetails));
	}
	
}

