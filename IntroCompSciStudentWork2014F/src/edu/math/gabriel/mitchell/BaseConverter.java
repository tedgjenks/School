package edu.math.gabriel.mitchell;
import java.util.*;
import edu.jenks.dist.math.*;
public class BaseConverter extends AbstractBaseConverter
{
    private String Alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public BaseConverter(double relativeDelta){
        super(relativeDelta);
    }
    public String convertBase(String numberToConvert, int currentRadix, int newRadix){
        return null;
    }
    public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix){
        return null;
    }
    public String convertBinaryToDecimal(String binaryNumber){
        int decValue = 0;
        int k = 0;
        for(int i = binaryNumber.length()-1; i >= 0; i--){
            String lonelyBin = binaryNumber.substring(i,i+1);
            decValue += Integer.parseInt(lonelyBin) * Math.pow(2,k);
            k++;
        }
        return Integer.toString(decValue);
    }
    public String convertDecimalToBinary(String decimalNumber){
        StringBuilder binValue = new StringBuilder();
        int decInt = Integer.parseInt(decimalNumber);
        while(decInt >= 1){
            if(decInt % 2 == 0){
                binValue.insert(0,"0");
            }else {
                binValue.insert(0, "1");
            }
            decInt = decInt/2;
        }
        return binValue.toString();
    }
    public boolean willTerminate(int currentRadix, int newRadix){
        return false;
    }
    private int alphaVal(String a){
        return Alpha.indexOf(a.toUpperCase()) + 10;
    }
}
