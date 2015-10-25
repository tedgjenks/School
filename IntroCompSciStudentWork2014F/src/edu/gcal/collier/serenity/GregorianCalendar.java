package edu.gcal.collier.serenity;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		String leapyear;
		int finalyear = 0;
		if (year <= 1582)
			year=year%4;
		if (year >= 1582)
			year=year%4;
		else if ((year%100)+(year%400)<= 1582)
			leapyear=("is a leapyear.");
		else if ((year%100)+(year%400) >= 1582)
			leapyear=("is not a leapyear.");
		
		return "is leapyear.";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
