package edu.jenks.phone_number;

import java.util.Random;

import edu.jenks.util.StringUtil;

/**
 * @author Ted Jenks
 *
 */
public class RandomPhoneNumber {
	
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	
	/**
	 * Generates a random phone number in the form ###-###-####.</br>
	 * The area code must not contain an 8 or a 9.</br>
	 * The exchange must be less than or equal to 742.
	 * 
	 * @return a random phone number
	 */
	public static String generateRandomPhoneNumber() {
		StringBuilder sb = new StringBuilder(12);
		buildAreaCode(sb);
		sb.append("-");
		buildExchange(sb);
		sb.append("-");
		buildSubscriber(sb);
		return sb.toString();
	}
	
	private static void buildAreaCode(StringBuilder sb) {
		for(int count = 3; count > 0; count--)
			sb.append(RANDOM.nextInt(8));
	}
	
	private static void buildExchange(StringBuilder sb) {
		int exchange = RANDOM.nextInt(RandomPhoneNumberConstants.MAX_EXCHANGE_EXCLUSIVE);
		if(exchange < 100)
			sb.append(StringUtil.prependCharacter(exchange, '0', 3));
		else
			sb.append(exchange);
	}
	
	private static void buildSubscriber(StringBuilder sb) {
		int subscriber = RANDOM.nextInt(10000);
		if(subscriber < 1000)
			sb.append(StringUtil.prependCharacter(subscriber, '0', 4));
		else
			sb.append(subscriber);
	}

	public static void main(String[] args) {
		for(int count = 10; count >= 0; count--)
			System.out.println(generateRandomPhoneNumber());

	}

}
