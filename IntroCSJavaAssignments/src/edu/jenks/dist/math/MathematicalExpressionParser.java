/**
 * 
 */
package edu.jenks.calculator.dist;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import edu.jenks.util.MathUtil;

/**
 * @author Ted Jenks
 *
 */
public class MathematicalExpressionParser {
	
	private final static String LEGAL_OPERATORS = "/\\*\\-\\+\\(\\)";
	private final static String USER_REG_EXP = "[\\d\\.\\s" + LEGAL_OPERATORS + "]+";
	private final static Pattern USER_PATTERN = Pattern.compile(USER_REG_EXP);
	
	/**
	 * Tokenize a mathematical expression.<br/>
	 * Each token is a number or an operator.<br/>
	 * The negation operation creates two tokens: '-1' followed by '*'<br/>
	 * 
	 * @param input the mathematical expression to tokenize
	 * @return a list of tokens
	 * @throws IllegalArgumentException if input contains unsupported characters
	 */
	public static List<String> tokenizeExpression(String input) throws IllegalArgumentException {
		if(input == null || !USER_PATTERN.matcher(input).matches())
			throw createIllegalArgumentException(input);
		input = input.replaceAll("\\s", "");
		String[] tokens = input.split("((?<=[" + LEGAL_OPERATORS + "])|(?=[" + LEGAL_OPERATORS + "]))");
		List<String> tokenList = new LinkedList<String>();
		for(String token : tokens) {
			if(token.length() > 0)
				tokenList.add(token);
		}
		parseNegatives(tokenList);
		parseImplicitOperations(tokenList);
		return tokenList;
	}
	
	private static void parseImplicitOperations(List<String> tokens) {
		int initialSize = tokens.size(); // size may not decrease
		for(int index = initialSize - 1; index >= 0; index--) {
			String token = tokens.get(index);
			if(token.equals(")") && index < initialSize - 1) {
				String rightToken = tokens.get(index + 1);
				if(MathUtil.isRealNumber(rightToken))
					tokens.add(index + 1, "*");
			} else if(token.equals("(") && index > 0) {
				String leftToken = tokens.get(index - 1);
				if(MathUtil.isRealNumber(leftToken) || ")".equals(leftToken))
					tokens.add(index, "*");
			}
		}
	}
	
	private static void parseNegatives(List<String> tokens) {
		for(int index = tokens.size() - 2; index >= 0; index--) {
			String token = tokens.get(index);
			if(token.equals("-")) {
				boolean negation = (index == 0);
				if(!negation) {
					String previousToken = tokens.get(index - 1);
					if(!")".equals(previousToken) && !MathUtil.isRealNumber(previousToken)) {
						String nextToken = tokens.get(index + 1);
						if(!"(".equals(nextToken) && !MathUtil.isRealNumber(nextToken))
							throw new IllegalArgumentException("Negation must precede a number or group.");
						negation = true;
					}
				}
				if(negation) {
					tokens.set(index, "-1");
					tokens.add(index + 1, "*");
				}
			}
		}
	}
	
	private static IllegalArgumentException createIllegalArgumentException(String input) {
		StringBuilder message = new StringBuilder(100);
		message.append("Input may only contain numbers, arithmetic operators, and parentheses\n");
		message.append("Client input: " + input);
		return new IllegalArgumentException(message.toString());
	}
}
