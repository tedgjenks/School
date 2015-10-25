package edu.payroll.wicker.marshall;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator{

	public double calculatePay(double hours) {
		return hours <= 40 ? 10.5 * hours : 420 + (hours - 40) * 15.75;
	}

	public double convertToHours(int hours, int minutes, int seconds) {
		return hours + minutes / 60.0 + seconds / 3600.0;
	}

	public String formatPay(double payAmount) {
		return NumberFormat.getCurrencyInstance().format(payAmount);
	}
	
}
