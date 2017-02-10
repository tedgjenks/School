/**
 * 
 */
package edu.jenks.dist.math;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractBaseConverter {
	
	/**
	 * The default expected number of correct decimal places in the converted number
	 */
	public static final int DECIMAL_PRECISION = 5;
	
	/**
	 * The maximum number of correct decimal places in the converted number
	 */
	public static final int MAX_DECIMAL_PRECISION = 30;
	
	/**
	 * The minimum supported radix (2 inclusive)
	 */
	public static final int MIN_RADIX = 2;
	
	/**
	 * The maximum supported radix (20 exclusive)
	 */
	public static final int MAX_RADIX = 20;
	
	/**
	 * Converts a number from one base to another base.
	 * 
	 * @param numberToConvert must be a positive integer less than <code>Integer.MAX_VALUE</code>, and digits must be legal for currentRadix.
	 * @param currentRadix the base of numberToConvert.
	 * @param newRadix the base of the return value.
	 * @return the number in the base specified by newRadix.
	 */
	public abstract String convertBase(String numberToConvert, int currentRadix, int newRadix);
	
	/**
	 * Converts a floating number from one base to another base.<br>
	 * Precision is maintained to <code>DECIMAL_PRECISION</code> decimal places
	 * 
	 * @param numberToConvert must be a positive floating number less than <code>Double.MAX_VALUE</code>, and digits must be legal for currentRadix.
	 * @param currentRadix the base of numberToConvert.
	 * @param newRadix the base of the return value.
	 * @return the number in the base specified by newRadix.
	 */
	public abstract String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix);
	
	/**
	 * Converts a binary number to base 10.
	 * 
	 * @param binaryNumber must be a positive integer less than <code>Integer.MAX_VALUE</code> and digits must be legal for binary numbers.
	 * @return a base ten version of binaryNumber.
	 */
	public abstract String convertBinaryToDecimal(String binaryNumber);
	
	/**
	 * Converts a base 10 number to binary.
	 * 
	 * @param decimalNumber must be a positive integer less than <code>Integer.MAX_VALUE</code> and digits must be legal for base 10 numbers.
	 * @return a binary version of decimalNumber.
	 */
	public abstract String convertDecimalToBinary(String decimalNumber);
}
