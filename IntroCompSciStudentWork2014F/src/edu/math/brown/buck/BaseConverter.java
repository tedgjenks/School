package edu.math.brown.buck;

import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter
{
	public BaseConverter()
	{
		
	}
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix)
	{
		int original = convertToDecimal(numberToConvert, currentRadix);
		int rem = original;
		String newNum = "";
		while(original != 0)
		{
			rem = original % newRadix;
			if(rem >= 10)
			{
				if(rem == 10)
					newNum = "a" + newNum;
				else if(rem == 11)
					newNum = "b" + newNum;
				else if(rem == 12)
					newNum = "c" + newNum;
				else if(rem == 13)
					newNum = "d" + newNum;
				else if(rem == 14)
					newNum = "e" + newNum;
				else if(rem == 15)
					newNum = "f" + newNum;
				else if(rem == 16)
					newNum = "g" + newNum;
				else if(rem == 17)
					newNum = "h" + newNum;
				else if(rem == 18)
					newNum = "i" + newNum;
				else if(rem == 19)
					newNum = "j" + newNum;
			}
			else
				newNum = rem + newNum;
			original = original / newRadix;
		}
		return newNum;
	}
	
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix)
	{
		double original = convertDoubleToDecimal(numberToConvert, currentRadix);
		int rem = 0;
		String convert = "" + original;
		String temp = "";
		String answer = "";
		int count = 0;
		int length = 0;
		if(newRadix == 10)
			return convert;
		while(convert.charAt(count) != '.')
		{
			length += 1;
			temp = temp + numberToConvert.charAt(count);
			count++;
		}
		answer = convertBase(temp, 10, newRadix);
		answer = answer + ".";
		int count2 = length + 1;
		count = 1;
		while(count2 < convert.length())
		{
			temp = convert.substring(count2, count2 + 1);
			rem = (Integer.parseInt(temp) * (int) Math.pow(newRadix, count)) / (int) Math.pow(10, count);
			if(rem >= 10)
			{
				if(rem == 10)
					answer += "a";
				else if(rem == 11)
					answer += "b";
				else if(rem == 12)
					answer += "c";
				else if(rem == 13)
					answer += "d";
				else if(rem == 14)
					answer += "e";
				else if(rem == 15)
					answer += "f";
				else if(rem == 16)
					answer += "g";
				else if(rem == 17)
					answer += "h";
				else if(rem == 18)
					answer += "i";
				else if(rem == 19)
					answer += "j";
			}
			else
				answer = answer + rem;
			count++;
			count2++;
		}
		return answer;
	}
	
	public String convertBinaryToDecimal(String binaryNumber)
	{
		int decimal = convertToDecimal(binaryNumber, 2);
		String decimalReturn = "";
		return decimalReturn + decimal;
	}
	
	public String convertDecimalToBinary(String decimalNumber)
	{
		int decimal = Integer.parseInt(decimalNumber);
		int rem = decimal;
		String binary = "";
		while(decimal != 0)
		{
			rem = decimal % 2;
			binary = rem + binary;
			decimal = decimal / 2;
		}
		return binary;
	}
	
	public int convertToDecimal(String a, int base)
	{
		if(a.length() > 0)
		{
			String temp = "";
			int length = a.length() - 1;
			int count = 0;
			int decimal = 0;
			while(length >= 0)
			{
				if(a.charAt(count) != '0')
				{
					temp = a.substring(count, count + 1);
					if(temp.equals("a"))
						temp = "10";
					else if(temp.equals("b"))
						temp = "11";
					else if(temp.equals("c"))
						temp = "12";
					else if(temp.equals("d"))
						temp = "13";
					else if(temp.equals("e"))
						temp = "14";
					else if(temp.equals("f"))
						temp = "15";
					else if(temp.equals("g"))
						temp = "16";
					else if(temp.equals("h"))
						temp = "17";
					else if(temp.equals("i"))
						temp = "18";
					else if(temp.equals("j"))
						temp = "19";
					decimal += (int) Math.pow(base, length) * Integer.parseInt(temp);
				}
				count++;
				length--;
			}
			return decimal;
		}
		return 0;
	}
	
	public double convertDoubleToDecimal(String a, int base)
	{
		String temp = "";
		int count = 0;
		int length = 0;
		double decimal = 0.0;
		while(a.charAt(count) != '.')
		{
			length += 1;
			count++;
		}
		decimal += convertToDecimal(a.substring(0, count), base);
		int length2 = a.length();
		count = count + 1;
		int count2 = 1;
		while(count < length2)
		{
			if(a.charAt(count) != '0')
			{
				temp = a.substring(count, count + 1);
				if(temp.equals("a"))
					temp = "10";
				else if(temp.equals("b"))
					temp = "11";
				else if(temp.equals("c"))
					temp = "12";
				else if(temp.equals("d"))
					temp = "13";
				else if(temp.equals("e"))
					temp = "14";
				else if(temp.equals("f"))
					temp = "15";
				else if(temp.equals("g"))
					temp = "16";
				else if(temp.equals("h"))
					temp = "17";
				else if(temp.equals("i"))
					temp = "18";
				else if(temp.equals("j"))
					temp = "19";
				decimal += (1.0 / Math.pow(base, count2)) * (double) Integer.parseInt(temp);
			}
			count++;
			count2++;
		}
		return decimal;
	}
}
 