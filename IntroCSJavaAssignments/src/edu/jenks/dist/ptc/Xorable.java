package edu.jenks.dist.ptc;

/**
 * @author Ted Jenks
 *
 */
public interface Xorable {

	/**
	 * Execute xor operations on the characters from left to right.<br>
	 * 0 is false. 1 is true.
	 * 
	 * @param binaryString not null and not empty; contains only zeros and ones
	 * @return true or false
	 */
	boolean xorBinaryString(String binaryString);
}
