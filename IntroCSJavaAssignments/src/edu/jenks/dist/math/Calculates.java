package edu.math.jenks.ted;

public interface Calculates {

	/**
	 * Evaluate an Infix expression with base-10 numbers.<br/>
	 * Whitespace should be ignored.<br/>
	 * Numbers with decimals are supported.<br/>
	 * Floating point arithmetic is supported.<br/>
	 * The following operations are supported:<br/>
	 * + (addition)<br/>
	 * - (subtraction and negation)<br/>
	 * \* (multiplication)<br/>
	 * \/ (division)<br/>
	 * Grouping (including nested grouping) using parentheses<br/><br/>
	 * Implicit operations are not supported.<br/><br/>
	 * Precondition: decimal values are not so large or small that double cannot be used.
	 * 
	 * @param input the expression to be evaluated.
	 * @return the value of the evaluated expression.
	 * @throws IllegalArgumentException if input contains illegal characters or is not formatted properly.
	 * @throws ArithmeticException if division by zero occurs (not tested).
	 */
	double performCalculation(String input) throws IllegalArgumentException, ArithmeticException;
}
