package edu.math.creswell.jasmine;
import edu.jenks.dist.math.*;


public class BaseConverter extends AbstractBaseConverter
{
    public BaseConverter(double relativeDelta) {
        super(relativeDelta);
        
    }
    public String convertBase(String numberToConvert,int currentRadix,int newRadix) {
        int convertedToDecimal = 0;
        String convertedToNewBase="";
        int numOfPlaces;
        int place;
        String iWantToDie= "0123456789abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<numberToConvert.length(); i++) {
            convertedToDecimal+=(iWantToDie.indexOf(numberToConvert.charAt(i))) * Math.pow(currentRadix, (numberToConvert.length()-i-1));
        }
        System.out.println("1."+ convertedToDecimal);
        numOfPlaces=((int)(Math.log(convertedToDecimal)))/((int)(Math.log(newRadix)))+1;
        System.out.println(numOfPlaces);
        for (int i=0; i<numOfPlaces+1; i++) {
            place= convertedToDecimal/(int) Math.pow(newRadix, numOfPlaces-i);
            convertedToNewBase+=iWantToDie.substring(place, place+1);
            convertedToDecimal-=(Math.pow(newRadix, numOfPlaces-i)* place);
        }
        int findFirstNonZero=0;
        for (int i=0; i<convertedToNewBase.length(); i++) {
            if (convertedToNewBase.substring(i,i+1).equals("0")) {
                continue;
            } else {
                findFirstNonZero=i;
                break;
            }
        }
        System.out.println(findFirstNonZero);
        convertedToNewBase= convertedToNewBase.substring(findFirstNonZero);
        return convertedToNewBase;
    }
    public String convertBaseWithFloat(String numberToConvert,int currentRadix,int newRadix) {
 
        int decimalPlace= numberToConvert.indexOf(".");
        String whole = numberToConvert.substring(0, decimalPlace);
        String decimal= numberToConvert.substring(decimalPlace+1);
        int numDecimalPlaces = decimal.length();
        String returnedWhole = convertBase(whole,currentRadix, newRadix);
        String returnedDecimal = convertBase(decimal,currentRadix, 10);
        System.out.println("decimal" + decimal);
        System.out.println("returnedDecimal" + returnedDecimal);
        int convertedToDecimal=0;
        String iWantToDie= "0123456789abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<returnedDecimal.length(); i++) {
            convertedToDecimal+=(iWantToDie.indexOf(returnedDecimal.charAt(i))) * Math.pow(currentRadix, (returnedDecimal.length()-i-1));
        }
        System.out.println("Decimal converted to num:"+convertedToDecimal);
        double decimalInBase = convertedToDecimal/ Math.pow(currentRadix,(decimal.length()));
        System.out.println(decimalInBase);
        String returned= returnedWhole + "." +decimalInBase;
        int remove= returned.indexOf("0.");
        return returned.substring(0, remove) + returned.substring(remove+2);
        
    }
    public boolean willTerminate(int currentRadix, int newRadix) {
        return false;
    }
    public String convertBinaryToDecimal(String binaryNumber) {
        int decimalNum = 0;
        for (int i=0; i<binaryNumber.length(); i++) {
            if (binaryNumber.charAt(i) == '1') {
                decimalNum+= Math.pow(2, (binaryNumber.length()-1-i));
            } 
        }
        return String.valueOf(decimalNum);
        
    }
    public String convertDecimalToBinary(String decimalNumber) {
        int num = Integer.parseInt(decimalNumber);
         boolean even=false;
        if (num%2==0) {
            even=true;
        }
        String bin="";
        for (int i=32; i>=0; i--) {
            if ((num-(Math.pow(2, i))) >= 0) {
                bin += "1";
                num-= Math.pow(2, i);
            } else {
                bin+="0";
            }
        }
        int index = bin.indexOf("1");
        if (even) {
            bin= bin.substring(index);
            return bin;
        } else {
            bin=bin.substring(index,bin.length()-1);
            bin+="1";
            return bin;
        }
    }
    public static void main(String[] args) {
        BaseConverter test = new BaseConverter(0);
        //String dec = test.convertBinaryToDecimal("010011");
        //System.out.println(dec);
        //String deci = test.convertDecimalToBinary("64");
        //System.out.println(deci);
        //String convertBaseToBase= test.convertBase("558", 22, 4);
        //System.out.println(convertBaseToBase);
        System.out.println(test.convertBaseWithFloat("10.011", 2, 10));
    }
}
