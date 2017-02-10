package edu.math.ball.daniel;
import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter
{
	public BaseConverter(){}
	
	public String convertBase(String numberToConvert, int currentRadix, int newRadix)
	{
		int build = 0;
		for (int a = numberToConvert.length()-1; a >= 0; a--)
		{
			int z = numberToConvert.length()-1 - a;
			System.out.println(numberToConvert.charAt(a) + " " + z);
			if (numberToConvert.charAt(a) == 'A'){
				build += 10*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'B'){
				build += 11*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'C'){
				build += 12*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'D'){
				build += 13*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'E'){
				build += 14*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'F'){
				build += 15*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'G'){
				build += 16*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'H'){
				build += 17*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'I'){
				build += 18*(Math.pow(currentRadix,z));}
			else if (numberToConvert.charAt(a) == 'J'){
				build += 19*(Math.pow(currentRadix,z));}
			else{
				build += Character.getNumericValue(numberToConvert.charAt(a))*(Math.pow(currentRadix,z));}
		}
		System.out.println(build);
		int temp = 0;
		String tempChar = new String("0");
		int rais = 1;
		StringBuilder ret = new StringBuilder();
		while (rais > 0)
		{
			while (((int) Math.pow(newRadix,temp)) <= build)
			{
				temp++;
			}
			temp--;
			rais = (int) Math.pow(newRadix,temp);
			if (rais > 0)
			{
				int div = (build / rais);
				if (div == 0)
				{
					tempChar = ("0");
				}
				else
				{
					if (div == 10){
						tempChar = ("A");}
					else if (div == 11){
						tempChar = ("B");}
					else if (div == 12){
						tempChar = ("C");}
					else if (div == 13){
						tempChar = ("D");}
					else if (div == 14){
						tempChar = ("E");}
					else if (div == 15){
						tempChar = ("F");}
					else if (div == 16){
						tempChar = ("G");}
					else if (div == 17){
						tempChar = ("H");}
					else if (div == 18){
						tempChar = ("I");}
					else if (div == 19){
						tempChar = ("J");}
					else {
						tempChar = String.valueOf(div);
					}
				}
			build = (build % rais);
			ret.append(tempChar);
			}
			else
			{	
				break;
			}
		}
		System.out.println(ret);
		return ret.toString();
	}
	public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix)
	{
		int dec = numberToConvert.indexOf(".");
		String whole = new String(numberToConvert.substring(0,dec));
		String decim = new String(numberToConvert.substring(dec+1,numberToConvert.length()));
		whole = convertBase(whole, currentRadix, newRadix);
		double build = 0.0;
		for (int i = 0; i < decim.length(); i++)
		{
			int z = -(i+1);
			if (decim.charAt(i) == 'A'){
				build += 10*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'B'){
				build += 11*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'C'){
				build += 12*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'D'){
				build += 13*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'E'){
				build += 14*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'F'){
				build += 15*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'G'){
				build += 16*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'H'){
				build += 17*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'I'){
				build += 18*(Math.pow(currentRadix,z));}
			else if (decim.charAt(i) == 'J'){
				build += 19*(Math.pow(currentRadix,z));}
			else{
			build += Character.getNumericValue(decim.charAt(i))*(Math.pow(currentRadix,z));}
		}
		String newDec = String.valueOf(build);
		int dec2 = newDec.indexOf(".");
		String addOn = new String(newDec.substring(dec2+1,newDec.length()));
		String bassTen = new String(whole + "." + addOn);
		System.out.println(bassTen);
		return bassTen;
	}
	public String convertBinaryToDecimal(String binaryNumber)
	{
		int ret = 0;
		int pow = 0;
		for (int i = binaryNumber.length()-1; i >= 0; i--)
		{
			int z = binaryNumber.length()-1 - i;
			if (binaryNumber.charAt(i) == '1')
			{
				pow = (int)Math.pow(2,z);
			}
			else
			{
				pow = 0;
			}
			ret += pow;
		}
		return String.valueOf(ret);
	}
	public String convertDecimalToBinary(String dec)
	{
		int pars = Integer.parseInt(dec);
		int temp = 0;
		int bint = 0;
		int rais = 1;
		StringBuilder binary = new StringBuilder();
		while (rais > 0)
		{
			while (((int) Math.pow(2,temp)) <= pars)
			{
				temp++;
			}
			temp--;
			rais = (int) Math.pow(2,temp);
			if (rais > 0)
			{
				if ((pars / rais) == 0)
				{
					bint = 0;
				}
				else
				{
					bint = 1;
				}
			pars = (pars % rais);
			binary.append(bint);
			}
			else
			{	
				break;
			}
		}
		return binary.toString();
	}
}