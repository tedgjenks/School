/**
 * 
 */
package edu.jenks.ptc.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import edu.jenks.dist.ptc.PhoneNumberable;
import edu.jenks.test.Testable;
import edu.jenks.util.*;

/**
 * @author Ted Jenks
 *
 */
public class PhoneNumberTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {String.class};
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final byte MIN_ASCII = 32, MAX_ASCII = 127;
	private static final int[] LEGAL_ALPHAS_ABOVE_MIN = {' ', '-'};
	private static final String NOT_VALID_TOSTRING = "not valid",
			NOT_VALID_LINE_NUMBER = "unknown line number",
			NOT_VALID_PREFIX = "unknown prefix",
			NOT_VALID_AREA_CODE = "unknown area code";

	private PhoneNumberable solution = new edu.ptc.jenks.ted.PhoneNumber(""), studentInstance;
	private String studentClassName;
	
	public void test70Equals() {
		final String message = "Equals";
		int points = 4;
		boolean pass = true;
		
		solution.setNumber(buildRandomLegal());
		studentInstance.setNumber(buildRandomIllegal());
		boolean expected = false;
		boolean actual = studentInstance.equals(solution);
		pass = pass && (expected == actual);
		if(expected != actual) {
			logFail(message, expected + " - " + solution.toString(), actual + " - " + studentInstance.toString(), points);
			continueTesting = false;
		}
		
		solution.setNumber(buildRandomIllegal());
		studentInstance.setNumber(buildRandomLegal());
		expected = false;
		actual = studentInstance.equals(solution);
		pass = pass && (expected == actual);
		if(expected != actual) {
			logFail(message, expected + " - " + solution.toString(), actual + " - " + studentInstance.toString(), points);
			continueTesting = false;
		}
		
		for(int count = 100; count > 0; count--) {
			String testNumber = buildRandomLegal();
			setNumbers(testNumber);
			expected = true;
			actual = studentInstance.equals(solution);
			pass = pass && (expected == actual);
			if(expected != actual) {
				logFail(message + " " + testNumber, expected, actual, points);
				continueTesting = false;
			}
		}
		
		for(int count = 20; count > 0; count--) {
			String testNumber = buildRandomWithDigits(count);
			setNumbers(testNumber);
			expected = solution.equals(studentInstance);
			actual = studentInstance.equals(solution);
			pass = pass && (expected == actual);
			if(expected != actual) {
				logFail(message + " " + testNumber, expected, actual, points);
				continueTesting = false;
			}
		}
		
		for(int count = 100; count > 0; count--) {
			String testNumber = buildRandomIllegal();
			setNumbers(testNumber);
			expected = false;
			actual = studentInstance.equals(solution);
			pass = pass && (expected == actual);
			if(expected != actual) {
				logFail(message + " " + testNumber, expected, actual, points);
				continueTesting = false;
			}
		}
		
		for(int count = 100; count > 0; count--) {
			solution.setNumber(buildRandomLegal());
			studentInstance.setNumber(buildRandomLegal());
			expected = solution.equals(studentInstance);
			actual = studentInstance.equals(solution);
			pass = pass && (expected == actual);
			if(expected != actual) {
				logFail(message, expected + " - " + solution.toString(), actual + " - " + studentInstance.toString(), points);
				continueTesting = false;
			}
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	public void test60ToString() {
		final String message = "ToString";
		int points = 5;
		boolean pass = true;
		
		String testNumber = "800-111-2222";
		pass = pass && verifyToString(testNumber, testNumber, message, points);
		
		testNumber = "1-800-111-2222";
		pass = pass && verifyToString("800-111-2222", testNumber, message, points);
		
		testNumber = "8001112222";
		pass = pass && verifyToString("800-111-2222", testNumber, message, points);
		
		testNumber = "18001112222";
		pass = pass && verifyToString("800-111-2222", testNumber, message, points);
		
		testNumber = "800-111-222";
		pass = pass && verifyToString(NOT_VALID_TOSTRING, testNumber, message, points);
		
		for(int count = 20; count > 0; count--) {
			testNumber = buildRandomWithDigits(count);
			solution.setNumber(testNumber);
			pass = pass && verifyToString(solution.toString(), testNumber, message, points);
		}
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomLegal();
			solution.setNumber(testNumber);
			pass = pass && verifyToString(solution.toString(), testNumber, message, points);
		}
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomIllegal();
			pass = pass && verifyToString(NOT_VALID_TOSTRING, testNumber, message, points);
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	private boolean verifyToString(String expected, String testNumber, String message, int points) {
		String detailMessage = message + " " + testNumber;
		studentInstance.setNumber(testNumber);
		String actual = studentInstance.toString();
		return verifyStringEquality(expected, actual, detailMessage, points);
	}
	
	private boolean verifyStringEquality(String expected, String actual, String message, int points) {
		boolean pass = expected.equals(actual);
		if(!pass) {
			logFail(message, expected, actual, points);
			continueTesting = false;
		}
		return pass;
	}
	
	private boolean verifyLineNumber(String expected, String testNumber, String message, int points) {
		String detailMessage = message + " " + testNumber;
		studentInstance.setNumber(testNumber);
		boolean pass = verifyStringEquality(expected, studentInstance.getLineNumber(), detailMessage, points);
		return pass;
	}
	
	public void test50LineNumber() {
		final String message = "LineNumber";
		int points = 2;
		boolean pass = true;
		
		String testNumber = "800-111-2222";
		pass = pass && verifyLineNumber("2222", testNumber, message, points);
		
		testNumber = "1-" + testNumber;
		pass = pass && verifyLineNumber("2222", testNumber, message, points);
		
		testNumber = "800-111-222";
		pass = pass && verifyLineNumber(NOT_VALID_LINE_NUMBER, testNumber, message, points);
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomLegal();
			solution.setNumber(testNumber);
			pass = pass && verifyLineNumber(solution.getLineNumber(), testNumber, message, points);
		}
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomIllegal();
			pass = pass && verifyLineNumber(NOT_VALID_LINE_NUMBER, testNumber, message, points);
		}
		
		for(int count = 20; count > 0; count--) {
			testNumber = buildRandomWithDigits(count);
			solution.setNumber(testNumber);
			pass = pass && verifyLineNumber(solution.getLineNumber(), testNumber, message, points);
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	private boolean verifyPrefix(String expected, String testNumber, String message, int points) {
		String detailMessage = message + " " + testNumber;
		studentInstance.setNumber(testNumber);
		boolean pass = verifyStringEquality(expected, studentInstance.getPrefix(), detailMessage, points);
		return pass;
	}
	
	public void test40Prefix() {
		final String message = "Prefix";
		int points = 2;
		boolean pass = true;
		
		String testNumber = "800-111-2222";
		pass = pass && verifyPrefix("111", testNumber, message, points);
		
		testNumber = "1-" + testNumber;
		pass = pass && verifyPrefix("111", testNumber, message, points);
		
		testNumber = "800-111-222";
		pass = pass && verifyPrefix(NOT_VALID_PREFIX, testNumber, message, points);
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomLegal();
			solution.setNumber(testNumber);
			pass = pass && verifyPrefix(solution.getPrefix(), testNumber, message, points);
		}
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomIllegal();
			pass = pass && verifyPrefix(NOT_VALID_PREFIX, testNumber, message, points);
		}
		
		for(int count = 20; count > 0; count--) {
			testNumber = buildRandomWithDigits(count);
			solution.setNumber(testNumber);
			pass = pass && verifyPrefix(solution.getPrefix(), testNumber, message, points);
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	private boolean verifyAreaCode(String expected, String testNumber, String message, int points) {
		String detailMessage = message + " " + testNumber;
		studentInstance.setNumber(testNumber);
		boolean pass = verifyStringEquality(expected, studentInstance.getAreaCode(), detailMessage, points);
		return pass;
	}
	
	public void test30AreaCode() {
		final String message = "AreaCode";
		int points = 2;
		boolean pass = true;
		
		String testNumber = "800-111-2222";
		pass = pass && verifyAreaCode("800", testNumber, message, points);
		
		testNumber = "1-" + testNumber;
		pass = pass && verifyAreaCode("800", testNumber, message, points);
		
		testNumber = "800-111-222";
		pass = pass && verifyAreaCode(NOT_VALID_AREA_CODE, testNumber, message, points);
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomLegal();
			solution.setNumber(testNumber);
			pass = pass && verifyAreaCode(solution.getAreaCode(), testNumber, message, points);
		}
		
		for(int count = 100; count > 0; count--) {
			testNumber = buildRandomIllegal();
			pass = pass && verifyAreaCode(NOT_VALID_AREA_CODE, testNumber, message, points);
		}
		
		for(int count = 20; count > 0; count--) {
			testNumber = buildRandomWithDigits(count);
			solution.setNumber(testNumber);
			pass = pass && verifyAreaCode(solution.getAreaCode(), testNumber, message, points);
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	public void test20IsValidChars() {
		final String message = "IsValidChars";
		int points = 20;
		boolean pass = true;
		
		//space
		String testNumber = " 0123456789";
		String detailMessage = message + " space " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		testNumber = "0123 456789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		testNumber = "0123456789 ";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		
		//hyphen
		testNumber = "-0123456789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		testNumber = "0123-456789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		testNumber = "0123456789-";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		testNumber = "012-345-6789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		testNumber = "1-012-345-6789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		
		//illegal chars
		testNumber = "a0123456789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, false, detailMessage, points);
		testNumber = "0123B456789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, false, detailMessage, points);
		testNumber = "0123456789!";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, false, detailMessage, points);
		
		//random legal
		for(int testCount = 100; pass && testCount > 0; testCount--) {
			testNumber = buildRandomLegal();
			detailMessage = message + " " + testNumber;
			pass = pass && verifyValid(testNumber, true, detailMessage, points);
		}
		
		//random illegal
		for(int testCount = 100; pass && testCount > 0; testCount--) {
			testNumber = buildRandomIllegal();
			detailMessage = message + " iteration " + testCount + " " + testNumber;
			pass = pass && verifyValid(testNumber, false, detailMessage, points);
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	private String buildRandomIllegal() {
		StringBuilder sb = new StringBuilder(buildRandomLegal());
		char c = StringUtil.getRandomAsciiCharacterExcludeRange(MIN_ASCII, MAX_ASCII, StringUtil.FIRST_ASCII_DIGIT, 
				StringUtil.FIRST_ASCII_DIGIT + 9, LEGAL_ALPHAS_ABOVE_MIN);
		/*if(c == ' ' || c == '-')
			throw new IllegalStateException("Generated " + c);
		else {
			System.out.println("Illegal character " + c + " - " + (int)c);
		}*/
		sb.setCharAt(RANDOM.nextInt(sb.length()), c);
		return sb.toString();
	}
	
	private String buildRandomWithDigits(int numDigits) {
		StringBuilder sb = new StringBuilder(15);
		final boolean prefix1 = RANDOM.nextBoolean();
		if(prefix1) {
			appendLegalNonDigitSequence(sb);
			sb.append('1');
		}
		for(; numDigits > 0; numDigits--) {
			appendLegalNonDigitSequence(sb);
			sb.append(RANDOM.nextInt(10));
		}
		appendLegalNonDigitSequence(sb);
		return sb.toString();
	}
	
	private String buildRandomLegal() {
		return buildRandomWithDigits(10);
	}
	
	private void appendLegalNonDigitSequence(StringBuilder sb) {
		final byte maxLength = 5;
		if(RANDOM.nextBoolean()) {
			char nonDigit = RANDOM.nextBoolean() ? '-' : ' ';
			for(int numChars = RANDOM.nextInt(maxLength) + 1; numChars > 0; numChars--)
				sb.append(nonDigit);
		}
	}
	
	public void test10IsValidLength() {
		final String message = "IsValidLength";
		int points = 15;
		boolean pass = true;
		
		String testNumber = "";
		String detailMessage = message + " empty string";
		pass = pass && verifyValid(testNumber, false, detailMessage, points);
		
		//short
		testNumber = "012345678";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, false, detailMessage, points);
		
		//long
		testNumber = "01234567891";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, false, detailMessage, points);
		
		//long, leading 1
		testNumber = "101234567891";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, false, detailMessage, points);
		
		// correct
		testNumber = "0123456789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		
		// correct
		testNumber = "10123456789";
		detailMessage = message + " " + testNumber;
		pass = pass && verifyValid(testNumber, true, detailMessage, points);
		
		//random length with numbers
		boolean elevenRan = false;
		for(int numberLength = 20; pass && numberLength > 0; numberLength--) {
			boolean expected = numberLength == 10;
			if(numberLength == 11) {
				if(!elevenRan) {
					expected = true;
					testNumber = "1" + StringUtil.buildRandomString(numberLength - 1, StringUtil.FIRST_ASCII_DIGIT, StringUtil.FIRST_ASCII_DIGIT + 9);
					numberLength++;
					elevenRan = true;
				} else {
					int r = RANDOM.nextInt(9);
					if(r > 0)
						r++;
					testNumber = r + StringUtil.buildRandomString(numberLength - 1, StringUtil.FIRST_ASCII_DIGIT, StringUtil.FIRST_ASCII_DIGIT + 9);
				}
			} else
				testNumber = StringUtil.buildRandomString(numberLength, StringUtil.FIRST_ASCII_DIGIT, StringUtil.FIRST_ASCII_DIGIT + 9);
			detailMessage = message + " random " + testNumber;
			solution.setNumber(testNumber);
			pass = pass && verifyValid(testNumber, expected, detailMessage, points);
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		}
	}
	
	private boolean verifyValid(String testNumber, boolean expected, String message, int points) {
		studentInstance.setNumber(testNumber);
		boolean actual = studentInstance.isValid(); 
		boolean pass = expected == actual;
		if(!pass) {
			logFail(message, expected, actual, points);
			continueTesting = false;
		}
		return pass;
	}
	
	private void setNumbers(String number) {
		solution.setNumber(number);
		studentInstance.setNumber(number);
	}
	
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".PhoneNumber";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "java.lang.Object");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] constructorArgs = {""};
		studentInstance = (PhoneNumberable)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, constructorArgs);
		totalPoints += 50;
	}

}
