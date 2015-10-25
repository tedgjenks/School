package edu.phone.collier.serenity;

import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {

	@Override
	public String generateRandomPhoneNumber() {
		Random rnd = new Random();
		String areaCode = ""+ rnd.nextInt(8) + rnd.nextInt(8) +rnd.nextInt(8);
		String threeNum = ""+ rnd.nextInt(743);
		if (threeNum.length() == 1)
			threeNum = "00"+threeNum;
		else if (threeNum.length() == 2)
			threeNum = "0"+ threeNum;
		
		String lastDigs = ""+ rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10);
		String RandomPhoneNumber = areaCode + "-" + threeNum + "-" + lastDigs;
		
		return RandomPhoneNumber;

	}

}
