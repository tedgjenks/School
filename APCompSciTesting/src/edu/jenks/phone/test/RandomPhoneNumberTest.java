/**
 * 
 */
package edu.jenks.phone.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Pattern;
import static java.lang.System.out;
import edu.jenks.dist.phone.AbstractRandomPhoneNumber;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted
 *
 */
public class RandomPhoneNumberTest extends Testable {
	
	private static final Pattern AREA_CODE_PATTERN = Pattern.compile("[0-7]{3}");
	private static final Pattern EXCHANGE_PATTERN = Pattern.compile("\\d{3}");
	private static final Pattern SUBSCRIBER_PATTERN = Pattern.compile("\\d{4}");
	private static final short MAX_PLUS1_EXCHANGE_EXCLUSIVE = 742 + 1;
	private static final short NUM_AREA_CODES = 778;
	private static final short NUM_EXCHANGES = MAX_PLUS1_EXCHANGE_EXCLUSIVE;
	private static final short NUM_SUBSCRIBERS = 1000;
	private static final int TOTAL_TESTS = (int)Math.pow(10, 6);
	private static final double EXPECTED_AREA_CODE_REPEATS = TOTAL_TESTS / NUM_AREA_CODES;
	private static final double EXPECTED_NUM_EXCHANGES_REPEATS = TOTAL_TESTS / NUM_EXCHANGES;
	private static final double EXPECTED_NUM_SUBSCRIBER_REPEATS = TOTAL_TESTS / NUM_SUBSCRIBERS;
	private static final byte EXPECTED_DELTA_FACTOR = 20;
	private static final byte MAX_DUPLICATES = 2;
	
	private String studentClassName;
	private AbstractRandomPhoneNumber studentRPN;
	int previousAreaCode = 0, previousExchange = 0, previousSubscriber = 0;
	int consecutiveDuplicateAreaCodes = 0, consecutiveDuplicateExchanges = 0, consecutiveDuplicateSubscribers = 0;
	private final Map<Short, Integer> AREA_CODE_COUNT = new HashMap<>((int)Math.ceil(1.5 * NUM_AREA_CODES));
	private final Map<Short, Integer> EXCHANGES_COUNT = new HashMap<>((int)Math.ceil(1.5 * NUM_EXCHANGES));
	private final Map<Short, Integer> SUBSCRIBERS_COUNT = new HashMap<>((int)Math.ceil(1.5 * NUM_SUBSCRIBERS));
	
	public RandomPhoneNumberTest() {
		for(byte maxHundred = 7; maxHundred >= 0; maxHundred--) {
			for(byte maxTen = 7; maxTen >= 0; maxTen--) {
				for(byte maxOne = 7; maxOne >= 0; maxOne--) {
					StringBuilder sb = new StringBuilder(3).append(maxHundred).append(maxTen).append(maxOne);
					AREA_CODE_COUNT.put(Short.parseShort(sb.toString()), 0);
				}
			}
		}
		
		for(short exchange = MAX_PLUS1_EXCHANGE_EXCLUSIVE - 1; exchange >= 0; exchange--)
			EXCHANGES_COUNT.put(exchange, 0);
		
		for(short subscriber = 9999; subscriber >= 0; subscriber--)
			SUBSCRIBERS_COUNT.put(subscriber, 0);
	}
	
	private boolean verifyRandomness(double expectedRepeats, Map<Short, Integer> numCounts) {
		final int minCount = (int)(expectedRepeats / EXPECTED_DELTA_FACTOR);
		final int maxCount = (int)(expectedRepeats * EXPECTED_DELTA_FACTOR);
		Iterator<Short> keys = numCounts.keySet().iterator();
		boolean pass = true;
		while(keys.hasNext() && pass) {
			short number = keys.next();
			int count = numCounts.get(number);
			pass = count >= minCount && count <= maxCount;
			if(!pass)
				out.println("count: " + count + " for number " + number);
		}
		return pass;
	}

	private boolean testAreaCode(String areaCode) {
		boolean pass = true;
		if(areaCode != null && AREA_CODE_PATTERN.matcher(areaCode).matches()) {
			final short curAreaCode = Short.parseShort(areaCode);
			if(MathUtil.equals(previousAreaCode, curAreaCode, 2) && ++consecutiveDuplicateAreaCodes > MAX_DUPLICATES) {
				pass = false;
				feedbackLogger.log(Level.WARNING, "Area code duplicates detected.");
			} else
				consecutiveDuplicateAreaCodes = 0;
			previousAreaCode = curAreaCode;
			AREA_CODE_COUNT.put(curAreaCode, AREA_CODE_COUNT.get(curAreaCode) + 1);
		} else {
			pass = false;
			logFail("area code", "3 digits excluding 8 and 9", areaCode, 1);
		}
		return pass;
	}
	
	private boolean testExchange(String exchange) {
		boolean pass = true;
		if(exchange != null && EXCHANGE_PATTERN.matcher(exchange).matches()) {
			final short curExchange = Short.parseShort(exchange);
			if(curExchange >= MAX_PLUS1_EXCHANGE_EXCLUSIVE) {
				pass = false;
				logFail("exchange", "3 digit number less than " + MAX_PLUS1_EXCHANGE_EXCLUSIVE, exchange, 1);
			} else if(MathUtil.equals(previousExchange, curExchange, 2) && ++consecutiveDuplicateExchanges > MAX_DUPLICATES) {
				pass = false;
				feedbackLogger.log(Level.WARNING, "Exchange duplicates detected.");
			} else
				consecutiveDuplicateExchanges = 0;
			previousExchange = curExchange;
			EXCHANGES_COUNT.put(curExchange, EXCHANGES_COUNT.get(curExchange) + 1);
		} else {
			pass = false;
			logFail("exchange", "3 digit number less than " + MAX_PLUS1_EXCHANGE_EXCLUSIVE, exchange, 1);
		}
		return pass;
	}
	
	private boolean testSubscriber(String subscriber) {
		boolean pass = true;
		if(subscriber != null && SUBSCRIBER_PATTERN.matcher(subscriber).matches()) {
			final short cursubscriber = Short.parseShort(subscriber);
			if(MathUtil.equals(previousSubscriber, cursubscriber, 2) && ++consecutiveDuplicateSubscribers > MAX_DUPLICATES) {
				pass = false;
				feedbackLogger.log(Level.WARNING, "subscriber randomness suspect.");
			} else
				consecutiveDuplicateSubscribers = 0;
			previousSubscriber = cursubscriber;
			SUBSCRIBERS_COUNT.put(cursubscriber, SUBSCRIBERS_COUNT.get(cursubscriber) + 1);
		} else {
			pass = false;
			logFail("subscriber", "4 digits", subscriber, 1);
		}
		return pass;
	}
	
	public void testGenerateRandomPhoneNumber() {
		int phoneNumberPoints = 5, areaCodePoints = 15, exchangePoints = 15, subscriberPoints = 15;
		for(int count = TOTAL_TESTS; count > 0 &&
				(areaCodePoints > 0 || exchangePoints > 0 || subscriberPoints > 0);
				count--) {
			String phoneNumber = studentRPN.generateRandomPhoneNumber();
			String[] parts = null;
			if(phoneNumber != null)
				parts = phoneNumber.split("-");
			if(parts != null && parts.length == 3) {
				boolean fullPass = true;
				if(areaCodePoints > 0 && !testAreaCode(parts[0])) {
					fullPass = false;
					areaCodePoints--;
				}
				if(exchangePoints > 0 && !testExchange(parts[1])) {
					fullPass = false;
					exchangePoints--;
				}
				if(subscriberPoints > 0 && !testSubscriber(parts[2])) {
					fullPass = false;
					subscriberPoints--;
				}
				if(phoneNumberPoints > 0 && !fullPass)
					phoneNumberPoints--;
			} else {
				phoneNumberPoints--;
				areaCodePoints--;
				exchangePoints--;
				subscriberPoints--;
				logFail("poorly formatted", "XXX-XXX-XXXX", phoneNumber, 4);
			}
		}
		
		if(!verifyRandomness(EXPECTED_AREA_CODE_REPEATS, AREA_CODE_COUNT)) {
			logFail("Area code failed randomness test");
			areaCodePoints /= 2;
			/*Iterator<Short> keys = AREA_CODE_COUNT.keySet().iterator();
			while(keys.hasNext()) {
				short key = keys.next();
				out.println(key + ", " + AREA_CODE_COUNT.get(key));
			}*/
		}
		
		if(!verifyRandomness(EXPECTED_NUM_EXCHANGES_REPEATS, EXCHANGES_COUNT)) {
			logFail("Exchange failed randomness test");
			areaCodePoints /= 2;
		}
		
		if(!verifyRandomness(EXPECTED_NUM_SUBSCRIBER_REPEATS, SUBSCRIBERS_COUNT)) {
			logFail("Subscribers failed randomness test");
			areaCodePoints /= 2;
		}
		
		totalPoints += phoneNumberPoints > 0 ? phoneNumberPoints : 0;
		totalPoints += areaCodePoints > 0 ? areaCodePoints : 0;
		totalPoints += exchangePoints > 0 ? exchangePoints : 0;
		totalPoints += subscriberPoints > 0 ? subscriberPoints : 0;
		
		if(totalPoints != getPointsAvailable()) {
			logInfo("Phone number points: " + phoneNumberPoints);
			logInfo("Area code points: " + areaCodePoints);
			logInfo("Exchange points: " + exchangePoints);
			logInfo("Subscriber points: " + subscriberPoints);
		}
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#buildStudentClassNameToSuperclassName()
	 */
	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".RandomPhoneNumber";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.phone.AbstractRandomPhoneNumber");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentRPN = (AbstractRandomPhoneNumber)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 50;
	}

}
