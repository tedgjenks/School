package edu.payroll.shearer.richard;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double workedhours) {
		double money = 0;
		if (workedhours > 40) {
			double extrahours;
			double extramoney;
			double overtyme;
			extrahours = workedhours - 40;
				overtyme = extrahours*1.50;
					extramoney = overtyme*10.50;
			money = (40*10.50) + extramoney; 
			}
		else if (workedhours <= 40) {
			money = workedhours*10.50;
		}
		return money;
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		double workedhours = hours + minutes/60.0 + seconds/3600.0;
		return workedhours;
	}

	@Override
	public String formatPay(double money) {
	double pay = money;
	return NumberFormat.getCurrencyInstance().format(pay); 
	}
	public static void main(String[] args){
		PayrollCalculator pc = new PayrollCalculator();
		double hoursAct = pc.convertToHours(90, 20, 3);
		double payAct = pc.calculatePay(hoursAct);
		String payFormat = pc.formatPay(payAct);
		System.out.println("Hours = " + hoursAct);
		System.out.println("Pay = " + payAct);
		System.out.println("Actual pay = " + payFormat);
		// HEY MR. JENKS!!!!!!!!!!!!!!!111111111111
	}
	
}