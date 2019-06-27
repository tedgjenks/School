package edu.jenks.dist.math;

public interface Calculates {
	double RELATIVE_DELTA = Math.pow(10, -5);
	String ERROR_CODE_NO_INPUT = "Error - no input";
	String ERROR_CODE_UNSUPPORTED_CHARACTER = "Error - unsupported character";
	String ERROR_CODE_UNMATCHED_PARENTHESIS = "Error - unmatched parenthesis";
	String ERROR_CODE_NOT_REAL = "Error - not a real number";
	String ERROR_CODE_OPERATOR_WITHOUT_OPERAND = "Error - operator without operand";
	String ERROR_CODE_NUMBER_FORMAT = "Error - number not formatted correctly";
	String ERROR_CODE_DIVISION_BY_ZERO = "Error - division by zero";

	/**
	 * Evaluate an Infix expression with base-10 numbers.<br>
	 * Whitespace should be ignored.<br>
	 * Numbers with decimals are supported.<br>
	 * Floating point arithmetic is supported.<br>
	 * The following operations are supported:<br>
	 * ^ (exponent)<br>
	 * + (addition)<br>
	 * - (subtraction and negation)<br>
	 * * (multiplication)<br>
	 * / (division)<br>
	 * Grouping (including nested grouping) using parentheses<br><br>
	 * Implicit operations<br><br>
	 * Precondition: decimal values are not so large or small that double overflows.
	 * 
	 * @param input the expression to be evaluated.
	 * @return the value of the evaluated expression or the appropriate error code.
	 */
	String performCalculation(String input);
}
