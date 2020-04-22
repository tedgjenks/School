package edu.ptc.salter.bella;

import edu.jenks.dist.ptc.*;

public class Xor implements Xorable {
		public static void main(String[] args) {
			Xor sim = new Xor(); //object used to run method
			String input = "10011"; //variable used to store value to evaluate
			boolean result = sim.xorBinaryString(input); 
			System.out.println(result);
		}
		public Xor() {
			
		}
		/*
		 * This method is intended to simulate the exclusive or operation
		 */
		public boolean xorBinaryString(String str) {
			while(str.length() > 1) {
				str = eval(str.substring(0,1), str.substring(1,2)) + str.substring(2);
			}
			if(str.substring(0).contentEquals("1")) {
				return true;
			} else {
				return false;
			}
			
		}
		/* 
		 * Helper method used to evaluate the result of two values
		 */
		public String eval(String num1, String num2) {
			if(num1.equals("1")) {
				if(num2.equals("1" )) {
					return "0";
				} else {
					return "1";
				}
			} else {
				if(num2.equals("1")) {
					return "1";
				} else {
					return "0";
				}
			}
		}
	}

