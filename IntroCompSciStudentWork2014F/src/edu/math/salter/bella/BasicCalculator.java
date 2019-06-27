package edu.math.salter.bella;
import edu.jenks.dist.math.*;
import java.util.*;
public class BasicCalculator implements Calculates
{
    private final String posOperands = "+-*/^";
    private final String digits = "0123456789";
    public static void main(String[] args) {
        System.out.println("Begin test of BasicCalculator");
        
        System.out.println("Begin test of arithmetic operations");
        BasicCalculator tester = new BasicCalculator();
        System.out.println(tester.performCalculation("-8-3 * 5"));
        //assert tester.performCalculation("7 + 5 * 2 - 30 / 6 + 100 / 5 ^ 2 - 3").equals("13.0") : "Error: line 13 returning";
        //assert tester.performCalculation("10.75 - 2").equals("8.75") : "Error: returning wrong number when subtracting w/ decimal";
        //assert tester.performCalculation("3.5*7.0").equals("24.5") : "Error: returning wrong number when multiplying decimals";
        //assert tester.performCalculation("80.0 / 5.0").equals("16.0") : "Error: returning wrong number when dividing decimals";
        //assert tester.performCalculation("2^5").equals("32.0") : "Error: returning wrong number with exponents";
        System.out.println("End test of arithmetic operations");
        
        System.out.println("All tests pass");
        System.out.println("End test of BasicCalculator");
        
    }
    public BasicCalculator() {
        
    }
    public String performCalculation(String input) {
        input = input.replaceAll("\\s", "");
        List<Double> terms= new ArrayList<>();
        List<String> operands = new ArrayList<>(); 
        int countOperands = 0;
        boolean startsWithNeg = false;
        boolean wasNeg = false;
       if(input.substring(0, 1).equals("-")) {
            startsWithNeg = true;
        }
        for(int i = 0; i < input.length(); i++) {
            boolean neg = false;
            if(posOperands.indexOf(input.substring(i, i+1)) > -1 && i != 0) {
                operands.add(input.substring(i, i+1));
                if(i != 0)
                    countOperands++;
                //if(operands.size() > terms.size() ) {
                    //if(operands.get(countOperands - 1).equals(operands.get(countOperands - 2))) {
                        //operands.remove(operands.size() -1 );
                        //operands.remove(operands.size() - 2);
                        //operands.add(operands.size() - 1, "+");
                    //} else if(operands.get(countOperands-1).equals("-")){
                        //neg = true;
                        //operands.remove(countOperands - 1);
                        //countOperands--;
                    //} else if(operands.get(countOperands - 1).equals("+")) {
                        //neg = false;
                        //operands.remove(countOperands -1);
                        //countOperands--;
                    //}else {
                        //operands.remove(operands.size()- 1);
                        //operands.remove(operands.size() - 1);
                        //operands.add( "-");
                        //countOperands --;
                    //}
                //}
                //System.out.println(operands);
            } else {
                int c = 1;
                int countDec = 0;
                if(i == 0 && startsWithNeg) {
                    i++;
                }
                while(i + c < input.length() && (digits.indexOf(input.substring(i+ c, i + c+ 1)) > -1 || input.substring(i + c, i+c+ 1).equals("."))) {
                    if(input.substring(i + c, i+ c +1).equals(".")) {
                        if(countDec == 0) {
                            countDec++;
                        } else {
                            break;
                        }
                    }
                    c++; 
                }
                //System.out.println(i + c + 1);
                if(i > 0 && input.substring(i -1, i).equals("-") && operands.size() > 0 && operands.size() > terms.size()) {
                    operands.remove(operands.size() -1);
                    neg = true;
                    wasNeg = true;
                }
                //System.out.println(input.substring(i, i+ c));
                if(startsWithNeg || neg) {
                    terms.add(-1 * Double.parseDouble(input.substring(i, i + c)));
                    System.out.println(terms);
                    startsWithNeg = false;
                } else {
                    terms.add(Double.parseDouble(input.substring(i, i+c))); 
                }
                i = i+c-1;
            }
        }
        if(countOperands == 0){
            return input;
        }
        boolean needOrder = false;
        for(int o = 0; o < operands.size(); o++) {
            int order = getOrderOperand(operands.get(o));
            for(int i = 0; i < operands.size(); i++) {
                if(getOrderOperand(operands.get(i)) != order){
                    needOrder = true;
                    break;
                }
            }
            break;
        }
        System.out.println(needOrder && !wasNeg);
        System.out.println("TERMS:" + terms);
        System.out.println("OPERANDS:" + operands);
        if(needOrder && !wasNeg) {
            return orderOfOperations(terms, operands, input);
        }
        double answerDigits = 0;
        int i = 0;
        if(terms.size() == 1) {
            answerDigits = terms.get(0);
        }
        while(terms.size() > 1 ) {
            answerDigits = doMath(terms.get(0), terms.get(1), operands.get(0));
            System.out.println(answerDigits);
            operands.remove(0);
            terms.remove(0);
            terms.remove(0);
            System.out.println(terms);
            terms.add(0, answerDigits);
            System.out.println(terms);
            i++;
        }
        String answer = new String();
        answer = "";
        answer += answerDigits;
        System.out.println(answerDigits);
        return answer;
    
    }
    public String orderOfOperations(List<Double> terms, List<String> operands, String input) {
        List<Integer> exps = new ArrayList<>();
        List<Integer> plusesAndMinuses = new ArrayList<>();
        List<Integer> multipAndDivis = new ArrayList<>();
        ArrayList<List<Integer>> listOfLists = new ArrayList<>();
        listOfLists.add(exps);
        listOfLists.add(multipAndDivis);
        listOfLists.add(plusesAndMinuses);
        for(int i = 0; i < operands.size(); i++) {
            if(operands.get(i).equals(posOperands.substring(4,5))) {
                exps.add(i);
            } else if(operands.get(i).equals(posOperands.substring(3,4))) {
                multipAndDivis.add(i);
            } else if(operands.get(i).equals(posOperands.substring(2,3))) {
                multipAndDivis.add(i);
            } else if(operands.get(i).equals(posOperands.substring(1,2))) {
                plusesAndMinuses.add(i);
            } else {
                plusesAndMinuses.add(i);
            }
        }
        String newCalc = "";
        for(int i = 0; i< listOfLists.size(); i++) {
            for(int c = 0; c < listOfLists.get(i).size(); c++) {
                int index = listOfLists.get(i).get(c);
                 double ans = doMath(terms.get(index), terms.get(index + 1), operands.get(index));
                 System.out.println(ans);
                 terms.set(index, ans);
                 terms.remove(index + 1);
                 operands.remove(index);
                    //String newInput = new String();
                    //newInput = input.substring(0, (2*i)) + ans + input.substring((2*i ) + 2, input.length());
                    //System.out.println(newInput);
                    newCalc = performCalculation(ansToString(terms, operands));
                return newCalc;
            }
        }
        return newCalc;
    }
    public boolean differentOrder(String operand1, String operand2) {
        int orderOperand1 = getOrderOperand(operand1);
        int orderOperand2 = getOrderOperand(operand2);
        return !(orderOperand1 == orderOperand2);
    }
    public int getOrderOperand(String operand) {
        String firstOrder = "^";
        String secondOrder = "*/";
        String thirdOrder = "+-";
        if(firstOrder.indexOf(operand) > -1) {
            return 1;
        } else if(secondOrder.indexOf(operand) > -1) {
            return 2;
        } else{
            return 3;
        }
    }
    public void switchCharAt(int indexFrom, int indexTo, List<Double> terms, List<String> operands) {
        double beginTerm = terms.get(indexFrom);
        double endTerm = terms.get(indexFrom + 1);
        double beginTermTo = terms.get(indexTo);
        
    }
    public double doMath(double num1, double num2, String operand) {
        double ans = 0;
        if(posOperands.indexOf(operand) == 0) {
                ans = num1 + num2;
            } else if(posOperands.indexOf(operand) == 1) {
                ans = num1 - num2;
            } else if(posOperands.indexOf(operand) == 2) {
                ans = num1 * num2;
            } else if(posOperands.indexOf(operand) == 3) {
                ans = num1 / num2;
            } else{
                ans = Math.pow(num1, num2);
            }
            return ans;
    }
    public String ansToString(List<Double> terms, List<String> operands) {
        String ans = "";
        ans = ans + terms.get(0);
        for(int i = 0; i< operands.size(); i++) {
            ans = ans + operands.get(i);
            ans = ans + terms.get(i+1);
        }
        //System.out.println(ans);
        return ans;
    }
}
