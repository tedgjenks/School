package edu.payroll.ball.daniel;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator 
{

	@Override
	public double calculatePay(double hour) 
	{
		double dosh = 0;
		double hoursWorked = hour;
		if ((hoursWorked - 40) <= 0)
		{
			hoursWorked = hour;
		}
		else
		{
			hoursWorked = (hoursWorked - 40) * 1.5 + (40);
		}
		
		dosh = hoursWorked * 10.50;
		
		return dosh;

	}

	@Override
	public double convertToHours(int hour, int minute, int second) 
	{
		double sum = 0;
		double chours = hour;
		System.out.println(chours);
		double cminutes = (((double)minute) / 60);
		System.out.println(cminutes);
		double cseconds = (((double)second) / 3600);
		System.out.println(cseconds);
		sum = (chours + cminutes + cseconds);
		
		return sum;
	}

	@Override
	public String formatPay(double pay) 
	{
		
		double mony = pay;
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		return currency.format(mony);
		
	}
	
	public static void main(String[] args)
	{

	}

}
