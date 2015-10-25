package edu.gcal.slimmer.ben;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar
extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		if(year<1582)
			return year+" is not greater than or equal to 1582.";
		if(year%100==0 && year%400!=0)
			return year+" is not a leap year.";
		if(year%4==0)
			return year+" is a leap year.";
		else
			return year+" is not a leap year.";
	}

}