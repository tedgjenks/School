/**
 * 
 */
package edu.jenks.math.test;
import static java.lang.System.out;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.math.AbstractBaseConverter;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class BaseConverterTest extends Testable {
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {double.class};
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static final double RELATIVE_DELTA = 0.0001;
	
	private String studentClassName;
	private AbstractBaseConverter studentInstance;
	private edu.math.jenks.ted.BaseConverter solutionInstance;
	
	public BaseConverterTest() {
		solutionInstance = new edu.math.jenks.ted.BaseConverter(RELATIVE_DELTA);
	}
	
	// 14 points
	public void test01ConvertBinaryToDecimal() {
		int points = 7;
		boolean allPass = true;
		String message = "convertBinaryToDecimal";
		int dec = RANDOM.nextInt(9000) + 1000;
		String binaryNumber = Integer.toString(dec, 2);
		String act = studentInstance.convertBinaryToDecimal(binaryNumber);
		String exp = String.valueOf(dec);
		if(exp.equalsIgnoreCase(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + binaryNumber, exp, act, points);
		}
		
		dec = RANDOM.nextInt(9000) + 1000;
		binaryNumber = Integer.toString(dec, 2);
		act = studentInstance.convertBinaryToDecimal(binaryNumber);
		exp = String.valueOf(dec);
		if(exp.equalsIgnoreCase(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + binaryNumber, exp, act, points);
		}
		
		binaryNumber = "0";
		act = studentInstance.convertBinaryToDecimal(binaryNumber);
		exp = "0";
		if(!exp.equalsIgnoreCase(act)) {
			allPass = false;
			logFail(message + " - you can't even convert zero???: ", exp, act, points);
		}
		
		if(allPass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	// 14 points
	public void test02ConvertDecimalToBinary() {
		int points = 7;
		boolean allPass = true;
		String message = "convertDecimalToBinary";
		int dec = 1000 + RANDOM.nextInt(9000);
		String exp = Integer.toString(dec, 2);
		String act = studentInstance.convertDecimalToBinary(String.valueOf(dec));
		if(exp.equalsIgnoreCase(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + dec, exp, act, points);
		}
		
		dec = 1000 + RANDOM.nextInt(9000);
		exp = Integer.toString(dec, 2);
		act = studentInstance.convertDecimalToBinary(String.valueOf(dec));
		if(exp.equalsIgnoreCase(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + dec, exp, act, points);
		}
		
		if(allPass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	// 10 points
	public void test03ConvertBase() {
		boolean allPass = true;
		String message = "convertBase", currentNumber, exp, act;
		int points = 5, currentRadix, newRadix, decNumberToConvert;
		
		//convert up - do not use 2 or 10
		decNumberToConvert = RANDOM.nextInt(9000) + 1000;
		currentRadix = 3 + RANDOM.nextInt(7);
		newRadix = 11 + RANDOM.nextInt(AbstractBaseConverter.MAX_RADIX - 11);
		currentNumber = Integer.toString(decNumberToConvert, currentRadix);
		exp = Integer.toString(decNumberToConvert, newRadix);
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		act = studentInstance.convertBase(currentNumber, currentRadix, newRadix);
		if(exp.equalsIgnoreCase(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix, exp, act, points);
		}
		
		//convert down - do not use 2 or 10
		decNumberToConvert = RANDOM.nextInt(9000) + 1000;
		currentRadix = 11 + RANDOM.nextInt(AbstractBaseConverter.MAX_RADIX - 11);
		newRadix = 3 + RANDOM.nextInt(7);
		currentNumber = Integer.toString(decNumberToConvert, currentRadix);
		exp = Integer.toString(decNumberToConvert, newRadix);
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		act = studentInstance.convertBase(currentNumber, currentRadix, newRadix);
		if(exp.equalsIgnoreCase(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix, exp, act, points);
		}
		
		if(allPass)
			logPass(message);
		else
			continueTesting = false;
	}
	
	private boolean equalToLastSignificatDecimal(String exp, String act, int currentRadix, int newRadix) {
		int decIndexExp = exp.indexOf('.');
		if(decIndexExp < 0)
			throw new IllegalArgumentException("Silly Mr. Jenks - your expected float value does not have a decimal point!  What are you doing?");
		if(solutionInstance.willTerminate(currentRadix, newRadix) && !solutionInstance.overflowsInt)
			return exp.equals(act);
		else
			return act != null && act.startsWith(exp);
	}
	
	public void test04ConvertBaseWithFloat() {
		boolean allPass = true;
		String message = "convertBaseWithFloat", currentNumber;
		int points = 1, currentRadix, newRadix;
		
		currentNumber = "10.1";
		currentRadix = 2;
		newRadix = 10;
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		if(!verifyAndLogConversionWithFloat(message, currentNumber, currentRadix, newRadix, points))
			allPass = false;
		else {
			totalPoints += points;
			logPass(message + " base 2 to 10");
		}
		
		currentNumber = "1.5";
		currentRadix = 10;
		newRadix = 2;
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		if(!verifyAndLogConversionWithFloat(message, currentNumber, currentRadix, newRadix, points))
			allPass = false;
		else {
			totalPoints += points;
			logPass(message + " base 10 to 2");
		}
		
		// convert base 10 down
		currentNumber = String.valueOf(1 + 100 * RANDOM.nextDouble());
		currentRadix = 10;
		newRadix = 3 + RANDOM.nextInt(currentRadix);
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		if(!verifyAndLogConversionWithFloat(message, currentNumber, currentRadix, newRadix, points))
			allPass = false;
		else {
			totalPoints += points;
			logPass(message + " base 10 down");
		}
		
		// convert base 10 up
		currentNumber = String.valueOf(1 + 100 * RANDOM.nextDouble());
		currentRadix = 10;
		newRadix = 11 + RANDOM.nextInt(AbstractBaseConverter.MAX_RADIX - 11);
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		if(!verifyAndLogConversionWithFloat(message, currentNumber, currentRadix, newRadix, points))
			allPass = false;
		else {
			totalPoints += points;
			logPass(message + " base 10 up");
		}
		
		// convert base 3 and 4 to bases 12 and 24
		int exactConversionPoints = 2;
		exact:
		for(int cRadix = 3; cRadix <= 4; cRadix++) {
			for(int nRadix = 12; nRadix <= 24; nRadix *= 2) {
				if(!verifyAndLogConversionWithFloat(message, buildRandomNumber(cRadix, 2, 4), cRadix, nRadix, exactConversionPoints)) {
					allPass = false;
					break exact;
				}
			}
		}
		if(allPass)
			logPass(message + " base 3 and 4 to 12 and 24");
		
		// convert base 9 to 6
		currentRadix = 9;
		newRadix = 6;
		if(!verifyAndLogConversionWithFloat(message, buildRandomNumber(currentRadix, 1, 4), currentRadix, 6, exactConversionPoints))
			allPass = false;
		else
			logPass(message + " base " + currentRadix + " to " + newRadix);
		
		// convert base 18 to 6
		currentRadix = 18;
		newRadix = 6;
		if(!verifyAndLogConversionWithFloat(message, buildRandomNumber(currentRadix, 1, 4), currentRadix, newRadix, exactConversionPoints))
			allPass = false;
		else
			logPass(message + " base " + currentRadix + " to " + newRadix);
		
		// award exact conversion points
		if(allPass) {
			logPass(message + " all non-random exact conversion");
			totalPoints += exactConversionPoints;
		}
		
		// possible exact conversion, but approximation expected due to overflow
		int allPassPoints = 4;
		currentRadix = 25;
		newRadix = 20;
		String willTerminateButApproximate = message + " will terminate, but approximate to avoid integer overflow";
		if(!verifyAndLogConversionWithFloat(willTerminateButApproximate, "fk.b5437", currentRadix, newRadix, allPassPoints))
			allPass = false;
		else
			logPass(willTerminateButApproximate);
		
		// convert between bases 3 and MAX
		message += " random";
		final int minRadix = 3, maxRadix = AbstractBaseConverter.MAX_RADIX;
		for(int count = 500; count > 0 && allPass; count--) {
			currentRadix = minRadix + RANDOM.nextInt(maxRadix - minRadix);
			currentNumber = buildRandomNumber(currentRadix, 2, 5);
			newRadix = minRadix + RANDOM.nextInt(maxRadix - minRadix);
			constructInputToStudentCode(currentRadix, newRadix, currentNumber);
			inputToStudentCode += "; " + count + " remaining.";
			if(!verifyAndLogConversionWithFloat(message, currentNumber, currentRadix, newRadix, allPassPoints))
				allPass = false;
		}
		
		if(allPass) {
			totalPoints += allPassPoints;
			logPass(message);
		}
	}
	
	private boolean verifyAndLogConversionWithFloat(String message, final String curNum, final int currentRadix, final int newRadix, int points) {
		boolean pass = true;
		String exp = solutionInstance.convertBaseWithFloat(curNum, currentRadix, newRadix);
		String act = studentInstance.convertBaseWithFloat(curNum, currentRadix, newRadix);
		if(!equalToLastSignificatDecimal(exp, act, currentRadix, newRadix)) {
			if(solutionInstance.willTerminate(currentRadix, newRadix))
				message += " exact conversion";
			logFail(buildConvertBaseFailMessage(message, curNum, currentRadix, newRadix), exp, act, points);
			pass = false;
		}
		if(exp.substring(exp.indexOf('.') + 1).length() > 15) {
			if(solutionInstance.willTerminate(currentRadix, newRadix))
				message += " exact conversion";
			logInfo("Show this to Mr. Jenks! " + buildConvertBaseFailMessage(message, curNum, currentRadix, newRadix) + "; expected: " + exp);
		}
		return pass;
	}
	
	private String buildConvertBaseFailMessage(String message, String number, int currentRadix, int newRadix) {
		StringBuilder sb = new StringBuilder(50);
		sb.append(message).append(": ").append(number).append(" base ").append(currentRadix).append(", to base ").append(newRadix);
		return sb.toString();
	}
	
	/**
	 * 2 points
	 */
	public void test05WillTerminate() {
		String message = "willTerminate";
		int points = 2;
		boolean allPass = true;
		outer:
		for(int curRadix = AbstractBaseConverter.MAX_RADIX; curRadix >= AbstractBaseConverter.MIN_RADIX; curRadix--) {
			for(int newRadix = AbstractBaseConverter.MAX_RADIX; newRadix >= AbstractBaseConverter.MIN_RADIX; newRadix--) {
				if(solutionInstance.willTerminate(curRadix, newRadix) != studentInstance.willTerminate(curRadix, newRadix)) {
					allPass = false;
					 break outer;
				}
			}
		}
		
		if(allPass) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message);
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
		studentClassName = studentPackage + ".BaseConverter";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.math.AbstractBaseConverter");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object[] constructorValues = {RELATIVE_DELTA};
		studentInstance = (AbstractBaseConverter)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, constructorValues);
		totalPoints += 50;
	}
	
	private void constructInputToStudentCode(int currentRadix, int newRadix, String currentNumber) {
		StringBuilder sb = new StringBuilder(50);
		sb.append("current radix: ").append(currentRadix);
		sb.append("; new radix: ").append(newRadix);
		sb.append("; current number: ").append(currentNumber);
		inputToStudentCode = sb.toString();
	}
	
	private String buildRandomNumber(int radix, int wholeDigits, int fractionDigits) {
		StringBuilder sb = new StringBuilder(wholeDigits + fractionDigits + 1);
		sb.append(Character.forDigit(1 + RANDOM.nextInt(radix - 1), radix)); // first digit not zero
		for(int count = wholeDigits; count > 1; count--)
			sb.append(Character.forDigit(RANDOM.nextInt(radix), radix));
		sb.append(".");
		for(int count = fractionDigits; count > 1; count--)
			sb.append(Character.forDigit(RANDOM.nextInt(radix), radix));
		sb.append(Character.forDigit(1 + RANDOM.nextInt(radix - 1), radix)); // last digit not zero
		return sb.toString();
	}
	
	public static void main(String[] args) {
		BaseConverterTest bct = new BaseConverterTest();
		out.println(bct.buildRandomNumber(3, 2, 4));
	}
}
