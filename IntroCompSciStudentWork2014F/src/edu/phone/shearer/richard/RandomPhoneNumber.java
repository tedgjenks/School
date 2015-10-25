package edu.phone.shearer.richard;

import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {
 
	@Override
	public String generateRandomPhoneNumber() {
		Random cheeseburger = new Random();
		int areaCode1, areaCode2, areaCode3, last1, last2, last3, last4;
		areaCode1 = cheeseburger.nextInt(8);
		areaCode2 = cheeseburger.nextInt(8);
		areaCode3 = cheeseburger.nextInt(8);
		String middigits = "" + cheeseburger.nextInt(743);
		if (middigits.length() == 1)
			middigits = "00" + middigits;
		else if (middigits.length() == 2)
			middigits = "0" + middigits;
		last1 = cheeseburger.nextInt(10);
		last2 = cheeseburger.nextInt(10);
		last3 = cheeseburger.nextInt(10);
		last4 = cheeseburger.nextInt(10);
		return "" + areaCode1 + areaCode2 + areaCode3 + "-" + middigits + "-" + last1 + last2 + last3 + last4;
	}
}