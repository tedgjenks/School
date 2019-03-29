/**
 * 
 */
package edu.jenks.math.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import edu.jenks.dist.math.AbstractSeries;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class SeriesTest extends Testable {
	
	private static final float RELATIVE_DELTA = 0.001f;
	
	private AbstractSeries studentSeries;
	private String studentClassName;

	/**
	 * 
	 */
	public SeriesTest() {}
	
	// 27 points
	public void testSumGeometric() {
		int points;
		float fTerm, lTerm, cRatio;
		double exp, act;
		points = 3;
		fTerm = 1;
		lTerm = 16;
		cRatio = 2;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 7;
		lTerm = 7;
		cRatio = 1000;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 3;
		lTerm = 46875;
		cRatio = 5;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 6;
		lTerm = 24576;
		cRatio = -8;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);

		fTerm = 6;
		lTerm = -1458;
		cRatio = -3;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 128;
		lTerm = 1;
		cRatio = 1f/2;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 16;
		lTerm = 1;
		cRatio = -1f/2;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = 8;
		lTerm = 1f/32;
		cRatio = 1f/4;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
		
		fTerm = (float)Math.pow(3, 5);
		lTerm = fTerm/(float)Math.pow(3, 7);
		cRatio = 1f/3;
		setInputForLogging(fTerm, lTerm, cRatio);
		exp = geometricSeriesFormula(fTerm, lTerm, cRatio);
		act = studentSeries.sumGeometric(fTerm, lTerm, cRatio);
		if(MathUtil.equalsRelative(exp, act, RELATIVE_DELTA))
			totalPoints += points;
		else
			logFail("testSumGeometric, first term: " + fTerm + ", last term: " + lTerm + ", common ratio: " + cRatio, exp, act, points);
	}
	
	private double geometricSeriesFormula(float fTerm, float lTerm, float cRatio) {
		int numTerms = (int)Math.round(Math.log(Math.abs(lTerm / fTerm)) / Math.log(Math.abs(cRatio))) + 1;
		return fTerm * (1 - Math.pow(cRatio, numTerms)) / (1 - cRatio);
	}
	
	private void setInputForLogging(double fTerm, double lTerm, double common) {
		inputToStudentCode = new StringBuilder(50).append("First term: ").append(fTerm).append("; last term: ").append(lTerm).append("; common: ").append(common).toString();
	}
	
	// 25 points
	public void testSumArithmetic() {
		int fTerm, lTerm, cDiff, points, exp, act;
		points = 5;
		fTerm = 1;
		lTerm = 9;
		cDiff = 2;
		setInputForLogging(fTerm, lTerm, cDiff);
		exp = arithmeticSeriesFormula(fTerm, lTerm, cDiff);
		act = studentSeries.sumArithmetic(fTerm, lTerm, cDiff);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumArithmetric, first term: " + fTerm + ", last term: " + lTerm + ", common difference: " + cDiff, exp, act, points);
		
		fTerm = 20;
		lTerm = 132;
		cDiff = 7;
		setInputForLogging(fTerm, lTerm, cDiff);
		exp = arithmeticSeriesFormula(fTerm, lTerm, cDiff);
		act = studentSeries.sumArithmetic(fTerm, lTerm, cDiff);
		if(exp == act)
			totalPoints += points;
		else
			logFail("testSumArithmetric, first term: " + fTerm + ", last term: " + lTerm + ", common difference: " + cDiff, exp, act, points);
		
		fTerm = 132;
		lTerm = 20;
		cDiff = -7;
		setInputForLogging(fTerm, lTerm, cDiff);
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
		
		fTerm = 80;
		lTerm = 80;
		cDiff = -700;
		setInputForLogging(fTerm, lTerm, cDiff);
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
		totalPoints += 48;
	}

}
