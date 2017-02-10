package edu.math.jha.anish;

import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter
{
	public BaseConverter(){}
	
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix)
	{
		String r = "";
		
		return r;				
	}
	
	
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix)
	{
		String r = "";
		
		return r;
	}
	
	
	
	public String convertBinaryToDecimal(String binaryNumber)
	{
		String r = "";
		
		return r;
	}
	
	
	public String convertDecimalToBinary(String decimalNumber)
	{
		int d = Integer.valueOf(decimalNumber);
		
		String bN = "";
		
		for(int i = 0; d > i; i += i)
		{
			int r = d % 2;
			
			d /= 2;
			
			bN = "" + r + bN;			
			
		}
						
		return bN;
	}
	
	
	
	
}