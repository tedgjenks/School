package edu.math.mathis.justin;
import edu.jenks.dist.math.*;
public class BaseConverter extends AbstractBaseConverter{

	public BaseConverter(){}

	public String convertBase (String num, int curr, int newr) {
		String str = "";
		int nenum = 0;
		if (curr == 2 && newr == 10)
			return convertBinaryToDecimal(num);
		if (curr == 10 && newr == 2)
			return convertDecimalToBinary(num);
		if (curr <= 10 && newr <= 10){
			for (int i = 0; i < num.length();i++){
				String a = ""+num.charAt(num.length()-1-i);
				int b = Integer.parseInt(a);
				b *= Math.pow(curr,i);
				nenum += b;
			}
			while (nenum > 0){
				int d = nenum%newr;
				nenum /= newr;
				str = d + str;
			}
		}
		else {
			for (int i = 0; i < num.length();i++){
				String a = ""+num.charAt(num.length()-1-i);
				int b = abc(a);
				b *= Math.pow(curr,i);
				nenum += b;
			}
			if (newr <= 10){
				while (nenum > 0){
				int d = nenum%newr;
				nenum /= newr;
				str = d + str;
				}
			}
			else {
				while (nenum > 0) {
					int d = nenum%newr;
					nenum /= newr;
					if (d >= 10)
						str = cba(d) + str;
					else str = d+str;
				}
			}
		}
		
		return str;
	}
	
	public String convertBinaryToDecimal(String num){
		double nnum = 0;
		for (int i = 0; i <= num.length() - 1; i++){
			String character = "" + num.charAt(num.length()-1-i);
			nnum += Integer.parseInt(character)*Math.pow(2,i);
		}
		String a = String.valueOf((int)nnum);
		return a;
	}
	public String convertBaseWithFloat(String cnum, int curr, int newr){
		String str = "";
		return str;
	}
	public String convertDecimalToBinary(String dnum){
		String str = "";
		int a = Integer.parseInt(dnum);
		while (a > 0){
			int b = a%2;
			a /= 2;
			str = b + str;
		}	
		return str;
	}
	public int abc(String a){
		if (a == "a")
			return 10;
		if (a == "b")
			return 11;
		if (a == "c")
			return 12;
		if (a == "d")
			return 13;
		if (a == "e")
			return 14;
		else return 15;
	}
	public String cba(int a){
		if (a == 10)
			return "a";
		if (a == 11)
			return "b";
		if (a == 12)
			return "c";
		if (a == 13)
			return "d";
		if (a == 14)
			return "e";
		else return "f";
	}
}