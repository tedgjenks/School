/**
 * 
 */
package edu.jenks.unitconversion.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import edu.jenks.dist.unitconversion.Convertible;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted
 *
 */
public class ConvertibleTest extends Testable {
	
	private String studentClassName;
	private Convertible studentConvertible;
	private Convertible solutionConvertible = new edu.unitconversion.jenks.ted.MetricLengthConverter();
	private final double RELATIVE_DELTA = Math.pow(10, -3);
	private final Random random = new Random(System.currentTimeMillis());
	private boolean passedIntermediateCalculations = true;
	private double numberUnits = random.nextDouble() * 1000;
	
	private void testConvertFromMetersHelper(String unit, int points) {
		double actVal = studentConvertible.convertFromMeters(numberUnits, unit);
		double expVal = solutionConvertible.convertFromMeters(numberUnits, unit);
		String message = "convertFromMeters " + unit;
		if(MathUtil.equalsRelative(actVal, expVal, RELATIVE_DELTA)) {
			logPass(message, expVal, actVal);
			totalPoints += points;
		} else {
			logFail(message, expVal, actVal, points);
			feedbackLogger.log(Level.WARNING, "Fail: " + unit + " from meters.");
			passedIntermediateCalculations = false;
		}
	}
	
	//10 points
	public void test2ConvertFromMeters() {
		String unit = "m";
		testConvertFromMetersHelper(unit, 2);
		
		unit = "dm";
		testConvertFromMetersHelper(unit, 4);
		
		unit = "dam";
		testConvertFromMetersHelper(unit, 4);
	}
	
	private void testConvertToMetersHelper(String unit, int points) {
		double actVal = studentConvertible.convertToMeters(numberUnits, unit);
		double expVal = solutionConvertible.convertToMeters(numberUnits, unit);
		String message = "convertToMeters " + unit;
		if(MathUtil.equalsRelative(actVal, expVal, RELATIVE_DELTA)) {
			logPass(message, expVal, actVal);
			totalPoints += points;
		} else {
			logFail(message, expVal, actVal, points);
			passedIntermediateCalculations = false;
		}
	}
	
	//10 points
	public void test1ConvertToMeters() {
		String unit = "m";
		testConvertToMetersHelper(unit, 2);
		
		unit = "cm";
		testConvertToMetersHelper(unit, 4);
		
		unit = "km";
		testConvertToMetersHelper(unit, 4);
	}
	
	private void testConvertHelper(String startingUnit, String desiredUnit, int points) {
		testConvertHelper(startingUnit, numberUnits, desiredUnit, points);
	}
	
	private void testConvertHelper(String startingUnit, double numStartingUnit, String desiredUnit, int points) {
		double actVal = studentConvertible.convert(numStartingUnit, startingUnit, desiredUnit);
		double expVal = solutionConvertible.convert(numStartingUnit, startingUnit, desiredUnit);
		String message = "convert " + startingUnit + " to " + desiredUnit;
		if(MathUtil.equalsRelative(actVal, expVal, RELATIVE_DELTA)) {
			logPass(message, expVal, actVal);
			totalPoints += points;
		} else {
			logFail(message, expVal, actVal, points);
			passedIntermediateCalculations = false;
		}
	}
	
	//16 points
	public void test3Convert() {
		if(passedIntermediateCalculations) {
			String startingUnit = "nm", desiredUnit = "Gm";
			testConvertHelper(startingUnit, desiredUnit, 3);
			
			startingUnit = "Gm";
			desiredUnit = "nm";
			testConvertHelper(startingUnit, desiredUnit, 3);
			
			startingUnit = "Mm";
			desiredUnit = "um";
			testConvertHelper(startingUnit, desiredUnit, 3);
			
			startingUnit = "mm";
			desiredUnit = "km";
			testConvertHelper(startingUnit, desiredUnit, 3);
			
			startingUnit = "m";
			desiredUnit = "m";
			testConvertHelper(startingUnit, 0, desiredUnit, 4);
		} else
			feedbackLogger.log(Level.WARNING, "convert method not tested due to failure of dependent methods.");
	}
	
	private void testConvertForDisplayHelper(String startingUnit, double numStartingUnit, String desiredUnit, int points) {
		String expVal = solutionConvertible.convertForDisplay(numStartingUnit, startingUnit, desiredUnit);
		String actVal = studentConvertible.convertForDisplay(numStartingUnit, startingUnit, desiredUnit);
		String message = "convertForDisplay";
		if(testDisplay(expVal, actVal)) {
			totalPoints += points;
			logPass(message, expVal, actVal);
		} else
			logFail(message, expVal, actVal, points);
	}
	
	private void testConvertForDisplayHelper(String startingUnit, String desiredUnit, int points) {
		testConvertForDisplayHelper(startingUnit, numberUnits, desiredUnit, points);
	}
	
	// 6 points
	public void test4ConvertForDisplay() {
		if(passedIntermediateCalculations) {
			String startingUnit = "bm";
			String desiredUnit = "m";
			testConvertForDisplayHelper(startingUnit, desiredUnit, 1);

			startingUnit = "m";
			desiredUnit = "bm";
			testConvertForDisplayHelper(startingUnit, desiredUnit, 1);

			startingUnit = "cm";
			desiredUnit = "km";
			testConvertForDisplayHelper(startingUnit, desiredUnit, 1);

			startingUnit = "km";
			desiredUnit = "cm";
			testConvertForDisplayHelper(startingUnit, desiredUnit, 1);
			
			startingUnit = "m";
			desiredUnit = "m";
			testConvertForDisplayHelper(startingUnit, 0, desiredUnit, 2);
		} else
			feedbackLogger.log(Level.WARNING, "Display not tested due to failure of earlier tests.");
	}
	
	private boolean testDisplay(String expected, String actual) {
		String[] expectedTokens = expected.split(" ");
		String[] actualTokens = actual.split(" ");
		// <code>numberStartingUnits</code> <code>startingUnit</code> = returned_value <code>desiredUnit</code>.<br/>
		boolean passed = expectedTokens.length == actualTokens.length;
		if(passed) {
			for(int index = expectedTokens.length - 1; index >= 0 && passed; index--) {
				if(index == 3) {
					try {
						double expNum = Double.parseDouble(expectedTokens[index]);
						double actNum = Double.parseDouble(actualTokens[index]);
						passed = MathUtil.equalsRelative(expNum, actNum, RELATIVE_DELTA);
					} catch(NumberFormatException e) {
						feedbackLogger.log(Level.WARNING, e.getMessage());
						passed = false;
					}
				} else
					passed = expectedTokens[index].equals(actualTokens[index]);
			}
		} else
			feedbackLogger.log(Level.WARNING, "Display string has incorrect number of tokens.");
		return passed;
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
		studentClassName = studentPackage + ".MetricLengthConverter";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "java.lang.Object");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentConvertible = (Convertible)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 58;
		feedbackLogger.log(Level.INFO, "Pass: object creation.");
	}

}
