/**
 * 
 */
package edu.jenks.payroll;

import java.util.Scanner;

/**
 * @author Ted Jenks
 *
 */
public class EnterPayroll {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Begin EnterPayroll.main()");
		Scanner scanner = new Scanner(System.in);
		try {
			PayrollCalculator pc = new PayrollCalculator();
			System.out.println("Enter the number of hours, minutes, and seconds worked.  The numbers must be integers separated by spaces.");
			int hours = 0, minutes = 0, seconds = 0;
			if(scanner.hasNextInt())
				hours = scanner.nextInt();
			if(scanner.hasNextInt())
				minutes = scanner.nextInt();
			if(scanner.hasNextInt())
				seconds = scanner.nextInt();
			double hoursWorked = pc.convertToHours(hours, minutes, seconds);
			System.out.println("You worked " + hoursWorked + " hours.");
			System.out.println("Your pay is " + pc.formatPay(pc.calculatePay(hoursWorked)) + ".");
		} catch(Throwable t) {
			scanner.close();
			t.printStackTrace();
		}
		System.out.println("End EnterPayroll.main()");
	}

}
