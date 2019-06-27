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
	 * The minimum supported radix (inclusive)
	 */
	public static final int MIN_RADIX = 2;
	
	/**
	 * The maximum supported radix (exclusive)
	 */
	public static final int MAX_RADIX = 36;
	
	/**
	 * float conversions must be accurate within <code>RELATIVE_DELTA</code>
	 */
	public final double RELATIVE_DELTA;
	
	/**
	 * @param relativeDelta float conversions must be accurate within <code>relativeDelta</code>
	 */
	public AbstractBaseConverter(double relativeDelta) {
		RELATIVE_DELTA = relativeDelta;
	}
	
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
	 * Precision is maintained to <code>DECIMAL_PRECISION</code> decimal places.<br>
	 * If <code>currentRadix</code> can be converted exactly to <code>newRadix</code> without a repeating decimal,<br>
	 * then perform the exact conversion.<br>
	 * If an exact conversion would require a place value that overflows <code>int</code>, perform the approximation.
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
	
	/**
	 * @param currentRadix
	 * @param newRadix
	 * @return true if <code>currentRadix</code> can be represented as a nonterminating decimal in <code>newRadix</code>, otherwise false
	 */
	public abstract boolean willTerminate(int currentRadix, int newRadix);
}
