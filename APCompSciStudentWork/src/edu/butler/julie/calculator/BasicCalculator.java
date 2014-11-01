
package edu.butler.julie.calculator;
import java.util.*;

import edu.jenks.calculator.dist.Calculates;
public class BasicCalculator implements Calculates {
	private ArrayList<String> main=new ArrayList<String>();
	public double performCalculation(String input){
		//******************REMOVE SPACES***************************************************************************************************
		input=input.replaceAll(" ","");
		//*************************************RETURN IF ONE NUMBER**************************************************************************
		int numberCount=0;
		int negativeCount=0;
		for(int i=0;i<input.length();i++){
			String current=input.substring(i,i+1);
			if(current.equals("0")||current.equals("1")||current.equals("2")||current.equals("3")||current.equals("4")||current.equals("5")||current.equals("6")||current.equals("7")||current.equals("8")||current.equals("9")||current.equals(".")||current.equals("-")){
				numberCount++;
				if(current.equals("-")&&input.indexOf("-")==0){
					negativeCount++;
				}
			}
		}
		if(numberCount==input.length()&&negativeCount==1)
			return Double.parseDouble(input);
		
		
		
		else{
			//*********************************************IMPLICIT MULTIPLICATION***********************************************************************
			
			
			for(int index=1;index<input.length();index++){
				String beforeChar="",afterChar="";
				String currentChar=input.substring(index,index+1);
				if(currentChar.equals("("))
					 beforeChar=input.substring(index-1,index);
				if(currentChar.equals(")")&&index<=input.length()-2)
					 afterChar =input.substring(index+1,index+2);
				if(currentChar.equals("(")){
					if(beforeChar.equals("0")||beforeChar.equals("1")||beforeChar.equals("2")||beforeChar.equals("3")||beforeChar.equals("4")||beforeChar.equals("5")||beforeChar.equals("6")||beforeChar.equals("7")||beforeChar.equals("8")||beforeChar.equals("9")||beforeChar.equals(")")){
						input=input.substring(0,index)+"*"+input.substring(index);
					}

				}
				if(currentChar.equals(")")){
					if(afterChar.equals("0")||afterChar.equals("1")||afterChar.equals("2")||afterChar.equals("3")||afterChar.equals("4")||afterChar.equals("5")||afterChar.equals("6")||afterChar.equals("7")||afterChar.equals("8")||afterChar.equals("9")||afterChar.equals(")")){
						input=input.substring(0,index+1)+"*"+input.substring(index+2);
					
				}
			}
			}
			//*****************************************MORE THAN ONE NUMBER*****************************************************************************
			int x=input.indexOf(")");
			if(x==-1){
				return helpCalculate(input);
			}
			else{
				int y=input.lastIndexOf("(",x);
			

				return(performCalculation(input.substring(0,y)+Double.toString(helpCalculate(input.substring(y,x+1)))+input.substring(x+1)));
			}
		}
	}						
						
					
				
			
	
	
	public int numberIn(String input,String find){
		int result=0;
		for(int index=0;index<input.length();index++){
			String currentChar=input.substring(index,index+1);
			if(currentChar.equals(find))
				result++;
		}
		return result;
	}
	public double helpCalculate(String input){
		//*****************************************************PARSING****************************************************************************
		String original = new String(input);
		if(!input.substring(0,1).equals("(")){
			input="("+input;
		}
		if(!input.substring(input.length()-1).equals(")")){
			input=input+")";
		}
		while(input.length()>0){
			String currentChar = input.substring(0,1);
			if(currentChar.equals("(")||currentChar.equals(")")||currentChar.equals("^")||currentChar.equals("*")||currentChar.equals("/")||currentChar.equals("+")||currentChar.equals("-")){
				main.add(currentChar);
				input = input.substring(1);
			}
			else if(currentChar.equals("0")||currentChar.equals("1")||currentChar.equals("2")||currentChar.equals("3")||currentChar.equals("4")||currentChar.equals("5")||currentChar.equals("6")||currentChar.equals("7")||currentChar.equals("8")||currentChar.equals("9")||currentChar.equals(".")){
				String number="";
				int here = 0;
				number += currentChar;
				boolean found = false;
				for(int i=1;i < input.length() && found==false;i++){
					String thisone=input.substring(i,i+1);
					if(thisone.equals("0")||thisone.equals("1")||thisone.equals("2")||thisone.equals("3")||thisone.equals("4")||thisone.equals("5")||thisone.equals("6")||thisone.equals("7")||thisone.equals("8")||thisone.equals("9")||thisone.equals(".")){
						number+=thisone;
					}
					else{
						found=true;
						here=i;
						main.add(number);
					}
					
				}
				input=input.substring(here);
			}
			else if(currentChar.equals(" ")){
				input=input.substring(1);
			}
			else
				throw new IllegalArgumentException("Equation Contains Illegal Characters");
			
		}
		
		//printMain();
		//System.out.println();
		if(main.get(0).equals("(")){
			main.remove(0);
		}
		if(main.get(main.size()-1).equals(")")){
			main.remove(main.size()-1);
		}
		//********************************************************NEGATIVE NUMBER FIRST AND NEGATIVE SIGN FOLLOWING A SIGN***************************************************************
		if(original.indexOf("-")!=-1){
			if(main.get(0).equals("-")){
				double math;
				try{
					math=Double.parseDouble(main.get(1));
				}
				catch(NumberFormatException w){
					throw new IllegalArgumentException();
				}
				math=(-1)*math;
				main.set(1,Double.toString(math));
				main.remove(0);
			}
			for(int i=1;i<main.size()-1;i++){
				String current =main.get(i);
				if(current.equals("-")){
					String newCurrent=main.get(i-1);
					if(newCurrent.equals("*")||newCurrent.equals("/")||newCurrent.equals("+")||newCurrent.equals("-")||newCurrent.equals("(")){
						double x=-1*(Double.parseDouble(main.get(i+1)));
						main.set(i+1, Double.toString(x));
						main.remove(i);
					}
				}
			}
		}
		
		//*********************************************RESOLVE SIGNS*********************************************************************
		if(original.indexOf("^")!=-1){
			int count=1;
			for(int i=1;i<main.size()-1&&original.indexOf("^",count)!=-1;i++){
				//printMain();
				String current=main.get(i);
				if(current.equals("^")){
					double math;
					try{
						math = Math.pow(Double.parseDouble(main.get(i-1)),Double.parseDouble(main.get(i+1))  );
					}catch(NumberFormatException e){
						throw new IllegalArgumentException();
					}				
					main.remove(i+1);
					main.remove(i);
					main.set(i-1, Double.toString(math));
					i-=1;
					count++;
				}
			}
		}
		if(original.indexOf("*")!=-1){
			int count =1;
			for(int i=1;i<main.size()-1 && original.indexOf("*",count)!=-1;i++){
				//printMain();
				String current=main.get(i);
				if(current.equals("*")){
					double math;
					try{
						math = Double.parseDouble(main.get(i-1))*Double.parseDouble(main.get(i+1));
					}catch(NumberFormatException e){
						throw new IllegalArgumentException();
					}
					main.remove(i+1);
					main.remove(i);
					main.set(i-1, Double.toString(math));
					i-=1;
					//System.out.print("Second: ");
					//printMain();
					count++;
					//System.out.println(count);
					//System.out.println(original.indexOf("*",count));
				}
			}
		
		}
		if(original.indexOf("/")!=-1){
			int count=1;
			for(int i=1;i<main.size()-1&&original.indexOf("/",count)!=-1;i++){
				//printMain();
				String current=main.get(i);
				if(current.equals("/")){
					double math;
					try{
						math = Double.parseDouble(main.get(i-1))/Double.parseDouble(main.get(i+1));
					}catch(NumberFormatException e){
						throw new IllegalArgumentException();
					}
					main.remove(i+1);
					main.remove(i);
					main.set(i-1, Double.toString(math));
					i-=1;
					count++;
				}
			}
		
		}
		if(original.indexOf("+")!=-1){
			int count=1;
			for(int i=1;i<main.size()-1&&original.indexOf("+",count)!=-1;i++){
				//printMain();
				String current=main.get(i);
				if(current.equals("+")){
					double math=Double.parseDouble(main.get(i-1))+Double.parseDouble(main.get(i+1));
					main.remove(i+1);
					main.remove(i);
					main.set(i-1, Double.toString(math));
					i-=1;
					count++;
				}
			}
		
		}
		if(original.indexOf("-")!=-1){
			int count=1;
			for(int i=1;i<main.size()-1&&original.indexOf("-",count)!=-1;i++){
				//printMain();
				String current=main.get(i);
				if(current.equals("-")){
					double math=Double.parseDouble(main.get(i-1))-Double.parseDouble(main.get(i+1));
					main.remove(i+1);
					main.remove(i);
					main.set(i-1, Double.toString(math));
					i-=1;
					count++;
				}
			}
		
		}
		//***********************************************************************IF ONE NUMBER*******************************************************
		if(main.size()==1){
			double result=Double.parseDouble(main.get(0));
			main.clear();
			return result;
			
		}
		else{
			StringBuilder s = new StringBuilder();
			for(int i=0;i<main.size();i++){
				s.append(main.get(i)+"\t");
			}
			throw  new IllegalArgumentException("Not Resolved to One Element: " + s.toString());
		}
	}
	public void printMain(){
		for(int i=0;i<main.size();i++){
			System.out.print(main.get(i)+"\t");
		}
		System.out.println();
	}
	public static void main(String[] args){
		BasicCalculator c=new BasicCalculator();
		System.out.println(c.performCalculation("(2-3)(2+9)"));
		//c.printMain();
	}
	
}
