package edu.jenks.math.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.math.AbstractRational;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

public class RationalTest extends Testable {
	
	private static final Class<?>[] CONSTRUCTOR_PARAM_TYPES = {int.class, int.class};
	
	private String studentClassName;
	private final Random RANDOM = new Random(System.currentTimeMillis());
	private final Map<Integer, Boolean> DECIMAL_TERMINATES_TESTS = new HashMap<Integer, Boolean>(20);
	
	public void test01Constructor() {
		int points = 6;
		boolean pass = true;
		String message = "constructor", expString = "5/2", failMessage = message + " - testing aborted";
		int expNum = 5, expDen = 2;
		AbstractRational studentInstance = studentInstance(expNum, expDen);
		if(studentInstance.getNumerator() != expNum || studentInstance.getDenominator() != expDen) {
			pass = false;
			logFail(failMessage, expString, studentInstance.toString(), points);
		}
		
		expString = "-5/2";
		expNum = -5;
		studentInstance = studentInstance(expNum, expDen);
		if(studentInstance.getNumerator() != expNum || studentInstance.getDenominator() != expDen) {
			pass = false;
			logFail(failMessage, expString, studentInstance.toString(), points);
		}
		
		expString = "-5/2";
		expNum = -5;
		studentInstance = studentInstance(5, -2);
		if(studentInstance.getNumerator() != expNum || studentInstance.getDenominator() != expDen) {
			pass = false;
			logFail(failMessage, expString, studentInstance.toString(), points);
		}
		
		if(pass) {
			logPass(message);
			totalPoints += points;
		} else
			continueTesting = false;
	}
	
	public void test02ToString() {
		int points = 6;
		boolean pass = false;
		String message = "toString", failMessage = message + " - testing aborted";
		int num = 3, den = 1;
		String exp = "3";
		AbstractRational studentRational = studentInstance(num, den);
		String actual = studentRational.toString();
		if(exp.equals(actual)) {
			den = 2;
			exp = num + "/" + den;
			studentRational.setNumerator(num);
			studentRational.setDenominator(den);
			studentRational.reduce();
			actual = studentRational.toString();
			if(exp.equals(actual)) {
				den = nonZeroRandomNumer(1000);
				num = 0;
				exp = "0";
				studentRational.setNumerator(num);
				studentRational.setDenominator(den);
				studentRational.reduce();
				actual = studentRational.toString();
				if(exp.equals(actual)) {
					totalPoints += points;
					logPass(message);
					pass = true;
				}
			}
		}
		if(!pass) {
			logFail(failMessage, exp, actual, points);
			continueTesting = false;
		}
	}
	
	public void test03Reduce() {
		int points = 8;
		boolean pass = false;
		AbstractRational studentRational = studentInstance(30, 25);
		String expected = "6/5";
		String actual = studentRational.toString();
		if(expected.equals(actual)) {
			studentRational.setNumerator(80);
			studentRational.setDenominator(44);
			studentRational.reduce();
			expected = "20/11";
			actual = studentRational.toString();
			if(expected.equals(actual)) {
				int num = nonZeroRandomNumer(1000);
				int den = num * nonZeroRandomNumer(100);
				AbstractRational solutionRational = solutionInstance(num, den);
				studentRational = studentInstance(num, den);
				expected = solutionRational.toString();
				actual = studentRational.toString();
				if(expected.equals(actual)) {
					totalPoints += points;
					logPass("reduce");
					pass = true;
				}
			}
		} else
			logFail("the current state of the data must always be in reduced form - check constructor");
		if(!pass) {
			continueTesting = false;
			logFail("reduce", expected, actual, points);
		}	
	}
	
	public void test04Equals() {
		int points = 2;
		int sNum = 2, sDen = 3, dNum = 2, dDen = 5;
		AbstractRational studentRationalS1 = studentInstance(sNum, sDen);
		AbstractRational studentRationalS2 = studentInstance(sNum, sDen);
		AbstractRational studentRationalD = studentInstance(dNum, dDen);
		if(studentRationalS1.equals(studentRationalS2) && !studentRationalS1.equals(studentRationalD)) {
			logPass("equals");
			totalPoints += points;
		} else
			logFail("equals");
	}
	
	public void test05Reciprocal() {
		int points = 2;
		boolean pass = false;
		int num = 2, den = 5;
		String expected = "5/2";
		AbstractRational studentRational = studentInstance(num, den);
		studentRational = studentRational.reciprocal();
		String actual = studentRational.toString();
		if(expected.equals(actual)) {
			num = nonZeroRandomNumer(1000);
			den = nonZeroRandomNumer(1000);
			studentRational.setNumerator(num);
			studentRational.setDenominator(den);
			studentRational.reduce();
			studentRational = studentRational.reciprocal();
			AbstractRational solutionRational = solutionInstance(num, den).reciprocal();
			expected = solutionRational.toString();
			actual = studentRational.toString();
			if(solutionRational.equals(studentRational)) {
				pass = true;
				totalPoints += points;
				logPass("reciprocal");
			}
		}
		if(!pass)
			logFail("reciprocal", expected, actual, points);
	}
	
	public void test06Add() {
		int points = 8;
		boolean pass = false;
		String message = "add";
		int num1 = 1, den1 = 8, num2 = 17, den2 = 24;
		String expected = "5/6";
		AbstractRational studentRational1 = studentInstance(num1, den1);
		AbstractRational studentRational2 = studentInstance(num2, den2);
		AbstractRational studentRationalSum = studentRational1.add(studentRational2);
		String actual = studentRationalSum.toString();
		if(expected.equals(actual)) {
			num1 = nonZeroRandomNumer(1000);
			den1 = nonZeroRandomNumer(1000);
			num2 = nonZeroRandomNumer(1000);
			den2 = nonZeroRandomNumer(1000);
			studentRational1.setNumerator(num1);
			studentRational1.setDenominator(den1);
			studentRational1.reduce();
			studentRational2.setNumerator(num2);
			studentRational2.setDenominator(den2);
			studentRational2.reduce();
			studentRationalSum = studentRational1.add(studentRational2);
			AbstractRational solutionRationalSum = solutionInstance(num1, den1).add(solutionInstance(num2, den2));
			expected = solutionRationalSum.toString();
			actual = studentRationalSum.toString();
			if(expected.equals(actual)) {
				pass = true;
				totalPoints += points;
				logPass(message);
			}
		}
		if(!pass)
			logFail(message, expected, actual, points);
	}
	
	public void test07Substract() {
		int points = 3;
		boolean pass = false;
		String message = "subtract";
		int num1 = 1, den1 = 8, num2 = 17, den2 = 24;
		String expected = "-7/12";
		AbstractRational studentRational1 = studentInstance(num1, den1);
		AbstractRational studentRational2 = studentInstance(num2, den2);
		AbstractRational studentRationalDiff = studentRational1.subtract(studentRational2);
		String actual = studentRationalDiff.toString();
		if(expected.equals(actual)) {
			num1 = nonZeroRandomNumer(1000);
			den1 = nonZeroRandomNumer(1000);
			num2 = nonZeroRandomNumer(1000);
			den2 = nonZeroRandomNumer(1000);
			studentRational1.setNumerator(num1);
			studentRational1.setDenominator(den1);
			studentRational1.reduce();
			studentRational2.setNumerator(num2);
			studentRational2.setDenominator(den2);
			studentRational1.reduce();
			studentRationalDiff = studentRational1.subtract(studentRational2);
			AbstractRational solutionRationalDiff = solutionInstance(num1, den1).subtract(solutionInstance(num2, den2));
			expected = solutionRationalDiff.toString();
			actual = studentRationalDiff.toString();
			if(expected.equals(actual)) {
				pass = true;
				totalPoints += points;
				logPass(message);
			}
		}
		if(!pass)
			logFail(message, expected, actual, points);
	}
	
	public void test08Multiply() {
		int points = 8;
		boolean pass = false;
		String message = "multiply";
		int num1 = 1, den1 = 4, num2 = 2, den2 = 3;
		String expected = "1/6";
		AbstractRational studentRational1 = studentInstance(num1, den1);
		AbstractRational studentRational2 = studentInstance(num2, den2);
		AbstractRational studentRationalProd = studentRational1.multiply(studentRational2);
		String actual = studentRationalProd.toString();
		if(expected.equals(actual)) {
			num1 = nonZeroRandomNumer(1000);
			den1 = nonZeroRandomNumer(1000);
			num2 = nonZeroRandomNumer(1000);
			den2 = nonZeroRandomNumer(1000);
			studentRational1.setNumerator(num1);
			studentRational1.setDenominator(den1);
			studentRational1.reduce();
			studentRational2.setNumerator(num2);
			studentRational2.setDenominator(den2);
			studentRational1.reduce();
			studentRationalProd = studentRational1.multiply(studentRational2);
			AbstractRational solutionRationalProd = solutionInstance(num1, den1).multiply(solutionInstance(num2, den2));
			expected = solutionRationalProd.toString();
			actual = studentRationalProd.toString();
			if(expected.equals(actual)) {
				pass = true;
				totalPoints += points;
				logPass(message);
			}
		}
		if(!pass)
			logFail(message, expected, actual, points);
	}
	
	public void test09Divide() {
		int points = 3;
		boolean pass = false;
		String message = "divide";
		int num1 = 1, den1 = 4, num2 = 2, den2 = 3;
		String expected = "3/8";
		AbstractRational studentRational1 = studentInstance(num1, den1);
		AbstractRational studentRational2 = studentInstance(num2, den2);
		AbstractRational studentRationalQuot = studentRational1.divide(studentRational2);
		String actual = studentRationalQuot.toString();
		if(expected.equals(actual)) {
			num1 = nonZeroRandomNumer(1000);
			den1 = nonZeroRandomNumer(1000);
			num2 = nonZeroRandomNumer(1000);
			den2 = nonZeroRandomNumer(1000);
			studentRational1.setNumerator(num1);
			studentRational1.setDenominator(den1);
			studentRational1.reduce();
			studentRational2.setNumerator(num2);
			studentRational2.setDenominator(den2);
			studentRational1.reduce();
			studentRationalQuot = studentRational1.divide(studentRational2);
			AbstractRational solutionRationalQuot = solutionInstance(num1, den1).divide(solutionInstance(num2, den2));
			expected = solutionRationalQuot.toString();
			actual = studentRationalQuot.toString();
			if(expected.equals(actual)) {
				pass = true;
				totalPoints += points;
				logPass(message);
			}
		}
		if(!pass)
			logFail(message, expected, actual, points);
	}
	
	public void test10DecimalTerminates() {
		int points = 4;
		String message = "decimalTerminates";
		boolean pass = true;
		int denom, numer = 1;
		AbstractRational studentRational;
		boolean expected, actual;
		Iterator<Integer> denominators = DECIMAL_TERMINATES_TESTS.keySet().iterator();
		while(denominators.hasNext() && pass) {
			denom = denominators.next();
			expected = DECIMAL_TERMINATES_TESTS.get(denom);
			studentRational = studentInstance(numer, denom);
			actual = studentRational.decimalTerminates();
			if(expected != actual) {
				pass = false;
				logFail(message + " (basic " + denom + ") - " + studentRational.toString(), String.valueOf(expected), String.valueOf(actual), points);
			}
		}
		
		for(int count = 100; count > 0 && pass; count--) {
			expected = true;
			// power of 2
			denom = (int)Math.pow(2, RANDOM.nextInt(10) + 1);
			if(!testDecimalTerminates(points, expected, denom)) {
				logFail(message + " P2");
				pass = false;
			}
			
			// power of 5
			denom = (int)Math.pow(5, RANDOM.nextInt(10) + 1);
			if(!testDecimalTerminates(points, expected, denom)) {
				logFail(message + " P5");
				pass = false;
			}
			
			// power of 2 and 5
			denom = (int)Math.pow(2, RANDOM.nextInt(8) + 1) * (int)Math.pow(5, RANDOM.nextInt(8) + 1);
			if(!testDecimalTerminates(points, expected, denom)) {
				logFail(message + " P2&5");
				pass = false;
			}
			
			// not power of only 2 or 5
			expected = false;
			denom = 410 * nonZeroRandomNumer(1000);
			if(!testDecimalTerminates(points, expected, denom)) {
				logFail(message + " !(P2|5)");
				pass = false;
			}
		}
		
		if(pass) {
			totalPoints += points;
			logPass(message);
		}
	}
	
	private boolean testDecimalTerminates(int points, boolean expected, int denom) {
		int numer = nonZeroRandomNumer(1000);
		while(denom > 1 && numer % denom == 0)
			numer /= denom;
		AbstractRational studentRational = studentInstance(numer, denom);
		boolean actual = studentRational.decimalTerminates();
		boolean pass = expected == actual;
		if(!pass)
			logFail("decimalTerminates - " + studentRational.toString(), String.valueOf(expected), String.valueOf(actual), points);
		return pass;
	}
	
	private int nonZeroRandomNumer(int bound) {
		boolean negative = RANDOM.nextBoolean();
		int numer = 1;
		if(negative)
			numer *= -1;
		return numer;
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".Rational";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.math.AbstractRational");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		AbstractRational studentInstance = studentInstance(1, 1);
		String message = "object creation";
		if(studentInstance != null) {
			totalPoints += 50;
			initDecimalMap();
		} else {
			continueTesting = false;
			logFail(message);
		}
	}
	
	private void initDecimalMap() {
		DECIMAL_TERMINATES_TESTS.put(1, true);
		DECIMAL_TERMINATES_TESTS.put(2, true);
		DECIMAL_TERMINATES_TESTS.put(3, false);
		DECIMAL_TERMINATES_TESTS.put(4, true);
		DECIMAL_TERMINATES_TESTS.put(5, true);
		DECIMAL_TERMINATES_TESTS.put(6, false);
		DECIMAL_TERMINATES_TESTS.put(7, false);
		DECIMAL_TERMINATES_TESTS.put(8, true);
		DECIMAL_TERMINATES_TESTS.put(9, false);
		DECIMAL_TERMINATES_TESTS.put(10, true);
	}
	
	private AbstractRational studentInstance(int numer, int denom) {
		AbstractRational instance = null;
		Object[] constructorValues = {numer, denom};
		try {
			instance = (AbstractRational)ReflectionUtil.newInstance(studentClassName, CONSTRUCTOR_PARAM_TYPES, constructorValues);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logException("studentInstance", e);
		}
		return instance;
	}
	
	private AbstractRational solutionInstance(int numer, int denom) {
		return new edu.math.jenks.ted.Rational(numer, denom);
	}

}
