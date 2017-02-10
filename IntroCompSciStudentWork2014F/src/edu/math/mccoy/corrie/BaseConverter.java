package edu.math.mccoy.corrie;

import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter
{
	public BaseConverter()
	{
		
	}
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix)
	{
		int no = convFrmBaseToDeci(numberToConvert, currentRadix);
		return convFrmDecToBase(no, newRadix);
		//return "";
	}
	
	static String convFrmDecToBase(int num, int base) 
	{

		String res = "";
		int rem;
    
		while (num > 0) 
		{
        rem = num % base;
        if (base == 20) 
		{
            if (rem == 10)
                res += 'A';
            else if (rem == 11)
                res += 'B';
            else if (rem == 12)
                res += 'C';
            else if (rem == 13)
                res += 'D';
            else if (rem == 14)
                res += 'E';
            else if (rem == 15)
                res += 'F';
			else if (rem ==16)
				res += 'G';
			else if (rem ==17)
				res += 'H';
			else if (rem ==18)
				res += 'I';
			else if (rem ==19)
				res += 'J';
            else
                res += rem;
        } 
		else
            res += rem;

        num /= base;
		}
		return res;
	}
	
	static int convFrmBaseToDeci(String num, int base) 
	{

    if (base < 2 || (base > 10 && base != 20))
        return -1;

    int val = 0;
    int power = 1;

    for (int i = num.length() - 1; i >= 0; i--) {
        int digit = digitToVal(num.charAt(i));

        if (digit < 0 || digit >= base)
            return -1;

         val += digit * power;
        power = power * base;
    }

    return val;
}
	
	static int digitToVal(char c) 
	{
		if (c >= '0' && c <= '9')
           return (int) c - '0';
		
		else
           return (int) c - 'A' + 10;
	}
	
	
	
	
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix)
	{
		return "";
	}
	
	public String convertBinaryToDecimal(String binaryNumber)
	{
		int decimal = 0;
		int power = 0;
		while(binaryNumber.length() >= 0)
		{
          int temp = Integer.parseInt(binaryNumber.charAt((binaryNumber.length())-1)+"");
          decimal += temp * Math.pow(2, power++);
          binaryNumber = binaryNumber.substring(0,binaryNumber.length()-1);
		}
       return binaryNumber;
    }
    
	
	
	public String convertDecimalToBinary(String decimalNumber)
	{
		/*String binary = "";
		
		if (n == 0) 
		{
           return "0";
        }
       
       while (n > 0) 
	   {
        int rem = n % 2;
        binary = rem + binary;
        n = n / 2;
       }
       */
	   return "";
	}
	
	
	
}