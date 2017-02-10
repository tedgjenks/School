package edu.math.tran.duc;

import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter
{
	public BaseConverter(){}
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix)
	{
		String number = "";
		int exponent = 0, temp = 0, holder = 0;
		for(int count = numberToConvert.length() - 1; count >= 0; count--)
		{
			if(numberToConvert.charAt(count) == 'A' || numberToConvert.charAt(count) == 'a'){
				temp += 10*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'B' || numberToConvert.charAt(count) == 'b'){
				temp += 11*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'C' || numberToConvert.charAt(count) == 'c'){
				temp += 12*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'D' || numberToConvert.charAt(count) == 'd'){
				temp += 13*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'E' || numberToConvert.charAt(count) == 'e'){
				temp += 14*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'F' || numberToConvert.charAt(count) == 'f'){
				temp += 15*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'G' || numberToConvert.charAt(count) == 'g'){
				temp += 16*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'H' || numberToConvert.charAt(count) == 'h'){
				temp += 17*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'I' || numberToConvert.charAt(count) == 'i'){
				temp += 18*(int)Math.pow(currentRadix,exponent);
			}else if(numberToConvert.charAt(count) == 'J' || numberToConvert.charAt(count) == 'j'){
				temp += 19*(int)Math.pow(currentRadix,exponent);
			}else{
				temp += (Integer.parseInt(String.valueOf(numberToConvert.charAt(count)))*(int)Math.pow(currentRadix,exponent));
			}
			exponent++;
		}
		if(newRadix == 10)
		{
			return String.valueOf(temp);
		}
		if(temp == 0)
		{
			return "0";
		}
		while(temp > 0)
		{
			holder = temp % newRadix;
			if(holder == 10){
				number = "A" + number;
			}else if(holder == 11){
				number = "B" + number;
			}else if(holder == 12){
				number = "C" + number;
			}else if(holder == 13){
				number = "D" + number;
			}else if(holder == 14){
				number = "E" + number;
			}else if(holder == 15){
				number = "F" + number;
			}else if(holder == 16){
				number = "G" + number;
			}else if(holder == 17){
				number = "H" + number;
			}else if(holder == 18){
				number = "I" + number;
			}else if(holder == 19){
				number = "J" + number;
			}else{
				number = String.valueOf(holder) + number;
			}
			temp /= newRadix;
		}
		return number;
	}
	
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix)
	{
		if(currentRadix == newRadix)
		{
			return numberToConvert;
		}
		if(numberToConvert == "0" || numberToConvert == "0.0"){
			return "0.0";
		}
		int period = numberToConvert.indexOf(".");
		String firstHalf = numberToConvert.substring(0, period);
		String firstHalfConverted = convertBase(firstHalf, currentRadix, newRadix);
		String secondHalf = numberToConvert.substring(period + 1, numberToConvert.length());
		double temp = 0.0;
		int exponent = 1;
		for(int count = 0; count < secondHalf.length(); count++ )
		{
			if(secondHalf.charAt(count) == 'A' || secondHalf.charAt(count) == 'a'){
				temp += 10/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'B' || secondHalf.charAt(count) == 'b'){
				temp += 11/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'C' || secondHalf.charAt(count) == 'c'){
				temp += 12/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'D' || secondHalf.charAt(count) == 'd'){
				temp += 13/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'E' || secondHalf.charAt(count) == 'e'){
				temp += 14/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'F' || secondHalf.charAt(count) == 'f'){
				temp += 15/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'G' || secondHalf.charAt(count) == 'g'){
				temp += 16/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'H' || secondHalf.charAt(count) == 'h'){
				temp += 17/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'I' || secondHalf.charAt(count) == 'i'){
				temp += 18/Math.pow(currentRadix,exponent);
			}else if(secondHalf.charAt(count) == 'J' || secondHalf.charAt(count) == 'j'){
				temp += 19/Math.pow(currentRadix,exponent);
			}else{
				temp += (Integer.parseInt(String.valueOf(secondHalf.charAt(count)))/Math.pow(currentRadix,exponent));
			}
			exponent++;
		}
		String replace = "";
		if(newRadix == 10)
		{
			double x = Integer.parseInt(firstHalfConverted);
			double total = x + temp;
			return String.valueOf(total);
		}
		int newExp = 1, radixCount = 0;
		double newDec = 0.0, comp = 0.0;
		boolean addZero = true;
		for(int C = 0; C < 5 && !(Math.abs(temp - newDec) <= 0.0000000001); C++)
		{
			addZero = true;
			radixCount = newRadix - 1;
			while(radixCount > 0)
			{
				comp = radixCount/Math.pow(newRadix,newExp);
				if(temp >= (newDec + comp))
				{
					newDec = newDec + comp;
					if(radixCount == 19){
						replace += "J";
					}else if(radixCount == 18){
						replace += "I";
					}else if(radixCount == 17){
						replace += "H";
					}else if(radixCount == 16){
						replace += "G";
					}else if(radixCount == 15){
						replace += "F";
					}else if(radixCount == 14){
						replace += "E";
					}else if(radixCount == 13){
						replace += "D";
					}else if(radixCount == 12){
						replace += "C";
					}else if(radixCount == 11){
						replace += "B";
					}else if(radixCount == 10){
						replace += "A";
					}else{
						replace += String.valueOf(radixCount);
					}
					radixCount = 0;
					addZero = false;
				}
				radixCount--;
			}
			newExp++;
			if(addZero){
				replace += "0";
			}
		}
		return firstHalfConverted + "." + replace;
	}
	
	public String convertBinaryToDecimal(String binaryNumber)
	{
		int number = 0, exponent = 0;
		char current = '0';
		for(int c = binaryNumber.length() - 1; c >= 0; c--)
		{
			current = binaryNumber.charAt(c);
			if(current == '1')
			{
				number += (int)Math.pow(2,exponent);
			}
			exponent++;
		}
		return String.valueOf(number);
	}
	
	public String convertDecimalToBinary(String decimalNumber)
	{
		int number = Integer.parseInt(decimalNumber);
		if(number == 0)
		{
			return "0";
		}
		String binary = "";
		while(number > 0)
		{
			if(number % 2 != 0)
				binary = "1" + binary;
			else
				binary = "0" + binary;
			number /= 2;
		}
		return binary;
	}
}