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
	 * The expected number of correct decimal places
	 */
	public static final int DECIMAL_PRECISION = 5;
	
	/**
	 * Converts a number from one base to another base.
	 * 
	 * @param numberToConvert must be a positive integer less than Integer.MAX_VALUE, and digits must be legal for currentRadix.
	 * @param currentRadix the base of numberToConvert, which must be between 2 and 16 inclusive.
	 * @param newRadix the base of the return value, which must be between 2 and 16 inclusive.
	 * @return the number in the base specified by newRadix.
	 */
	public abstract String convertBase(String numberToConvert, int currentRadix, int newRadix);
	
	/**
	 * Converts a floating number from one base to another base.<br>
	 * Precision is maintained to 3 decimal places
	 * 
	 * @param numberToConvert must be a positive floating number less than Double.MAX_VALUE, and digits must be legal for currentRadix.
	 * @param currentRadix the base of numberToConvert, which must be between 2 and 16 inclusive.
	 * @param newRadix the base of the return value, which must be between 2 and 16 inclusive.
	 * @return the number in the base specified by newRadix.
	 */
	public abstract String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix);
	
	/**
	 * Converts a binary number to base 10.
	 * 
	 * @param binaryNumber must be a positive integer less than Integer.MAX_VALUE and digits must be legal for binary numbers.
	 * @return a base ten version of binaryNumber.
	 */
	public abstract String convertBinaryToDecimal(String binaryNumber);
	
	/**
	 * Converts a base 10 number to binary.
	 * 
	 * @param decimalNumber must be a positive integer less than Integer.MAX_VALUE and digits must be legal for base 10 numbers.
	 * @return a binary version of decimalNumber.
	 */
	public abstract String convertDecimalToBinary(String decimalNumber);
}
