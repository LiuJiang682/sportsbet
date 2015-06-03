package au.com.sportsbet.traffic.file.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import au.com.sportsbet.traffic.constants.Constants.Strings;

public class FileReader implements Reader {

	private static final Logger LOGGER = Logger.getLogger(FileReader.class
			.getName());

	private static final String REGEX_TRAFFIC_DATA = "^[AaBb]\\d+$";

	private String fileName;

	public FileReader(final String fileName) {
		this.fileName = fileName;
	}

	public List<String> read() {
		List<String> trafficDetails = new ArrayList<>();

		File dataFile = new File(fileName);
		Path path = dataFile.toPath();
		Charset charset = Charset.forName(Strings.UTF_8);
		String line = null;
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			while ((line = reader.readLine()) != null) {
				addLineToList(trafficDetails, line);
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Fail to read print job data file "
					+ dataFile.getAbsolutePath(), e);
		}
		return trafficDetails;
	}

	protected void addLineToList(List<String> trafficDetails, String line) {
		/**
		 * Validates the line content, only allow AB and 0-9 pass thought. Drop
		 * the line if the line contains any other symbol.
		 *
		 * The REGEX_PRINT_JOB_DATA string can be externalized on the
		 * configuration properties as requires for flexibility.
		 */
		if (line.matches(REGEX_TRAFFIC_DATA)) {
			trafficDetails.add(line);
		}
	}

}
