package edu.manalich.patrick.calculator;

import java.util.List;

import edu.jenks.calculator.dist.Calculates;
import edu.jenks.calculator.dist.MathematicalExpressionParser;

public class BasicCalculator extends Object implements Calculates {
	
	
	public BasicCalculator(){
		
	}
	public double performCalculation(String input)
            throws IllegalArgumentException,
                   ArithmeticException{
		
		
		List<String> commands = MathematicalExpressionParser.tokenizeExpression(input);
		for(int index = 0; index < commands.size();index++){
			if(commands.get(index).equals("(")){
				checkForParenthesis(commands);
			}
		}
		for( int index = 1; index < commands.size(); index++){
			index += checkForOperationsMD(commands, index, commands.get(index-1),commands.get(index),commands.get(index+1));
				
		}
		for( int index = 1; index < commands.size(); index++){
			index += checkForOperationsAS(commands, index, commands.get(index-1),commands.get(index),commands.get(index+1));
				
		}
			
		return Double.parseDouble(commands.get(0));
	}
	public int checkForOperationsMD(List<String> commands,int index, String a,String operation, String b){
		if(a.equals("*") || a.equals("/")  || b.equals("*") || b.equals("/") || a.equals("+") || a.equals("-")  || b.equals("+") || b.equals("-")){
			throw new IllegalArgumentException();
		}
		double d = Double.parseDouble(a);
		double d2 = Double.parseDouble(b);
		double solution = 0;
		int returnindex = -1;
		
		if(operation.equals("*")){
			solution = d*d2;
			reset(commands, solution, index);
			
		}
		else if(operation.equals("/")){
			solution = d/d2;
			reset(commands, solution, index);
		}
		else{
			returnindex = index;
			
			
		}
		return returnindex;
		
		
	}
	public int checkForOperationsAS(List<String> commands,int index, String a,String operation, String b){
		if(a.equals("*") || a.equals("/")  || b.equals("*") || b.equals("/") || a.equals("+") || a.equals("-")  || b.equals("+") || b.equals("-")){
			throw new IllegalArgumentException();
		}
		double d = Double.parseDouble(a);
		double d2 = Double.parseDouble(b);
		double solution = 0;
		int returnindex = -1;
		
		if(operation.equals("+")){
			solution = d+d2;
			reset(commands, solution, index);
		}
		else if(operation.equals("-")){
			solution = d-d2;
			reset(commands, solution, index);
		}
		else{
			returnindex = 0;
			
			
		}
		return returnindex;
		
	}
	public void reset(List<String> commands, double solution, int currentindex){
			int index= currentindex;
			commands.set(index-1, String.valueOf(solution));
			commands.remove(index+1);
			commands.remove(index);
		
		
	}
	public void checkForParenthesis(List<String> commands){
		
		while(true){	
			int startIndex = commands.indexOf("(");
			
			if(startIndex == -1){
				evaluateExpression(commands);
				return;
			}
			else{
				int lastIndex = findCP(commands, startIndex);
				List<String> newCommands = commands.subList(startIndex +1, lastIndex);
				checkForParenthesis(newCommands);
				commands.remove(startIndex + 2);
				commands.remove(startIndex);
			}
		}
					
			
		
	}
	public int findCP(List<String> commands, int startIndex){
		int count = 0;
		int bucketsAway = 0;
		for(int index = startIndex + 1; index < commands.size();index++){
			if(commands.get(index).equals("(")){
				count++;
			}
			if(commands.get(index).equals(")")){
				
				if( count == 0 ){
					int lastIndex = index;
					bucketsAway = lastIndex - startIndex;//?? should this return 
				
					
				}
				else{
					count--;
				}
			}
		}
		return bucketsAway + startIndex;
	}
		
		
	//}
	public void evaluateExpression(List<String> commands){
		for( int index = 1; index < commands.size(); index++){
			index += checkForOperationsMD(commands, index, commands.get(index-1),commands.get(index),commands.get(index+1));
				
		}
		for( int index = 1; index < commands.size(); index++){
			index += checkForOperationsAS(commands, index, commands.get(index-1),commands.get(index),commands.get(index+1));
				
		}
		
	}
		
		
	

}
