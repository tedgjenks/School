package edu.jenks.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MathUtil {
	
	private static final Pattern REAL_NUMBER_PATTERN = Pattern.compile("(-?\\d+)|(-?\\d*\\.\\d+)");
	private static final Pattern INTEGER_NUMBER_PATTERN = Pattern.compile("-?\\d+");

	public static boolean sameSign(long l1, long l2) {
		return (l1 >= 0) ^ (l2 < 0);
	}
	
	public static double stripInteger(double number) {
		int wholePart = (int)number;
		return number - wholePart;
	}
	
	public static boolean equals(double d1, double d2, double delta) {
		return Math.abs(d1 - d2) <= delta;
	}
	
	public static boolean equalsRelative(double d1, double d2, double delta) {
		return Math.abs(d1 - d2) <= Math.max(Math.abs(d1),  Math.abs(d2)) * delta;
	}
	
	public static long calculateRadixPrecision(double precisionCurrentRadix, double currentRadix, double desiredRadix) {
		return Math.round(precisionCurrentRadix / (Math.log10(desiredRadix) / Math.log10(currentRadix)));
	}
	
	public static boolean isRealNumber(String s) {
		return s != null && REAL_NUMBER_PATTERN.matcher(s).matches();
	}
	
	public static boolean isIntegerNumber(String s) {
		return s != null && INTEGER_NUMBER_PATTERN.matcher(s).matches();
	}
	
	public static int findGreatestCommonFactor(int num1, int num2) throws IllegalArgumentException {
		if(num1 <= 0 || num2 <= 0)
			throw new IllegalArgumentException("Parameters must be greater than zero.");
		while (num1 != num2) {
			if (num1 > num2)
				num1 = num1 - num2;
			else
				num2 = num2 - num1;
		}
		return num1;
	}
	
	public static List<Integer> factorAsPrimes(int number) {
		if(number < 0)
			number *= -1;
		List<Integer> factors = new ArrayList<Integer>();
		int quotient = number;
		for(int i = 2; i <= quotient / i; i++) {
			while(quotient % i == 0) {
				factors.add(i);
				quotient /= i;
			}
		}
		if(quotient > 1)
			factors.add(quotient);
		return factors;
	}
	
	/**
	 * @param smallPos a small positive integer
	 * @param largePos greater than <code>smallPos</code>
	 * @return true if <code>largePos</code> is a power of <code>smallPos</code>
	 */
	public static boolean isPower(long smallPos, long largePos) {
		if(smallPos < 1 || smallPos > largePos)
			throw new IllegalArgumentException("smallPos must be greater than or equal to 1 and less than or equal to largePos");
        if (smallPos == 1) 
            return largePos == 1; 
        long pow = 1; 
        while (pow < largePos) 
            pow = pow * smallPos; 
        return pow == largePos; 
    }
	
	public static void main(String[] args) {
		System.out.println(calculateRadixPrecision(2, 6, 10));
	}
}
