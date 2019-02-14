package edu.phone.macias.marcus;

import edu.jenks.dist.phone.*;


public class RandomPhoneNumber extends AbstractRandomPhoneNumber
{
    public static void main(String[] args){
        System.out.println("Begin");
        //System.out.println(random());
        random(2);
        RandomPhoneNumber instance = new RandomPhoneNumber();
        
        for(int i = 0 ; i < 1000 ; i++){
            String testNum = instance.generateRandomPhoneNumber();
            assert testNum.length() == 12: "Lenth is " + testNum.length();
            assert testNum.charAt(3) == '-' && testNum.charAt(7) == '-': "Hyphens Missplased";
            String areaCode = testNum.substring(0,4);
            assert validateAreaCode(areaCode): "Wrong";
        }
        System.out.println("End without Error.");
    }
    public static String random(int x){
        int max = x; 
        int min = 0; 
        int range = max - min + 1;
        int rand = (int)(Math.random() * range) + min;
        if(rand < 100 && x > 10 && rand > 9){
            String string2 = "0";
            string2 += rand;
            return string2;
        }
        if(rand < 10 && x > 10){
            String string3 = "00";
            string3 += rand;
            return string3;
        }
        String string = "";
        string += rand;
        return string;
    }
    public static boolean validateAreaCode(String areaCode){
        for(int index = 0 ; index < 3; index++){
            String num = areaCode.substring(index , index + 1);
            int digit = Integer.parseInt(num);
            if(digit > 7){
                return false;
            }
        }
        return true;
    }
    private String areaCode(){
        String string = "";
        int x = 7;
        for(int i = 0; i < 3; i++){
           String num = random(x);
           string += num;
        }
        string += "-";
        return string;
    }
    private String exchange(){
        String string = "";
        String num2 = random(742);
        string += num2;
        string += "-";
        return string;
    }
    private String lastNumbers(){
        String string = "";
        for(int i = 0; i < 4; i++){
            String num = random(9);
            string += num;
        }
        return string;
    }
    
    public String generateRandomPhoneNumber(){
        String phoneNumber = "";
        
        phoneNumber += areaCode();
        phoneNumber += exchange();
        phoneNumber += lastNumbers();
        
        System.out.println(phoneNumber);

        return phoneNumber;
    }
}
