/**
 * 
 */
package edu.math.jenks.ted;

import edu.jenks.dist.math.AbstractBaseConverter;
import edu.jenks.util.MathUtil;
import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class BaseConverter extends AbstractBaseConverter {

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractBaseConverter#convertBase(java.lang.String, int, int)
	 */
	@Override
	public String convertBase(String numberToConvert, int currentRadix, int newRadix) {
		if(!radixSupported(currentRadix) || !radixSupported(newRadix))
			throw new IllegalArgumentException("Radix out of bounds. current : " + currentRadix + "; new: " + newRadix);
		//System.out.println("Convert " + numberToConvert + " base " + currentRadix + " to base " + newRadix);
		String base10Int = ((currentRadix == 10) ? numberToConvert : convertToBase10(numberToConvert, currentRadix));
		//System.out.println("converted base 10 number: " + base10Int);
		return (newRadix == 10) ? base10Int : convertFromBase10(Integer.parseInt(base10Int), newRadix);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractBaseConverter#convertBaseWithFloat(java.lang.String, int, int)
	 */
	@Override
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix) {
		if(!radixSupported(currentRadix) || !radixSupported(newRadix))
			throw new IllegalArgumentException("Radix out of bounds. current : " + currentRadix + "; new: " + newRadix);
		String convertedNumber = null;
		if(currentRadix != newRadix) {
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
		} else
			convertedNumber = numberToConvert;
		return convertedNumber;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractBaseConverter#convertBinaryToDecimal(java.lang.String)
	 */
	@Override
	public String convertBinaryToDecimal(String binaryNumber) {
		return convertBase(binaryNumber, 2, 10);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractBaseConverter#convertDecimalToBinary(java.lang.String)
	 */
	@Override
	public String convertDecimalToBinary(String decimalNumber) {
		return convertBase(decimalNumber, 10, 2);
	}

	private String convertFloatFromBase10(double base10Float, int newRadix) {
		if(base10Float > 1)
			throw new IllegalArgumentException("argument must be less than 1: " + base10Float);
		StringBuilder sb = new StringBuilder(10);
		if(newRadix == 10) {
			String s = String.valueOf(base10Float);
			sb.append(s.substring(1)); // strip leading 0
		} else {
			sb.append(".");
			double factor = base10Float;
			long newRadixPrecision = MathUtil.calculateRadixPrecision(DECIMAL_PRECISION, 10, newRadix) + 2;
			for(long count = newRadixPrecision; count > 0 && factor > 0; count--) {
				double product = factor * newRadix;
				int wholeProduct = (int)product;
				if(wholeProduct > 9) {
					char alphaDigit = translateAlphaDigit(wholeProduct);
					sb.append(alphaDigit);
				} else
					sb.append(wholeProduct);
				factor = MathUtil.stripInteger(product);
			}
		}
		return sb.toString();
	}
	
	private String convertFromBase10(int base10Number, int newRadix) {
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
	
	private double convertFloatToBase10(String numberToConvert, int currentRadix) {
		double base10Number = 0;
		int newRadixPrecision = (int)MathUtil.calculateRadixPrecision(DECIMAL_PRECISION, currentRadix, 10) + 2;
		for(int loopCount = newRadixPrecision, numberToConvertIndex = 0, numberToConvertLength = numberToConvert.length(), power = -1;
				loopCount > 0 && numberToConvertIndex < numberToConvertLength;
				loopCount--, numberToConvertIndex++, power--) {
			int digit = parseDigit(numberToConvert.charAt(numberToConvertIndex));
			base10Number += digit * Math.pow(currentRadix, power);
		}
		return base10Number;
	}
	
	private String convertToBase10(String numberToConvert, int currentRadix) {
		int base10Int = 0;
		for(int index = numberToConvert.length() - 1, placeValue = 0; index >= 0; index--, placeValue++) {
			int digit = parseDigit(numberToConvert.charAt(index));
			//System.out.println("digit " + digit + " at place value " + placeValue);
			base10Int += (digit * Math.pow(currentRadix, placeValue));
		}
		return String.valueOf(base10Int);
	}
	
	private int parseDigit(char digit) {
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
				case 'g':
					base10Value = 16;
					break;
				case 'h':
					base10Value = 17;
					break;
				case 'i':
					base10Value = 18;
					break;
				case 'j':
					base10Value = 19;
					break;
				default:
					throw new IllegalArgumentException("Unsupported digit: " + digit);
			}
		}
		return base10Value;
	}
	
	private char translateAlphaDigit(int digitOver9) {
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
			case 16:
				alphaDigit = 'g';
				break;
			case 17:
				alphaDigit = 'h';
				break;
			case 18:
				alphaDigit = 'i';
				break;
			case 19:
				alphaDigit = 'j';
				break;
			default:
				throw new IllegalArgumentException(String.valueOf(digitOver9));
		}
		return alphaDigit;
	}
	
	private boolean radixSupported(int radix) {
		return radix >= MIN_RADIX && radix < MAX_RADIX;
	}
	
	public static void main(String[] args) {
		BaseConverter bc = new BaseConverter();
		String curR = bc.convertBaseWithFloat("1.6443693833718012", 10, 8);
		System.out.println(curR);
		String newR = bc.convertBaseWithFloat(curR, 8, 16);
		System.out.println(newR);
	}
}
