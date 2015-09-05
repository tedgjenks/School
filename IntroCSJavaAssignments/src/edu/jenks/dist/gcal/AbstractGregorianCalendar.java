/**
 * 
 */
package edu.jenks.dist.gcal;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractGregorianCalendar {

	/**
	 * 
	 */
	public AbstractGregorianCalendar() {}
	
	/**
	 * <p>Determine if <code>year</code> is a leap year.</p>
	 * 
	 * If <code>year</code> is not greater than or equal to 1582, report: <code>year</code> is not greater than or equal to 1582.<br>
	 * If <code>year</code> is a leap year, report: <code>year</code> is a leap year.<br>
	 * Otherwise, report: <code>year</code> is not a leap year.<br>
	 * 
	 * @param year The year to be tested.
	 * @return String reporting if <code>year</code> is a leap year
	 */
	public abstract String reportLeapYear(int year);

}
