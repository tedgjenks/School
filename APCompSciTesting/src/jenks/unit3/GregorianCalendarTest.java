package jenks.unit3;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.jenks.unit3.GregorianCalendar;

public class GregorianCalendarTest {
	private int pointTotal = 0;
	private final String PASSED = "Passed";
	private final String FAILED = "Failed";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Point total: " + pointTotal);
	}

	@Test
	public void test() {
		int year = 2001;
		String passedOrFailed;
		if(!GregorianCalendar.isLeapYear(year)) {
			pointTotal++;
			passedOrFailed = PASSED;
		} else
			passedOrFailed = FAILED;
		System.out.println(passedOrFailed + " not / by 4");
		
		year = 1900;
		if(!GregorianCalendar.isLeapYear(year)) {
			pointTotal++;
			passedOrFailed = PASSED;
		} else
			passedOrFailed = FAILED;
		System.out.println(passedOrFailed + " / by 4, 100, not 400");
		
		year = 2000;
		if(GregorianCalendar.isLeapYear(year)) {
			pointTotal++;
			passedOrFailed = PASSED;
		} else
			passedOrFailed = FAILED;
		System.out.println(passedOrFailed + " / by 4, 100, 400");
		
		year = 1400;
		try {
			GregorianCalendar.isLeapYear(year);
			passedOrFailed = FAILED;
		} catch(IllegalArgumentException e) {
			pointTotal++;
			passedOrFailed = PASSED;
		}
		System.out.println(passedOrFailed + " error on year < 1582");
		
		Random random = new Random(System.currentTimeMillis());
		boolean passed = true;
		for(int count = 100; passed && count > 0; count--) {
			year = random.nextInt(500) + 1582;
			passed = (isLeapYear(year) == GregorianCalendar.isLeapYear(year));
		}
		if(passed) {
			pointTotal++;
			passedOrFailed = PASSED;
		} else
			passedOrFailed = FAILED;
		System.out.println(passedOrFailed + " random tests." + (passed ? "" : " " + year));
	}
	
	private boolean isLeapYear(int year) {
		return edu.jenks.unit3.GregorianCalendar.isLeapYear(year);
	}

}
