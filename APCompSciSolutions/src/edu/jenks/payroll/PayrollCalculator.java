package edu.jenks.payroll;

import java.text.NumberFormat;

/**
 * @author JenksT
 *
 */
public class PayrollCalculator {
	private final double MAX_STANDARD_HOURS_PER_WEEK = 40;
	private final double STANDARD_DOLLARS_PER_HOUR = 10.5;
	private final double OVERTIME_DOLLARS_PER_HOUR = STANDARD_DOLLARS_PER_HOUR * 1.5;
	
	/**
	 * Converts hours, minutes, and seconds into hours.
	 * 
	 * @param hours
	 * @param minutes
	 * @param seconds
	 * @return hours as a floating point number.
	 */
	public double convertToHours(int hours, int minutes, int seconds) {
		return hours + minutes/60.0 + seconds/3600.0; 
	}
	
	/**
	 * Calculate pay based on the hours worked.</br>
	 * Workers earn $10.50 for the first 40 hours. They earn overtime (time and a half) for hours over 40.
	 * 
	 * @param hoursWorked
	 * @return pay as a floating point number
	 */
	public double calculatePay(double hoursWorked) {
		double standardHours = 0, overtimeHours = 0;
		if(hoursWorked < MAX_STANDARD_HOURS_PER_WEEK)
			standardHours = hoursWorked;
		else {
			standardHours = MAX_STANDARD_HOURS_PER_WEEK;
			overtimeHours = hoursWorked - MAX_STANDARD_HOURS_PER_WEEK;
		}
		return (standardHours * STANDARD_DOLLARS_PER_HOUR) + (overtimeHours * OVERTIME_DOLLARS_PER_HOUR);
	}
	
	/**
	 * Format pay as a number of whole dollars and cents with $ in front.
	 * 
	 * @param pay
	 * @return $[dollars].[cents]
	 */
	public String formatPay(double pay) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return nf.format(pay);
	}
}
