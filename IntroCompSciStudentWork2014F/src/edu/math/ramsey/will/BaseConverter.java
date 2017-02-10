package edu.math.ramsey.will;

import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter {
	public String convertBase (String numberToConvert, int currentRadix, int newRadix) {
		String alphabet = "ABCDEFGHIJKLMNOPQSTUVWXZ";
		String num = BaseConverter.toDecimal(numberToConvert,currentRadix);
		String returnString = "";
		int newnum = Integer.valueOf(num);
		for(; newnum > 0; newnum /=newRadix)
			if(newnum%newRadix >= 10)
				returnString = alphabet.indexOf(newnum%newRadix - 10) + returnString;
			else{
			returnString = newnum%newRadix + returnString;
			}
		return returnString;
	}
	public String convertBaseWithFloat (String numberToConvert, int currentRadix, int newRadix) {
		return null;
	}
	public String convertBinaryToDecimal (String binaryNumber) {
		int holder = 0;
		for(int index = 0; index < binaryNumber.length(); index++){
			String sub = binaryNumber.substring(index,index+1);
			int digit = Integer.valueOf(sub);
			int power = binaryNumber.length()-index-1;
			holder = holder + digit * (int)Math.pow(2,power);
			System.out.println(holder + "," + digit + "," + power);
		}
		String returnString = Integer.toString(holder);
		System.out.println(returnString);
		return returnString;
	}
	public String convertDecimalToBinary (String decimalNumber) {
		int number = Integer.valueOf(decimalNumber);
		String binary = "";
		for (;number > 0; number /=2)
			binary = number%2 + binary;
		return binary;
	}
	public static String toDecimal (String num, int radix){
		num = num.toUpperCase();
		String alphabet = "ABCDEFGHIJKLMNOPQSTUVWXZ";
		int number = 0;
		String returnString = "";
		for(int index = 0; index < num.length(); index++){
			String stringThere = num.substring(index,index+1);
			int isthere = alphabet.indexOf(stringThere);
			int power = num.length()-index-1;
			if(isthere==-1){
				int digit = Integer.valueOf(stringThere);
				number = number + Integer.valueOf(stringThere) *(int)Math.pow(radix,power);
			}
			else{ 
				number = number + (isthere+10) * (int)Math.pow(radix,power);
			}
		}
		returnString = String.valueOf(number);
		return returnString;
	}
	public static String toDecimalWithFloat (String num, int radix){
		return null;
	}
}