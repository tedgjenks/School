package edu.payroll.johnson.tatum;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator{

	@Override
	public double calculatePay(double hoursWorked) {
		double pay;
		if (hoursWorked <= 40)
			pay  = hoursWorked * 10.5;
		else 
			pay = 420 + (hoursWorked- 40) * 1.5 * 10.5;
		return pay;
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		double mins = seconds / 60.0;
		double hour = (mins + minutes) / 60.0;
		double totalHours = hour + hours;
		return totalHours;
	}

	@Override
	public String formatPay(double pay) {
		return NumberFormat.getCurrencyInstance().format(pay);
	}

}
