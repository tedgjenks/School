/**
 * 
 */
package edu.math.jenks.ted;

import java.util.*;
import edu.jenks.dist.math.*;
import edu.jenks.util.*;

/**
 * @author Ted Jenks
 */
public class BaseConverter extends AbstractBaseConverter {
	
	private static final byte  LOWER_CASE_ASCII_OFFSET_FROM_10 = 87;
	
	public boolean overflowsInt = false;
	
	public BaseConverter(double relativeDelta) {
		super(relativeDelta);
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractBaseConverter#convertBase(java.lang.String, int, int)
	 */
	@Override
	public String convertBase(String numberToConvert, int currentRadix, int newRadix) {
		if(currentRadix == newRadix)
			return numberToConvert;
		if(!radixSupported(currentRadix) || !radixSupported(newRadix))
			throw new IllegalArgumentException("Radix out of bounds. current : " + currentRadix + "; new: " + newRadix);
		String base10Int = ((currentRadix == 10) ? numberToConvert : convertToBase10(numberToConvert, currentRadix));
		return (newRadix == 10) ? base10Int : convertFromBase10(Integer.parseInt(base10Int), newRadix);
	}
	
	private AbstractRational buildFraction(String fractionToConvert, int currentRadix) {
		final int maxDigits = (int)(Math.log(Long.MAX_VALUE) / Math.log(currentRadix));
		final int numDigits = Math.min(fractionToConvert.length(), maxDigits);
		long denominator = (long)Math.pow(currentRadix, numDigits);
		long numerator = 0;
		for(int index = numDigits - 1; index >= 0; index--) {
			char sDigit = fractionToConvert.charAt(index);
			int digit = parseDigit(sDigit);
			numerator += digit * Math.pow(currentRadix, numDigits - index - 1);
		}
		return new Rational(numerator, denominator);
	}
	
	/**
	 * @param numberToConvert fractional part only
	 * @param currentRadix
	 * @param newRadix
	 * @return
	 */
	private String convertBaseWithFloatTerminates(String numberToConvert, int currentRadix, int newRadix) {
		AbstractRational currentFraction = buildFraction(numberToConvert, currentRadix);
		final long currentDenominator = currentFraction.getDenominator();
		int newRadixPower = newRadix;
		int places = 1;
		while(newRadixPower % currentDenominator != 0) {
			if(newRadix < Integer.MAX_VALUE / (double)newRadixPower) {
				newRadixPower *= newRadix;
				places++;
			} else {
				System.out.println("Overflow for " + numberToConvert + " in base " + currentRadix + " to base " + newRadix);
				overflowsInt = true;
				return null;
			}
		}
		long newNumerator = currentFraction.getNumerator() * (newRadixPower / currentDenominator);
		String retValue = convertFromBase10(newNumerator, newRadix);
		return StringUtil.prependCharacter(retValue, '0', places);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractBaseConverter#convertBaseWithFloat(java.lang.String, int, int)
	 */
	@Override
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix) {
		if(currentRadix == newRadix)
			return numberToConvert;
		overflowsInt = false;
		String[] wholeFraction = numberToConvert.split("\\.");
		final String curFraction = wholeFraction[1];
		String newFraction = null;
		if(willTerminate(currentRadix, newRadix))
			newFraction = convertBaseWithFloatTerminates(curFraction, currentRadix, newRadix);
		if(newFraction == null) {
			AbstractRational currentFraction = buildFraction(curFraction, currentRadix);
			double b10 = (double)currentFraction.getNumerator() / currentFraction.getDenominator();
			newFraction = convertFloatFromBase10(b10, newRadix);
		}
		return convertBase(wholeFraction[0], currentRadix, newRadix) + "." + newFraction;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.math.AbstractBaseConverter#willTerminate(int, int)
	 */
	@Override
	public boolean willTerminate(int currentRadix, int newRadix) {
		List<Integer> nrPrimes = MathUtil.factorAsPrimes(newRadix);
		List<Integer> crPrimes = MathUtil.factorAsPrimes(currentRadix);
		for(int prime : crPrimes) {
			if(!nrPrimes.contains(prime))
				return false;
		}
		return true;
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

	private String convertFloatFromBase10(final double base10Float, final int newRadix) {
		StringBuilder runningResult = new StringBuilder(5);
		double remainder = base10Float;
		for(int exponent = -1; remainder / base10Float > RELATIVE_DELTA; exponent--) {
			double placeValueB10 = Math.pow(newRadix, exponent);
			int nextFloatDigit = (int)(remainder / placeValueB10);
			remainder -= nextFloatDigit * placeValueB10;
			if(nextFloatDigit > 9)
				runningResult.append(translateAlphaDigit(nextFloatDigit));
			else
				runningResult.append(nextFloatDigit);
		}
		return runningResult.toString();
	}
	
	private String convertFromBase10(long base10Number, int newRadix) {
		StringBuilder newBase = new StringBuilder(10);
		int remainder = 0;
		long dividend = base10Number;
		do {
			remainder = (int)(dividend % newRadix);
			if(remainder > 9)
				newBase.insert(0, translateAlphaDigit(remainder));
			else
				newBase.insert(0, remainder);
			dividend /= newRadix;
		} while(dividend != 0);
		return newBase.toString();
	}
	
	private String convertToBase10(String numberToConvert, int currentRadix) {
		int base10Int = 0;
		for(int index = numberToConvert.length() - 1, placeValue = 0; index >= 0; index--, placeValue++) {
			int digit = parseDigit(numberToConvert.charAt(index));
			base10Int += (digit * Math.pow(currentRadix, placeValue));
		}
		return String.valueOf(base10Int);
	}
	
	private int parseDigit(char digit) {
		int base10Value;
		String digitString = String.valueOf(digit);
		if(digitString.matches("\\d"))
			base10Value = Integer.parseInt(digitString);
		else
			base10Value = StringUtil.convertToLowerCase(digit) - LOWER_CASE_ASCII_OFFSET_FROM_10;
		if(base10Value < 0 || base10Value >= AbstractBaseConverter.MAX_RADIX)
			throw new IllegalArgumentException("Unsupported digit character: " + digit);
		return base10Value;
	}
	
	private char translateAlphaDigit(int digitOver9) {
		if(digitOver9 < 10 || digitOver9 >= AbstractBaseConverter.MAX_RADIX)
			throw new IllegalArgumentException("Digit value not supported: " + String.valueOf(digitOver9));
		return (char)(digitOver9 + LOWER_CASE_ASCII_OFFSET_FROM_10);	
	}
	
	private boolean radixSupported(int radix) {
		return radix >= MIN_RADIX && radix < MAX_RADIX;
	}
	
	public static void main(String[] args) {
		System.out.println("Min radix: " + Character.MIN_RADIX);
		System.out.println("Max radix: " + Character.MAX_RADIX);
		BaseConverter bc = new BaseConverter(.0001);
		assert "671/4096".equals(bc.buildFraction("1237", 8).toString()) : 10;
		assert "2101".equals(bc.convertBaseWithFloatTerminates("71", 9, 3)) : 20;
		assert "001001001".contentEquals(bc.convertBaseWithFloatTerminates("111", 8, 2)) : 30;
		assert "1121.2101".contentEquals(bc.convertBaseWithFloat("47.71", 9, 3)) : 40;
		assert bc.convertBaseWithFloat("21.1", 3, 10).startsWith("7.3333") : 50;
		assert bc.convertBaseWithFloat("1.6443695", 10, 8).startsWith("1.51172") : 60;
		assert bc.convertBaseWithFloat("2.1012", 3, 13).startsWith("2.519c") : 70;
		assert "2.48a8".contentEquals(bc.convertBaseWithFloat("2.1012", 3, 12)) : 80;
		assert "1l.eg".contentEquals(bc.convertBaseWithFloat("44.638981080525596", 10, 23)) : 90;
		assert "7.9036710894270245".startsWith(bc.convertBaseWithFloat("10.62165", 7, 10)) : 100;
		assert "377.724a".contentEquals(bc.convertBaseWithFloat("ll.gc6pp", 32, 14)) : 110;
		assert bc.convertBaseWithFloat("31.17125211670092", 10, 15).startsWith("21.287e") : 120;
		assert "1v.lf6g".contentEquals(bc.convertBaseWithFloat("3f.abcd", 16, 32)) : 130;
		//System.out.println(bc.convertBaseWithFloat("0.g", 18, 3));
		//System.out.println(bc.convertBaseWithFloat("87.87", 9, 6));
		System.out.println("Terminated without error.");
	}
}
