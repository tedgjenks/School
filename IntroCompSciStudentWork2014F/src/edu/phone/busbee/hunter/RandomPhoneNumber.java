package edu.phone.busbee.hunter;

import java.util.Random;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber{
	
	

	@Override
	public String generateRandomPhoneNumber() {
		// Could use Random class to use random.nextint();, but I'll do that later. ps. random.nextint does not include the bound. It keeps making the first digits the same.
		// Change the Variables for Math.random in order to not get similar numbers. Middle Section is between 2-3 characters because of the multiplication, FIX.
		Random random = new Random();
		String areaCodeDigits = "" + random.nextInt(8) + random.nextInt(8) + random.nextInt(8);
		int middleSection = random.nextInt(743);
		String middleNumbers = "" + middleSection;
		if(middleSection < 10)
			middleNumbers = "00" + middleSection;
		else if(middleSection < 100)
			middleNumbers = "0" + middleSection;
		String lastSection = "" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
		return "" + areaCodeDigits + "-" + middleNumbers + "-" + lastSection;
		
		
	}

}
