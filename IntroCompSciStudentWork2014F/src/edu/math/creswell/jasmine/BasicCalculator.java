
package edu.math.creswell.jasmine;
import edu.jenks.dist.math.*;


public class BasicCalculator implements Calculates
{
    
    public BasicCalculator()
    {
       
    }
    public int findEndNumber(String num) {
            for (int i=0; i< num.length(); i++) {
                if (num.substring(i,i+1).equals("+")||num.substring(i,i+1).equals("-")||num.substring(i,i+1).equals("*")||num.substring(i,i+1).equals("/")||num.substring(i,i+1).equals("^")) {
                    return i;
                }
                
            }
            return num.length();
     }
     public int findIndexOfBeginningOfNumber(String num) {
         for (int i=num.length(); i>0; i--) {
             if (num.substring(i-1,i).equals("+")||num.substring(i-1,i).equals("*")||num.substring(i-1,i).equals("/")||num.substring(i-1,i).equals("^")) {
                    return i;
              }
              if ((num.substring(i-1,i).equals("-") && i==1)) {
                  return 0;
               }
              if ((num.substring(i-1,i).equals("-"))) {
                  return i;
              }
          }
          return 0;
      }    
             
    public String performCalculation(String input) {
        input=input.replaceAll("\\s+","");
        input=input.replaceAll("--","+");
        input=input.replaceAll("\\+-","-");
        input=input.replaceAll("-\\+","-");
        input=input.replaceAll("\\+\\+","+");
        if (input.substring(0,1).equals("-") && input.indexOf("^")==-1 && input.indexOf("/")==-1
           && input.indexOf("*")==-1 && input.substring(1).indexOf("-")==-1 && input.indexOf("+")==-1) {
            return input;
        } 
        if(input.indexOf("(")==-1 && input.indexOf("^")==-1 && input.indexOf("/")==-1
           && input.indexOf("*")==-1 && input.indexOf("-")==-1 && input.indexOf("+")==-1) {
               return input;
        }
        if(input.indexOf("(")!=-1) {
            int lastParen=input.length();
            for(int i=input.length(); i>0; i--) {
                if (input.substring(i-1, i).equals(")")) {
                    lastParen=i-1;
                    break;
                }   
            }
            input= input.substring(0,input.indexOf("("))+performCalculation(input.substring(input.indexOf("(")+1,lastParen))+input.substring(lastParen+1);
            
            performCalculation(input); 
        } 
        if (input.substring(0,1).equals("-") && input.indexOf("^")==-1 && input.indexOf("/")==-1
           && input.indexOf("*")==-1 && input.substring(1).indexOf("-")==-1 && input.indexOf("+")==-1) {
            performCalculation("-1*"+ input);
        } 
        System.out.println(input+"this");
        
        if (input.indexOf("^")!=-1) {
            int oper= input.indexOf("^");
            String first = (input.substring(0, oper));
            int beginOfFirst=findIndexOfBeginningOfNumber(first);
            double firstNum = Double.parseDouble(first.substring(beginOfFirst,oper));
            String begin = first.substring(0, beginOfFirst);
            String second = input.substring(oper+1);
            int endOfLast = findEndNumber(second.substring(1))+1;
            String end = second.substring(endOfLast);
            double secondNum = Double.parseDouble(second.substring(0, endOfLast));
            System.out.println("ex");
            double result= Math.pow(firstNum,secondNum);
            return performCalculation(begin+Double.toString(result)+end);
        }
        
        if (input.indexOf("*")!=-1 && (input.indexOf("*")<input.indexOf("/")||input.indexOf("/")==-1) ) {
            int oper= input.indexOf("*");
            String first = (input.substring(0, oper));
            int beginOfFirst=findIndexOfBeginningOfNumber(first);
            double firstNum = Double.parseDouble(first.substring(beginOfFirst,oper));
            String begin = first.substring(0, beginOfFirst);
            String second = input.substring(oper+1);
            int endOfLast = findEndNumber(second.substring(1))+1;
            String end = second.substring(endOfLast);
            double secondNum = Double.parseDouble(second.substring(0, endOfLast));
            System.out.println("times");
            return performCalculation(begin+ Double.toString(firstNum*secondNum)+end);
        }
        if (input.indexOf("/")!=-1) {
            int oper= input.indexOf("/");
            String first = (input.substring(0, oper));
            int beginOfFirst=findIndexOfBeginningOfNumber(first);
            double firstNum = Double.parseDouble(first.substring(beginOfFirst,oper));
            String begin = first.substring(0, beginOfFirst);
            String second = input.substring(oper+1);
            int endOfLast = findEndNumber(second.substring(1))+1;
            String end = second.substring(endOfLast);
            double secondNum = Double.parseDouble(second.substring(0, endOfLast));
            System.out.println("divided");
            return performCalculation(begin+Double.toString(firstNum/secondNum)+end);
        }
        if (input.indexOf("+")!=-1 ) {
            if (input.indexOf("-")==-1 || input.indexOf("+") < input.indexOf("-") || input.indexOf("-")==0 ) {
                int oper = input.indexOf("+");
                String first = (input.substring(0, oper));
                int beginOfFirst=findIndexOfBeginningOfNumber(first);
                double firstNum = Double.parseDouble(first.substring(beginOfFirst,oper));
                String begin = first.substring(0, beginOfFirst);
                String second = input.substring(oper+1);
                int endOfLast = findEndNumber(second.substring(1))+1;
                String end = second.substring(endOfLast);
                double secondNum = Double.parseDouble(second.substring(0, endOfLast));
                System.out.println("plus");
                return performCalculation(begin+Double.toString(firstNum+secondNum)+end);
            }
        }
        if (input.indexOf("-")!=-1 && input.substring(1).indexOf("-")!=-1) {
            if (input.indexOf("+")==-1 || input.indexOf("-") < input.indexOf("+") ) {
                int oper= input.substring(1).indexOf("-")+1;
                String first = (input.substring(0, oper));
                int beginOfFirst=findIndexOfBeginningOfNumber(first);
                double firstNum = Double.parseDouble(first.substring(beginOfFirst,oper));
                String begin = first.substring(0, beginOfFirst);
                String second = input.substring(oper+1);
                int endOfLast = findEndNumber(second.substring(1))+1;
                String end = second.substring(endOfLast);
                double secondNum = Double.parseDouble(second.substring(0, endOfLast));
                return performCalculation(begin+Double.toString(firstNum-secondNum)+end);
            }
        }
        
        return "";
    }
      
    public static void main(String[] args) {
        BasicCalculator calc = new BasicCalculator();
        //String ahhh = calc.performCalculation("-1+4");
        //String ahhhh = calc.performCalculation("7 + 5 * 2 - 30 / 6 + 100 / 5 ^ 2 - 3");
        //String ahh = calc.performCalculation("-3 ^ 2");
        //String ah = calc.performCalculation("-1 - 4");
        String a = calc.performCalculation("(-3.66)");
        String b = calc.performCalculation("(-5) ^ 2");
        //System.out.println(ahhh + "3.0");
        //System.out.println(ahhhh + "13.0");
        //System.out.println(ahh + "-9.0");
        //System.out.println(ah + "-5.0");
        System.out.println(a + "-3.66");
        System.out.println(b + "25");
    }    
}
