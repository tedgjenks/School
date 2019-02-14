package edu.phone.sprouse.ryan;

import edu.jenks.dist.phone.*;

public class RandomPhoneNumber
extends AbstractRandomPhoneNumber
{
    public static void main(String[] args) {
        System.out.println("Begin.");
        RandomPhoneNumber instance = new RandomPhoneNumber();
        for(int testIter = 1; testIter <= 100; testIter++) {
            String testNum = instance.generateRandomPhoneNumber();
            assert testNum.length() == 12 : "Testiter: " + testIter + "; test number: " + testNum + " ; Length is " + testNum.length();
            assert testNum.charAt(3) == '-' && testNum.charAt(7) == '-' : "Test iter" + testIter + "; test number: " + testNum + "; Hyphens misplaced";
            String areaCode = testNum.substring(0, 4);
            assert validateAreaCode(areaCode) : "Test iter: " + testIter + "; test number: " + testNum + " ; Bad area code";
        }
        System.out.println("End without error.");
    }
    
    public static boolean validateAreaCode(String areaCode) {
        for(int index = 0; index < 3; index++) {
            String num = areaCode.substring(index, index + 1);
            int digit = Integer.parseInt(num);
            if(digit > 7) 
                return false;
        }
        return true;
    }
    
    private int random(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
    
    private String generateAreaCode() {
        String ac = "";
        for(int i = 3; i > 0; i--)
            ac += random(0, 7);
        return ac;
    }
    
    private String generateExchange() {
        String ex = "";
        int firstDigit = random(0, 7);
        int secondDigit = random(0, 9);
        int thirdDigit = random(0, 9);
        if(firstDigit == 7) {
            secondDigit = random(0, 4);
        }
        if((firstDigit == 7) && (secondDigit == 4)) {
            thirdDigit = random(0, 2);
        }
        ex += firstDigit;
        ex += secondDigit;
        ex += thirdDigit;
        return ex;
    }
    
    private String generateSubscriber() {
        String sub = "";
        for(int i = 4; i > 0; i--)
            sub += random(0, 9);
        return sub;
    }
    
    public String generateRandomPhoneNumber() {
        String result = "";
        result += generateAreaCode();
        result += '-';
        result += generateExchange();
        result += '-';
        result += generateSubscriber();
        return result;
    }
}
