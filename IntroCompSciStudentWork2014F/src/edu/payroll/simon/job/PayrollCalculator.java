package edu.payroll.simon.job;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator
extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hoursWorkerd) {
		// TODO Auto-generated method stub
		double finalPay = 0;
		double Overtime = 0;
		if (hoursWorkerd <= 40) {
			finalPay = (hoursWorkerd*10.50);
			return finalPay;
		}
		else if 
		(hoursWorkerd>40) {
			Overtime = ((hoursWorkerd-40) + (hoursWorkerd*15.75) + (420));
		}
			return Overtime;
		
		
	}
	
	@Override
	public double convertToHours(int Hours, int Min, int Sec) {
		// TODO Auto-generated method stub
		double Totalhours = (Hours +(Min/60.0) + (Sec/3600.0));
		return Totalhours;
	}

	@Override
	public String formatPay(double pay) {
		// TODO Auto-generated method stub 
		return NumberFormat.getCurrencyInstance().format(pay);
	
	}

}
