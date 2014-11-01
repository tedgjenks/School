package edu.jenks.unit3;

import java.util.Scanner;

/**
 * @author Ted Jenks
 *
 */
public class GregorianCalendar {
	
	private static final short FIRST_YEAR_GC = 1582;
	
	/**
	 * Determine if the year is a leap year.
	 * 
	 * @param year the year to test, which must not be less than 1582.
	 * @return true if year is a leap year, otherwise false.
	 * @throws IllegalArgumentException if year is less than 1582.
	 */
	public static boolean isLeapYear(int year) throws IllegalArgumentException {
		if(year < FIRST_YEAR_GC)
			throw new IllegalArgumentException("year may not be less than " + FIRST_YEAR_GC);
		return (year % 4 == 0) && ((year % 100 != 0) || year % 400 == 0);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter year:");
		try {
			int year = scanner.nextInt();
			System.out.println(isLeapYear(year));
		} catch(Throwable t) {
			t.printStackTrace(System.err);
			System.err.flush();
		}
		scanner.close();
		System.out.println("End program.");
	}

}
