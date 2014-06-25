package edu.wicker.marshall.calculator;

import java.util.List;

import edu.jenks.calculator.dist.Calculates;
import edu.jenks.calculator.dist.MathematicalExpressionParser;

public class BasicCalculator implements Calculates{
	@Override
	public double performCalculation(String input) throws IllegalArgumentException, ArithmeticException {
		if(input.charAt(0) != '('){
			input = "(" + input + ")";
		}
		List<String> inputParsed = MathematicalExpressionParser.tokenizeExpression(input);
		boolean doneWithParenthesis = false;
		int amountParenthesis = 0;
		//Count Opening Parenthesis
		for (int i = 0 ; i < inputParsed.size() ; i++){
			if (inputParsed.get(i).charAt(0) == '('){
				amountParenthesis++;
			}
		}
		int lastParenthesis = inputParsed.lastIndexOf("(");
		int lastClose = inputParsed.subList(lastParenthesis, inputParsed.size()).indexOf(")");
		while(amountParenthesis != 0){
			lastParenthesis = inputParsed.lastIndexOf("(");
			lastClose = inputParsed.subList(lastParenthesis, inputParsed.size()).indexOf(")");
			return solvePartialEquation(inputParsed.subList(lastParenthesis, lastClose));
		}
		return solvePartialEquation(inputParsed.subList(lastParenthesis, lastClose));
	}
	private double solvePartialEquation(List<String> input){
		int operatorAmount = 0;
		double output = 0;
		for(int i = 0 ; i < input.size() - 1 ; i++){
			if(input.get(i).charAt(0) == '*' || input.get(i).charAt(0) == '/' || input.get(i).charAt(0) == '+' || input.get(i).charAt(0) == '-' ){
				operatorAmount++;
			}
		}
		//Find Operator
		for(int ops = 0 ; ops < operatorAmount ; ops++){
			int curIndex = 0;
			boolean found = false;
			while (!found && curIndex < input.size()){
				if(input.get(curIndex).charAt(0) == '*' || input.get(curIndex).charAt(0) == '/' || input.get(curIndex).charAt(0) == '+' || input.get(curIndex).charAt(0) == '-' )
					found = true;
				else
					curIndex++;
			}
			switch(input.get(curIndex).charAt(0)){
			case '*':   output = Double.parseDouble(input.get(curIndex - 1)) * Double.parseDouble(input.get(curIndex + 1));
						break;
			case '/':   output = Double.parseDouble(input.get(curIndex - 1)) / Double.parseDouble(input.get(curIndex + 1));
						break;
			case '-':   output = Double.parseDouble(input.get(curIndex - 1)) - Double.parseDouble(input.get(curIndex + 1));
						break;
			case '+':   output += Double.parseDouble(input.get(curIndex - 1)) + Double.parseDouble(input.get(curIndex + 1));
						break;					
			default:
				output = exceptionThrower(input.get(curIndex));
			}
			input.remove(curIndex + 1);
			input.set(curIndex, String.valueOf(output));
			input.remove(curIndex - 1);
		}
		return output;
	}
	private double exceptionThrower(String input){
		try{
			return Double.parseDouble(input);
		}catch(NumberFormatException e){
			throw new IllegalArgumentException();
		}
	}
	public static void main(String[] args){
		System.out.println(new BasicCalculator().performCalculation("(250+3*5)"));
	}
}
