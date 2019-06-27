package edu.math.jenks.ted;

import java.util.*;
import java.util.regex.Pattern;
import edu.jenks.dist.math.*;
import edu.jenks.util.*;

public class BasicCalculator implements Calculates {
	
	private final static String LEGAL_OPERATORS = "/\\*\\-\\+\\(\\)\\^";
	private final static Pattern LEGAL_CHAR_PATTERN = Pattern.compile("[\\d\\." + LEGAL_OPERATORS + "]+");
	private final static Pattern MULTIPLE_DECIMAL_POINTS = Pattern.compile("(.*\\..*){2}");
	private final static Set<String> OPERATORS = new HashSet<>(12);
	private final static Set<String> EXPONENT_OPERATORS = new HashSet<>(1);
	private final static Set<String> MULTIPLICATION_OPERATORS = new HashSet<>(3);
	private final static Set<String> ADDITION_OPERATORS = new HashSet<>(3);
	private final static Set<String> GROUPING_OPERATORS = new HashSet<>(3);
	
	static {
		OPERATORS.addAll(Arrays.asList("/", "*", "-", "+", "(", ")", "^"));
		EXPONENT_OPERATORS.add("^");
		MULTIPLICATION_OPERATORS.addAll(Arrays.asList("/", "*"));
		ADDITION_OPERATORS.addAll(Arrays.asList("+", "-"));
		GROUPING_OPERATORS.addAll(Arrays.asList("(", ")"));
	}
	
	private final CurrentNeighbors CN = new CurrentNeighbors();

	@Override
	public String performCalculation(String input) {
		try {
			List<String> tokens = tokenizeExpression(input);
			if(tokens.isEmpty())
				return ERROR_CODE_NO_INPUT;
			evaluateExpressionWithParen(tokens);
			return tokens.get(0);
		} catch(IllegalArgumentException e) {
			return e.getMessage();
		}
	}
	
	private int findMatchingCloseParenthesis(List<String> expression, int openIndex) {
		int openCount = 1, currentIndex = -1;
		ListIterator<String> iterator = expression.listIterator();
		for(; currentIndex < openIndex; currentIndex++) {
			iterator.next();
		}
		while(openCount > 0) {
			if(!iterator.hasNext())
				return -1;
			String nextToken = iterator.next();
			currentIndex++;
			if("(".equals(nextToken))
				openCount++;
			else if(")".equals(nextToken))
				openCount--;
		}
		return currentIndex;
	}
	
	private void evaluateExpressionWithParen(List<String> expression) {
		int openIndex = expression.indexOf("(");
		int closeIndex = expression.indexOf(")");
		if(openIndex < 0 && closeIndex >= 0 || closeIndex < openIndex)
			throw new IllegalArgumentException(ERROR_CODE_UNMATCHED_PARENTHESIS);
		while(openIndex >= 0) {
			closeIndex = findMatchingCloseParenthesis(expression, openIndex);
			if(closeIndex < 0)
				throw new IllegalArgumentException(ERROR_CODE_UNMATCHED_PARENTHESIS);
			expression.remove(closeIndex);
			expression.remove(openIndex);
			evaluateExpressionWithParen(expression.subList(openIndex, closeIndex - 1));
			openIndex = expression.indexOf("(");
		}
		evaluateExpressionNoParen(expression);
	}
	
	private void evaluateOperators(List<String> expression, Set<String> operators) {
		ListIterator<String> iterator = expression.listIterator();
		CN.reset();
		while(iterator.hasNext()) {
			CN.current = iterator.next();
			if(operators.contains(CN.current)) {
				if(iterator.hasNext())
					CN.next = iterator.next();
				else
					CN.next = null;
				executeOperation();
				iterator.remove();
				iterator.previous();
				iterator.remove();
				iterator.previous();
				iterator.set(CN.current);
				iterator.next();
			}
			CN.previous = CN.current;
		}
	}
	
	private void executeOperation() {
		if(CN.next == null || CN.previous == null || !MathUtil.isRealNumber(CN.previous) || !MathUtil.isRealNumber(CN.next))
			throw new IllegalArgumentException(ERROR_CODE_OPERATOR_WITHOUT_OPERAND);
		double result;
		switch(CN.current) {
		case "^":
			double base = Double.parseDouble(CN.previous), exponent = Double.parseDouble(CN.next);
			if(base == 0 && exponent == 0)
				throw new IllegalArgumentException(ERROR_CODE_DIVISION_BY_ZERO);
			result = Math.pow(base, exponent);
			break;
		case "*":
			result = Double.parseDouble(CN.previous) * Double.parseDouble(CN.next);
			break;
		case "/":
			double divisor = Double.parseDouble(CN.next);
			if(divisor == 0)
				throw new IllegalArgumentException(ERROR_CODE_DIVISION_BY_ZERO);
			result = Double.parseDouble(CN.previous) / divisor;
			break;
		case "+":
			result = Double.parseDouble(CN.previous) + Double.parseDouble(CN.next);
			break;
		case "-":
			result = Double.parseDouble(CN.previous) - Double.parseDouble(CN.next);
			break;
		default:
			throw new IllegalArgumentException("Unhandled operator: " + CN.current);
		}
		if(Double.isNaN(result))
			throw new IllegalArgumentException(ERROR_CODE_NOT_REAL);
		CN.current = String.valueOf(result);
	}
	
	private void evaluateExpressionNoParen(List<String> expression) {
		parseExpressionNoParen(expression);
		evaluateOperators(expression, EXPONENT_OPERATORS);
		evaluateOperators(expression, MULTIPLICATION_OPERATORS);
		evaluateOperators(expression, ADDITION_OPERATORS);
	}
	
	/**
	 * Tokenize a mathematical expression.<br>
	 * Each token is a number or an operator.<br>
	 * 
	 * @param input the mathematical expression to tokenize
	 * @return a list of tokens
	 * @throws IllegalArgumentException if input contains unsupported characters
	 */
	private List<String> tokenizeExpression(String input) {
		if(input == null || (input = input.replaceAll("\\s", "")).length() == 0)
			throw new IllegalArgumentException(ERROR_CODE_NO_INPUT);
		if(!LEGAL_CHAR_PATTERN.matcher(input).matches())
			throw new IllegalArgumentException(ERROR_CODE_UNSUPPORTED_CHARACTER);
		String[] tokens = input.split("((?<=[" + LEGAL_OPERATORS + "])|(?=[" + LEGAL_OPERATORS + "]))");
		return createList(tokens);
	}
	
	/**
	 * Check for multiple decimal points
	 * Make implicit operations explicit
	 * 
	 * @param tokens
	 * @return
	 */
	private List<String> createList(String[] tokens) {
		List<String> tokenList = new LinkedList<>();
		CN.reset();
		for(int index = 0; index < tokens.length; index++) {
			CN.current = tokens[index];
			if(index + 1 < tokens.length)
				CN.next = tokens[index + 1];
			if(!OPERATORS.contains(CN.current)) {
				if(MULTIPLE_DECIMAL_POINTS.matcher(CN.current).matches())
					throw new IllegalArgumentException(ERROR_CODE_NUMBER_FORMAT);
				if(")".equals(CN.previous)) {
					tokenList.add("*");
					tokenList.add(CN.current);
				} else if("(".equals(CN.next)) {
					tokenList.add(CN.current);
					tokenList.add("*");
					tokenList.add(CN.next);
					index++;
					CN.current = CN.next;
				} else
					tokenList.add(CN.current);
			} else if(")".equals(CN.current) && "(".equals(CN.next)) {
				tokenList.add(CN.current);
				tokenList.add("*");
				CN.current = CN.next;
				tokenList.add(CN.current);
				index++;
			} else
				tokenList.add(CN.current);
			CN.previous = CN.current;
		}
		return tokenList;
	}
	
	private void parseExpressionNoParen(List<String> expression) {
		if(expression.isEmpty())
			throw new IllegalArgumentException("Empty expression detected.");
		ListIterator<String> iterator = expression.listIterator();
		CN.reset();
		while(iterator.hasNext()) {
			CN.current = iterator.next();
			if(OPERATORS.contains(CN.current)) {
				if(iterator.hasNext()) {
					CN.next = iterator.next();
					iterator.previous();
					iterator.previous();
					iterator.next();
					switch(CN.current) {
					case "-":
						parseNegative(iterator);
						break;
					}
				} else if(")".equals(CN.current))
					throw new IllegalArgumentException(ERROR_CODE_UNMATCHED_PARENTHESIS);
				else
					throw new IllegalArgumentException(ERROR_CODE_OPERATOR_WITHOUT_OPERAND);
			}
			CN.previous = CN.current;
		}
	}
	
	private void parseNegative(ListIterator<String> iterator) {
		if("-".equals(CN.next)) {
			CN.current = "+";
			iterator.remove();
			iterator.next();
			iterator.set(CN.current);
		} else if("+".equals(CN.next)) {
			iterator.next();
			iterator.remove();
			CN.next = iterator.next();
			iterator.previous();
			iterator.previous();
			iterator.next();
		} else if((CN.previous == null || OPERATORS.contains(CN.previous)) && MathUtil.isRealNumber(CN.next)) {
			if("^".equals(CN.previous)) {
				iterator.remove();
				CN.current = "-" + CN.next;
				iterator.next();
				iterator.set(CN.current);
			} else {
				CN.current = "-1";
				iterator.set(CN.current);
				String nextOperator = "/".equals(CN.previous) ? "/" : "*";
				CN.previous = CN.current;
				CN.current = nextOperator;
				iterator.add(CN.current);
			}
		}
	}
	
	private class CurrentNeighbors {
		String previous, current, next;
		
		void reset() {
			previous = current = next = null;
		}

		@Override
		public String toString() {
			return new StringBuilder(10).append(previous).append(", ").append(current).append(", ").append(next).toString();
		}
	}
}
