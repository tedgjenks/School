package edu.ptc.rhodes.maddux;

import edu.jenks.dist.ptc.Xorable;

public class Xor implements Xorable {
	
	public static void main(String[] args) {
		Xor xor = new Xor();
		System.out.println(xor.xorBinaryString("0"));
	}

	// Takes in a string as an input and compares the first two values, stores that
	// boolean and converts it back into its binary
	// equivalent and compares that to the next number.
	public boolean xorBinaryString(String arg0) {
		boolean xor = false;
		if(arg0.length() == 1) {
			return arg0.equals("1") ? true : false;
		}
		boolean numFirst = false;
		boolean numSecond = false;
		String firstNum = arg0.substring(0, 1);
		for (int i = 0; i < arg0.length() - 1; i++) {
			String secondNum = arg0.substring(i + 1, i + 2);
			numFirst = firstNum.equals("1");
			numSecond = secondNum.equals("1");
			if (numFirst == numSecond) {
				xor = false;
			} else {
				xor = true;
			}
			if (xor) {
				firstNum = "1";
			} else {
				firstNum = "0";
			}
		}
		return xor;
	}
}
