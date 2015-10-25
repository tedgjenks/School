package edu.gcal.ramsey.will;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	public static void main (String [] args){
		GregorianCalendar lc = new GregorianCalendar();
		System.out.println(lc.reportLeapYear(2016));
		}
	@Override
	public String reportLeapYear(int year) {
		// TODO Auto-generated method stub
		String isLeap = year + " is a leap year.";
		String isNotLeap = year + " is not a leap year.";
		String tooEarly = year + " is not greater than or equal to 1582.";
		boolean check = false;
		if (year % 4 == 0){
			check = true;
			if (year % 100 == 0)
				if (year % 400 == 0)
					check = true;
					else check = false;}
		if (check && year >= 1582 )
			return isLeap;
		else if (!check && year >= 1582)
			return isNotLeap;
		else return tooEarly;
	}

}
