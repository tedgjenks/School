/**
 * 
 */
package edu.jenks.unitconversion.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;

import edu.jenks.dist.unitconversion.Convertible;
import edu.jenks.test.Testable;
import edu.jenks.util.*;

/**
 * @author Ted
 *
 */
public class ConvertibleTest extends Testable {
	
	private static final String NANOMETER = "nm";
	private static final String MICROMETER = "um";
	private static final String MILLIMETER = "mm";
	private static final String CENTIMETER = "cm";
	private static final String DECIMETER = "dm";
	private static final String METER = "m";
	private static final String DECAMETER = "dam";
	private static final String HECTOMETER = "hm";
	private static final String KILOMETER = "km";
	private static final String MEGAMETER = "Mm";
	private static final String GIGAMETER = "Gm";
	private static final String[] UNIT_ARRAY;
	
	static {
		UNIT_ARRAY = new String[] {NANOMETER, MICROMETER, MILLIMETER, CENTIMETER, DECIMETER, METER, DECAMETER, HECTOMETER, KILOMETER, MEGAMETER, GIGAMETER};;
	}
	
	private String studentClassName;
	private Convertible studentConvertible;
	private Convertible solutionConvertible = new edu.unitconversion.jenks.ted.MetricLengthConverter();
	private final double RELATIVE_DELTA = Math.pow(10, -3);
	private final Random random = new Random(System.currentTimeMillis());
	private double numberUnits = random.nextDouble() * 1000 + .01;
	
	private void testConvertFromMetersHelper(String unit, int points) {
		double actVal = studentConvertible.convertFromMeters(numberUnits, unit);
		double expVal = solutionConvertible.convertFromMeters(numberUnits, unit);
		String message = "convertFromMeters " + unit;
		if(MathUtil.equalsRelative(actVal, expVal, RELATIVE_DELTA)) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message, expVal, actVal, points);
			feedbackLogger.log(Level.WARNING, "Fail: " + unit + " from meters.");
			continueTesting = false;
		}
	}
	
	//10 points
	public void test02ConvertFromMeters() {
		String unit = METER;
		testConvertFromMetersHelper(unit, 2);
		
		unit = DECIMETER;
		testConvertFromMetersHelper(unit, 4);
		
		unit = DECAMETER;
		testConvertFromMetersHelper(unit, 4);
	}
	
	private void testConvertToMetersHelper(String unit, int points) {
		double actVal = studentConvertible.convertToMeters(numberUnits, unit);
		double expVal = solutionConvertible.convertToMeters(numberUnits, unit);
		String message = "convertToMeters " + unit;
		if(MathUtil.equalsRelative(actVal, expVal, RELATIVE_DELTA)) {
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message, expVal, actVal, points);
			continueTesting = false;
		}
	}
	
	//10 points
	public void test01ConvertToMeters() {
		String unit = METER;
		testConvertToMetersHelper(unit, 2);
		
		unit = CENTIMETER;
		testConvertToMetersHelper(unit, 4);
		
		unit = KILOMETER;
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
			logPass(message);
			totalPoints += points;
		} else {
			logFail(message, expVal, actVal, points);
			continueTesting = false;
		}
	}
	
	//16 points
	public void test03Convert() {
		String startingUnit = NANOMETER, desiredUnit = GIGAMETER;
		testConvertHelper(startingUnit, desiredUnit, 3);
		
		startingUnit = GIGAMETER;
		desiredUnit = NANOMETER;
		testConvertHelper(startingUnit, desiredUnit, 3);
		
		startingUnit = MEGAMETER;
		desiredUnit = MICROMETER;
		testConvertHelper(startingUnit, desiredUnit, 3);
		
		startingUnit = MILLIMETER;
		desiredUnit = KILOMETER;
		testConvertHelper(startingUnit, desiredUnit, 3);
		
		startingUnit = METER;
		desiredUnit = METER;
		testConvertHelper(startingUnit, 0, desiredUnit, 4);
	}
	
	private boolean testConvertForDisplayHelper(String startingUnit, double numStartingUnit, String desiredUnit, int points) {
		boolean pass = false;
		String expVal = solutionConvertible.convertForDisplay(numStartingUnit, startingUnit, desiredUnit);
		String actVal = studentConvertible.convertForDisplay(numStartingUnit, startingUnit, desiredUnit);
		String message = "convertForDisplay";
		if(testDisplay(expVal, actVal)) {
			if(points > 0) {
				totalPoints += points;
				logPass(message);
			}
			pass = true;
		} else
			logFail(message, expVal, actVal, points);
		return pass;
	}
	
	private boolean testConvertForDisplayHelper(String startingUnit, String desiredUnit, int points) {
		return testConvertForDisplayHelper(startingUnit, numberUnits, desiredUnit, points);
	}
	
	// 11 points
	public void test04ConvertForDisplay() {
		String startingUnit = "bm";
		String desiredUnit = METER;
		testConvertForDisplayHelper(startingUnit, desiredUnit, 1);

		startingUnit = METER;
		desiredUnit = "bm";
		testConvertForDisplayHelper(startingUnit, desiredUnit, 1);

		startingUnit = CENTIMETER;
		desiredUnit = KILOMETER;
		testConvertForDisplayHelper(startingUnit, desiredUnit, 1);

		startingUnit = KILOMETER;
		desiredUnit = CENTIMETER;
		testConvertForDisplayHelper(startingUnit, desiredUnit, 1);
		
		startingUnit = METER;
		desiredUnit = METER;
		testConvertForDisplayHelper(startingUnit, 0, desiredUnit, 2);
		
		if(testAllUnits(1) && testAllUnits(-1) && testAllUnits(numberUnits) && testAllUnits(-1 * numberUnits)) {
			logPass("Convert for display - all units");
			totalPoints += 5;
		}
	}
	
	private boolean testAllUnits(double startingValue) {
		boolean passAllUnits = true;
		randomUnit:
		for(int oIndex = UNIT_ARRAY.length - 1; oIndex >= 0; oIndex--) {
			for(int iIndex = UNIT_ARRAY.length - 1; iIndex >= 0; iIndex--) {
				if(!testConvertForDisplayHelper(UNIT_ARRAY[oIndex], startingValue, UNIT_ARRAY[iIndex], 0)) {
					passAllUnits = false;
					break randomUnit;
				}
			}
		}
		return passAllUnits;
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
		totalPoints += 53;
		feedbackLogger.log(Level.INFO, "Pass: object creation.");
	}

}
