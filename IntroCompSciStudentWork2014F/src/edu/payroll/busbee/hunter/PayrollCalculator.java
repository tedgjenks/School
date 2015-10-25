package edu.payroll.busbee.hunter;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hoursWorked) {
		//float workedHours = hoursWorked;
		if(hoursWorked - 40 >= 0){
			double workHours = 40 * 10.5, overTime = ((hoursWorked - 40) * 1.5) * 10.5;
			double moneyEarned = workHours + overTime;
			return moneyEarned;
		}
		else{
			return hoursWorked * 10.5;
		}
		
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		double hoursFloat, minutesFloat = minutes, secondsFloat = seconds;
		hoursFloat = hours + (minutesFloat / 60) + (secondsFloat / 3600);
		return hoursFloat;
	}

	@Override
	public String formatPay(double pay) {
		return NumberFormat.getCurrencyInstance().format(pay);
	}

}
