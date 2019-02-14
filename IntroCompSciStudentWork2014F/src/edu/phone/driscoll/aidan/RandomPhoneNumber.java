package edu.phone.driscoll.aidan;

import edu.jenks.dist.phone.*;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber
{   
   public static void main(String[] args){
       System.out.println("Begin");
       RandomPhoneNumber instance = new RandomPhoneNumber();
        for(int testIter = 1; testIter <= 10; testIter++){
            String testNum = instance.generateRandomPhoneNumber();
            System.out.println(testNum);
            assert testNum.length() == 12 : "Test iter " + testIter + " Length is " + testNum.length();
            assert testNum.charAt(3) == '-' && testNum.charAt(7) == '-' :  "Test iter " + testIter + " Hyphens Misplaced";
            String areaCode = testNum.substring(0, 3);
            String secondSet = testNum.substring(4, 7);
            assert validateAreaCode(areaCode) : "Test iter " + testIter + " Bad Area Code";
            assert validateSecondNumbers(secondSet) : "Test iter " + testIter + " Too Large";
        }
       System.out.println("done");
    }
   public static boolean validateAreaCode(String areaCode) {
        for(int index = 0; index < 3; index++){
            String num = areaCode.substring(index, index + 1);
            int digit = Integer.parseInt(num);
            if(digit > 7){
                return false;
            }
    }
    return true; 
    }
   public static boolean validateSecondNumbers(String secondSet){
     int digit = Integer.parseInt(secondSet);
       if(digit > 742){
           return false;
       }
     return true;
   }
   
   private int random(int min, int max) {
       int range = max - min + 1;
       return (int)(Math.random() * range) + min;
   }
   
   private String generateAreaCode(){
       String ac = "";
       for(int i = 3; i > 0; i--){
           ac+= random(0, 7);
       }
       return ac;
   }
   
   private String generateSecondNumbers(){
       String sn = "";
       sn += random(0, 742);
       if(sn.length() < 3){
           int digit = 3 - sn.length();
           if(digit == 2){
               sn = "00" + sn;
           }
           if(digit == 1){
               sn = "0" + sn;
            }
       }
       
       return sn;
   }
   
   private String generateSubscriber(){
       String s = "";
       for(int i = 4; i > 0; i--){
           s+= random(0, 9);
       }
       return s;
   }
   
   public String generateRandomPhoneNumber() {
        String result = "";
        result += generateAreaCode();
        result += '-';
        result += generateSecondNumbers();
        result += '-';
        result += generateSubscriber();
        return result;
   }
}   