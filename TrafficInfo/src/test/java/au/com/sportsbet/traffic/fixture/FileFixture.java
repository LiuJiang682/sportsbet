package au.com.sportsbet.traffic.fixture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileFixture {

	public static File getTestFile() throws IOException {
		File testDataFile = new File(FileFixture.TEST_DATA_FILE_NAME);
		if (!testDataFile.exists()) {
			testDataFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(testDataFile);
			String data = "A268981\r\nA269123\r\nA604957";
			fos.write(data.getBytes());
			fos.flush();
			fos.close();
		}

		return testDataFile;
	}

	public static void deleteCSVTestFile() {
		File testDataFile = new File(FileFixture.TEST_DATA_FILE_NAME);
		if (testDataFile.exists()) {
			testDataFile.delete();
		}
	}

	public static final String TEST_DATA_FILE_NAME = "src/test/resources/testDataFile.txt";

}
