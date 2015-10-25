/**
 * 
 */
package edu.jenks.dist.payroll;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractPayrollCalculator {

	/**
	 * <p>Converts hours, minutes, and seconds into hours.</p>
	 * 
	 * <b>precondition</b>: all parameters are nonnegative.<br>
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return hours as a floating point number.
	 */
	public abstract double convertToHours(int hours, int minutes, int seconds);
	
	/**
	 * <p>Calculate pay based on the hours worked.</p>
	 * 
	 * Workers earn $10.50 per hour for the first 40 hours. They earn overtime (time and a half) for hours over 40.<br>
	 * <b>precondition</b>: <code>hoursWorked</code> is nonnegative.<br>
	 * @param hoursWorked
	 * @return pay as a floating point number
	 */
	public abstract double calculatePay(double hoursWorked);
	
	/**
	 * <p>Format pay as a number of whole dollars and cents with $ in front.</p>
	 * 
	 * <b>precondition</b>: <code>pay</code> is nonnegative.<br>
	 * @param pay
	 * @return $[dollars].[cents]
	 */
	public abstract String formatPay(double pay);
}
