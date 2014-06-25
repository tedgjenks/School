package edu.rogers.payton.calculator;

import edu.jenks.calculator.dist.Calculates;
import edu.jenks.calculator.dist.MathematicalExpressionParser;

import java.util.*;

public class BasicCalculator implements Calculates{
	List<Integer> numbers = new ArrayList<Integer>(); //These arrays store the index of each token type in the tokenized list
	List<Integer> pluses  = new ArrayList<Integer>();
	List<Integer> minuses  = new ArrayList<Integer>();
	List<Integer> stars  = new ArrayList<Integer>();
	List<Integer> slashes  = new ArrayList<Integer>();
	List<Integer> leftParentheses  = new ArrayList<Integer>();
	List<Integer> rightParentheses  = new ArrayList<Integer>();

	@Override
	public double performCalculation(String stringInput)
			throws IllegalArgumentException, ArithmeticException {
		String trimmed = trimThis(stringInput); //trims all whitespace and adds encapsulating parentheses
		List<String> a = MathematicalExpressionParser.tokenizeExpression(trimmed); //gets a tokenized trimmed list
		List<String> b = fixNegatives(a);
		fillLists(b); //fills class level lists of appropriate token indexes
		List<String> input = calculate(b); //calculates everything
		return Double.parseDouble(input.get(0));
	}
	
	public String trimThis(String a) {
		List<String> sorter = new ArrayList<String>(Arrays.asList(a.split(""))); //splits all characters into list
		for (int index = 0; index < sorter.size();) { //removes all whitespace
			if (sorter.get(index).equals(" ")) sorter.remove(index);
			else index++;
		}
		String concat = "("; 
		for(int index2 = 0; index2 < sorter.size(); index2++) { //concatenates all characters into string
			concat += sorter.get(index2);
		}
		concat = concat + ")";
		return concat;
	}

	
	public void fillLists(List<String> input) {
		this.leftParentheses = new ArrayList<Integer>();
		this.rightParentheses = new ArrayList<Integer>();
		this.pluses = new ArrayList<Integer>();
		this.minuses = new ArrayList<Integer>();
		this.stars = new ArrayList<Integer>();
		this.slashes = new ArrayList<Integer>();
		this.numbers = new ArrayList<Integer>();
		
		
		for(int index = 0; index < input.size(); index++) { //checks type of each token
			
			if (input.get(index).equals("+")) { //plus check
					this.pluses.add(index);
			} else
			
			if (input.get(index).equals("-")) { //minus check
				this.minuses.add(index);
			} else
			
			if (input.get(index).equals("*")) { //star check
				this.stars.add(index);
			} else
			
			if (input.get(index).equals("/")) { //slash check
				this.slashes.add(index);
			} else
			
			if (input.get(index).equals("(")) { //left parentheses check
				this.leftParentheses.add(index);
			} else
			
			if (input.get(index).equals(")")) { //right parentheses check
				this.rightParentheses.add(index);
			} else {
			
			try {
			  Double.parseDouble(input.get(index)); //tries to get a double/integer
			}
			catch(NumberFormatException e){
			  throw new IllegalArgumentException(); //throws exception if no integer, halts whole program
			}
			this.numbers.add(index);
			
			}
		}	
	}
	public List<String> calculate(List<String> input) {
		int leftParenthesesIndex = 0; //left parentheses index location in input, default to first index
		int rightParenthesesIndex = input.size() - 1; //right parentheses index location in input, default to last index
		while (this.rightParentheses.size() != 0) {
			rightParenthesesIndex = this.rightParentheses.get(0); 
			for (int count = 0; count < this.leftParentheses.size(); count++) { //this and if statement 1 line down pick the closest left parentheses
				if (rightParenthesesIndex > this.leftParentheses.get(count)) {
					leftParenthesesIndex = this.leftParentheses.get(count);
				}
			}
				List<String> inputClone = new ArrayList<String>(input);
				input = multiplication(input, leftParenthesesIndex, rightParenthesesIndex); //does all basic operations within parentheses
				if (!compareLists(input, inputClone)) {
					fillLists(input);
					continue;
				}
				input = division(input, leftParenthesesIndex, rightParenthesesIndex);
				if (!compareLists(input, inputClone)) {
					fillLists(input);
					continue;
				}
				input = addition(input, leftParenthesesIndex, rightParenthesesIndex);
				if (!compareLists(input, inputClone)) {
					fillLists(input);
					continue;
				}
				input = subtraction(input, leftParenthesesIndex, rightParenthesesIndex);
				if (!compareLists(input, inputClone)) {
					fillLists(input);
					continue;
				}
				input.remove(rightParenthesesIndex);
				input.remove(leftParenthesesIndex);
				fillLists(input);
			}
		return input;
	}
	
	public List<String> multiplication (List<String> input, int leftParenthesesIndex, int rightParenthesesIndex) {
		double leftNumber = 0;
		double rightNumber = 0;
		int leftNumberIndex = 0;
		int rightNumberIndex = 0;
		
		int count = 0;
		int starIndex = -1;
		double result = 0;
		for (count = 0; count < this.stars.size(); count++) { // cycles through all stars
			if (leftParenthesesIndex < this.stars.get(count) && this.stars.get(count) < rightParenthesesIndex) { //finds a star within the parentheses
				starIndex = this.stars.get(count);
				leftNumberIndex = starIndex - 1;
				rightNumberIndex = starIndex + 1;
				leftNumber = Double.parseDouble(input.get(leftNumberIndex)); //gets number left of star
				rightNumber = Double.parseDouble(input.get(rightNumberIndex)); //gets number right of star
				result = leftNumber * rightNumber; //multiplies
				break;
			}
		}
		if (starIndex != -1) { //if multiplication occurred, this will update the class level arrays
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.add(leftNumberIndex, String.valueOf(result));
			fillLists(input);
		}
		return input;
	}
	
	public List<String> division (List<String> input, int leftParenthesesIndex, int rightParenthesesIndex) {
		double leftNumber = 0;
		double rightNumber = 0;
		int leftNumberIndex = 0;
		int rightNumberIndex = 0;
		
		int count = 0;
		int slashIndex = -1;
		double result = 0;
		for (count = 0; count < this.slashes.size(); count++) { // cycles through all slashes
			if (leftParenthesesIndex < this.slashes.get(count) && this.slashes.get(count) < rightParenthesesIndex) { //finds a slash within the parentheses
				slashIndex = this.slashes.get(count);
				leftNumberIndex = slashIndex - 1;
				rightNumberIndex = slashIndex + 1;
				leftNumber = Double.parseDouble(input.get(leftNumberIndex)); //gets number left of slash
				rightNumber = Double.parseDouble(input.get(rightNumberIndex)); //gets number right of slash
				result = leftNumber / rightNumber; //divides
				break;
			}
		}
		if (slashIndex != -1) { //if division occurred, this will update the class level arrays
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.add(leftNumberIndex, String.valueOf(result));
			fillLists(input);
		}
		return input;
	}
	
	public List<String> addition (List<String> input, int leftParenthesesIndex, int rightParenthesesIndex) {
		double leftNumber = 0;
		double rightNumber = 0;
		int leftNumberIndex = 0;
		int rightNumberIndex = 0;
		
		int count = 0;
		int plusIndex = -1;
		double result = 0;
		for (count = 0; count < this.pluses.size(); count++) { // cycles through all pluses
			if (leftParenthesesIndex < this.pluses.get(count) && this.pluses.get(count) < rightParenthesesIndex) { //finds a plus within the parentheses
				plusIndex = this.pluses.get(count);
				leftNumberIndex = plusIndex - 1;
				rightNumberIndex = plusIndex + 1;
				leftNumber = Double.parseDouble(input.get(leftNumberIndex)); //gets number left of plus
				rightNumber = Double.parseDouble(input.get(rightNumberIndex)); //gets number right of plus
				result = leftNumber + rightNumber; //adds
				break;
			}
		}
		if (plusIndex != -1) { //if addition occurred, this will update the class level arrays
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.add(leftNumberIndex, String.valueOf(result));
			fillLists(input);
		}
		return input;
	}
	
	public List<String> subtraction (List<String> input, int leftParenthesesIndex, int rightParenthesesIndex) {
		double leftNumber = 0;
		double rightNumber = 0;
		int leftNumberIndex = 0;
		int rightNumberIndex = 0;
		
		fillLists(input);
		
		int count = 0;
		int minusIndex = -1;
		double result = 0;
		for (count = 0; count < this.minuses.size(); count++) { // cycles through all minuses
			if (leftParenthesesIndex < this.minuses.get(count) && this.minuses.get(count) < rightParenthesesIndex) { //finds a minus within the parentheses
				minusIndex = this.minuses.get(count);
				leftNumberIndex = minusIndex - 1;
				rightNumberIndex = minusIndex + 1;
				leftNumber = Double.parseDouble(input.get(leftNumberIndex)); //gets number left of minus
				rightNumber = Double.parseDouble(input.get(rightNumberIndex)); //gets number right of minus
				result = leftNumber - rightNumber; //subtracts
				break;
			}
		}
		if (minusIndex != -1) { //if subtraction occurred, this will update the class level arrays
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.remove(leftNumberIndex);
			input.add(leftNumberIndex, String.valueOf(result));
			fillLists(input);
		}
		return input;
	}
	
	public boolean compareLists (List<String> first, List<String> second) {
		if (first.size() != second.size()) return false;
		for (int index = 0; index < first.size(); index++) {
			if (first.get(index) != second.get(index))
				return false;
		}
		return true;
	}
	
	public List<String> fixNegatives (List<String> input) {
		for (int index = 2; index < input.size(); index++) { //index starts at 2 because 0 will be a "("
			
			boolean isNumber = true;
			try {
				Double.parseDouble(input.get(index)); //tests whether index is a number or not
				}
				catch(NumberFormatException e){
					isNumber = false; //if it isn't a number, isNumber = false
				}
		
			if (!isNumber) 
				continue;
			double number = Double.parseDouble(input.get(index));
			Double set = number -number * 2;
			if (input.get(index - 1).equals("-")) {
				input.set(index - 1, set.toString());
				input.remove(index);
				input.add(index - 1, "+"); //add plus
			}
		}
		return input;
	}
}

