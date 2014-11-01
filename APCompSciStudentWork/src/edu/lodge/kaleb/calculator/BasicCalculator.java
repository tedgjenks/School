package edu.lodge.kaleb.calculator;

import java.util.*;

import edu.jenks.calculator.dist.Calculates;
import edu.jenks.calculator.dist.MathematicalExpressionParser;

public class BasicCalculator extends Object implements Calculates {
	
	public BasicCalculator() {
	}
	
	public double performCalculation(String input)
				throws IllegalArgumentException, ArithmeticException{
			double result = 0;
			LinkedList<String> tokenizedinput = Parse(input);
			if (tokenizedinput.size() == 1){
				return Double.parseDouble(tokenizedinput.get(0));
			}
			String first, operator, second;
				for (int count = 2; count < tokenizedinput.size()+1;){
					first = tokenizedinput.get(count - 2);
					operator = tokenizedinput.get(count - 1);
					second = tokenizedinput.get(count);
					if (operator.compareTo("*") == 0 || operator.compareTo("/") == 0){
						result = performOperation(first, operator, second);
						tokenizedinput.set(count, ("" + result));
						tokenizedinput.remove(count-2);
						tokenizedinput.remove(count-2);
					}else{
						count += 2;
					}
					
				}
				for (int count = 2; count < tokenizedinput.size()+1;){
					
					first = tokenizedinput.get(count - 2);
					operator = tokenizedinput.get(count - 1);
					second = tokenizedinput.get(count);
					if (operator.compareTo("+") == 0 || operator.compareTo("-") == 0){
						result = performOperation(first, operator, second);
						tokenizedinput.set(count, ("" + result));
						tokenizedinput.remove(count-2);
						tokenizedinput.remove(count-2);
					}else{
						count += 2;
					}
					
				}
				
			return result;
	}
	
	
	private double performOperation(String s1, String operator, String s2) {
		double first = Double.parseDouble(s1);
		double second = Double.parseDouble(s2);
		double result = 0;
		switch(operator){
		case "+":
			result = first + second;
			break;
		case "-":
			result = first - second;
			break;
		case "*":
			result = first * second;
			break;
		case "/":
			result = first / second;
			break;
		}
		return result;
	}

	private static LinkedList<String> Parse(String input){
		MathematicalExpressionParser tokenizer = new MathematicalExpressionParser();
		LinkedList<String> tokenizedinput = 
				(LinkedList<String>) tokenizer.tokenizeExpression(input);
		return tokenizedinput;
		
	}
	
}
