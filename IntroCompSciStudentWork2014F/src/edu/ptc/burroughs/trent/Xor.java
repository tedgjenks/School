package edu.ptc.burroughs.trent;

import edu.jenks.dist.ptc.*;

public class Xor implements Xorable {

	public static void main(String[] args) {
		//this calls the method that does the calculations 
		Xor test = new Xor();
		test.xorBinaryString("1101");
	}

	public boolean xorBinaryString(String args) {
		System.out.println(args);
		for(int i = 0; i < args.length()-1; i++) {
			//this checks to see if it is false and if so sets the substring to 0 else it sets it to 1
			if( args.substring(i, i+1).equals(args.substring(i+1, i+2))){
				args = "0" + args.substring(i+2);
				i--;
			} else {
				args = "1" + args.substring(i+2);
				i--;
			}
		}
		//this return true or false based off the value
		if(args.equals("1")) {
			return true;
		}
		return false;
	}

}
