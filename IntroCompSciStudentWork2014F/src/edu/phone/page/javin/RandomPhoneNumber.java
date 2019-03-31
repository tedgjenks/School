package edu.phone.page.javin;

import edu.jenks.dist.phone.*;

/**
 * Write a description of class RandomPhoneNumber here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomPhoneNumber extends AbstractRandomPhoneNumber
{   
    public void main(){
        for(int i = 10; i > 0; i--){
            System.out.println(generateRandomPhoneNumber());
        }
    }
    public String generateRandomPhoneNumber(){
        String phoneNumber;
        String areaCode = "";
        for(int n = 0; n < 3; n++){
            int i = random(0, 7);
            areaCode += Integer.toString(i);
        }
        phoneNumber = areaCode + "-" + generateExchange() + "-" + generateSubscriber();;
        return phoneNumber;
    }
    private String generateExchange(){
        int l = random(0, 7);
        int a;
        int x;
        if (l == 7){
            a = random(0, 4);
        }else {
            a = random(0, 9);
        }  
        if(l == 7 && a == 4){
            x = random(0, 2);
        }else {
            x = random(0, 9);
        }
        return Integer.toString(l) + Integer.toString(a) + Integer.toString(x);
    }
    private String generateSubscriber(){
        String subscriber = "";
        for(int i = 0; i < 4; i++){
            int f = random(0, 9);
            subscriber += Integer.toString(f);
        }
        return subscriber;
    }
    private int random(int min, int max){
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }
}
