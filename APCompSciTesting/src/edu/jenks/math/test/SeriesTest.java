/**
 * 
 */
package edu.jenks.math.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.math.AbstractSeries;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class SeriesTest extends Testable {
	private AbstractSeries studentSeries;
	private String studentClassName;

	/**
	 * 
	 */
	public SeriesTest() {}
	
	// 20 points
	public void testSumGeometric() {
		int fTerm, lTerm, cRatio, points, exp, act;
		points = 5;
		fTerm = 1;
		lTerm = 16;
		cRatio = 2;
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 7;
		lTerm = 7;
		cRatio = 1000;
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 3;
		lTerm = 46875;
		cRatio = 5;
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 6;
		lTerm = 24576;
		cRatio = -8;
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
	}
	
	private int geometricSeriesFormula(int fTerm, int lTerm, int cRatio) {
		int numTerms = (int)(Math.log(Math.abs(lTerm / fTerm)) / Math.log(Math.abs(cRatio))) + 1;
		return fTerm * (1 - (int)Math.pow(cRatio, numTerms)) / (1 - cRatio);
	}
	
	// 20 points
	public void testSumArithmetic() {
		int fTerm, lTerm, cDiff, points, exp, act;
		points = 5;
		fTerm = 1;
		lTerm = 9;
		cDiff = 2;
		exp = arithmeticSeriesFormula(fTerm, lTerm, cDiff);
		act = studentSeries.sumArithmetic(fTerm, lTerm, cDiff);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumArithmetric, first term: " + fTerm + ", last term: " + lTerm + ", common difference: " + cDiff, exp, act, points);
		
		fTerm = 20;
		lTerm = 132;
		cDiff = 7;
		exp = arithmeticSeriesFormula(fTerm, lTerm, cDiff);
		act = studentSeries.sumArithmetic(fTerm, lTerm, cDiff);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumArithmetric, first term: " + fTerm + ", last term: " + lTerm + ", common difference: " + cDiff, exp, act, points);
		
		fTerm = 132;
		lTerm = 20;
		cDiff = -7;
		exp = arithmeticSeriesFormula(fTerm, lTerm, cDiff);
		act = studentSeries.sumArithmetic(fTerm, lTerm, cDiff);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumArithmetric, first term: " + fTerm + ", last term: " + lTerm + ", common difference: " + cDiff, exp, act, points);
		
		fTerm = 80;
		lTerm = 80;
		cDiff = -700;
		exp = arithmeticSeriesFormula(fTerm, lTerm, cDiff);
		act = studentSeries.sumArithmetic(fTerm, lTerm, cDiff);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumArithmetric, first term: " + fTerm + ", last term: " + lTerm + ", common difference: " + cDiff, exp, act, points);
	}
	
	private int arithmeticSeriesFormula(int fTerm, int lTerm, int cDiff) {
		int numTerms = (lTerm - fTerm)/cDiff + 1;
		return (int)((numTerms / 2.0)*(fTerm + lTerm));
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
		studentClassName = studentPackage + ".Series";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.math.AbstractSeries");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentSeries = (AbstractSeries)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 60;
	}

}
