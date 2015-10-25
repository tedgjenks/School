package edu.gcal.shearer.richard;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		boolean report = false;
		String dareport = "";
		if (year < 1582) {
			report = false;
		}
		else if (year >= 1582) {
			report = true;	
		}
		if (report == false) {
			dareport = year + " is not greater than or equal to 1582.";
		}
		else if (year % 100 == 0){
			if (year % 400 == 0)
				dareport = year +  " is a leap year.";
			else
				dareport = year + " is not a leap year.";
		}
		else if (year % 4 == 0 && report == true) 
			dareport = year + " is a leap year.";
			else
			dareport = year + " is not a leap year.";
		return dareport;
	}
}
