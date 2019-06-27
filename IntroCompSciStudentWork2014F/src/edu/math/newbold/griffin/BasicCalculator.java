package edu.math.newbold.griffin;
import edu.jenks.dist.math.*;
import java.util.*;

public class BasicCalculator implements Calculates{
  private static final String operators = "+-*/^";
  public BasicCalculator(){
    super();
  }
  public boolean testSingleNum(String input){
    for(int i = 0; i < input.length(); i++){
      String cur = input.substring(i,i+1);
      if(operators.indexOf(cur) >= 0){
        return false;
      }
    }
    return true;
  }
  public String performCalculation(String input){
      String withoutSpaces = input.replaceAll("\\s+","");
      scanForErrors(withoutSpaces);
      if(testSingleNum(withoutSpaces)){
        System.out.println("hi");
        return withoutSpaces;
      }
      ArrayList<String> tokens = insertElements(withoutSpaces);
      tokens = condenseList(tokens);
      if(tokens.size() == 1){
        return tokens.get(0);
      }
      int count = operatorCounter(tokens);
      if(count == 1){
       executeOperation(tokens, 1);
       if(tokens.get(0).equals("+")){
         return tokens.get(1);
       }else{
         return tokens.get(0);
       }
     }else{
      orderOfOperations(tokens);
     }
      return tokens.get(0);
  }
  public void scanForErrors(String input) {
	  if(input.equals("")) {
		  throw new NumberFormatException("ERROR_CODE_NO_INPUT");
	  }else if(input.indexOf("/0") >= 0) {
		  throw new NumberFormatException("ERROR_CODE_DIVISION_BY_ZERO");
	  }
  }
  public void evaluateParenthesis(ArrayList<String> list){
    if(list.contains("(")){
    	int open = list.indexOf("(");
    	int close = findClosingPar(list, open);
    	if(close == -1) {
    		throw new NumberFormatException("ERROR_CODE_UNMATCHED_PARENTHESIS");
    	}
    }
    orderOfOperations(list);
  }

  public void orderOfOperations(ArrayList<String> list){
     // evaluateParenthesis(list);
      System.out.println(list);
      while(list.contains("^")) {
    	  int index = list.indexOf("^");
    	  executeOperation(list, index);
      }
      System.out.println("After Exponents: " + list);
      for(int i = 0; i < list.size(); i++) {
    	  String cur = list.get(i);
    	  if(cur.equals("*")){
    		executeOperation(list, list.indexOf("*"));
    		i=0;
    	  }
    	  if(cur.equals("/")){
    		 executeOperation(list, list.indexOf("/"));
    		 i=0;
    	  }
      }
      System.out.println("After Multiply/Divide: " + list);
      for(int i = 0; i < list.size(); i++) {
    	  String cur = list.get(i);
    	  if(cur.equals("+")){
    		executeOperation(list, list.indexOf("+"));
    		i=0;
    	  }
    	  if(cur.equals("-")){
    		 executeOperation(list, list.indexOf("-"));
    		 i=0;
    	  }
      }
      System.out.println("After Addition/Subtraction: " + list);

  }

  public int operatorCounter(ArrayList<String> list){
      int count = 0;
      for(int i = 0; i < list.size(); i++){
          String cur = list.get(i);
          if(!isNumeric(cur)){
              count++;
          }
      }
      return count;
  }
  public int findClosingPar(ArrayList<String> list, int indexOfOpening){
    int counter = 1;
    for(int i = indexOfOpening+1; i < list.size(); i++){
      String cur = list.get(i);
      if(cur.equals("(")){
        counter++;
      }else if(cur.equals(")")){
        counter--;
        if(counter == 0){
          return i;
        }
      }
    }
    System.out.println("NO MATCH");
    return -1;
  }
  public void executeOperation(ArrayList<String> list, int indexOfOp){
    String operator = list.get(indexOfOp);
    String res = "";
    switch (operator) {
      case "+" :
        res = Double.toString(Double.parseDouble(list.get(indexOfOp-1)) + Double.parseDouble(list.get(indexOfOp+1)));
        list.set(indexOfOp, res);
        System.out.println(list);
        list.remove(indexOfOp+1);
        list.remove(indexOfOp-1);
        break;
      case "*" :
        res = Double.toString(Double.parseDouble(list.get(indexOfOp-1)) * Double.parseDouble(list.get(indexOfOp+1)));
        list.set(indexOfOp, res);
        list.remove(indexOfOp+1);
        list.remove(indexOfOp-1);
        break;
      case "-" :
        res = Double.toString(Double.parseDouble(list.get(indexOfOp-1)) - Double.parseDouble(list.get(indexOfOp+1)));
        list.set(indexOfOp, res);
        list.remove(indexOfOp+1);
        list.remove(indexOfOp-1);
        break;
      case "/" :
        res = Double.toString(Double.parseDouble(list.get(indexOfOp-1)) / Double.parseDouble(list.get(indexOfOp+1)));
        list.set(indexOfOp, res);
        list.remove(indexOfOp+1);
        list.remove(indexOfOp-1);
        break;
      case "^" :
        res = Double.toString(Math.pow(Double.parseDouble(list.get(indexOfOp-1)),Double.parseDouble(list.get(indexOfOp+1))));
        list.set(indexOfOp, res);
        list.remove(indexOfOp+1);
        list.remove(indexOfOp-1);
        break;
    }

  }

  public ArrayList<String> insertElements(String withoutSpaces){
    ArrayList<String> tokens = new ArrayList<>();
    String res = "";
    for(int i = 0; i < withoutSpaces.length(); i++) {
      String cur = withoutSpaces.substring(i,i+1);
      if(operators.indexOf(cur) >= 0 || cur.equals(")")){
        if(!res.equals("")){
          tokens.add(res);
        }
        tokens.add(cur);
        res = "";
      }else{
        res += cur;
      }
    }
    if(!res.equals("")){
      tokens.add(res);
    }
    return tokens;
  }
  public ArrayList<String> condenseList(ArrayList<String> input){
    ArrayList<String> list = input;
    for(int i = 0; i < list.size()-1; i++){
      if(list.get(i).equals("-") && list.get(i+1).equals("-")) {
    	  list.remove(i+1);
    	  list.set(i, "+");
      }else if(list.size()==2 && list.get(0).equals("-")) {
    	  list.set(1, "-" + list.get(1));
    	  list.remove(0);
      }else if(list.get(0).equals("-")){
        list.set(0, "-"+list.get(1));
        list.remove(1);
        i=0;
      }
    }
    if(list.get(list.size()-1).equals("")){
      list.remove(list.size()-1);
    }
    System.out.println("Condensed list: " + list);
    return list;
  }

  public static boolean isNumeric(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch(NumberFormatException e){
      return false;
    }
  }


  public static void main(String[] args){
    BasicCalculator cal = new BasicCalculator();

    System.out.println("Begin Basics..");
    System.out.println(cal.performCalculation("7"));
    System.out.println(cal.performCalculation("7 + 7"));
    System.out.println(cal.performCalculation("8 - 3"));
    System.out.println(cal.performCalculation("7 ^ 2"));
    System.out.println(cal.performCalculation("7 * 2"));
    System.out.println(cal.performCalculation("7 / 7"));
    System.out.println("End of Basics");
    System.out.println("Begin Advanced....");
    System.out.println(cal.performCalculation("7 * 5 + 2 / 2 - 5 ^ 2"));
    System.out.println(cal.performCalculation("5 ^ 2 ^ 2"));
    System.out.println(cal.performCalculation("49 * 21 / 7 + 4 - 2"));
    System.out.println(cal.performCalculation("76 - 45 * 2"));
    System.out.println(cal.performCalculation("21 + 32 + 88 * 2 / 4 ^ 2"));
    System.out.println("Passed Order of Operations");
    System.out.println(cal.performCalculation("1 - -4"));
    System.out.println(cal.performCalculation("-1 + 4"));
    System.out.println(cal.performCalculation("--243"));
    System.out.println("End");
  }
}
