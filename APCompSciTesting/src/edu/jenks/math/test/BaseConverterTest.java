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
		if(exp.equals(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + binaryNumber, exp, act, points);
		}
		
		dec = RANDOM.nextInt(9000) + 1000;
		binaryNumber = Integer.toString(dec, 2);
		act = studentInstance.convertBinaryToDecimal(binaryNumber);
		exp = String.valueOf(dec);
		if(exp.equals(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + binaryNumber, exp, act, points);
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
		if(exp.equals(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + dec, exp, act, points);
		}
		
		dec = 1000 + RANDOM.nextInt(9000);
		exp = Integer.toString(dec, 2);
		act = studentInstance.convertDecimalToBinary(String.valueOf(dec));
		if(exp.equals(act))
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
	
	public void test03ConvertBase() {
		boolean allPass = true;
		String message = "convertBase", currentNumber, exp, act;
		int points = 5, currentRadix, newRadix, decNumberToConvert;
		
		//convert up - do not use 2 or 10
		decNumberToConvert = RANDOM.nextInt(9000) + 1000;
		currentRadix = 3 + RANDOM.nextInt(7);
		newRadix = 11 + RANDOM.nextInt(9);
		currentNumber = Integer.toString(decNumberToConvert, currentRadix);
		exp = Integer.toString(decNumberToConvert, newRadix);
		act = studentInstance.convertBase(currentNumber, currentRadix, newRadix);
		if(exp.equals(act))
			totalPoints += points;
		else {
			allPass = false;
			logFail(message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix, exp, act, points);
		}
		
		//convert down - do not use 2 or 10
		decNumberToConvert = RANDOM.nextInt(9000) + 1000;
		currentRadix = 11 + RANDOM.nextInt(9);
		newRadix = 3 + RANDOM.nextInt(7);
		currentNumber = Integer.toString(decNumberToConvert, currentRadix);
		exp = Integer.toString(decNumberToConvert, newRadix);
		act = studentInstance.convertBase(currentNumber, currentRadix, newRadix);
		if(exp.equals(act))
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
	
	public void test04ConvertBaseWithFloat() {
		boolean allPass = true;
		String message = "convertBaseWithFloat", logMessage, currentNumber, exp, act;
		int points = 1, currentRadix, newRadix, decNumberToConvert;
		
		currentNumber = "10.1";
		currentRadix = 2;
		newRadix = 10;
		exp = "2.5";
		act = studentInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		logMessage = message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix;
		if(exp.equals(act)) {
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
		act = studentInstance.convertBaseWithFloat(currentNumber, currentRadix, newRadix);
		logMessage = message + ": " + currentNumber + " base " + currentRadix + ", to base " + newRadix;
		if(exp.equals(act)) {
			totalPoints += points;
			logPass(logMessage);
		} else {
			allPass = false;
			logFail(logMessage, exp, act, points);
		}
		
		if(allPass)
			logPass(message);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#getPointsAvailable()
	 */
	@Override
	public int getPointsAvailable() {
		return 94;
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

}
