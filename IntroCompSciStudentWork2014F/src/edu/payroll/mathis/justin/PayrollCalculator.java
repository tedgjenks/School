package edu.payroll.mathis.justin;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;

public class PayrollCalculator extends AbstractPayrollCalculator {

	@Override
	public double calculatePay(double hours) {
		double pay = 0;
		if (hours>=40){
			double pay1 = 40*10.50;
			double pay2 = (hours-40)*(10.50*1.5);
			pay = pay1+pay2;
		}
		else { 
			pay = hours*10.50;
		}
		return pay;
	}

	@Override
	public double convertToHours(int h, int m, int s) {
		double FinalHours = ((double)h)+((double)m/60)+((double)s/3600);
		return FinalHours;
	}

	@Override
	public String formatPay(double pay) {
		int dollars = (int) pay;
		System.out.println(dollars);
		double cents = ((double)pay)-dollars;
		System.out.println(cents);
		String centss = cents+"0";
		System.out.println(centss);
		String formatted = "";
		if (centss.length()>=4){
			System.out.print(centss);
			char roundchar = centss.charAt(4);
			String roundstring = ""+roundchar;
			int round = Integer.parseInt(roundstring);
			System.out.println(round);
			if (round >= 5){
				String char2 = centss.substring(3, 4);
				int char2int = Integer.parseInt(char2);
				String char1 = centss.substring(2, 3);
				int char1int = Integer.parseInt(char1);
				if (char2int!=10){
				formatted = ("$"+dollars+"."+centss.charAt(2)+(char2int+1));
				}
				else formatted = ("$"+dollars+"."+char1int+1);
			}
			else formatted = ("$"+dollars+"."+centss.charAt(2)+centss.charAt(3));
			
		}	
		else if (centss.length()>=3)
			formatted = ("$"+dollars+"."+centss.charAt(2)+"0");
		else if (centss.length()>=2)
			formatted = ("$"+dollars+"."+"00");
		/*else if (centss.length()>=3){
			String round = centss.substring(2, 3);
			int roundint = Integer.parseInt(round);
			if (roundint >= 5){
				formatted = ("$"+dollars+"."+centss.charAt(2)+"1");
			}
			else formatted = ("$"+dollars+"."+centss.charAt(2)+"0");
			
		}	
		else if (centss.length()>=2){
			String round = centss.substring(1, 2);
			int roundint = Integer.parseInt(round);
			if (roundint >= 5){
				formatted = ("$"+dollars+"."+"0"+"1");
			}
			else formatted = ("$"+dollars+"."+"00");
			
		}*/	
		System.out.print(formatted);
		return formatted;
	}

	public static void main(String[] args) {
		PayrollCalculator pc = new PayrollCalculator();
		pc.formatPay(1.555);

	}

}
