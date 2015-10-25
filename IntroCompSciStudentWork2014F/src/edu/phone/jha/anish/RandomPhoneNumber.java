package edu.phone.jha.anish;

import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {

	public static void main (String [] args){
		RandomPhoneNumber rpn = new RandomPhoneNumber();
		rpn.generateRandomPhoneNumber();
	
	
}
	@Override
	public String generateRandomPhoneNumber() {
		
		int num = (int)(Math.abs(Math.random()*1000-222));
		
		int num1 = (int)(Math.abs(Math.random()*1000-250));
		
		int num2 = (int)(Math.abs(Math.random()*10000));
				
		String numn = String.valueOf(num);
		
		String num1n = String.valueOf(num1);
		
		String num2n = String.valueOf(num2);
		
		if (numn.length() == 2){
			numn="0" + numn;
		}
		
		if (numn.length() == 1){
			numn="00" + numn;
		}

		if (numn.length() == 0){
			numn="000" + numn;
		}

		if (num1n.length() == 2){
			numn="0" + num1n;
		}

		if (num1n.length() == 1){
			num1n="00" + num1n;
		}

		if (num1n.length() == 2){
			num1n="000" + num1n;
		}
		
		if (num2n.length() == 3){
			numn="0" + numn;
		}
		
		if (num2n.length() == 2){
			numn="00" + num2n;
		}
		
		if (num2n.length() == 1){
			numn="000" + num2n;
		}
		
		if (num2n.length() == 0){
			numn="0000" + num2n;
		}
		
		String number = num + "-" + num1 + "-" + num2;
		
		System.out.println(number);
	
		return number;
		

	}

}


	
	

