package edu.gcal.burroughs.lauren;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		String isLeapYear = "k";
		if(year>= 1582 && year % 100 == 0 && year % 400 != 0)
			isLeapYear = year + " is not a leap year.";
		else if(year >= 1582 && year % 4 == 0)
			isLeapYear = year + " is a leap year.";
		else if(year >= 1582 && year % 4 != 0) 
			isLeapYear = year + " is not a leap year.";
		else if(year < 1582)
			isLeapYear = year + " is not greater than or equal to 1582.";
		
		return isLeapYear;
	}

}
