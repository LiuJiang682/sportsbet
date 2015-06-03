package au.com.sportsbet.traffic;

import au.com.sportsbet.traffic.constants.Constants.Numeral;
import au.com.sportsbet.traffic.file.reader.FileReader;
import au.com.sportsbet.traffic.file.reader.Reader;

public class TrafficInformer {

	public TrafficInformer(String fileName) {
		Reader reader = new FileReader(fileName);
	}

	public static void main(String[] args) {
		if ((args == null)||(args.length == Numeral.ZERO)) {
			usage();
		}
		
		new TrafficInformer(args[Numeral.ZERO]);

	}

	private static void usage() {
		System.err.println("Usage: java -cp .:TrafficInfo-0.0.1-SNAPSHOT.jar au.com.sportsbet.traffic.TrafficInformer /path/to/datafile");
	}

}
