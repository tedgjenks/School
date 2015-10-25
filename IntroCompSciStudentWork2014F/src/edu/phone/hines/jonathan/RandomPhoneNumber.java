package edu.phone.hines.jonathan;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

import java.util.Random;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber{

	@Override
	public String generateRandomPhoneNumber() {
		Random rand = new Random();
		String finalAreaCode = "" + rand.nextInt(8) + rand.nextInt(8) + rand.nextInt(8);
		String exchange = "" + rand.nextInt(743);
		if (exchange.length() == 1)
			exchange = "00" + exchange;
		else if (exchange.length() == 2)
			exchange = "0" + exchange;	
		String fourSet = "" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
		String randPhoneNumber = finalAreaCode + "-" + exchange + "-" + fourSet;
		return randPhoneNumber;
	}

}
