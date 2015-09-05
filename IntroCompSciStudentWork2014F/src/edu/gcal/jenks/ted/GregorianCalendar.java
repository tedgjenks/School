/**
 * 
 */
package edu.gcal.jenks.ted;

import edu.jenks.dist.gcal.AbstractGregorianCalendar;

/**
 * @author Ted Jenks
 *
 */
public class GregorianCalendar extends AbstractGregorianCalendar {
	
	private static final short BEGIN_GREGORIAN_CALENDAR = 1582;

	/**
	 * 
	 */
	public GregorianCalendar() {}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.gcal.AbstractGregorianCalendar#reportLeapYear(int)
	 */
	@Override
	public String reportLeapYear(int year) {
		StringBuilder report = new StringBuilder(30).append(year).append(" is ");
		if(year < BEGIN_GREGORIAN_CALENDAR)
			report.append("not greater than or equal to ").append(BEGIN_GREGORIAN_CALENDAR);
		else {
			boolean isLeapYear = (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
			if(!isLeapYear)
				report.append("not ");
			report.append("a leap year");
		}
		report.append(".");
		return report.toString();
	}

}
