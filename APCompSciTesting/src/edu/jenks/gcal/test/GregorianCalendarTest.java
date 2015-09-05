package edu.jenks.gcal.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class GregorianCalendarTest extends Testable {
	
	private static final short BEGIN_GREGORIAN_CALENDAR = 1582;
	private final String REPORT_INVALID_YEAR = " is not greater than or equal to " + BEGIN_GREGORIAN_CALENDAR + ".";
	private final String REPORT_LEAP_YEAR = " is a leap year.";
	private final String REPORT_NOT_LEAP_YEAR = " is not a leap year.";
	
	private String studentClassName;
	private AbstractGregorianCalendar studentCalendar, solutionCalendar = new edu.gcal.jenks.ted.GregorianCalendar();

	public GregorianCalendarTest() {}
	
	public void testReportLeapYear() {
		if(testExamples()) {
			String message = "test all years";
			int points = 3;
			for(int year = 10000; year > 1400 && points > 0; year--) {
				String expected = solutionCalendar.reportLeapYear(year);
				String actual = studentCalendar.reportLeapYear(year);
				if(!expected.equals(actual)) {
					points--;
					logFail(message, expected, actual, 1);
				}
			}
			if(points > 0) {
				totalPoints += points;
				logPass(message);
			}
		} else
			logFailPrereq();
	}
	
	private boolean testExamples() {
		boolean pass = true;
		String message = "examples";
		int points = 2;
		Map<Integer, String> yearExpected = new HashMap<Integer, String>(10) {{
			put(2003, REPORT_NOT_LEAP_YEAR);
			put(2004, REPORT_LEAP_YEAR);
			put(1900, REPORT_NOT_LEAP_YEAR);
			put(2000, REPORT_LEAP_YEAR);
			put(1400, REPORT_INVALID_YEAR);
		}};
		Iterator<Integer> keys = yearExpected.keySet().iterator();
		while(keys.hasNext() && pass) {
			int year = keys.next();
			String expected = year + yearExpected.get(year);
			String actual = studentCalendar.reportLeapYear(year);
			if(!expected.equals(actual)) {
				logFail(message, expected, actual, points);
				pass = false;
			}
		}
		
		if(pass) {
			totalPoints += points;
			logPass(message);
		}
		return pass;
	}

	@Override
	public int getPointsAvailable() {
		return 10;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".GregorianCalendar";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.gcal.AbstractGregorianCalendar");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		studentCalendar = (AbstractGregorianCalendar)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 5;
		logPass("object created");
	}

}
