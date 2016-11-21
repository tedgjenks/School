/**
 * 
 */
package edu.jenks.midterm.ics.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.midterm.ics.AbstractMidterm;
import edu.jenks.test.Testable;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 */
public class MidtermTest extends Testable {
	public static final Random RANDOM = new Random(System.currentTimeMillis());
	
	private AbstractMidterm studentMidterm, solutionMidterm = new edu.midterm.ics.jenks.ted.Midterm();

	/**
	 * 
	 */
	public MidtermTest() {}
	
	// 5 points
	public void testAssignGrade() {
		boolean passAll = true;
		final String method = "testAssignGrade";
		double score;
		String exp, act;
		int points = 1;
		
		score = 90;
		exp = "A";
		act = studentMidterm.assignGrade(score);
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " score = " + score, exp, act, points);
		}
		
		score = 83;
		exp = "B";
		act = studentMidterm.assignGrade(score);
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " score = " + score, exp, act, points);
		}
		
		score = 79;
		exp = "C";
		act = studentMidterm.assignGrade(score);
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " score = " + score, exp, act, points);
		}
		
		score = 60;
		exp = "D";
		act = studentMidterm.assignGrade(score);
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " score = " + score, exp, act, points);
		}
		
		score = 59;
		exp = "F";
		act = studentMidterm.assignGrade(score);
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " score = " + score, exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 5 points
	public void testIsIsosceles() {
		int s1, s2, s3, points = 1;
		final String method = "testIsIsosceles";
		boolean passAll = true, exp, act;
		
		s1 = 1;
		s2 = 1;
		s3 = 1;
		exp = true;
		act = studentMidterm.isIsosceles(s1, s2, s3);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3, String.valueOf(exp), String.valueOf(act), points);
		}
		
		s1 = 2;
		s2 = 1;
		s3 = 1;
		exp = true;
		act = studentMidterm.isIsosceles(s1, s2, s3);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3, String.valueOf(exp), String.valueOf(act), points);
		}
		
		s1 = 1;
		s2 = 2;
		s3 = 1;
		exp = true;
		act = studentMidterm.isIsosceles(s1, s2, s3);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3, String.valueOf(exp), String.valueOf(act), points);
		}
		
		if(passAll)
			logPass(method);
		
		s1 = 1;
		s2 = 1;
		s3 = 2;
		exp = true;
		act = studentMidterm.isIsosceles(s1, s2, s3);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3, String.valueOf(exp), String.valueOf(act), points);
		}
		
		if(passAll)
			logPass(method);
		
		s1 = 1;
		s2 = 2;
		s3 = 3;
		exp = false;
		act = studentMidterm.isIsosceles(s1, s2, s3);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "s1 = " + s1 + ", s2 = " + s2 + ", s3 = " + s3, String.valueOf(exp), String.valueOf(act), points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 12 points
	public void testFloatEquals() {
		int points = 2;
		double arg1, arg2, delta;
		boolean exp, act, passAll = true;
		final String method = "testFloatEquals";
		arg1 = 10.7;
		arg2 = 9.7;
		delta = 0.5;
		exp = false;
		act = studentMidterm.floatEquals(arg1, arg2, delta);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "n1 = " + arg1 + ", n2 = " + arg2 + ", delta = " + delta, String.valueOf(exp), String.valueOf(act), points);
		}
		
		arg1 = 9.7;
		arg2 = 10.7;
		delta = 0.5;
		exp = false;
		act = studentMidterm.floatEquals(arg1, arg2, delta);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "n1 = " + arg1 + ", n2 = " + arg2 + ", delta = " + delta, String.valueOf(exp), String.valueOf(act), points);
		}
		
		arg1 = -10.7;
		arg2 = -9.7;
		delta = 0.5;
		exp = false;
		act = studentMidterm.floatEquals(arg1, arg2, delta);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "n1 = " + arg1 + ", n2 = " + arg2 + ", delta = " + delta, String.valueOf(exp), String.valueOf(act), points);
		}
		
		arg1 = 10.7;
		arg2 = 10.3;
		delta = 0.5;
		exp = true;
		act = studentMidterm.floatEquals(arg1, arg2, delta);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "n1 = " + arg1 + ", n2 = " + arg2 + ", delta = " + delta, String.valueOf(exp), String.valueOf(act), points);
		}
		
		arg1 = 10.3;
		arg2 = 10.7;
		delta = 0.5;
		exp = true;
		act = studentMidterm.floatEquals(arg1, arg2, delta);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "n1 = " + arg1 + ", n2 = " + arg2 + ", delta = " + delta, String.valueOf(exp), String.valueOf(act), points);
		}
		
		arg1 = RANDOM.nextDouble() * 2;
		arg2 = RANDOM.nextDouble() * 2;
		delta = RANDOM.nextDouble();
		exp = solutionMidterm.floatEquals(arg1, arg2, delta);
		act = studentMidterm.floatEquals(arg1, arg2, delta);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "n1 = " + arg1 + ", n2 = " + arg2 + ", delta = " + delta, String.valueOf(exp), String.valueOf(act), points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 4 points
	public void testEvenlyDivisible() {
		int points = 1;
		boolean passAll = true;
		final String method = "testEvenlyDivisible";
		boolean exp, act;
		int arg1, arg2;
		arg1 = 2;
		arg2 = 1;
		exp = arg1 % arg2 == 0;
		act = studentMidterm.evenlyDivisible(arg1, arg2);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " dividend " + arg1 + ", divisor " + arg2);
		}
		
		arg1 = 1;
		arg2 = 2;
		exp = arg1 % arg2 == 0;
		act = studentMidterm.evenlyDivisible(arg1, arg2);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " dividend " + arg1 + ", divisor " + arg2);
		}
		
		arg1 = 2;
		arg2 = 2;
		exp = arg1 % arg2 == 0;
		act = studentMidterm.evenlyDivisible(arg1, arg2);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " dividend " + arg1 + ", divisor " + arg2);
		}
		
		for(int i = 100; i >= 0; i--) {
			arg1 = RANDOM.nextInt(1000);
			arg2 = RANDOM.nextInt(5) + 1;
			exp = arg1 % arg2 == 0;
			act = studentMidterm.evenlyDivisible(arg1, arg2);
			if(exp != act) {
				passAll = false;
				logFail(method + " dividend " + arg1 + ", divisor " + arg2);
			}
		}
		
		if(passAll) {
			totalPoints += points;
			logPass(method);
		}
	}
	
	// 5 points
	public void testCountSubString() {
		boolean passAll = true;
		final String method = "testCountSubString";
		String s, sub;
		int exp, act, points;
		points = 1;
		s = "a";
		sub = "a";
		exp = 1;
		act = studentMidterm.countSubString(s, sub);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "string: " + s + "; substring: " + sub, exp, act, points);
		}
			
		s = "a";
		sub = "b";
		exp = 0;
		act = studentMidterm.countSubString(s, sub);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "string: " + s + "; substring: " + sub, exp, act, points);
		}
		
		s = "a man a plan tucan";
		sub = "a";
		exp = 5;
		act = studentMidterm.countSubString(s, sub);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "string: " + s + "; substring: " + sub, exp, act, points);
		}
		
		s = "puns are not punny pun";
		sub = "pun";
		exp = 3;
		act = studentMidterm.countSubString(s, sub);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "string: " + s + "; substring: " + sub, exp, act, points);
		}
		
		s = "acolyte";
		sub = "acowlighsmethaneonfire";
		exp = 0;
		act = studentMidterm.countSubString(s, sub);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + "string: " + s + "; substring: " + sub, exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 4 points
	public void testMaxOfTwo() {
		boolean passAll = true;
		final String method = "testMaxOfTwo";
		int exp, act, points, arg1, arg2;
		points = 1;
		arg1 = 5;
		exp = arg2 = 10;
		act = studentMidterm.maxOfTwo(arg1, arg2);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method, exp, act, points);
		}
		
		arg1 = 5;
		exp = arg2 = 10;
		act = studentMidterm.maxOfTwo(arg2, arg1);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method, exp, act, points);
		}
		
		exp = arg1 = -5;
		arg2 = -10;
		act = studentMidterm.maxOfTwo(arg1, arg2);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method, exp, act, points);
		}
		
		exp = arg1 = 5;
		arg2 = -10;
		act = studentMidterm.maxOfTwo(arg1, arg2);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method, exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 5 points
	public void testSum() {
		boolean passAll = true;
		final String method = "sum";
		int points = 2;
		int lastNumber = 1;
		int exp = 1;
		int act = studentMidterm.sum(lastNumber);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " - last number " + lastNumber, exp, act, points);
		}
		
		points = 3;
		lastNumber = RANDOM.nextInt(100);
		exp = lastNumber*(lastNumber + 1)/2;
		act = studentMidterm.sum(lastNumber);
		if(exp == act)
			totalPoints += points;
		else {
			passAll = false;
			logFail(method + " - last number " + lastNumber, exp, act, points);
		}
		
		if(passAll)
			logPass(method);
	}
	
	// 10 points
	public void testTicketMessage() {
		boolean passAll = true;
		final String method = "ticketMessage";
		String oMethod = method + "(name, number)";
		int points = 3;
		int number = RANDOM.nextInt(1000);
		String name = "Jekyll";
		String exp = name + " is ticket number " + number + ".";
		String act = studentMidterm.ticketMessage(name, number);
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(oMethod, exp, act, points);
		}
		
		oMethod = method + "(name)";
		points = 3;
		exp = solutionMidterm.ticketMessage(name);
		act = studentMidterm.ticketMessage(name);
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(oMethod, exp, act, points);
		}
		
		points = 4;
		for(int i = 37; i >= 0; i--) {
			exp = solutionMidterm.ticketMessage(name);
			act = studentMidterm.ticketMessage(name);
		}
		if(exp.equals(act))
			totalPoints += points;
		else {
			passAll = false;
			logFail(oMethod, exp, act, points);
		}
		
		if(passAll)
			logPass(method);
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
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentPackage + ".Midterm", "edu.jenks.dist.midterm.ics.AbstractMidterm");
		return map;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.test.Testable#setUp()
	 */
	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentMidterm = (AbstractMidterm)ReflectionUtil.newInstance(studentPackage + ".Midterm");
		totalPoints += 50;
	}

}
