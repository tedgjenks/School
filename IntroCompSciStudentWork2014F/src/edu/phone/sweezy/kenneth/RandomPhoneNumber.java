package edu.phone.sweezy.kenneth;

import edu.jenks.dist.phone.*;


/**
 * Write a description of class RandomPhoneNumber here.
 *
 * @author Kenneth Blake Sweezy
 * @version 02/07/2019
 */
public class RandomPhoneNumber extends AbstractRandomPhoneNumber {
    public static void main(String[] args) {
        System.out.println("Begin Tests");
        RandomPhoneNumber instance = new RandomPhoneNumber();
        for(int testIteration = 1; testIteration <= 1000; testIteration++){
            String testNum = instance.generateRandomPhoneNumber();
            System.out.println("Currently testing " + testNum + " | Test iteration " + testIteration);
            assert testNum.length() == 12 : "Test iteration " + testIteration + ". Length of phone number " + testNum + " is " + testNum.length() + ", expected 12.";
            assert testNum.charAt(3) == '-' && testNum.charAt(7) == '-' : "Test iteration " + testIteration + ". Misplaced Hyphens";
            String areaCode = testNum.substring(0, 4);
            assert validateAreaCode(areaCode) : "Test iteration " + testIteration + ". Phone number " + testNum + ". Incorrectly formated area code.";
        }
        System.out.println("Tests completed without error");
    }

    public static boolean validateAreaCode (String areaCode) {
        for(int index = 0; index < 3; index++) {
            String numVal = areaCode.substring(index, index + 1);
            int digit = Integer.parseInt(numVal);
            if(digit > 7){
                return false;
            }
        }
        return true;
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

    private String generateAreaCode() {
        String areaCode = "";
        for(int i = 3; i > 0; i--) {
            areaCode += random(0, 7);
        }
        return areaCode;
    }

    private String generateExchange() {
        int tempRandomNumber = random(0,742);
        String exchangeNum = "";
        if(Integer.toString(tempRandomNumber).length() < 3) {
            if(Integer.toString(tempRandomNumber).length() == 2) {
                exchangeNum += "0";
                exchangeNum += tempRandomNumber;
            } else {
                exchangeNum += "00";
                exchangeNum += tempRandomNumber;
            }
        } else {
            exchangeNum += tempRandomNumber;
        }
        return exchangeNum;
    }

    private String generateSubscriber() {
        String subscriberNum = "";
        for(int i = 4; i > 0; i--) {
            subscriberNum += random(0,9);
        }
        return subscriberNum;
    }

    private int random(int min, int max){
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}
