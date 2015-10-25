package edu.gcal.ruhoff.brooke;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {
	public static void main(String[] args) {
		
	}
	@Override
	public String reportLeapYear(int year) {
		if (year<1582){
			System.out.println("Year must be greater than or equal to 1582.");
		}
		else if ((year%100==0)&&(year%400!=0))
			System.out.println(year + " is not a leap year.");
		else if (year%4==0){
			System.out.println(year + " is a leap year.");
		}
		else System.out.println(year + " is not a leap year.");
		
		return null;
	}
}