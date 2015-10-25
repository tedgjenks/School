package edu.payroll.mariscal.juan;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hours) {
		// TODO Auto-generated method stub
		double pay = 0;
		if (hours > 40 ){
			double workpay = 40 * 10.5;
			double overtime = (hours-40)*1.5;
			double overpay= overtime * 10.5;
			pay = workpay + overpay;
		}
		else  {
			pay = hours * 10.5;
			
		}
		System.out.println(pay);
		return pay;
	}

	@Override
	public double convertToHours(int hrs, int min, int sec) {
		// TODO Auto-generated method stub
		double hours = (double)hrs + ((double)min/60) + ((double)sec/3600);
		System.out.println(hours);
		return hours;
	}

	@Override
	public String formatPay(double pay) {
		// TODO Auto-generated method stub
		int dollars = (int) pay;
		int cents = (int)(pay * 1000) - (dollars * 1000);
		
		String cent= cents+ "";
		if (cent.length()== 2)
			cent = "0" + cent.charAt(0) + cent.charAt(1) ;
		if (cent.length()<= 1)
			cent = "00" + cent.charAt(0)  ;
		String char1 = cent.substring(0,1);
		int first = Integer.parseInt(char1);
		String char2 = cent.substring(1,2);
		int second = Integer.parseInt(char2);
		String char3 = cent.substring(2,3);
		int third = Integer.parseInt(char3);
		
		
		if (third >=5 ){
			second++ ;
		}
		if (second > 9){
			first++;
			second = 0;
		}
		String rcent = "" + first + second ;
		
		String payroll = "$" + dollars + "." + rcent;
		System.out.println(payroll);
		return payroll;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PayrollCalculator pc = new PayrollCalculator();
		pc.formatPay(1.586);
		pc.calculatePay(40);
		pc.convertToHours(10, 2563, 458);
	}

}
