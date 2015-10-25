package edu.phone.johnson.tatum;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;
import java.util.Random;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber{

	public static void main() {
		
	}
	public String generateRandomPhoneNumber() {
		Random random = new Random();
		int areaCodeFirstDig = random.nextInt(8);
		int areaCodeSecondDig = random.nextInt(8);
		int areaCodeThirdDig = random.nextInt(8);
		String areaCode = "" + (areaCodeFirstDig) + (areaCodeSecondDig) + (areaCodeThirdDig);
		int nextThreeDig = random.nextInt(743);
		String nextThreeDigits = "" + nextThreeDig ;
		if (nextThreeDig < 10)
			nextThreeDigits = "00" + nextThreeDig;
		else if (nextThreeDig < 100)
			nextThreeDigits = "0" + nextThreeDig;
		int lastFourDig = random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
		String lastFourDigits = "" + random.nextInt(10) + random.nextInt(10) + random.nextInt(10) + random.nextInt(10);
		String phoneNum = "" + areaCode + nextThreeDigits + lastFourDigits;
		return (""+areaCode+"-"+nextThreeDigits+"-"+lastFourDigits);
	}

}
 