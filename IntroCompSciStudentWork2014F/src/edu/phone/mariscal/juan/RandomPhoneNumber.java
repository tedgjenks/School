package edu.phone.mariscal.juan;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomPhoneNumber rpn = new RandomPhoneNumber();
		rpn.generateRandomPhoneNumber();
	}
	@Override
	public  String generateRandomPhoneNumber() {
		// TODO Auto-generated method stub
		int num1= (int)(Math.abs(Math.random()*10-2));
		int num2= (int)(Math.abs(Math.random()*10-2));
		int num3= (int)(Math.abs(Math.random()*10-2));
		int num456=(int)(Math.abs(Math.random()*1000-258));
		int num789=(int)(Math.abs(Math.random()*10000));
		String num123n = ""+ num1 + num2 +num3;
		String num456n= String.valueOf(num456);
		String num789n= String.valueOf(num789);
		if (num123n.length() == 2){
			num123n= "0" + num123n;
		}
		if (num123n.length() ==1){
			num123n="00" + num123n;
		}
		if (num123n.length() ==0){
			num123n="000" + num123n;
		}
		if (num456n.length() == 2){
			num456n= "0" + num456n;
		}
		if (num456n.length() ==1){
			num456n="00" + num456n;
		}
		if (num456n.length() ==0){
			num456n="000" + num456n;
		}
		if (num789n.length() == 3){
			num789n= "0" + num789n;
		}
		if (num789n.length() ==2){
			num789n="00" + num789n;
		}
		if (num789n.length() ==1){
			num789n="000" + num789n;
		}
		if (num789n.length() ==0){
			num789n="0000" + num789n;
		}
		String number= num123n + "-" + num456n + "-" + num789n;
		System.out.println(number);
		
		return number;
	}



}
