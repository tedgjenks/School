package edu.phone.simon.job;

import java.util.Random;
import java.util.concurrent.Exchanger;
import edu.jenks.dist.phone.AbstractRandomPhoneNumber;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber {
		@Override
	public String generateRandomPhoneNumber() {
		// TODO Auto-generated method stub
			Random rnd = new Random();
			String areacode = ""+ rnd.nextInt(8) + rnd.nextInt(8) + rnd.nextInt(8);
			String ThreeNum = ""+ rnd.nextInt(743);
			if (ThreeNum.length() == 1)
			 ThreeNum = "00"+ ThreeNum;
			else if (ThreeNum.length() == 2)
				 ThreeNum = "0"+ ThreeNum;	
				
			String lastDigs = ""+ rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10);
			String RandomPhoneNumber = areacode + "-" + ThreeNum +"-"+ lastDigs;
		return RandomPhoneNumber;
	}

}
