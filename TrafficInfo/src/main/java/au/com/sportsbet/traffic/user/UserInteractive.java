package au.com.sportsbet.traffic.user;

import java.util.List;
import java.util.Scanner;

import au.com.sportsbet.common.constants.Constants.Strings;
import au.com.sportsbet.traffic.data.base.TrafficInfoBase;
import au.com.sportsbet.traffic.dto.TrafficRecord;
import au.com.sportsbet.traffic.user.inactive.HalfHourCounter;
import au.com.sportsbet.traffic.user.inactive.HourCounter;
import au.com.sportsbet.traffic.user.inactive.MorningEveningCounter;

public class UserInteractive {

	private static final String HOURS = "2";
	private static final String MORNING_VS_EVENING = "1";
	private static final String COMMAND_PEAK_TIME_QUERY = "3";
	private static final String COMMAND_SUMMARY_QUERY = HOURS;
	private static final String COMMAND_DETAIL_QUERY = MORNING_VS_EVENING;
	private static final String COMMAND_QUIT = "q";
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
		// TODO Auto-generated method stub

	}

	private void doSummeryQuery(Scanner inputReader) {
		// TODO Auto-generated method stub

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
			countAndDisplayMorningNEvening(records);
			break;
		case HOURS :
			countAndDisplayHours(records);
			break;
		case "3" :
			countAndDisplayHalfHours(records);
		}
	}

	private void countAndDisplayHalfHours(List<TrafficRecord> records) {
		new HalfHourCounter(records).countAndDisplay();
		
	}

	private void countAndDisplayHours(List<TrafficRecord> records) {
		new HourCounter(records).countAndDisplay();
		
	}

	private void countAndDisplayMorningNEvening(List<TrafficRecord> records) {
		new MorningEveningCounter(records).countAndDisplay();
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
