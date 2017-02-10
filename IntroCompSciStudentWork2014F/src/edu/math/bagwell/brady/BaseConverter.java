package edu.math.bagwell.brady;
import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter{
	
	public BaseConverter() {}
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix) {
		
		int decNum = 0, valueOfDigit = 0;
		String nextNum = "";
		String rem = "";
		
		for (int i = 0; i < numberToConvert.length(); i++) {
			valueOfDigit = valueToDec(Character.toUpperCase(numberToConvert.charAt(numberToConvert.length() - i - 1)));
			decNum += Math.pow(currentRadix, i) * valueOfDigit;
		}
		
		while(decNum != 0) {
			rem = "" + decToValue(decNum % newRadix);
			decNum = decNum / newRadix;
			nextNum = rem + nextNum;
		}
		
		return nextNum;
	}
	
	public int valueToDec(char num) {
		if (Character.isDigit(num)) return Character.getNumericValue(num);
		else return num - 65 + 10;
	}
	
	public char decToValue(int num) {
		if (num >= 0 && num <= 9) return (char)(48 + num);
		if (num >= 10) return (char)(55 + num);
		return '0';
	}
	
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix) { 
		
		int decNum = 0;
		double floatNum = 0;
		int valueOfDigit = 0;
		String nextNum = "", nextNumFloat = "";
		String rem = "";
		
		String regularPart = numberToConvert.substring(0, numberToConvert.indexOf("."));
		String floatingPart = numberToConvert.substring(numberToConvert.indexOf(".") + 1);
		
		//System.out.println(regularPart + "\n" + floatingPart);
		
		for (int i = 0; i < regularPart.length(); i++) {
			valueOfDigit = valueToDec(Character.toUpperCase(regularPart.charAt(regularPart.length() - i - 1)));
			decNum += Math.pow(currentRadix, i) * valueOfDigit;
		}
		
/* 		for (int i = floatingPart.length() - 1; i >= 0; i--) {		
			valueOfDigit = valueToDec(Character.toUpperCase(floatingPart.charAt(floatingPart.length() - i - 1)));
			floatNum += Math.pow(currentRadix, i) * valueOfDigit;
		} */
		
		for (int i = -1; i >= -1 * floatingPart.length(); i--) {
			valueOfDigit = valueToDec(Character.toUpperCase(floatingPart.charAt((i * -1) - 1)));
			floatNum += (double)(Math.pow(currentRadix, i) * valueOfDigit);
			//System.out.println(decNum);
		}
		
/* 		String newRegularPart = ("" + decNum).substring(0, ("" + decNum).indexOf("."));
		String newFloatingPart = ("" + decNum).substring(("" + decNum).indexOf(".") + 1, 5); */
		
		/* String newRegularPart = "" + decNum;
		String newFloatingPart = "" + floatNum; */
		
		System.out.println(decNum + "\n" + floatNum);
		//1.01 0, -1, -2
		
		int reg = decNum;
		
		while(reg != 0) {
			rem = "" + decToValue(reg % newRadix);
			reg = reg / newRadix;
			nextNum = rem + nextNum;
		}
		
		System.out.println(nextNum);
		
		double fl = floatNum;
		//System.out.println(fl);
		rem = "";
		
		for (int i = 0; i < 10; i++) {
			rem = "" + decToValue((int)(fl * newRadix));
				//System.out.println(decToValue((int)fl * newRadix));
			fl = (fl * newRadix) - ((int)(fl * newRadix));
			nextNumFloat = nextNumFloat + rem;
		}
		
		String done = nextNum + "." + nextNumFloat;
		
		return done;
	}
	
	public String convertBinaryToDecimal(String binaryNumber) { 
		
		int num = 0;
		String intReturn = "";
		
		for (int i = 0; i < binaryNumber.length(); i++)
			if (binaryNumber.charAt(binaryNumber.length() - i - 1) == '1')
				num += Math.pow(2, i);
		
		intReturn = "" + num;
		return intReturn;
	}
	
	public String convertDecimalToBinary(String decimalNumber) {
		
		int num = Integer.parseInt(decimalNumber), rem;
		String bin = "";
		
		while(num != 0) {
			rem = num % 2;
			num = num / 2;
			bin = rem + bin;
		}
		
		return bin;
	}
	
	public static void main(String[] args) {
		
		BaseConverter bc = new BaseConverter();
		
		//System.out.println(bc.convertDecimalToBinary("327"));
		//System.out.println(bc.convertBinaryToDecimal("11"));
		//System.out.println(bc.convertBase("1a7h",18,4));
		System.out.println(bc.convertBaseWithFloat("52.294254855653286",10,8));
	} 
}