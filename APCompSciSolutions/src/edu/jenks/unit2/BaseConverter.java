package edu.jenks.unit2;

import edu.jenks.util.MathUtil;
import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class BaseConverter {
	
	private static final int DECIMAL_PRECISION = 5;
	
	/**
	 * Converts a number from one base to another base.
	 * 
	 * @param numberToConvert must be a positive integer less than Integer.MAX_VALUE, and digits must be legal for currentRadix.
	 * @param currentRadix the base of numberToConvert, which must be between 2 and 16 inclusive.
	 * @param newRadix the base of the return value, which must be between 2 and 16 inclusive.
	 * @return the number in the base specified by newRadix.
	 */
	public static String convertBase(String numberToConvert, int currentRadix, int newRadix) {
		//System.out.println("Convert " + numberToConvert + " base " + currentRadix + " to base " + newRadix);
		String base10Int = ((currentRadix == 10) ? numberToConvert : convertToBase10(numberToConvert, currentRadix));
		//System.out.println("converted base 10 number: " + base10Int);
		return (newRadix == 10) ? base10Int : convertFromBase10(Integer.parseInt(base10Int), newRadix);
	}
	
	/**
	 * Converts a floating number from one base to another base.</br>
	 * Precision is maintained to 3 decimal places
	 * 
	 * @param numberToConvert must be a positive floating number less than Double.MAX_VALUE, and digits must be legal for currentRadix.
	 * @param currentRadix the base of numberToConvert, which must be between 2 and 16 inclusive.
	 * @param newRadix the base of the return value, which must be between 2 and 16 inclusive.
	 * @return the number in the base specified by newRadix.
	 */
	public static String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix) {
		String convertedNumber = null;
		int decimalIndex = numberToConvert.indexOf(".");
		int numberToConvertLength = numberToConvert.length();
		if(decimalIndex < 0) // no decimal
			convertedNumber = convertBase(numberToConvert, currentRadix, newRadix);
		else if(decimalIndex == numberToConvertLength - 1) // nothing after decimal
			convertedNumber = convertBase(numberToConvert.substring(0, numberToConvertLength - 1), currentRadix, newRadix);
		else {
			StringBuilder sb = new StringBuilder(10);
			if(decimalIndex == 0) // nothing before the decimal
				sb.append("0");
			else
				sb.append(convertBase(numberToConvert.substring(0, decimalIndex), currentRadix, newRadix));
			
			//System.out.println("Value before float conversion: " + sb);
			String afterDecimal = numberToConvert.substring(decimalIndex + 1, numberToConvertLength);
			//System.out.println("Float part before conversion: " + afterDecimal);
			double base10Float = currentRadix == 10 ? Double.parseDouble("." + afterDecimal) : convertFloatToBase10(afterDecimal, currentRadix);
			//System.out.println("Float part converted to base 10: " + base10Float);
			sb.append(convertFloatFromBase10(base10Float, newRadix));
			convertedNumber = sb.toString();
		}
		return convertedNumber;
	}
	
	/**
	 * Converts a binary number to base 10.
	 * 
	 * @param binaryNumber must be a positive integer less than Integer.MAX_VALUE and digits must be legal for binary numbers.
	 * @return a base ten version of binaryNumber.
	 */
	public static String convertBinaryToDecimal(String binaryNumber) {
		return convertBase(binaryNumber, 2, 10);
	}
	
	/**
	 * Converts a base 10 number to binary.
	 * 
	 * @param decimalNumber must be a positive integer less than Integer.MAX_VALUE and digits must be legal for base 10 numbers.
	 * @return a binary version of decimalNumber.
	 */
	public static String convertDecimalToBinary(String decimalNumber) {
		return convertBase(decimalNumber, 10, 2);
	}
	
	private static String convertFloatFromBase10(double base10Float, int newRadix) {
		if(base10Float > 1)
			throw new IllegalArgumentException("argument must be less than 1");
		StringBuilder sb = new StringBuilder(10);
		if(newRadix == 10) {
			String s = String.valueOf(base10Float);
			sb.append(s.substring(1)); // strip leading 0
		} else {
			sb.append(".");
			double factor = base10Float;
			int newRadixPrecision = MathUtil.calculateRadixPrecision(DECIMAL_PRECISION, newRadix);
			for(int count = newRadixPrecision; count > 0 && factor > 0; count--) {
				double product = factor * newRadix;
				if(product > 9) {
					char alphaDigit = translateAlphaDigit((int)product);
					sb.append(alphaDigit);
				} else
					sb.append((int)product);
				factor = MathUtil.stripInteger(product);
			}
		}
		return sb.toString();
	}
	
	private static String convertFromBase10(int base10Number, int newRadix) {
		StringBuilder newBase = new StringBuilder(10);
		int remainder = 0, dividend = base10Number;
		do {
			remainder = dividend % newRadix;
			if(remainder > 9) {
				char alphaDigit = translateAlphaDigit(remainder);
				newBase.insert(0, alphaDigit);
			} else
				newBase.insert(0, remainder);
			//System.out.println("Dividend = " + dividend + " and current number is " + newBase);
			dividend /= newRadix;
		} while(dividend != 0);
		return newBase.toString();
	}
	
	private static double convertFloatToBase10(String numberToConvert, int currentRadix) {
		double base10Number = 0;
		int newRadixPrecision = MathUtil.calculateRadixPrecision(DECIMAL_PRECISION, currentRadix);
		for(int loopCount = newRadixPrecision, numberToConvertIndex = 0, numberToConvertLength = numberToConvert.length(), power = -1;
				loopCount > 0 && numberToConvertIndex < numberToConvertLength;
				loopCount--, numberToConvertIndex++, power--) {
			int digit = parseDigit(numberToConvert.charAt(numberToConvertIndex));
			base10Number += digit * Math.pow(currentRadix, power);
		}
		return base10Number;
	}
	
	private static String convertToBase10(String numberToConvert, int currentRadix) {
		int base10Int = 0;
		for(int index = numberToConvert.length() - 1, placeValue = 0; index >= 0; index--, placeValue++) {
			int digit = parseDigit(numberToConvert.charAt(index));
			//System.out.println("digit " + digit + " at place value " + placeValue);
			base10Int += (digit * Math.pow(currentRadix, placeValue));
		}
		return String.valueOf(base10Int);
	}
	
	private static int parseDigit(char digit) {
		int base10Value;
		String digitString = String.valueOf(digit);
		if(digitString.matches("\\d"))
			base10Value = Integer.parseInt(digitString);
		else {
			digit = StringUtil.convertToLowerCase(digit);
			switch(digit) {
				case 'a':
					base10Value = 10;
					break;
				case 'b':
					base10Value = 11;
					break;
				case 'c':
					base10Value = 12;
					break;
				case 'd':
					base10Value = 13;
					break;
				case 'e':
					base10Value = 14;
					break;
				case 'f':
					base10Value = 15;
					break;
				default:
					throw new IllegalArgumentException("Unsupported digit: " + digit);
			}
		}
		return base10Value;
	}
	
	private static char translateAlphaDigit(int digitOver9) {
		char alphaDigit;
		switch(digitOver9) {
			case 10:
				alphaDigit = 'a';
				break;
			case 11:
				alphaDigit = 'b';
				break;
			case 12:
				alphaDigit = 'c';
				break;
			case 13:
				alphaDigit = 'd';
				break;
			case 14:
				alphaDigit = 'e';
				break;
			case 15:
				alphaDigit = 'f';
				break;
			default:
				throw new IllegalArgumentException(String.valueOf(digitOver9));
		}
		return alphaDigit;
	}
	
}
