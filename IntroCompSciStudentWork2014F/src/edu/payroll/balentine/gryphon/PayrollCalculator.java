package edu.payroll.balentine.gryphon;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hoursWorked) {
		double payPerHr=10.50;
		double pay=0;
		if(hoursWorked<=40){
			pay=hoursWorked*payPerHr;
			return pay;
		}
		else if(hoursWorked>40){
			pay=(((hoursWorked-40)*1.5)+40)*payPerHr;
			return pay;
		}
		else
			return 0;
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		double hrsDouble=hours;
		double minsDouble=minutes;
		double secsDouble=seconds;
		double calcHours=hrsDouble+(minsDouble/60)+(secsDouble/3600);
		return calcHours;
	}

	@Override
	public String formatPay(double pay) {
		double earned=pay;
		NumberFormat defCurrency=NumberFormat.getCurrencyInstance();
		String currency=defCurrency.format(earned);
		return currency;
	}

	public static void main(String[] args) {
		PayrollCalculator pc=new PayrollCalculator();
		double hours=pc.convertToHours(1, 120, 3600);
		System.out.println("Approximate hours worked: " + hours);
		double pay=pc.calculatePay(hours);
		System.out.println("Pay: " + pay);
		String formatPay=pc.formatPay(66);
		System.out.println(formatPay);
	}

}
