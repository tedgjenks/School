package edu.gcal.piland.will;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		// TODO Auto-generated method stub
		String isleapyear =  year+" is a leap year.";
		String isnotleapyear = year+" is not a leap year.";
		String notvalidyear = year+" is not greater than or equal to 1582.";
		if (year % 400 == 0 & year >= 1582){
			return isleapyear;
		}
		if (year >= 1582){
			if (year % 4 == 0 & year % 100 != 0){
					return isleapyear;
				}
			else {
				return isnotleapyear;
				}	
			}
		else {
			return notvalidyear;
		}
		
		
		
			
	}

}
