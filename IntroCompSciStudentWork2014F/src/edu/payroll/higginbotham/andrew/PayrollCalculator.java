package edu.payroll.higginbotham.andrew;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hoursWorked) {
		double totalPay = 0;
		totalPay = hoursWorked * 10.50;
		if(hoursWorked > 40){
			double extraHours = hoursWorked - 40;
			double paysum1 = (extraHours * 1.5 ) * 10.50;
			double paysum2 = ((hoursWorked - extraHours) * 10.50);
			totalPay = paysum1 + paysum2;
		}
		
		return totalPay;
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) 
	{
		double calcHours = ( hours + minutes/60.0 + seconds/3600.0);
		return calcHours;
	}

	@Override
	public String formatPay(double pay) {
		
		return NumberFormat.getCurrencyInstance().format(pay);
	}
	
	public static void main(String[] args){
		
		PayrollCalculator pc = new PayrollCalculator();
		double hoursAct = pc.convertToHours(3, 0, 0);
		double payAct = pc.calculatePay(hoursAct);
		String payFormat = pc.formatPay(payAct);
		System.out.println("Hours = " + hoursAct);
		System.out.println("Pay = " + payAct);
		System.out.println("Actual pay = " + payFormat);
	}
}
