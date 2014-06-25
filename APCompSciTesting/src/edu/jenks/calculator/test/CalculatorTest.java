package edu.jenks.calculator.test;

import java.util.logging.Level;

import edu.jenks.calculator.dist.Calculates;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author Ted Jenks
 *
 * 15 points available
 * 3 parsing
 * 12 calculation
 */
public class CalculatorTest extends Testable {
	
	private static final CalculatorTest SINGLETON = new CalculatorTest();
	
	private final double RELATIVE_DELTA = 0.0001;
	private Calculates calculator;
	
	// 1 point (parsing)
	public void test40ImplicitOperations() {
		String testName = "Implicit Multiplication";
		if(testExpression(testName, "5(2)", 10) && testExpression(testName, "(2)5", 10)
				&& testExpression(testName, "2(5)2", 20) && testExpression(testName, "(5)(2)", 10)
				&& testExpression(testName, "(2)5(2)", 20) && testExpression(testName, "((2))((5))", 10)
				&& testExpression(testName, "5(1 + 1)", 10) && testExpression(testName, "2(5(5 - 2))3", 90)) {
				totalPoints++;
		}
	}
	
	// 1 point (parsing)
	public void testIllegalCharacters() {
		String[] illegalExpressions = {"a", "# + 1", "7 + @"};
		boolean pass = true;
		for(int index = illegalExpressions.length - 1; index >= 0 && pass; index--) {
			try {
				pass = false;
				calculator.performCalculation(illegalExpressions[index]);
				logger.log(Level.INFO, "Illegal character did not generate exception");
			} catch(IllegalArgumentException e) {
				pass = true;
				logger.log(Level.FINE, "correctly handled exception: " + e.getMessage());
			} catch(Exception e) {
				logger.log(Level.INFO, "illegal character produced wrong exception type: " + e.getMessage());
			}
		}
		if(pass) {
			totalPoints++;
			logger.log(Level.FINE, "Pass illegal character test");
		}
	}
	
	// 1 point (paren)
	public void testIllegalExpressions() {
		String[] illegalExpressions = {"1+", "+1", "(9*7", "7*9)", "8*(3+(4-(6 / 3.0) - 4)"};
		boolean pass = true;
		for(int index = illegalExpressions.length - 1; index >= 0 && pass; index--) {
			pass = false;
			String expression = illegalExpressions[index];
			try {
				calculator.performCalculation(expression);
				logger.log(Level.INFO, "Illegal expression did not generate exception");
			} catch(IllegalArgumentException e) {
				pass = true;
				logger.log(Level.FINE, "correctly handled exception: " + e.getMessage());
			} catch(Exception e) {
				logger.log(Level.INFO, "illegal expression produced wrong exception type: " + e.getMessage());
			}
		}
		if(pass) {
			totalPoints++;
			logger.log(Level.FINE, "Pass illegal expression test");
		}	
	}
	
	// 2 points
	public void test10Integer() {
		if(testNumber("integer > 0", "22")) {
			totalPoints++;
			if(testNumber("integer < 0", "-22"))
				totalPoints++;
		} else
			continueTesting = false;
	}
	
	// 1 point (parsing)
	public void test30Whitespace() {
		if(testNumber("test whitespace", "   2      2   "))
			totalPoints++;
		else
			continueTesting = false;
	}
	
	// 1 point
	public void test20Decimal() {
		if(testNumber("decimal > 1", "12.77") && testNumber("decimal < 1", ".443"))
			totalPoints++;
		else
			continueTesting = false;
	}
	
	private boolean testNumber(String testName, final String input) {
		double expected = Double.parseDouble(input.replaceAll("\\s", ""));
		return testExpression(testName, input, expected);
	}
	
	// 2 points
	public void test50MultiplicationAndDivision() {
		if(testExpression("single mult", "8 * 12", 8 * 12) && testExpression("single div", "8 / 12", 8.0 / 12))
			totalPoints++;
		String expression = "5/3*40*7/2/17";
		double expected = 5.0/3*40*7/2/17;
		if(testExpression("multiple operations", expression, expected))
			totalPoints++;
	}
	
	// 2 points
	public void test60AddAndSubtract() {
		if(testExpression("single add", "8 + 12", 8 + 12) && testExpression("single subtract", "8 - 12", 8 - 12))
			totalPoints++;
		String expression = "11.5 + 4.7 - 6.9 + 12.33 + 5.5 - 3 - 33";
		double expected = 11.5 + 4.7 - 6.9 + 12.33 + 5.5 - 3 - 33;
		if(testExpression("several add/subtract", expression, expected))
			totalPoints++;
	}
	
	// 1 point
	public void test70MixedOperations() {
		String expression = "5.0 + 3.1 / 1.4 - 77 * 3.1 + 6.0 / 4 * 11";
		double expected = 5.0 + 3.1 / 1.4 - 77 * 3.1 + 6.0 / 4 * 11;
		if(testExpression("mixed operations", expression, expected))
			totalPoints++;
	}
	
	// 3 points (paren)
	public void test80Grouping() {
		if(testExpression("grouped number", "(11)", 11)) {
			String expression = "(1 + 2) * (3 + 4)";
			double expected = (1 + 2) * (3 + 4);
			if(testExpression("changed order of operations", expression, expected)) {
				totalPoints++;
				if(testExpression("nested grouped number", "((11))", 11)) {
					expression = "((10.0) / (12))";
					expected = ((10.0) / (12));
					if(testExpression("nested group operation", expression, expected)) {
						totalPoints++;
						expression = "((4*1/5.0) - 1*2) * 4";
						expected = ((4*1/5.0) - 1*2) * 4;
						if(testExpression("complex grouping", expression, expected)) {
							expression = "((((4*(3.3 - 5.7)/5.0) - (7 - 3.3)*2) * 4) / (34.8 + 11.444)*(-3.0 + 7 / 4.8))";
							expected = ((((4*(3.3 - 5.7)/5.0) - (7 - 3.3)*2) * 4) / (34.8 + 11.444)*(-3.0 + 7 / 4.8));
							if(testExpression("complex grouping", expression, expected))
								totalPoints++;
						}
					}
				}
			}
		}
	}
	
	private boolean testExpression(String testName, String expression, double expected) {
		boolean passed = false;
		double actual = calculator.performCalculation(expression);
		if(MathUtil.equalsRelative(expected, actual, RELATIVE_DELTA)) {
			logger.log(Level.FINE, "Pass " + testName);
			passed = true;
		} else
			logger.log(Level.INFO, "Fail " + testName);
		return passed;
	}

	@Override
	public String getLogFilePath() {
		return LOG_FILE_PATH_START + "calculator/test/log.txt";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SINGLETON.execute();
	}

	@Override
	public Testable getSingleton() {
		return SINGLETON;
	}
	
	@Override
	public void setUp() {
		super.setUp();
		calculator = createInstance();
		verifySuperclass(Object.class.getName(), calculator);
	}
	
	private Calculates createInstance() {
		Calculates calculator = null;
		try {
			calculator = (Calculates)ReflectionUtil.newInstance(studentPackage + ".BasicCalculator");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			logger.log(Level.WARNING, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
		return calculator;
	}
}
