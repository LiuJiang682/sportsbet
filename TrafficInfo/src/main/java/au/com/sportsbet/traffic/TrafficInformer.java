package au.com.sportsbet.traffic;

import java.util.List;
import java.util.logging.Logger;

import au.com.sportsbet.common.CollectionUtils;
import au.com.sportsbet.common.constants.Constants.Numeral;
import au.com.sportsbet.traffic.data.base.TrafficInfoBase;
import au.com.sportsbet.traffic.file.reader.FileReader;
import au.com.sportsbet.traffic.file.reader.Reader;
import au.com.sportsbet.traffic.organiser.TrafficDataOrganiser;
import au.com.sportsbet.traffic.user.UserInteractive;

public class TrafficInformer {

	private static final Logger LOGGER = Logger.getLogger(TrafficInformer.class.getName());
	
	private List<String> trafficDetails;
	
	public TrafficInformer(String fileName) {
		Reader reader = new FileReader(fileName);
		trafficDetails = reader.read();
	}

	void doTrafficInfoAnalyse () {
		if (CollectionUtils.isEmpty(this.getTrafficDetails())) {
			// Failed to load the data file
			LOGGER.severe("Cannot load the traffic data! Check your file and input parameter.");
			usage();
		} else {
			TrafficInfoBase trafficInfoBase = doDataSorting(this.trafficDetails);
			doUserInteractive(trafficInfoBase);
		}
		
	}
	
	void doUserInteractive(TrafficInfoBase trafficInfoBase) {
		new UserInteractive(trafficInfoBase).start();
	}

	TrafficInfoBase doDataSorting(List<String> trafficDetails) {
		return TrafficDataOrganiser.doDataOrganise(trafficDetails);
	}

	public List<String> getTrafficDetails() {
		return trafficDetails;
	}

	private static void usage() {
		System.err.println("Usage: java -cp .:TrafficInfo-0.0.1-SNAPSHOT.jar au.com.sportsbet.traffic.TrafficInformer /path/to/datafile");
	}
	
	public static void main(String[] args) {
		if ((args == null)||(args.length == Numeral.ZERO)) {
			usage();
		}
		
		new TrafficInformer(args[Numeral.ZERO]).doTrafficInfoAnalyse();

	}

}
