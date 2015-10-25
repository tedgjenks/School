package edu.payroll.ramsey.will;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {
	public static void main (String [] args){
		PayrollCalculator pc = new PayrollCalculator();
		System.out.println(pc.convertToHours(1, 1, 1));
	}

	@Override
	public double calculatePay(double hours) {
		// TODO Auto-generated method stub
		double pay = 0;
		if (hours > 40){
			hours-= 40;
			pay = 40*10.50;
			double overTime = hours * 1.5;
			pay += overTime * 10.50;
		}
		else pay = hours*10.50;
		return pay;
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		// TODO Auto-generated method stub
		double returnHours = hours + (double)minutes/60 + (double)seconds/3600;
		return returnHours;
	}

	@Override
	public String formatPay(double pay) {
		// TODO Auto-generated method stub
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String money = nf.format(pay);
		return money;
	}

}
