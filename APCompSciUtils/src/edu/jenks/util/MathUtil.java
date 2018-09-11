package edu.jenks.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MathUtil {
	
	private static final Pattern REAL_NUMBER_PATTERN = Pattern.compile("(-?\\d+)|(-?\\d*\\.\\d+)");
	private static final Pattern INTEGER_NUMBER_PATTERN = Pattern.compile("-?\\d+");

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
		return REAL_NUMBER_PATTERN.matcher(s).matches();
	}
	
	public static boolean isIntegerNumber(String s) {
		return INTEGER_NUMBER_PATTERN.matcher(s).matches();
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
	
	public static void main(String[] args) {
		System.out.println(calculateRadixPrecision(2, 6, 10));
	}
}
