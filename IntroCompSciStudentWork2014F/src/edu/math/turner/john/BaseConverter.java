package edu.math.turner.john;
import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter{
	public BaseConverter() {}
	
	public String convertDecimalToBinary(String decimalNumber){
		int number = Integer.parseInt(decimalNumber);
		String result = ""; 
		while(number > 0){
			result = "" + (number % 2) + result;
			number /= 2;
		}
		return result;
	}
	
	public String convertBinaryToDecimal(String binaryNumber){
		int result = 0;
		long number = Long.parseLong(binaryNumber);
		int length = binaryNumber.length();
		for(int i = 0; i < length; i++){
			result += (number % 10) * Math.pow(2, i);
			number /= 10;
		}
		return "" + result;
	}
	
	public int getNumber(char num){
		if(num == 'A') return 10; 
		else if(num == 'B') return 11;
		else if(num == 'C') return 12; 
		else if(num == 'D') return 13; 
		else if(num == 'E') return 14; 
		else if(num == 'F') return 15;
		else if(num == 'G') return 16; 
		else if(num == 'H') return 17;
		else if(num == 'I') return 18;
		else if(num == 'L') return 19; 
		else return Character.getNumericValue(num);
	}
	
	public char toChar(int num){
		if(num == 10) return 'A'; 
		else if(num == 11) return 'B'; 
		else if(num == 12) return 'C'; 
		else if(num == 13) return 'D'; 
		else if(num == 14) return 'E'; 
		else if(num == 15) return 'F'; 
		else if(num == 16) return 'G'; 
		else if(num == 17) return 'H'; 
		else if(num == 18) return 'I'; 
		else if(num == 19) return 'L'; 
		else return (char)(num + 48); 
	}
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix){
		String result = "";
		int inDecimal = 0;
		int length = numberToConvert.length();
		for(int i = 0; i < length; i++){
			inDecimal += (getNumber(numberToConvert.charAt(length - 1 - i))) * Math.pow(currentRadix, i);
		}
		while(inDecimal > 0){
			result = "" + toChar(inDecimal % newRadix) + result;
			inDecimal /= newRadix;
		}
		return "" + result;
	}
	
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix){
		String whole = numberToConvert;
		String decimal = "";
		
		String result1 = "";
		String result2 = "";
		
		int inTen1 = 0;
		double inTen2 = 0;
		
		for(int i = 0; i < numberToConvert.length(); i++){
			if(numberToConvert.charAt(i) == '.'){
				whole = numberToConvert.substring(0, i);
				decimal = numberToConvert.substring(i + 1);
			}
		}
		
		for(int i = 0; i < whole.length(); i++){
			inTen1 += (double)(getNumber(whole.charAt(whole.length() - 1 - i))) * Math.pow(currentRadix, i);
		}
		for(int i = 0; i < decimal.length(); i++){
			inTen2 += (getNumber(decimal.charAt(i))) * (double)Math.pow(currentRadix, (i + 1) * -1);
		}
		System.out.println(inTen2);
		
		while(inTen1 > 0){
			result1 = "" + toChar(inTen1 % newRadix) + result1;
			inTen1 /= newRadix;
		}
		
		String stringTen = "" + inTen2;
		int multiplier = (stringTen.length() - 1);
		
		double inTenInt = inTen2;
		for(int i = 0; i < multiplier; i++){
			inTenInt *= 10;
		}
		
		System.out.println(inTenInt);
		
		int inTenIntReal = (int)inTenInt;
		
		for(int i = 1; i <= 10; i++){
			result2 += toChar((int)(inTen2 * newRadix));
			inTen2 = (inTen2 * newRadix) - ((int)(inTen2 * newRadix));
		}
		System.out.println(result2);
		
		return "" + result1 + "." + result2;
	}
}