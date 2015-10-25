package edu.payroll.burroughs.lauren;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hoursWorked) {
		double ePay = 0;
		double overtime;
		double regPay;
		if(hoursWorked - 40 > 0){
			overtime = hoursWorked - 40;
			ePay = overtime * (10.50 * 1.5);
			regPay = 40 * 10.50;
			return ePay + regPay;
		}
		else {
			regPay = hoursWorked * 10.50;
			return regPay;
		}
		
		
		
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		double hoursWorked = hours + (double)minutes / 60 + (double) seconds / 3600;		
		return hoursWorked;
	}

	@Override
	public String formatPay(double pay) {
		NumberFormat form = NumberFormat.getCurrencyInstance();
		return form.format(pay);
			
	
	}

}
