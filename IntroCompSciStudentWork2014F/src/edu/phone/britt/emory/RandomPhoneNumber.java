package edu.phone.britt.emory;

import edu.jenks.dist.phone.*;

/**
 * Write a description of class RandomPhoneNumber here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomPhoneNumber extends AbstractRandomPhoneNumber
{
   public static void main(String[] args){
        System.out.println("Begin");
        RandomPhoneNumber instance = new RandomPhoneNumber();
        for(int testIter = 1; testIter <= 10; testIter++){
            String testNum = instance.generateRandomPhoneNumber();
            System.out.println("number: " + testNum);
            assert testNum.length() == 12 :"Test iteration: " + testIter + " Test number: " + testNum + " length is " + testNum.length(); 
            assert testNum.charAt(3) == '-' && testNum.charAt(7) == '-' :"Test iteration: " + testIter + " Test number: " + testNum + " hyphon misplaced"; 
            String areaCode = testNum.substring(0, 4);
            assert validateAreaCode(areaCode) : "Test iteration: " + testIter + " Test number: " + testNum + " bad area code";
        }
        System.out.println("End without error");
   }
   public static boolean validateAreaCode(String areaCode){
       for(int index = 0; index < 3; index++){
           String parameter = areaCode.substring(index, index + 1);
           int digit = Integer.parseInt(parameter);
           if(digit > 7)
                return false;
        }  
        return true;
   }
   public String generateRandomPhoneNumber() {
       String areaCode = generateRandomNumber(8, 3, 0);
       String subscriber = generateRandomNumber(10, 4, 0); 
       String exchange = generateRandomNumber(10, 3, 0);
       String parameterExch = exchange.substring(0, exchange.length());
       int exchNum = Integer.parseInt(parameterExch);
       if(exchNum >= 743){
           while(exchNum >= 743){
               exchange = generateRandomNumber(9, 3, 0);
               parameterExch = exchange.substring(0, exchange.length());
               exchNum = Integer.parseInt(parameterExch);
           }
       }
       return areaCode + "-" + exchange + "-" + subscriber;
   }    
   public String generateRandomNumber(int max, int nums, int min){
       String returnVal = "";
       int range = max - min;
       for(int i = 0; i < nums; i++){
           int num = (int)(Math.random() * range) + min;
           returnVal += num; 
       }
       return returnVal; 
   }
}
    
