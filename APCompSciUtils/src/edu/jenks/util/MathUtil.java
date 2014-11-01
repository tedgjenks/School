package edu.jenks.util;

import java.util.regex.Pattern;

public class MathUtil {
	
	private static final Pattern REAL_NUMBER_PATTERN = Pattern.compile("(-?\\d+)|(-?\\d*\\.\\d+)");

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
	
	public static int calculateRadixPrecision(double radix10Precision, double desiredRadix) {
		return (int)(radix10Precision / Math.log10(desiredRadix));
	}
	
	public static boolean isRealNumber(String s) {
		return REAL_NUMBER_PATTERN.matcher(s).matches();
	}
}
