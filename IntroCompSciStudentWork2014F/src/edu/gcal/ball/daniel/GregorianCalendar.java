package edu.gcal.ball.daniel;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

public class GregorianCalendar extends AbstractGregorianCalendar 
{

	@Override
	public String reportLeapYear(int year) 
	{
		String returnString = ("");
		if (year < 1582)
		{
			returnString = (year + " is not greater than or equal to 1582.");
		}
		else
			
		if ((year % 4) == 0)
			{
				if ((year % 100) == 0 && ((year % 400) != 0))
						{
							returnString = (year + " is not a leap year.");
						}
					else
						{
							returnString = (year + " is a leap year.");
						}
				}
				
		else
			{
				returnString = (year + " is not a leap year.");
			}
			
		return returnString;
	}

	public static void main(String[] args) 
	{

	}

}
