package au.com.sportsbet.traffic.user;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.data.base.TrafficInfoBase;
import au.com.sportsbet.traffic.dto.TrafficRecord;
import au.com.sportsbet.traffic.user.inactive.FifteenMinutesCounter;
import au.com.sportsbet.traffic.user.inactive.HalfHourCounter;
import au.com.sportsbet.traffic.user.inactive.HourCounter;
import au.com.sportsbet.traffic.user.inactive.MorningEveningCounter;
import au.com.sportsbet.traffic.user.inactive.PeakTimeCounter;
import au.com.sportsbet.traffic.user.inactive.TwentyMinutesCounter;

public class UserInteractive {
	private static final Logger LOGGER = Logger.getLogger(UserInteractive.class
			.getName());

	private static final String TWENTY_MINUTES = "4";
	private static final String HALF_HOUR = "3";
	private static final String HOURS = "2";
	private static final String MORNING_VS_EVENING = "1";
	private static final String COMMAND_PEAK_TIME_QUERY = HALF_HOUR;
	private static final String COMMAND_SUMMARY_QUERY = HOURS;
	private static final String COMMAND_DETAIL_QUERY = MORNING_VS_EVENING;
	private static final String COMMAND_QUIT = "q";
	private static final int NUMBER_OF_DAYS = 5;
	private static final String FIFTEEN_MINUTES = "5";
	
	private TrafficInfoBase trafficInfoBase;

	public UserInteractive(TrafficInfoBase trafficInfoBase) {
		this.trafficInfoBase = trafficInfoBase;
	}

	public void start() {
		String command = null;
		Scanner inputReader = null;

		do {
			printFirstLevelMenu();
			inputReader = new Scanner(System.in);
			command = inputReader.nextLine();
			executeCommand(command, inputReader);

		} while (!COMMAND_QUIT.equalsIgnoreCase(command));

		if (inputReader != null) {
			inputReader.close();
		}
	}

	void executeCommand(String command, Scanner inputReader) {
		switch (command) {
		case COMMAND_DETAIL_QUERY:
			doDetailQuery(inputReader);
			break;
		case COMMAND_SUMMARY_QUERY:
			doSummeryQuery(inputReader);
			break;
		case COMMAND_PEAK_TIME_QUERY:
			doPeakTimeQuery(inputReader);
			break;

		default:
			return;
		}
	}

	private void doPeakTimeQuery(Scanner inputReader) {
		List<TrafficRecord> records = this.getTrafficInfoBase().getTrafficRecords(Strings.DIRECTION_A);
		new PeakTimeCounter(records, Strings.DIRECTION_A).countAndDisplay();
		records = this.getTrafficInfoBase().getTrafficRecords(Strings.DIRECTION_B);
		new PeakTimeCounter(records, Strings.DIRECTION_B).countAndDisplay();
	}

	void doSummeryQuery(Scanner inputReader) {
		List<TrafficRecord> records = this.getTrafficInfoBase().getTrafficRecords(Strings.DIRECTION_A);
		System.out
		.println(Strings.NL
				+ Strings.NL
				+ "====================== Summary Result ==============================="
				+ Strings.NL);
		doFiveDaysAverage(records, Strings.DIRECTION_A);
		records = this.getTrafficInfoBase().getTrafficRecords(Strings.DIRECTION_B);
		doFiveDaysAverage(records, Strings.DIRECTION_B);
		inputReader.nextLine();
		return;
	}

	private void doFiveDaysAverage(List<TrafficRecord> records, final String direction) {
		int average = records.size() / NUMBER_OF_DAYS;
		System.out.println("The 5 day average on direction " + direction +" is " + average);
	}

	private void doDetailQuery(Scanner inputReader) {
		printDirectionSelectionMenu();
		String direction = inputReader.nextLine();
		printQueryType();
		String queryType = inputReader.nextLine();
		executeQuery(direction, queryType);
		inputReader.nextLine();
		return;
	}

	void executeQuery(String direction, String queryType) {
		List<TrafficRecord> records = this.getTrafficInfoBase()
				.getTrafficRecords(direction);

		switch (queryType) {
		case MORNING_VS_EVENING:
			countAndDisplayMorningNEvening(records, direction);
			break;
		case HOURS:
			countAndDisplayHours(records, direction);
			break;
		case HALF_HOUR:
			countAndDisplayHalfHours(records, direction);
			break;
		case TWENTY_MINUTES:
			countAndDisplay20Minutes(records, direction);
			break;
		case FIFTEEN_MINUTES:
			countAndDsiplay15Minutes(records, direction);
			break;
		default:
			LOGGER.severe("Unknown query type: " + queryType);
		}
	}

	private void countAndDsiplay15Minutes(List<TrafficRecord> records, final String direction) {
		new FifteenMinutesCounter(records, direction).countAndDisplay();

	}

	private void countAndDisplay20Minutes(List<TrafficRecord> records, final String direction) {
		new TwentyMinutesCounter(records, direction).countAndDisplay();
	}

	private void countAndDisplayHalfHours(List<TrafficRecord> records, final String direction) {
		new HalfHourCounter(records, direction).countAndDisplay();

	}

	private void countAndDisplayHours(List<TrafficRecord> records,final String direction) {
		new HourCounter(records, direction).countAndDisplay();

	}

	private void countAndDisplayMorningNEvening(List<TrafficRecord> records, final String direction) {
		new MorningEveningCounter(records, direction).countAndDisplay();
	}

	static void printQueryType() {
		System.out.println(Strings.NL + Strings.NL
				+ "Please select the query type:" + Strings.NL);
		System.out.println(Strings.TAB + " 1. Morning vs evening");
		System.out.println(Strings.TAB + " 2. per hour");
		System.out.println(Strings.TAB + " 3. per half hour");
		System.out.println(Strings.TAB + " 4. per 20 minutes");
		System.out.println(Strings.TAB + " 5. per 15 minutes");
	}

	static void printDirectionSelectionMenu() {
		System.out.println(Strings.NL + Strings.NL
				+ "Please select the direction you looking for:" + Strings.NL);
		System.out.println(Strings.TAB + " 1. Direction A");
		System.out.println(Strings.TAB + " 2. Direction B" + Strings.NL);
		System.out.print("Please enter your choice[1-2]:");

	}

	static void printFirstLevelMenu() {
		System.out
				.println(Strings.NL
						+ Strings.NL
						+ "==================  Welcome to Traffic Informer  =================="
						+ Strings.NL);
		System.out.println("Please select your query type:" + Strings.NL);
		System.out.println(Strings.TAB
				+ " 1. Detail Query(Includes comparison, time period average)");
		System.out.println(Strings.TAB + " 2. Daily Summery Query");
		System.out.println(Strings.TAB + " 3. Peak Time Query" + Strings.NL);
		System.out
				.print("Please enter your choice [1-3] or q to quit the application:");

	}

	public TrafficInfoBase getTrafficInfoBase() {
		return trafficInfoBase;
	}

	public static void main(String[] args) {
		new UserInteractive(null).start();
	}
}
