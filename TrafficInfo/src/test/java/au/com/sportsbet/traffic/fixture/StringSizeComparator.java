package au.com.sportsbet.traffic.fixture;

import java.util.Comparator;

public class StringSizeComparator implements Comparator<String> {

	@Override
	public int compare(String arg0, String arg1) {
		Integer size1 = new Integer(arg0.length());
		Integer size2 = new Integer(arg1.length());
		int result = size1.compareTo(size2);
		if (result == 0) {
			return arg0.compareTo(arg1);
		}
		
		return result;
	}

}
