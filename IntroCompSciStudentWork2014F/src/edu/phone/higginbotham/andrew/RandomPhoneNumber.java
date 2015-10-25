package edu.phone.higginbotham.andrew;

import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber{

	

	@Override
	public String generateRandomPhoneNumber() {
		Random rand = new Random();
		int firstDigit, secondDigit, thirdDigit, seventhDigit, eighthDigit, ninthDigit, tenthDigit;  
		firstDigit = rand.nextInt(8) ;
		secondDigit = rand.nextInt(8) ;
		thirdDigit = rand.nextInt(8) ;
		String middleDigits ="" + rand.nextInt(743);
		if(middleDigits.length() == 1)
			middleDigits = "00" + middleDigits;
		else if(middleDigits.length() == 2)
			middleDigits = "0" + middleDigits;
		seventhDigit = rand.nextInt(5) ;
		eighthDigit = rand.nextInt(3) ;
		ninthDigit = rand.nextInt(10) ;
		tenthDigit = rand.nextInt(10) ;
		return "" + firstDigit + secondDigit + thirdDigit + "-" + middleDigits+ "-" + seventhDigit+ eighthDigit + ninthDigit + tenthDigit;
	}

}
