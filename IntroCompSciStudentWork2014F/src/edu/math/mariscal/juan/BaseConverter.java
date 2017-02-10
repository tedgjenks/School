package edu.math.mariscal.juan;

import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter{
	public String[] help = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
	public BaseConverter(){
		
	}
	
	public String convertBase(String numberToConvert, int curRadix, int newRadix){
		String ntr ="";
		numberToConvert=numberToConvert.toUpperCase();
		int decnum = 0;
		int index=0;
		for(int i=numberToConvert.length()-1; i>=0; i--){
			decnum = decnum + (toDec(numberToConvert.substring(i, i+1))*(int)Math.pow(curRadix, index));
			index++;
		}
		while(decnum >0){
			ntr = fromDec(decnum%newRadix) + ntr;
			decnum/=newRadix;
		}
		return ntr; 
	}
	
	public String convertBaseWithFloat(String numberToConvert, int curRadix, int newRadix){
		String ntr = "";
		numberToConvert = numberToConvert.toUpperCase();
		int dI = numberToConvert.indexOf(".");
		String p1 = numberToConvert.substring(0, dI);
		String p2 = numberToConvert.substring(dI+1, numberToConvert.length());
		p1 = convertBase(p1, curRadix, newRadix);
		double decnum = 0;
		int index = -1;
		for(int i =0; i<p2.length(); i++){
			decnum = decnum + (toDec(p2.substring(i, i+1)) * Math.pow(curRadix, index));
			index --; 
		}
		double b =0, i=-1;
		for(int index2 = 0; index2 < p2.length() && i >-10; index2++){
			b = b + (toDec(p2.substring(index2, index2+1)) * Math.pow(curRadix, i));
			i--;
	}
	String pp2="";
	int i2 =0;
	
	while(b>0 && i2<15){
		double c = b*newRadix;
		
		pp2 =pp2+ String.valueOf(fromDec((int)c));
		if((int)c==0&&b!=0)
			b=c;
		else if(c>0&&b!=0)
			b=c-(int)c;
		
	i2++;
	}
	
		ntr = p1 + "." + pp2;
	
		
		return ntr; 
	}
	
	public String convertBinaryToDecimal(String binaryNumber){
		int dec = 0; 
		int exp = 0;
		for(int index = binaryNumber.length()-1; index>=0; index--){
			char c = binaryNumber.charAt(index); 
			if(c == '1'){
				dec = dec + (int)Math.pow(2, exp);
			}
			exp++;
			
		} 
		String decNum = String.valueOf(dec);
		return decNum; 
	}
	
	public String convertDecimalToBinary(String decimalNumber){
		int dec = Integer.valueOf(decimalNumber);
		String binNum = "";
		while(dec > 0){
			
			if(dec%2==0)
				binNum = "0" + binNum;
			else if (dec%2==1)
				binNum = "1" + binNum;
			dec = dec/2; 
			
		}
		return binNum; 
	}
	
	public int toDec(String b){
		int a = 0;
		for(int i =0; i<help.length; i++){
			if(b.equals(help[i]))
				a=i+10;
			else if(i==help.length-1 && a==0)
				a=Integer.parseInt(b);
		}
		return a; 
	}
	public String fromDec(int a){
		String b="";
		for(int i =0; i<help.length; i++){
			if(a==i+10)
				b=help[i];
			else if(b.equals("")&& i==help.length-1)
				b=String.valueOf(a);
		}
		return b;
	}
}

