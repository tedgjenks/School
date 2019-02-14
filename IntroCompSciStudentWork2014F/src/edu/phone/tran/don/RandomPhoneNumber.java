package edu.phone.tran.don;

import edu.jenks.dist.phone.*;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber
{
    public static void main (String[] args) {
       System.out.println("Begin");
       RandomPhoneNumber instance = new RandomPhoneNumber();
       for(int i = 0; i < 1000; i++) {
           String whatever = instance.generateRandomPhoneNumber();
           System.out.println(whatever);
        }
       
       /*
       boolean testFirst = false; 
       while(testFirst == false) {
           double firstNum = Math.random(); 
           firstNum *= 10; 
           int number = (int)firstNum;
           System.out.println(number);
           if(number <= 7) {
               System.out.println(number + "YES");
               testFirst = true;
            }
       }
       */
        /*
        for(int testIter = 0; testIter <= 100; testIter++) {
            String testNum = instance.generateRandomPhoneNumber();
            assert testNum.length() == 12 : "Test iter: " + testIter + "; test number: " + testNum + "Length is " + testNum.length(); 
            assert testNum.charAt(3) == '-' && testNum.charAt(7) == '-' : "Hyphens misplaced" ;
            String areaCode = testNum.substring(0, 3); 
            assert validateAreaCode(areaCode) : "Test iter: " + testIter + "; test number: " + testNum + "Length is " + testNum.length() + "; Bad area code";
        }
        */
        //System.out.println("End without error."); 
    }
    
    public static boolean validateAreaCode(String areaCode) {
        for(int index = 0; index < 3; index++) {
            String num = areaCode.substring(index, index + 1);
            int digit = Integer.parseInt(num);
            if(digit > 7) {
                return false;
            }
        }
        return true; 
    }
    
    private int random(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;  
    }
    
    private String generateSubscriber() {
        String sub = "";
        for(int i = 4; i > 0; i--) {
            sub += random(0,9);
        }
        return sub;
    }
    private String generateAreaCode() {
        String ac = "";
        for(int i = 3; i > 0; i--) {
            ac += random(0,7);
        }
        return ac;
    }
    private String generateExchange() {
        String exch = "";
        int firstDigit = random(0,7);
        int secondDigit = 0;
        int thirdDigit = 0;
        if(firstDigit == 7) {
            secondDigit = random(0,4); 
            if(secondDigit == 4) {
                thirdDigit = random(0,2);
            } else {
                thirdDigit = random(0,9);
            }
        } else {
            secondDigit = random(0,9);
            thirdDigit = random(0,9);
        }
        String firstNum = Integer.toString(firstDigit);
        String secondNum = Integer.toString(secondDigit);
        String thirdNum = Integer.toString(thirdDigit);
        exch += firstNum;
        exch += secondNum;
        exch += thirdNum;
        return exch;
    }
    public String generateRandomPhoneNumber() {
        String result = "";
        result += generateAreaCode();
        result += "-";
        result += generateExchange();
        result += "-";
        result += generateSubscriber(); 
        return result;
    }
}
