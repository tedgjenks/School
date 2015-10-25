package edu.payroll.hines.jonathan;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator 
{

	@Override
	public double calculatePay(double hoursWorked) 
	{
		double origPay =  hoursWorked * 10.50;
		double overTime = hoursWorked - 40; 
		double payCalced =  overTime * 1.5 * 10.50 + 420;
		
		if (hoursWorked <= 40)
			return origPay;
		else
			return payCalced;
		
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) 
	{
		double secsToHours = seconds/3600.0;
		double minsToHours = minutes/60.0;
		double finalHours = (hours + secsToHours + minsToHours); 
		return finalHours;
	}

	@Override
	public String formatPay(double pay) 
	{
		return NumberFormat.getCurrencyInstance().format(pay);
	}

}
