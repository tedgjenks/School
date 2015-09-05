package edu.phone.wicker.marshall;

import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber{
	private Random randGen = new Random();
	private String secondSectionString;
	private int secondSectionNumber;
	
	public String generateRandomPhoneNumber(){
		secondSectionNumber = randGen.nextInt(743);
		secondSectionString = secondSectionNumber == 0 ? "000" : 
									 secondSectionNumber < 10 ? "00" + secondSectionNumber : 
									 secondSectionNumber < 100 ? "0" + secondSectionNumber : 
									 "" + secondSectionNumber;
		
		return  "" + randGen.nextInt(8) + randGen.nextInt(8) + randGen.nextInt(8) + "-"
				+ secondSectionString + "-"
				+ randGen.nextInt(10) + randGen.nextInt(10) + randGen.nextInt(10) + randGen.nextInt(10);
	}
}
