/**
 * 
 */
package edu.jenks.math.test;

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
	
	private static final Random RANDOM = new Random(System.currentTimeMillis()); 
	
	private String studentClassName;
	private AbstractBaseConverter studentInstance, solutionInstance;
	
	public BaseConverterTest() {
		solutionInstance = new edu.math.jenks.ted.BaseConverter();
	}
	
	// 16 points
	public void test01ConvertBinaryToDecimal() {
		int points = 8;
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
	
	// 16 points
	public void test02ConvertDecimalToBinary() {
		int points = 8;
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
	
	private boolean equalToLastSignificatDecimal(String exp, String act) {
		boolean equal = false;
		int decIndexExp = exp.indexOf('.'), decIndexAct = act.indexOf('.');
		if(decIndexExp < 0)
			throw new IllegalArgumentException("Silly Mr. Jenks - your expected float value does not have a decimal point!  What are you doing?");
		if(decIndexAct >= 0) {
			int endIndex = Math.min(exp.length(), decIndexExp + AbstractBaseConverter.DECIMAL_PRECISION);
			equal = endIndex <= act.length() && exp.substring(0, endIndex).equalsIgnoreCase(act.substring(0, endIndex));
		}
		return equal;
	}
	
	public void test04ConvertBaseWithFloat() {
		boolean allPass = true;
		String message = "convertBaseWithFloat", logMessage, currentNumber, exp, act;
		int points = 1, currentRadix, newRadix;
		
		currentNumber = "10.1";
		currentRadix = 2;
		newRadix = 10;
		exp = "2.5";
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		act = studentInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		logMessage = message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix;
		if(equalToLastSignificatDecimal(exp, act)) {
			totalPoints += points;
			logPass(logMessage);
		} else {
			allPass = false;
			logFail(logMessage, exp, act, points);
		}
		
		currentNumber = "1.5";
		currentRadix = 10;
		newRadix = 2;
		exp = "1.1";
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		act = studentInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		logMessage = message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix;
		if(equalToLastSignificatDecimal(exp, act)) {
			totalPoints += points;
			logPass(logMessage);
		} else {
			allPass = false;
			logFail(logMessage, exp, act, points);
		}
		
		// convert base 10 down
		currentNumber = String.valueOf(1 + 100 * RANDOM.nextDouble());
		currentRadix = 10;
		newRadix = 3 + RANDOM.nextInt(currentRadix);
		exp = solutionInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		act = studentInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		logMessage = message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix;
		if(equalToLastSignificatDecimal(exp, act)) {
			totalPoints += points;
			logPass(logMessage, exp, act);
		} else {
			allPass = false;
			logFail(logMessage, exp, act, points);
		}
		
		// convert base 10 up
		currentNumber = String.valueOf(1 + 100 * RANDOM.nextDouble());
		currentRadix = 10;
		newRadix = 11 + RANDOM.nextInt(AbstractBaseConverter.MAX_RADIX - 11);
		exp = solutionInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		constructInputToStudentCode(currentRadix, newRadix, currentNumber);
		act = studentInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		logMessage = message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix;
		if(equalToLastSignificatDecimal(exp, act)) {
			totalPoints += points;
			logPass(logMessage, exp, act);
		} else {
			allPass = false;
			logFail(logMessage, exp, act, points);
		}
		
		// convert between bases 3 and MAX
		final int minRadix = 3, maxRadix = AbstractBaseConverter.MAX_RADIX;
		for(int count = 100; count > 0 && allPass; count--) {
			String decNumber = String.valueOf(1 + 100 * RANDOM.nextDouble());
			currentRadix = minRadix + RANDOM.nextInt(maxRadix - minRadix);
			currentNumber = solutionInstance.convertBaseWithFloat(decNumber, 10, currentRadix);
			newRadix = minRadix + RANDOM.nextInt(maxRadix - minRadix);
			exp = solutionInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
			constructInputToStudentCode(currentRadix, newRadix, currentNumber);
			act = studentInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
			logMessage = message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix;
			if(!equalToLastSignificatDecimal(exp, act)) {
				allPass = false;
				logFail(logMessage, exp, act, points);
			}
		}
		
		if(allPass) {
			totalPoints += 4;
			logPass(message);
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
		studentInstance = (AbstractBaseConverter)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 50;
	}
	
	private void constructInputToStudentCode(int currentRadix, int newRadix, String currentNumber) {
		StringBuilder sb = new StringBuilder(50);
		sb.append("current radix: ").append(currentRadix);
		sb.append("; new radix: ").append(newRadix);
		sb.append("; current number: ").append(currentNumber);
		inputToStudentCode = sb.toString();
	}

}
