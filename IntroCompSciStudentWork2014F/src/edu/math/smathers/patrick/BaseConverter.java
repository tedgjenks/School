package edu.math.smathers.patrick;
import edu.jenks.dist.math.*;
public class BaseConverter extends AbstractBaseConverter{
	public BaseConverter(){}
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix){
		String returnString = "";
		numberToConvert = numberToConvert.toLowerCase();
		String dec = convertToDecimal(numberToConvert, currentRadix);
		int decimalNumber = Integer.parseInt(dec);
		if(newRadix != 10){
			while(!(decimalNumber <= 0)){
				int temp = decimalNumber % newRadix;
				if(temp > 9)
					returnString += getLetter(temp);
				else
						returnString += decimalNumber % newRadix;
				decimalNumber /= newRadix;
			}
		}
		else{
			return Integer.toString(decimalNumber);
		}
		returnString = new StringBuilder(returnString).reverse().toString();
		return returnString;
	}
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix){
		return "";
	}
	public String convertToDecimal (String number, int currentRadix){
		int returnNumber = 0;
		number.toLowerCase();
		for(int i = number.length() - 1; i >= 0; i--){
			
			int digit = Character.getNumericValue(number.charAt(i));
			int base = (number.length() - 1) - i;
			if(digit != 0){
				returnNumber += digit * (Math.pow(currentRadix, base));
			}
		}
		//returnNumber /= 2;
		
		String returnString = Integer.toString(returnNumber);
		return returnString;
	}
	public String getLetter (int numberToConvert){
		if(numberToConvert == 10) return "a";
		if(numberToConvert == 11) return "b";
		if(numberToConvert == 12) return "c";
		if(numberToConvert == 13) return "d";
		if(numberToConvert == 14) return "e";
		if(numberToConvert == 15) return "f";
		if(numberToConvert == 16) return "g";
		if(numberToConvert == 17) return "h";
		if(numberToConvert == 18) return "i";
		if(numberToConvert == 19) return "j";
		if(numberToConvert == 20) return "k";
		return "";
	}
	public String convertBinaryToDecimal(String binaryNumber){
		String returnString = "";
		int number;
		int decimal = 0;
		int length = binaryNumber.length() - 1;
		for(int i = binaryNumber.length() - 1; i >= 0; i--){
			int base = length - i;
			number = Character.getNumericValue(binaryNumber.charAt(i));
			if(number == 1){
				decimal += number * (Math.pow(2, base));
			}
	
		}
		returnString = Integer.toString(decimal);
		return returnString;
	}
	public String convertDecimalToBinary(String decimalNumber){
		int convertNumber = Integer.parseInt(decimalNumber);
		int power = 0;
		String converted = "";
		String returnString = "";
		boolean go = true;
		while(convertNumber != 0){
			if(convertNumber % 2 == 0){
				converted += 0;
			}
			else{
				converted += 1;
			}
			convertNumber /= 2;
		}
		for(int i = converted.length() - 1; i >= 0 ; i--){
			returnString += converted.charAt(i);
		}
		return returnString;
	}
	
	
}
	