package edu.joseph.juliet.calculator;

import java.util.ArrayList;
import java.util.List;
import edu.jenks.calculator.dist.Calculates;
import edu.jenks.calculator.dist.MathematicalExpressionParser;

public class BasicCalculator implements Calculates {
	  public BasicCalculator(){
	  }
	  
	  List<String> array;
	  
	  /*public void printList(List<String> array){
		  for(int index=0;index<array.size();index++){
		   System.out.print(array.get(index)+"\t");
		  }
	
		  System.out.println();
	  }*/
	  List<String> array1=new ArrayList<String>(),arrayend,arraybeg;
	  int indexclose=-1,indexopen=-1;
	  public String toStringList(List<String> a){
		  String result="";
		  for(int index=0;index<a.size();index++)
		  result+=a.get(index);
		  return result;
	  }
	  public double performCalculation(String input) {
		   array =MathematicalExpressionParser.tokenizeExpression(input);
		  /****************ONE DIGIT********************/
		  if(array.size()==1)
		      return Double.parseDouble(array.get(0));
		  indexclose=array.indexOf(")");
		  if(array.indexOf("(") != -1){
		  /*************PARANTHESES RECURSION*****************/
		      if(indexclose!=-1){
			      boolean found = false;
			      for (int index=indexclose; index>=0&&found==false;index--){
			          if(array.get(index).equals("(")){
			           found = true;
			          indexopen=index;
			          }
			      }
		      }
			  else
			      indexopen = array.lastIndexOf("(");
		      if(indexopen!=-1&&indexclose!=-1){
			      for(int i =0;i<indexopen-indexclose;i++){
			      String a =array.get(indexopen+1);
			      }
			      
				  List<String> one = new ArrayList(array.subList(0,indexopen));
				  List<String> two = new ArrayList(array.subList(indexopen,indexclose+1));
				  List<String> three = new ArrayList(array.subList(indexclose+1, array.size()));
				  String evaluated=executeOperation(two);
				  array.clear();
				  array.addAll(one);
				  array.add(evaluated);
				  array.addAll(three);
				  String result="";
				  for(int i=0;i<array.size();i++){
				      result+=array.get(i);
				  }
				  return performCalculation(result);
		      }
		  }
		 
		  if (indexopen ==-1&& indexclose==-1){
			  try{
			  return Double.parseDouble(executeOperation(array));
			  }
			  catch(NumberFormatException e){
			  throw new IllegalArgumentException();
			  }
		  }
		  else {
			  try{
			  return Double.parseDouble(executeOperation(array));
			  }
			  catch(NumberFormatException e){
			  throw new IllegalArgumentException();
			  }
		  }
	  }
	   public String executeOperation(List<String> array){
	      if(array.get(0).equals("("))
	          array.remove(0);
	      if(array.get(array.size()-1).equals(")")){
	          array.remove(array.size()-1);
	      }
	 
	
	      for(int index=0;index<array.size()&&(array.indexOf("*")!=-1||array.indexOf("/")!=-1);index++){
	       
	          if(array.get(index).equals("*")){
	              double one, two;
	              try{
	                   one=Double.parseDouble(array.get(index-1));
	                  two=Double.parseDouble(array.get(index+1));
	              }
	              catch(NumberFormatException e){
	                  throw new IllegalArgumentException("Invalid Argument");
	               }
	              array.set(index-1, Double.toString(one*two));
	              array.remove(index+1);
	              array.remove(index);
	              index-=1;
	          }
	          else if(array.get(index).equals("/")){
	               double one, two;
	              try{
	                  one=Double.parseDouble(array.get(index-1));
	                  two=Double.parseDouble(array.get(index+1));
	              }
	              catch(NumberFormatException e){
	                   throw new IllegalArgumentException("Invalid Argument");
	              }
	              array.set(index-1, Double.toString(one/two));
	              array.remove(index+1);
	              array.remove(index);
	               index-=1;
	          }
	          //index-=1;
	      }
		  for(int index=0;index<array.size()&&(array.indexOf("+")!=-1||array.indexOf("-")!=-1);index++){
		      if(array.get(index).equals("+")){
		          double one, two;
		          try{
		              one=Double.parseDouble(array.get(index-1));
		              two=Double.parseDouble(array.get(index+1));
		           }
		          catch(NumberFormatException e){
		              throw new IllegalArgumentException("Invalid Argument");
		          }
		          array.set(index-1, Double.toString(one+two));
		           array.remove(index+1);
		          array.remove(index);
		          index-=1;
		          
		      }
		      else if(array.get(index).equals("-")){
		          double one, two;
		          try{
		              one=Double.parseDouble(array.get(index-1));
		              two=Double.parseDouble(array.get(index+1));
		          }
		          catch(NumberFormatException e){
		              throw new IllegalArgumentException("Invalid Argument");
		          }
		          array.set(index-1, Double.toString(one-two));
		          array.remove(index+1);
		          array.remove(index);
		          index-=1;
		      }
		     
		  }
	  return array.get(0);
	  }
}
