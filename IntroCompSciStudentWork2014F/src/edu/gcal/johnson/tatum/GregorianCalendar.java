package edu.gcal.johnson.tatum;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {
	public static void main (String [] args){
		GregorianCalendar Yes = new GregorianCalendar();
		String test = Yes.reportLeapYear(2000);
		System.out.println(test);
	}

	@Override
	public String reportLeapYear(int year) {
		if (year < 1582)
			return year + " is not greater than or equal to 1582.";
		else if (year % 4 != 0 || (year % 100 == 0 && year % 400 != 0))
			return year + " is not a leap year.";
		else if (year % 4 == 0 || (year % 100 == 0 && year % 400 == 0))
			return year + " is a leap year.";
		else 
			return null;
	}

}