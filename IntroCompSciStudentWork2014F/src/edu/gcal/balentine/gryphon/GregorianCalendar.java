package edu.gcal.balentine.gryphon;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		String output="";
		if(year<1582){
			output=year + " is not greater than or equal to 1582.";
		}
		else if(year%100==0 && !(year%400==0)){
			output=year + " is not a leap year.";
		}
		else if(year>=1582)
			if(year%4==0){
				output=year + " is a leap year.";
			}
		
		else{
			output=year + " is not a leap year.";
		}
		return output;
	}

	public static void main(String[] args) {
		GregorianCalendar gc=new GregorianCalendar();
		int year=2000;
		String isLeap=gc.reportLeapYear(year);
		System.out.println(isLeap);
	}

}
