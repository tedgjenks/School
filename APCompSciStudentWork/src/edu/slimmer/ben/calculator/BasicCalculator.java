package edu.slimmer.ben.calculator;
import java.util.*;

import edu.jenks.calculator.dist.*;

public class BasicCalculator
extends java.lang.Object
implements Calculates {

	public String basicCalculation(String d1, String operator, String d2) {
		double first= Double.parseDouble(d1);
		double second= Double.parseDouble(d2);
		if (operator.equals("*"))
			return Double.toString(first*second);
		if (operator.equals("/"))
			return Double.toString(first/second);
		if (operator.equals("-"))
			return Double.toString(first-second);
		if (operator.equals("+"))
			return Double.toString(first+second);
		return "";
		
				
	}
	
	@Override
	public double performCalculation(String arg0)
			throws IllegalArgumentException, ArithmeticException {
		List <String> equation= MathematicalExpressionParser.tokenizeExpression(arg0);
		List <String> equation1= Multiply(equation);
		List <String> equation2= Add(equation1);
		return Double.parseDouble(equation2.get(0));
	}
	
	public List <String> Multiply(List <String> input) {
		for (int index=0; index<input.size();index++) {
			if (input.get(index).equals("*")||input.get(index).equals("/")) {
				input.set(index-1, basicCalculation(input.get(index-1),input.get(index),input.get(index+1)));
				input.remove(index+1);
				input.remove(index);
				index=0;
			}
		}
		return input;
			
		}
	
	private List <String> Add(List <String> input) {
		for (int index=0; index<input.size(); index++) {
			if (input.get(index).equals("+")||input.get(index).equals("-")) {
				input.set(index-1,basicCalculation(input.get(index-1),input.get(index), input.get(index+1)));
				input.remove(index+1);
				input.remove(index);
				index=0;
			}
		}
		return input;
		}
	}