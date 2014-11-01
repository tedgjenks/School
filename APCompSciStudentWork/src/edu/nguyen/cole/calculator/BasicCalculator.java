package edu.nguyen.cole.calculator;
import java.util.*;
import edu.jenks.calculator.dist.*;

public class BasicCalculator implements Calculates{


        List <String> Parsed;


        public static void main(String[] args){
        BasicCalculator calc = new BasicCalculator();
        System.out.println(calc.performCalculation("7*4/18.0"));
        }



        public double performCalculation(String arg0)
        throws IllegalArgumentException, ArithmeticException {
        Parsed=MathematicalExpressionParser.tokenizeExpression(arg0);
        if(Parsed.size()==1){
        return Double.parseDouble(Parsed.get(0));

        }else if(Parsed.size()==3){
        List<String> secondList = evalSigns(Parsed);
        return Double.parseDouble(secondList.get(0));
        }else if(Parsed.size()>= 5){
        int w = Parsed.indexOf(")");

        if(w!=-1){
        int r = -1;
        boolean t = false;
        for(int index=w; index>=0 && t==false; index--){
        if(Parsed.get(index).equals("(")){
        t=true;
        r=index;
        }

        }
        List<String> secondList = new ArrayList<String>(Parsed.subList(0, r));
        List<String> thirdList= new ArrayList<String>(Parsed.subList(r, w+1));
        List<String> fourthList = new ArrayList<String>(Parsed.subList(w+1,
Parsed.size()));
        Parsed.clear();
        List<String> fifthList = evalSigns(thirdList);
        Parsed.addAll(secondList);
        Parsed.addAll(fifthList);
        Parsed.addAll(fourthList);


        return performCalculation(convert(Parsed));
        }else{
        List<String> newList=evalSigns(Parsed);
        return Double.parseDouble(newList.get(0));
        }
        }

        throw new IllegalArgumentException("Invalid.");
        }


        public List<String> evalSigns(List<String> input){
        if(input.get(0).equals("("))
        input.remove(0);
        if(input.get(input.size()-1).equals(")"))
        input.remove(input.size()-1);
        for(int index=1; index<input.size()-1; index++){
        if(input.get(index).equals("*")){
        double one, two;
        try{
        one = Double.parseDouble(input.get(index-1));
        two = Double.parseDouble(input.get(index+1));
        }
        catch(NumberFormatException E){
        throw new IllegalArgumentException("Invalid input. Try again!");
        }
        double three = one*two;
        input.set(index-1, Double.toString(three));
        input.remove(index+1);
        input.remove(index);
        index=index-1;
        }

        }
        for(int index=1; index<input.size()-1; index++){
        if(input.get(index).equals("/")){
        double one, two;
        try{
        one = Double.parseDouble(input.get(index-1));
        two = Double.parseDouble(input.get(index+1));
        }
        catch(NumberFormatException E){
        throw new IllegalArgumentException("Invalid input. Try again!");
        }
        double three = one/two;
        input.set(index-1, Double.toString(three));
        input.remove(index+1);
        input.remove(index);
        index=index-1;
        }
        }

        for(int index=1; index<input.size()-1; index++){
        if(input.get(index).equals("+")){
        double one, two;
        try{
        one = Double.parseDouble(input.get(index-1));
        two = Double.parseDouble(input.get(index+1));
        }
        catch(NumberFormatException E){
        throw new IllegalArgumentException("Invalid input. Try again!");
        }
        double three = one+two;
        input.set(index-1, Double.toString(three));
        input.remove(index+1);
        input.remove(index);
        index=index-1;
        }
        }

        for(int index=1; index<input.size()-1; index++){
        if(input.get(index).equals("-")){
        double one, two;
        try{
        one = Double.parseDouble(input.get(index-1));
        two = Double.parseDouble(input.get(index+1));
        }
        catch(NumberFormatException E){
        throw new IllegalArgumentException("Invalid input. Try again!");
        }
        double three = one-two;
        input.set(index-1, Double.toString(three));
        input.remove(index+1);
        input.remove(index);
        index=index-1;
        }
        }
        return input;
        }


        public String convert(List<String> i){
        String y = "";
        for(String o: i){
        y+=o;

        }return y;
        }
}
