package edu.phone.burroughs.lauren;

import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {

	@Override
	public String generateRandomPhoneNumber() {
		Random randNum= new Random();
		String areaC = "" + randNum.nextInt(8) + randNum.nextInt(8) + randNum.nextInt(8)+ "-";
		String midNums = "" + randNum.nextInt(743) ;		
		if(midNums.length()<2)
			midNums = "00" + midNums;	
		else if(midNums.length()<3)
			midNums = "0" + midNums;
		String lastNums = "-" + randNum.nextInt(10) + randNum.nextInt(10) + randNum.nextInt(10) + randNum.nextInt(10);
		String pNum = areaC + midNums + lastNums;
		
		return pNum;
	}

}
