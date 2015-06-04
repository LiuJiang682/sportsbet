package au.com.sportsbet.traffic.fixture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.common.constants.Constants.Strings;

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

	public static void main(String[] args) throws IOException {
		int number = 10;
		if ((args != null) && (Numeral.ZERO < args.length)) {
			String numberStr = args[Numeral.ZERO];
			try {
				number = Integer.parseInt(numberStr);
			} catch (Exception e) {
				number = 10;
			}
		}
		createTestFile(number);
	}

	private static void createTestFile(int number) throws IOException {
		File testDataFile = new File("LargeDataFile.txt");
		if (testDataFile.exists()) {
			testDataFile.delete();
		}
			testDataFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(testDataFile);
			Random random = new Random(System.currentTimeMillis());
			List<TreeSet<String>> sortedData = new ArrayList<>();
			
			for (int i = 0; i < 5; i++) {
				TreeSet<String> treeSet = new TreeSet<>(new StringSizeComparator());
				List<String> myList = new ArrayList<>();
				int j = 0;
				while (j < number) {
					boolean b = random.nextBoolean();
					String direction = b ? Strings.DIRECTION_A : Strings.DIRECTION_B;
					int next = random.nextInt(Numeral.DAY_IN_MILLISECOND.intValue());
					StringBuilder buf = new StringBuilder(direction);
					buf.append(next);
					buf.append(Strings.NL);
					buf.append(direction);
					buf.append(next + 100);
					buf.append(Strings.NL);
					String time = buf.toString();
					if (!myList.contains(time)) {
						myList.add(time);
						++j;
					}
				}
				treeSet.addAll(myList);
				sortedData.add(treeSet);
			}
			
			for (TreeSet<String> treeSet : sortedData) {
				for (String s : treeSet) {
					fos.write(s.getBytes());
					fos.flush();
				}
			}
			fos.close();
	}

}
