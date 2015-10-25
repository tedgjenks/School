package edu.gcal.mariscal.juan;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		// TODO Auto-generated method stub
		String result= year + " is not a leap year.";
		if (year < 1582){
		 result = year + " is not greater than or equal to 1582.";
		}
		
		else if (year % 4 == 0 ){
			
			if (year % 100 ==0 && year % 400 != 0)
				result= year + " is not a leap year.";
			else 
				result= year + " is a leap year.";}
						
		
		System.out.print(result);
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GregorianCalendar gc = new GregorianCalendar();
		gc.reportLeapYear(1584);
	}}


