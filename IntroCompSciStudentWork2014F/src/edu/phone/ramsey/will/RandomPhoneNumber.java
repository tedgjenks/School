
package edu.phone.ramsey.will;
import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;
public class RandomPhoneNumber extends AbstractRandomPhoneNumber{
	public static void main (String [] args){
		RandomPhoneNumber test = new RandomPhoneNumber();
		System.out.println(test.generateRandomPhoneNumber());
	}
	
	@Override
	public String generateRandomPhoneNumber() {
		// TODO Auto-generated method stub
		Random random = new Random();
		int number1 = random.nextInt(8);
		int number2 = random.nextInt(8);
		int number3 = random.nextInt(8);
		int number4 = random.nextInt(10);
		int number5 = random.nextInt(10);
		int number6 = random.nextInt(10);
		String setTwo = String.valueOf(number4) + number5 + number6;
		int set2 = Integer.parseInt(setTwo);
		for (;set2 > 742; set2 = Integer.parseInt(setTwo) ){
			number4 = random.nextInt(10);
			number5 = random.nextInt(10);
			number6 = random.nextInt(10);
			setTwo = String.valueOf(number4) + number5 + number6;
		}
		int number7 = random.nextInt(10);
		int number8 = random.nextInt(10);
		int number9 = random.nextInt(10);
		int number10 = random.nextInt(10);
		String setOne = String.valueOf(number1) + number2 + number3;
		int set1 = Integer.parseInt(setOne);
		if (set1 < 10)
			setOne = "00" + set1;
		else if (set1 < 100)
			setOne = "0" + set1;
		setTwo = String.valueOf(number4) + number5 + number6;
		set2 = Integer.parseInt(setTwo); 
		if (set2 < 10)
			setTwo = "00" + set2;
		else if (set2 < 100)
			setTwo = "0" + set2;
		String setThree = String.valueOf(number7) + number8 + number9 + number10;
		int set3 = Integer.parseInt(setThree);
		if (set3 < 10)
			setThree = "000" + set3;
		else if (set3 < 100)
			setThree = "00" + set3;
		else if ( set3 < 1000)
			setThree = "0" +set3;
		String returnString = setOne + "-" + setTwo + "-" + setThree;
		return returnString;
	}
}
