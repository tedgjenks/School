package edu.payroll.slimmer.ben;

import java.text.NumberFormat;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator
extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hrs) {
		if (hrs>40.0){
			double over= hrs-40.0;
			return 420.0+(over*1.5*10.5);
		}
		else
			return hrs*10.5;
	}

	@Override
	public double convertToHours(int hours, int minutes, int seconds) {
		double hrs=(double)hours;
		hrs+= minutes/60.0;
		hrs+= seconds/3600.0;
		return hrs;
	}

	@Override
	public String formatPay(double pay) {
		return NumberFormat.getCurrencyInstance().format(pay);
	}

}
