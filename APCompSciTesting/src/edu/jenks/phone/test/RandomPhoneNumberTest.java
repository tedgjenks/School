/**
 * 
 */
package edu.jenks.phone.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Pattern;

import edu.jenks.dist.phone.AbstractRandomPhoneNumber;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted
 *
 */
public class RandomPhoneNumberTest extends Testable {
	
	private final static Pattern AREA_CODE_PATTERN = Pattern.compile("[0-7]{3}");
	private final static Pattern EXCHANGE_PATTERN = Pattern.compile("\\d{3}");
	private final static Pattern SUBSCRIBER_PATTERN = Pattern.compile("\\d{4}");
	private final short MAX_PLUS1_EXCHANGE_EXCLUSIVE = 742 + 1;
	
	private String studentClassName;
	private AbstractRandomPhoneNumber studentRPN;
	private final int TOTAL_TESTS = 1000;
	int previousAreaCode = 0, previousExchange = 0, previousSubscriber = 0;
	int suspectAreaCodes = 0, suspectExchanges = 0, suspectSubscribers = 0;
	private final int SUSPECT_LIMIT = 3;
	
	private boolean testAreaCode(String areaCode) {
		boolean pass = true;
		if(areaCode != null && AREA_CODE_PATTERN.matcher(areaCode).matches()) {
			int curAreaCode = Integer.parseInt(areaCode);
			if(MathUtil.equals(previousAreaCode, curAreaCode, 2) && ++suspectAreaCodes > SUSPECT_LIMIT) {
				pass = false;
				feedbackLogger.log(Level.WARNING, "Area code randomness suspect.");
			} else
				suspectAreaCodes = 0;
			previousAreaCode = curAreaCode;
		} else {
			pass = false;
			logFail("area code", "3 digits excluding 8 and 9", areaCode, 1);
		}
		return pass;
	}
	
	private boolean testExchange(String exchange) {
		boolean pass = true;
		if(exchange != null && EXCHANGE_PATTERN.matcher(exchange).matches()) {
			int curExchange = Integer.parseInt(exchange);
			if(curExchange >= MAX_PLUS1_EXCHANGE_EXCLUSIVE) {
				pass = false;
				logFail("exchange", "3 digit number less than " + MAX_PLUS1_EXCHANGE_EXCLUSIVE, exchange, 1);
			} else if(MathUtil.equals(previousExchange, curExchange, 2) && ++suspectExchanges > SUSPECT_LIMIT) {
				pass = false;
				feedbackLogger.log(Level.WARNING, "Exchange randomness suspect.");
			} else
				suspectExchanges = 0;
			previousExchange = curExchange;
		} else {
			pass = false;
			logFail("exchange", "3 digit number less than " + MAX_PLUS1_EXCHANGE_EXCLUSIVE, exchange, 1);
		}
		return pass;
	}
	
	private boolean testSubscriber(String subscriber) {
		boolean pass = true;
		if(subscriber != null && SUBSCRIBER_PATTERN.matcher(subscriber).matches()) {
			int cursubscriber = Integer.parseInt(subscriber);
			if(MathUtil.equals(previousSubscriber, cursubscriber, 2) && ++suspectSubscribers > SUSPECT_LIMIT) {
				pass = false;
				feedbackLogger.log(Level.WARNING, "subscriber randomness suspect.");
			} else
				suspectSubscribers = 0;
			previousSubscriber = cursubscriber;
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
		
		totalPoints += phoneNumberPoints > 0 ? phoneNumberPoints : 0;
		totalPoints += areaCodePoints > 0 ? areaCodePoints : 0;
		totalPoints += exchangePoints > 0 ? exchangePoints : 0;
		totalPoints += subscriberPoints > 0 ? subscriberPoints : 0;
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
		feedbackLogger.log(Level.INFO, "Pass: object creation.");
	}

}
