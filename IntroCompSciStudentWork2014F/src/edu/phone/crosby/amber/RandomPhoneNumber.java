package edu.phone.crosby.amber;

import edu.jenks.dist.phone.*;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber
{
    public static void main(String[] args) {
        System.out.println("Begin.");
        RandomPhoneNumber instance = new RandomPhoneNumber();
        for(int testIter = 1; testIter <= 10; testIter++){
        String testNum = instance.generateRandomPhoneNumber();
        System.out.println(testNum);
        assert testNum.length()== 12 : "Test iter" + testIter + "Length is";
        assert testNum. charAt(3) == '-'&& testNum.charAt(7) == '-': "Hyphens misplaced";
        String areaCode = testNum.substring(0,3);
        assert validateAreaCode(areaCode) : "Test iter" + testIter + "; test number" + testNum + "; Bad area code";
        String exchange = testNum.substring(4,7);
        assert validateExchange(exchange) : "Test iter" + testIter + "; test number" + testNum + "; Bad exchange";
    }
        System.out.println("End without error.");
    }
    public static boolean validateAreaCode(String areaCode){
        for(int index = 0; index < 3; index++){
           String area = areaCode.substring(index, index + 1);
           int digit = Integer.parseInt(area);
           if (digit > 7) {
               return false;
            }
        }
        return true;
    }
    public static boolean validateExchange(String exchange){
        int digit = Integer.parseInt(exchange);
        if (digit > 742) {
            return false;
        }
        return true;
    }
    private int random(int min, int max){
      int range = max - min + 1;  
      return (int)(Math.random() * range) + min;
    }
    private String generateAreaCode(){
        String ac = "";
        for(int i = 3; i > 0; i--){
            ac += random(0, 7);
        }
        return ac;
    }
    private String generateExchange(){
        String e = "";
        for(int i = 3; i > 0; i--){
            e += random(0,9);
        }
        int digit = Integer.parseInt(e);
        if(digit > 742){
            while(digit > 742){
                e = "";
                for(int i = 3; i > 0; i--){
                    e += random(0,9);
                }
                digit = Integer.parseInt(e);
            }
        }
        return e;
    }
    private String generateSubscriber(){
        String s = "";
        for(int i = 4; i > 0; i--){
            s += random(0,9);
        }   
        return s;
    }
    public String generateRandomPhoneNumber(){
        String result = "";
        result += generateAreaCode();
        result += "-";
        result += generateExchange();
        result += "-";
        result += generateSubscriber();
        return result;
     }
}
