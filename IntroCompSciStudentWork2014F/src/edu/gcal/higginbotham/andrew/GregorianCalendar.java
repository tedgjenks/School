package edu.gcal.higginbotham.andrew;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		boolean yearValue;
		String yearReport = "";
		if(year < 1582)
			yearValue = false;
		else
			yearValue = true;
		
		if(yearValue == false )
			yearReport = year + " is not greater than or equal to 1582.";
		
		else if(year % 100 == 0)
		{
			if(year % 400 == 0)
				yearReport = year + " is a leap year.";
			else
				yearReport = year + " is not a leap year.";
		}
		else if (year % 4 == 0 && yearValue == true)
			yearReport = year + " is a leap year.";
		
		else
			yearReport = year + " is not a leap year.";
		
		return yearReport;
	}
	
	public static void main(String[] args){
		GregorianCalendar gc = new GregorianCalendar();
		String test = gc.reportLeapYear(7020);
		System.out.println(test);
	}

}
