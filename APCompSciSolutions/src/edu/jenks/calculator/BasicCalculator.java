package edu.jenks.calculator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import edu.jenks.calculator.dist.Calculates;
import edu.jenks.calculator.dist.MathematicalExpressionParser;
import edu.jenks.util.CollectionUtil;
import edu.jenks.util.MathUtil;

public class BasicCalculator implements Calculates {
	
	private final static Set<String> MULT_DIV_OPERATORS = new HashSet<String>(4);
	private final static Set<String> ADD_SUB_OPERATORS = new HashSet<String>(4);
	private final static Pattern OPERATORS_PATTERN = Pattern.compile("/\\*\\-\\+");
	
	static {
		MULT_DIV_OPERATORS.add("*");
		MULT_DIV_OPERATORS.add("/");
		ADD_SUB_OPERATORS.add("+");
		ADD_SUB_OPERATORS.add("-");
	}
	
	@Override
	public double performCalculation(String input) throws IllegalArgumentException, ArithmeticException {
		List<String> tokenList = MathematicalExpressionParser.tokenizeExpression(input);
		if(!validateExpression(tokenList))
			throw new IllegalArgumentException("Not a valid expression: " + input);
		String value = evaluateInput(tokenList);
		return Double.parseDouble(value);
	}
	
	private static boolean validateExpression(List<String> tokens) throws IllegalArgumentException {
		boolean pass = true;
		int size = tokens.size();
		int openParenCount = CollectionUtil.count(tokens, "(");
		int closedParenCount = CollectionUtil.count(tokens, ")");
		if(openParenCount != closedParenCount)
			throw new IllegalArgumentException("Number of open parens ("
					+ openParenCount + ") different from number of closed parens ("
					+ closedParenCount + ")");
		for(int index = 0; index < size; index++) {
			if("(".equals(tokens.get(index)))
				findMatchingClosedParen(tokens, index); // will throw exception if not found
		}
		for(int index = 0; pass && index < size - 1; index++) {
			String token = tokens.get(index);
			String nextToken = tokens.get(index + 1);
			if(OPERATORS_PATTERN.matcher(token).matches() || "(".equals(token)) // next token number or open paren
				pass = (MathUtil.isRealNumber(nextToken) || "(".equals(nextToken));
			else if(MathUtil.isRealNumber(token)) // next token operator
				pass = OPERATORS_PATTERN.matcher(nextToken).matches();
			else if(")".equals(token)) // next token operator or closed paren
				pass = (OPERATORS_PATTERN.matcher(nextToken).matches() || ")".equals(nextToken));
			else
				throw new IllegalArgumentException("Illegal token: " + token);
		}
		String lastToken = tokens.get(size - 1);
		pass = (MathUtil.isRealNumber(lastToken) || ")".equals(lastToken)); // last token must be number or closed paren
		return pass;
	}
	
	private static String evaluateInput(List<String> tokens) throws IllegalArgumentException {
		int openParenIndex = tokens.indexOf("(");
		while(openParenIndex >= 0) {
			int matchingClosedParenIndex = findMatchingClosedParen(tokens, openParenIndex);
			tokens.remove(matchingClosedParenIndex);
			tokens.remove(openParenIndex);
			evaluateInput(tokens.subList(openParenIndex, matchingClosedParenIndex - 1));
			openParenIndex = tokens.indexOf("(");
		}
		if(tokens.size() > 1)
			evaluateExpressionWithoutParen(tokens);
		if(tokens.size() != 1)
			throw new IllegalArgumentException("Input could not be evaluated (more than one token remaining).");
		return tokens.get(0);
	}
	
	private static int findMatchingClosedParen(List<String> tokens, int firstOpenParenIndex) throws IllegalArgumentException {
		int matchingClosedParenIndex = -1;
		for(int index = firstOpenParenIndex + 1, size = tokens.size(), unmatchedOpenParenCount = 1; matchingClosedParenIndex < 0 && index <= size; index++) {
			String token = tokens.get(index);
			switch(token) {
			case "(":
				unmatchedOpenParenCount++;
				break;
			case ")":
				if(--unmatchedOpenParenCount == 0)
					matchingClosedParenIndex = index;
				break;
			}
		}
		if(matchingClosedParenIndex < firstOpenParenIndex + 2)
			throw new IllegalArgumentException("Matching closed paren not found for index " + firstOpenParenIndex);
		return matchingClosedParenIndex;
	}
	
	private static void evaluateExpressionWithoutParen(List<String> tokens) {
		if(tokens.size() == 0)
			throw new IllegalArgumentException("tokens empty");
		parseOperations(tokens, MULT_DIV_OPERATORS);
		parseOperations(tokens, ADD_SUB_OPERATORS);
	}
	
	private static void parseOperations(List<String> tokens, Set<String> operations) {
		int listSize = tokens.size();
		for(int index = 1; index <= listSize - 1; index++) {
			String token = tokens.get(index);
			if(operations.contains(token)) {
				String result = executeOperation(tokens.get(index - 1), token, tokens.get(index + 1));
				tokens.set(index - 1, result);
				tokens.remove(index);
				tokens.remove(index);
				listSize -= 2;
				index--;
			}
		}
	}
	
	private static String executeOperation(String s1, String operator, String s2) throws ArithmeticException {
		if(!MathUtil.isRealNumber(s1) || !MathUtil.isRealNumber(s1))
			throw new IllegalArgumentException("Parameters must be numbers.\ns1: " + s1 + "\ns2: " + s2);
		double d1 = Double.parseDouble(s1), d2 = Double.parseDouble(s2);
		double result;
		switch(operator) {
		case "*":
			result = d1 * d2;
			break;
		case "/":
			result = d1 / d2;
			break;
		case "+":
			result = d1 + d2;
			break;
		case "-":
			result = d1 - d2;
			break;
		default:
			throw new IllegalArgumentException("operator must be *, /, +, or -");
		}
		return String.valueOf(result);
	}
}
