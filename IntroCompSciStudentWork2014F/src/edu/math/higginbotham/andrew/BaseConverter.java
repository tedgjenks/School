package edu.math.higginbotham.andrew;

import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter
{
	public BaseConverter(){}

	public String convertBase(String numberToConvert, int currentRadix, int newRadix)
	{
		
			
		return null;
	}

	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix)
	{
		
		return null;
	}

	public String convertBinaryToDecimal(String binaryNumber)
	{
		int place = 0;
		double finalValue = 0;
		String returned = "";
		int length = binaryNumber.length();
		while(place <= binaryNumber.length() - 1)
		{
			char number = binaryNumber.charAt(place);
			if(number == '1')
				finalValue = finalValue + Math.pow(2.0, (length-1) - place);
			place++;
		}
		returned = returned + (int)finalValue;
		return returned;
	}

	public String convertDecimalToBinary(String decimalNumber)
	{
		double finalValue = 0;
		String returned = "";
		int number = Integer.parseInt(decimalNumber);
		while(number != 0)
		{
			if(number%2 == 1)
				returned = "1" + returned;
			else
				returned = "0" + returned;
			
			number = number / 2;
		}
		return returned;
	}
}