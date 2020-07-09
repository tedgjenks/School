/**
 * 
 */
package edu.ptc.jenks.ted;

import edu.jenks.dist.ptc.Xorable;
import static java.lang.System.out;

/**
 * @author Ted Jenks
 *
 */
public class Xor implements Xorable {

	/* (non-Javadoc)
	 * @see edu.jenks.dist.ptc.Xorable#xorBinaryString(java.lang.String)
	 */
	@Override
	public boolean xorBinaryString(String binaryString) {
		boolean current = binaryString.charAt(0) == '1';
		for(int index = 1; index < binaryString.length(); index++) {
			boolean next = binaryString.charAt(index) == '1';
			current = (current && !next) || (!current && next);
		}
		return current;
	}

	public static void main(String[] args) {
		out.println("Begin");
		Xor obj = new Xor();
		assert(obj.xorBinaryString("1"));
		assert(!obj.xorBinaryString("0"));
		assert(!obj.xorBinaryString("11"));
		assert(obj.xorBinaryString("10"));
		assert(obj.xorBinaryString("01"));
		assert(!obj.xorBinaryString("00"));
		assert(!obj.xorBinaryString("11011"));
		assert(obj.xorBinaryString("11010"));
		out.println("End");
	}

}
