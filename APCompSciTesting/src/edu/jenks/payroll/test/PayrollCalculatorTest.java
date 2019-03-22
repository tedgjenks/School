package edu.jenks.payroll.test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.jenks.dist.payroll.AbstractPayrollCalculator;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

public class PayrollCalculatorTest extends Testable {

	private String studentClassName;
	private AbstractPayrollCalculator studentPC, solutionPC = new edu.payroll.jenks.ted.PayrollCalculator();
	private final double REL_DELTA = Math.pow(10, -4);
	private final Random RANDOM = new Random(System.currentTimeMillis());
	
	private void testAndLog(String passMessage, String failMessage, int points, double expected, double actual, boolean continueOnFail) {
		if(MathUtil.equalsRelative(expected, actual, REL_DELTA)) {
			totalPoints += points;
			logPass(passMessage);
		} else {
			continueTesting = continueOnFail;
			logFail(failMessage, expected, actual, points);
		}
	}
	
	private void testAndLog(String message, int points, String expected, String actual) {
		if(expected.equals(actual)) {
			totalPoints += points;
			logPass(message);
		} else
			logFail(message, expected, actual, points);
	}
	
	//4*5
	public void test03FormatPay() {
		int points = 4;
		String message = "formatPay";
		double pay = 1;
		String expected = "$1.00";
		String actual = studentPC.formatPay(pay);
		testAndLog(message, points, expected, actual);
		
		pay = 1.5;
		expected = "$1.50";
		actual = studentPC.formatPay(pay);
		testAndLog(message, points, expected, actual);
		
		pay = 1.55;
		expected = "$1.55";
		actual = studentPC.formatPay(pay);
		testAndLog(message, points, expected, actual);
		
		pay = 1.556;
		expected = "$1.56";
		actual = studentPC.formatPay(pay);
		testAndLog(message, points, expected, actual);
		
		pay = RANDOM.nextDouble() * 500;
		expected = solutionPC.formatPay(pay);
		actual = studentPC.formatPay(pay);
		testAndLog(message, points, expected, actual);
	}
	
	//5*4
	public void test02CalculatePay() {
		String passMessage = "calculatePay";
		String failMessage = passMessage + ", testing other methods aborted!";
		int points = 5;
		double hoursWorked = 10;
		double expected = 105;
		double actual = studentPC.calculatePay(hoursWorked);
		testAndLog(passMessage, failMessage, points, expected, actual, false);
		
		hoursWorked = 50;
		expected = 577.5;
		actual = studentPC.calculatePay(hoursWorked);
		testAndLog(passMessage, failMessage, points, expected, actual, false);
		
		hoursWorked = RANDOM.nextDouble() * 40;
		expected = solutionPC.calculatePay(hoursWorked);
		actual = studentPC.calculatePay(hoursWorked);
		testAndLog(passMessage, failMessage, points, expected, actual, false);
		
		hoursWorked = 41 + RANDOM.nextDouble() * 100;
		expected = solutionPC.calculatePay(hoursWorked);
		actual = studentPC.calculatePay(hoursWorked);
		testAndLog(passMessage, failMessage, points, expected, actual, false);
	}
	
	//5*2
	public void test01ConvertToHours() {
		String passMessage = "convertToHours";
		String failMessage = passMessage + ", testing other methods aborted!";
		int points = 5;
		int hours = 1, minutes = 60, seconds = 3600;
		double expected = 3;
		double actual = studentPC.convertToHours(hours, minutes, seconds);
		testAndLog(passMessage, failMessage, points, expected, actual, false);
		
		int bound = 1000;
		hours = RANDOM.nextInt(bound);
		minutes = RANDOM.nextInt(bound);
		seconds = RANDOM.nextInt(bound);
		expected = solutionPC.convertToHours(hours, minutes, seconds);
		actual = studentPC.convertToHours(hours, minutes, seconds);
		testAndLog(passMessage, failMessage, points, expected, actual, false);
	}
	
	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".PayrollCalculator";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "edu.jenks.dist.payroll.AbstractPayrollCalculator");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentPC = (AbstractPayrollCalculator)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 50;
		logPass("object creation");
	}

}
