/**
 * 
 */
package edu.payroll.jenks.ted;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

/**
 * @author Ted Jenks
 *
 */
public class PayrollCalculator extends AbstractPayrollCalculator {

	private final double MAX_STANDARD_HOURS_PER_WEEK = 40;
	private final double STANDARD_DOLLARS_PER_HOUR = 10.5;
	private final double OVERTIME_DOLLARS_PER_HOUR = STANDARD_DOLLARS_PER_HOUR * 1.5;
	private final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance();
	
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.payroll.AbstractPayrollCalculator#convertToHours(int, int, int)
	 */
	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		return hours + minutes/60.0 + seconds/3600.0;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.payroll.AbstractPayrollCalculator#calculatePay(double)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see edu.jenks.dist.payroll.AbstractPayrollCalculator#formatPay(double)
	 */
	@Override
	public String formatPay(double pay) {
		return CURRENCY_FORMATTER.format(pay);
	}

}
