package edu.math.burroughs.trent;
import edu.jenks.dist.math.*;
import java.util.*;

public class BaseConverter extends AbstractBaseConverter{
    
    public static void main(String[] args){
        System.out.println("start");
        BaseConverter test = new BaseConverter(0.00001);
        test.convertBase("9531",10, 4);
        System.out.println("end");
    }
    
    public boolean willTerminate(int currentRadix, int newRadix){
        return false;
    }
    
    public BaseConverter(double relativeDelta){
        super(relativeDelta);
    }
    
    public String convertBase(String numberToConvert,int currentRadix,int newRadix){
        String num = new String ("");
        int sum = 0;
        int pow = numberToConvert.length() - 1;
        //this converts it to base 10
        for(int i = 0; i < numberToConvert.length(); i++){
            if(!Character.isDigit(numberToConvert.charAt(i))){
                sum += (numberToConvert.charAt(i) - 55) * (Math.pow(currentRadix, pow));
                pow--;
            } else {
                sum += (numberToConvert.charAt(i) - 48) * (Math.pow(currentRadix, pow));
                pow--;
            }
        }
        System.out.println("sum: " + sum);
        
        //this converts from base 10 to givin base
        char tracker;
        while(true){
            if(sum < newRadix){
                if(sum < 10){
                    tracker = (char)((sum) + 48);
                    num = tracker + num;
                    break;
                } else {
                    tracker = (char)((sum % newRadix) + 55);
                    sum = sum/newRadix;
                    num = tracker + num;
                }
            }
            if(sum % newRadix < 10){
                tracker = (char)((sum % newRadix) + 48);
                sum = sum / newRadix;
                num = tracker + num;
            } else {
                tracker = (char)((sum % newRadix) + 55);
                sum = sum/newRadix;
                num = tracker + num;
            }
        }
        System.out.println("num: " + num);
        return num;
    }
    
    public String convertBaseWithFloat(String numberToConvert, int currentRadix,int newRadix){
        String baseWithFloat = new String ("");
        
        return baseWithFloat;
    }
    
    public String convertBinaryToDecimal(String binaryNumber){
        int pow = 1, sum = 0;
        for(int i = binaryNumber.length() - 1; i >= 0; i--){
            if(binaryNumber.charAt(i) == '1'){
                sum += pow;
            }
            pow *= 2;
        }
        return new String (sum + "");
    }
    
    public String convertDecimalToBinary(String decimalNumber){
        String sum = "";
        int pow = 1, decNum = Integer.parseInt(decimalNumber);
        while(pow < decNum){
            pow *= 2;
        }
        if(pow > decNum){
            pow /= 2;
        }
        int count = 0;
        for(int i = 1; i <= pow; i*=2){
            count++;
        }
        int[] arr = new int[count];
        arr[0] = 1;
        decNum -= pow;
        System.out.println("decNum: " + decNum + " pow: " + pow);
        for(int i = 1; i < arr.length; i++){
            pow /= 2;
            if(pow <= decNum){
                arr[i] = 1;
                decNum -= pow;
            }
        }
        System.out.println(Arrays.toString(arr));
        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }
        System.out.println(sum);
        return new String (sum + "");
    }
}
