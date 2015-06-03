package au.com.sportsbet.traffic.file.reader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import au.com.sportsbet.common.CollectionUtils;
import au.com.sportsbet.traffic.FileFixture;

public class FileReaderIT {
	private FileReader testInstance;

	@Before
	public void setUp() {
		testInstance = new FileReader(FileFixture.TEST_DATA_FILE_NAME);
	}

	@After
	public void tearDown() {
		testInstance = null;
	}

	@Test
	public void testRead() throws IOException {
		FileFixture.getTestFile();
		List<String> data = testInstance.read();
		assertTrue(CollectionUtils.isNotEmpty(data));
		assertTrue(data.size() == 3);
		FileFixture.deleteCSVTestFile();
	}

	@Test
	public void testReadFail() {
		List<String> data = testInstance.read();
		assertNotNull(data);
		assertTrue(data.isEmpty());
	}

}
