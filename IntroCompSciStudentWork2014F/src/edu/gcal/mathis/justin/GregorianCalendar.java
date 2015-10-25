package edu.gcal.mathis.justin;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar {

	@Override
	public String reportLeapYear(int year) {
		String result = "";
		if (year<1582)
			result = year+" is not greater than or equal to 1582.";
		else if (year%4==0){
			if (year%400==0){
				result = year+" is a leap year.";
			}
			else if (year%100!=0){
				result = year+" is a leap year.";
			}
			else result = year+" is not a leap year.";
		}
		else result = year+" is not a leap year.";
		return result;
	}

	public static void main(String[] args) {
		GregorianCalendar gc = new GregorianCalendar();
		String swag = gc.reportLeapYear(1582);
		System.out.print(swag); 

	}

}
