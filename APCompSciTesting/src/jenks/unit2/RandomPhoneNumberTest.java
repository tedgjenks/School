package jenks.unit2;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Test;

import edu.jenks.phone_number.RandomPhoneNumber;
import edu.jenks.phone_number.RandomPhoneNumberConstants;

public class RandomPhoneNumberTest {
	
	private int pointTotal = 0;
	private final int NUMBER_TESTS = 10000;
	private final int MIN_UNIQUE_PHONE_NUMBERS = NUMBER_TESTS / 2;
	private final Pattern PHONE_PATTERN = Pattern.compile("(\\d{3}-){2}\\d{4}");

	@After
	public void tearDown() throws Exception {
		System.out.println("Total points: " + pointTotal);
	}

	@Test
	public void test() {
		System.out.println("Begin test()");
		Set<String> uniquePhoneNumbers = new HashSet<String>(500);
		int successfulPattern = 0, successfulRestrictions = 0;
		for(int index = NUMBER_TESTS; index > 0; index--) {
			boolean goodNumber = false;
			String phoneNumber = RandomPhoneNumber.generateRandomPhoneNumber();
			if(PHONE_PATTERN.matcher(phoneNumber).matches()) {
				successfulPattern++;
				String areaCode = phoneNumber.substring(0, 3);
				if(!areaCode.contains("8") && !areaCode.contains("9")) {
					int exchange = Integer.parseInt(phoneNumber.substring(4, 7));
					if(exchange < RandomPhoneNumberConstants.MAX_EXCHANGE_EXCLUSIVE) {
						successfulRestrictions++;
						uniquePhoneNumbers.add(phoneNumber);
						goodNumber = true;
					}
				}
			}
			if(!goodNumber)
				System.out.println("Bad number: " + phoneNumber);
		}
		
		System.out.println(NUMBER_TESTS + " tests run.");
		if(successfulPattern == NUMBER_TESTS) {
			pointTotal += 2;
			System.out.println("All patterns met.");
		} else if(successfulPattern >= NUMBER_TESTS / 2) {
			pointTotal++;
			System.out.println("Half patterns met - " + successfulPattern);
		} else
			System.out.println("Patterns failed - " + successfulPattern + " successful.");
		
		if(successfulRestrictions == NUMBER_TESTS) {
			pointTotal += 2;
			System.out.println("All restrictions met.");
		} else if(successfulRestrictions >= NUMBER_TESTS / 2) {
			pointTotal++;
			System.out.println("Half restrictions met - " + successfulRestrictions);
		} else
			System.out.println("Restrictions failed - " + successfulRestrictions + " successful.");
		
		int numUnique = uniquePhoneNumbers.size();
		if(numUnique >= MIN_UNIQUE_PHONE_NUMBERS) {
			pointTotal += 1;
			System.out.println("Adequate unique numbers: " + numUnique);
		} else
			System.out.println("Not enough unique numbers: " + numUnique + " - CHECK CODE!");
		System.out.println("End test()");
	}

}
