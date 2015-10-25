package edu.phone.piland.will;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {

	@Override
	public String generateRandomPhoneNumber() {
		// TODO Auto-generated method stub
		 int firstNumber = Math.abs((int)(Math.random() * 10 - 2));
		 int secondNumber = Math.abs((int)(Math.random() * 10 - 2));
		 int thirdNumber = Math.abs((int)(Math.random() * 10 - 2));
		 int forthNumberCheck = Math.abs((int)(Math.random() * 1000) - 258);
		 
		 int fifthNumber = Math.abs((int)(Math.random() * 10000));
		 String num456 = ""+forthNumberCheck;
		 String num789 = ""+fifthNumber;
		 String newNumber = num789;
		 String newNumber1 = num456;
		if (num789.length() == 3) {
			newNumber = "0"+fifthNumber;
		 }
		if (num789.length() == 2){
			newNumber = "00" + fifthNumber;
		}
		if (num789.length() == 1){
			newNumber = "000" + fifthNumber;
		}
		if (num789.length() == 0){
			newNumber= "0000";
		}
		if (num456.length() == 2) {
			newNumber1 = "0"+forthNumberCheck;
		 }
		if (num456.length() == 1){
			newNumber1 = "00" + forthNumberCheck;
		}
		if (num456.length() == 0){
			newNumber1 = "000";
		}
		
		 System.out.println("" +firstNumber+"" + secondNumber+"" + thirdNumber + "-"  + newNumber1 +"-" + newNumber);
		 
		 String phoneNumber = "" +firstNumber+""+ secondNumber+""+thirdNumber + "-" + newNumber1 + "-" + newNumber;
	
		return phoneNumber;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	}


