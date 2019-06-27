/**
 * 
 */
package edu.jenks.math.test;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import edu.jenks.dist.math.Calculates;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

/**
 * @author tedgj
 *
 */
public class BasicCalculatorTest extends Testable {
	
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	private static class TestData {
		final String EXPECTED;
		final String INPUT;
		TestData(String exp, String input) {
			EXPECTED = exp;
			INPUT = input;
		}
		
		TestData(Calculates solutionInstance, String input) {
			EXPECTED = solutionInstance.performCalculation(input);
			INPUT = input;
		}
		
		TestData(String inputExpected) {
			EXPECTED = INPUT = inputExpected;
		}
	}
	
	private String studentClassName;
	private Calculates studentInstance, solutionInstance = new edu.math.jenks.ted.BasicCalculator();

	// 4 points - 52
	public void test70Formatting() {
		boolean allPass = true;
		String message = "Formatting";
		int pointsAwarded = 0, testsPassed = 0;
		class FormattingData {
			String expErrorCode;
			String[] testInput;
			FormattingData(String expEc, String... ti) {
				expErrorCode = expEc;
				testInput = ti;
			}
		}
		FormattingData[] testData = {
				new FormattingData(Calculates.ERROR_CODE_UNMATCHED_PARENTHESIS, "(8 * 9) + (5 - (6 * (3 - 1) + 8) - 5) + 3)", "(5", "5)", "((5)", "(5))"),
				new FormattingData(Calculates.ERROR_CODE_OPERATOR_WITHOUT_OPERAND, "*(5 + 6)", "(5 + 6)-", "(*5 + 6)", "(5 + 6/)", "8**7", "*7", "7/", "*"),
				new FormattingData(Calculates.ERROR_CODE_DIVISION_BY_ZERO, generateRandomOperands("(? * ?) / (3 - 3)", -10, 10, true, false), "0 ^ 0", "101 / 0"),
				new FormattingData(Calculates.ERROR_CODE_NOT_REAL, "(10 - 15) ^ 1.25", "(-4) ^ .5"),
				new FormattingData(Calculates.ERROR_CODE_NUMBER_FORMAT, generateRandomOperands("? + ? ?", 5, 100, true, false), "126.3.2.450", "6..0"),
				new FormattingData(Calculates.ERROR_CODE_UNSUPPORTED_CHARACTER, generateRandomOperands("? % ?", -100, 100, true, false), "56!"),
				new FormattingData(Calculates.ERROR_CODE_NO_INPUT, " ")
		};
		final int points = 1, maxPoints = 4, passedTestsPerPoint = (int)Math.ceil(testData.length / (double)maxPoints);
		try {
			for(int index = testData.length - 1; index >= 0; index--) {
				boolean passFormatErrorType = true;
				String detailMessage = message + " " + testData[index].expErrorCode;
				for(int inputIndex = testData[index].testInput.length - 1; inputIndex >= 0; inputIndex--) {
					inputToStudentCode = testData[index].testInput[inputIndex];
					if(!compareActualToExpected(message, inputToStudentCode, testData[index].expErrorCode, true, points))
						passFormatErrorType = false;
				}
				if(!passFormatErrorType)
					allPass = false;
				else {
					logPass(detailMessage);
					if(++testsPassed % passedTestsPerPoint == 0) {
						totalPoints += points;
						pointsAwarded += points;
					}
				}
			}
		} catch(Exception e) {
			logFail("Unhandled exception (" + e.getMessage() + ") for input " + inputToStudentCode);
		}
		
		if(allPass) {
			logPass(message);
			totalPoints += (maxPoints - pointsAwarded);
		}
	}
	
	private void runTest(final String message, final int points, TestData... testData) {
		boolean allPass = true;
		for(int testIndex = testData.length - 1; testIndex >= 0; testIndex--) {
			inputToStudentCode = testData[testIndex].INPUT;
			if(!compareActualToExpected(message, inputToStudentCode, testData[testIndex].EXPECTED, points))
				allPass = false;
		}
		if(allPass) {
			logPass(message);
			totalPoints += points;
		} else
			continueTesting = false;
	}
		
	// 2 points - 48
	public void test65ImplicitMultiplication() {
		String message = "ImplicitMultiplication";
		int points = 2;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("?(?(? / (? + ?)?) - ?)(? + ?) - ?(? * ?)?", -5, 5, true, false)),
			new TestData("52", "(4 + 9)(5 - 1)"),
			new TestData("-9.3", "3(2.4 + 1.5) - 2(4.8 - 2.7)5")
		};
		runTest(message, points, testData);
	}
	
	// 2 points - 46
	public void test60GroupNegation() {
		String message = "GroupNegation";
		int points = 2;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("? ^ -(? / ?)", 1, 3, true, false)),
			new TestData(solutionInstance, generateRandomOperands("? / -((? + ?) * ?)", -3, 3, true, false)),
			new TestData("16", ".5 ^ -(1 + 30 / 10)"),
			new TestData("10", "-(30 * (20 + 50)) / (-(2 + 5)*30)"),
			new TestData("10", "-(20 + 50) / -(2 + 5)"),
			new TestData("-10", "(20 + 50) / -(2 + 5)"),
			new TestData("1.8", "(2.4 + 1.5) - (4.8 - 2.7)")
		};
		runTest(message, points, testData);
	}
	
	// 6 points - 44
	public void test55GroupMultiple() {
		String message = "GroupMultiple";
		int points = 6;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("(? - ?) ^ 2 / ((? + ?) / (? * (? - ?))) + (? / (? + ?))", -3, 3, true, false)),
			new TestData("8.19", "(2.4 + 1.5) * (4.8 - 2.7)"),
			new TestData("-6", "4 * (6 - 1) / 10 * (4 + 3) - 20"),
		};
		runTest(message, points, testData);
	}
	
	// 8 points - 38
	public void test50GroupNested() {
		String message = "GroupNested";
		int points = 8;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("? - ? * (? / (? * ? + (? - ? * ?)) / ?) + ?", -3, 3, true, false)),
			new TestData("-7.86470588235", "-6.1 + (3 / (4.1 + -7.5)) * 2"),
			new TestData("10", "((10))")
		};
		runTest(message, points, testData);
	}
	
	// 4 points - 30
	public void test45GroupSingle() {
		String message = "GroupSingle";
		int points = 4;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("? + (? + ? ^ 3) / ? - ? * ?", -5, 5, true, false)),
			new TestData(solutionInstance, generateRandomOperands("(? - ?) / ?", -10, 10, true, false)),
			new TestData("125", "5 ^ (63 / 21)"),
			new TestData("25", "(-5) ^ 2"),
			new TestData("45", "5 * (10 - 1)"),
			new TestData("-3.66", "(-3.66)")
		};
		runTest(message, points, testData);
	}
	
	// 2 points - 26
	public void test40FloatingPointArithmetic() {
		String message = "FloatingPointArithmetic";
		int points = 2;
		TestData[] testData = {
			new TestData("-13.845313581259", "4.1 - -4 ^ 3.9 / -6.2 + 10 * 1.8"),
			new TestData("64", ".25 ^ -3"),
			new TestData("3", "81 ^ 0.25"),
			new TestData("23.5", "-5 * -4.7"),
			new TestData("-10.0", "5 / -0.5"),
			new TestData("-10.0", "-5 / 0.5"),
			new TestData(generateRandomOperands("?", -500, 500, true, false))
		};
		runTest(message, points, testData);
	}
	
	// 2 points - 24
	public void test35NegateOperator() {
		String message = "NegateOperator";
		int points = 2;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("? - +?", 2, 100, false, false)),
			new TestData(solutionInstance, generateRandomOperands("? - ?", -100, -2, false, false)),
			new TestData("-5", "-1 - +4"),
			new TestData("3", "-1 - -4")
		};
		runTest(message, points, testData);
	}
	
	// 4 points - 22
	public void test30NegativeOperands() {
		String message = "NegativeOperands";
		int points = 4;
		TestData[] testData = {
			new TestData("-9.0", "-3 ^ 2"),
			new TestData("-243.0", "-3 ^ 5"),
			new TestData("-3", "-12 / 4"),
			new TestData("3", "-12 / -4"),
			new TestData("12", "-3 * -4"),
			new TestData("-5", "-1 - 4"),
			new TestData("-5", "-1 + -4"),
			new TestData("3", "-1 + 4"),
			new TestData("-15")
		};
		runTest(message, points, testData);
	}
	
	// 10 points - 18
	public void test25OrderOfOperations() {
		String message = "OrderOfOperations";
		int points = 10;
		StringBuilder input = new StringBuilder(50);
		input.append(generateRandomOperands("? + ? ^ ? * ?", 2, 5, false, false));
		int divisor = RANDOM.nextInt(5) + 2;
		int dividend = divisor * (RANDOM.nextInt(5) + 2);
		input.append(" * ").append(dividend).append(" / ").append(divisor);
		TestData[] testData = {
			new TestData(solutionInstance, input.toString()),
			new TestData("13", "7 + 5 * 2 - 30 / 6 + 100 / 5 ^ 2 - 3")
		};
		runTest(message, points, testData);
	}
	
	// 2 points - 8
	public void test20Exponent() {
		String message = "Exponent";
		int points = 2;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("? ^ ?", 2, 10, false, false)),
			new TestData("81", "3 ^ 4")
		};
		runTest(message, points, testData);
	}
	
	// 2 points - 6
	public void test15MultiplicationAndDivision() {
		String message = "MultiplicationAndDivision";
		int points = 2;
		int divisor = RANDOM.nextInt(1000) + 1;
		int dividend = divisor * RANDOM.nextInt(20) + 1;
		TestData[] testData = {
			new TestData(solutionInstance, divisor + " / " + dividend),
			new TestData(solutionInstance, generateRandomOperands("? * ?", 5, 100, false, false)),
			new TestData("11", "55 / 5"),
			new TestData("55", "5 * 11")
		};
		runTest(message, points, testData);
	}
	
	// 2 points - 4
	public void test10AdditionAndSubtraction() {
		String message = "AdditionAndSubtraction";
		int points = 2;
		TestData[] testData = {
			new TestData(solutionInstance, generateRandomOperands("? - ?", 1, 100, false, true)),
			new TestData(solutionInstance, generateRandomOperands("? + ?", 1, 100, false, true)),
			new TestData("5", "8 - 3"),
			new TestData("11", "3 + 8")
		};
		runTest(message, points, testData);
	}

	// 2 points
	public void test05SingleNumber() {
		String message = "SingleNumber";
		int points = 2;
		TestData[] testData = {
			new TestData("12"),
			new TestData(String.valueOf(RANDOM.nextInt(1000) + 1))
		};
		runTest(message, points, testData);
	}
	
	/**
	 * @param format question marks are place-holders for values
	 * @param min minimum random value
	 * @param max maximum random value
	 * @return question marks replaced with values between min and max
	 */
	private String generateRandomOperands(String format, int min, int max, boolean useDecimal, boolean allowZero) {
		StringBuilder sb = new StringBuilder(50);
		int nextIndex = 0;
		int qIndex = format.indexOf('?');
		while(qIndex >= 0) {
			String keep = format.substring(nextIndex, qIndex);
			sb.append(keep);
			String repl = String.valueOf(randomNumber(min, max, useDecimal, allowZero));
			sb.append(repl);
			nextIndex = qIndex + 1;
			qIndex = format.indexOf('?', nextIndex);
		}
		sb.append(format.substring(nextIndex));
		return sb.toString();
	}
	
	private double randomNumber(int min, int max, boolean useDecimal, boolean allowZero) {
		int range = max - min + 1;
		double retValue;
		do {
			retValue = (useDecimal ? RANDOM.nextDouble() * range : RANDOM.nextInt(range)) + min;
		} while((retValue == 0 && !allowZero) || String.valueOf(retValue).indexOf('E') >= 0);
		return retValue;
	}
	
	private boolean compareActualToExpected(String message, final String input, final String expected, boolean exact, final int points) {
		String actual = studentInstance.performCalculation(input);
		boolean pass = exact ? expected.equals(actual) : MathUtil.equalsRelative(Double.parseDouble(expected), Double.parseDouble(actual), Calculates.RELATIVE_DELTA);
		if(!pass)
			logFail(message + " with input " + input, expected, actual, points);
		return pass;
	}
	
	private boolean compareActualToExpected(String message, final String input, final String expected, final int points) {
		return compareActualToExpected(message, input, expected, false, points);
	}

	@Override
	public int getPointsAvailable() {
		return 100;
	}

	@Override
	public Map<String, String> buildStudentClassNameToSuperclassName() {
		studentClassName = studentPackage + ".BasicCalculator";
		Map<String, String> map = new HashMap<String, String>();
		map.put(studentClassName, "java.lang.Object");
		return map;
	}

	@Override
	public void setUp() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		studentInstance = (Calculates)ReflectionUtil.newInstance(studentClassName);
		totalPoints += 48;
	}

}
