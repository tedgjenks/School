package edu.math.burroughs.trent;
import edu.jenks.dist.math.*;
import java.util.*;

public class BasicCalculator implements Calculates{
    
    public static void main(String[] args){
        BasicCalculator num = new BasicCalculator();
        //assert "13.0".equals(num.performCalculation("7 + 5 * 2 - 30 / 6 + 100 / 5 ^ 2 - 3")): 10 + " " +  num.performCalculation("7 + 5 * 2 - 30 / 6 + 100 / 5 ^ 2 - 3");
        //assert "-1.0".equals(num.performCalculation("2-3")) : 20 + " " + num.performCalculation("2-3");
        //assert "3.0".equals(num.performCalculation("2--1")) : 30 + " " + num.performCalculation("2--1");
        //assert "-9.0".equals(num.performCalculation("-3^2")) : 40 + " " + num.performCalculation("-3^2");
        //assert "243.0".equals(num.performCalculation("--243.0")) : 50 + " " + num.performCalculation("--243.0");
        String sum = num.performCalculation("-3^3");
        System.out.println("Final answer: " + sum);
    }
    
    public int beforeIndex = 0;
    public int afterIndex = 0;
    
    public BasicCalculator(){
        
    }
    
    public String performCalculation(String num){
        String input = num.replaceAll("\\s+","");
        input = input.replaceAll("--","+");
        input = input.replaceAll("\\+-","-");
        input = input.replaceAll("-\\+","-");
        input = input.replaceAll("\\+\\+","+");
        System.out.println("Calculation call:  " + input);
        ArrayList<String> parenthesis = new ArrayList<>();
        double sum = 0.0;
        //returns if the num is just a number
        if(input.substring(0,1).equals("+")){
            input = input.substring(1);
            return input;
        }
        for(int i = 1; i < input.length(); i++){
            if(i == input.length() - 1){
                return input;
            }
            if(input.substring(i, i + 1).equals("^") || input.substring(i, i + 1).equals("*") || input.substring(i, i + 1).equals("/") || input.substring(i, i + 1).equals("+") || input.substring(i, i + 1).equals("-"))
                break;
        }
        for(int close = 0; close < input.length() - 1; close++){
            if(input.substring(close, close + 1).equals(")")){
                for(int open = close; open >= 0; open--){
                    if(input.substring(open - 1, open).equals("(")){
                        
                    }
                }
            }
        }
        sum = basicfive(input);
        
        return Double.toString(sum);
    }
    
    public double basicfive(String num){
        double sum = 0.0;
        String input = num;
                                                             //finds and calculates exponts 
        for(int i = 0; i < input.length(); i++){
            beforeIndex = 0;
            afterIndex = 0;
            if(input.substring(i, i + 1).equals("^")){
                System.out.println("numBeofre: " + numBefore(input, i) + " numAfter: " + numAfter(input, i));
                sum += Math.pow(numBefore(input, i), numAfter(input, i));
                double dab = numBefore(input, i);
                String firstNum = Double.toString(dab);
                if(beforeIndex == 0){
                    if(firstNum.substring(0, 1).equals("-") && numAfter(input, i) % 2 == 0){
                        input = "-" + sum + input.substring(afterIndex);
                        sum = Double.parseDouble(input);
                    } else {
                        input = sum + input.substring(afterIndex);
                    }
                } else { 
                    if(firstNum.substring(0, 1).equals("-")){
                        input = "-" + input.substring(0, beforeIndex) + sum + input.substring(afterIndex);
                        sum = Double.parseDouble(input);
                    } else {
                        input = input.substring(0, beforeIndex) + sum + input.substring(afterIndex);
                    }
                }
                i--;
            }
        }
                                                          //finds and does mult and division
        for(int i = 0; i < input.length(); i++){
            beforeIndex = 0;
            afterIndex = 0;
            if(input.substring(i, i + 1).equals("*") || input.substring(i, i + 1).equals("/")){
                if(input.substring(i, i + 1).equals("*"))
                    sum = numBefore(input, i) * numAfter(input, i);
                else 
                    sum = numBefore(input, i) / numAfter(input, i);
                if(beforeIndex == 0)
                    input = sum + input.substring(afterIndex);
                else 
                    input = input.substring(0, beforeIndex) + sum + input.substring(afterIndex);
                i--;
            }
        }
                                                        //finds and does addition and substraction
        for(int i = 1; i < input.length(); i++){
            beforeIndex = 0;
            afterIndex = 0;
            if(input.substring(i, i + 1).equals("+") || input.substring(i, i + 1).equals("-")){
                if(input.substring(i, i + 1).equals("+"))
                    sum = numBefore(input, i) + numAfter(input, i);
                else 
                    sum = numBefore(input, i) + numAfter(input, i);
                if(beforeIndex == 0)
                    input = sum + input.substring(afterIndex);
                else 
                    input = input.substring(0, beforeIndex) + sum + input.substring(afterIndex);
                i--;
            }
        }
        return sum;
    }
    
    //helper methods 
    public double numBefore(String input, int index){
        int i;
        if(index > 0)
            i = index - 1;
        else
            i = 0;
        while(i > 0 && !input.substring(i - 1, i).equals("(") && !input.substring(i - 1, i).equals(")") && !input.substring(i - 1, i).equals("+") && !input.substring(i - 1, i).equals("/") && !input.substring(i - 1, i).equals("*") && !input.substring(i - 1, i).equals("^")){
            if(input.substring(i - 1, i).equals("-")){
                i--;
                break;
            }                                                                 
            i--;
        }
        beforeIndex = i;
        double num = Double.parseDouble(input.substring(i, index));
        return num;
    }
    public double numAfter(String input, int index){
        int i = index + 1;
        while(i < input.length() && !input.substring(i, i + 1).equals("(") && !input.substring(i, i + 1).equals(")") && !input.substring(i, i + 1).equals("+") && !input.substring(i, i + 1).equals("/") && !input.substring(i, i + 1).equals("*") && !input.substring(i, i + 1).equals("^")){
            i++;
            if(i < input.length() - 1 && input.substring(i, i + 1).equals("-")){
                break;
            }
        }
        afterIndex = i;
        double num;
        if(input.substring(index, index+1).equals("*") || input.substring(index, index+1).equals("/") || input.substring(index, index+1).equals("^")){
            num = Double.parseDouble(input.substring(index + 1, i));
        } else {
            num = Double.parseDouble(input.substring(index, i));
        }
        return num;
    }
}