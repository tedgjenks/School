package edu.math.salter.bella;
import edu.jenks.dist.math.*;
import java.util.*;
    public class BaseConverter extends AbstractBaseConverter
{
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        System.out.println("Begin test of BaseConverter");
        
        //begin test of convertDecimalToBinary
        BaseConverter tester = new BaseConverter(0.1);
        assert tester.convertDecimalToBinary("55").equals(Integer.toBinaryString(55)): "Error: call to dectobinary returning incorrect answer for odd case";
        assert tester.convertDecimalToBinary("1756").equals(Integer.toBinaryString(1756)): "Error: call to dectobinary returning incorrect answer for even case";
        //begin test of convertBinaryToDecimal
        assert tester.convertBinaryToDecimal("110110011").equals("435") : "Error: call to binarytodec returning incorrect answer for odd case";
        assert tester.convertBinaryToDecimal("1001001011110").equals("4702") : "Error: call to binarytodc returning incorrect answer for even case";
        
        //begin test of converttodec
        System.out.println(tester.convertToDecimal("1100110011111",2));
        //System.out.println(tester.convertBinaryToDecimal("100000000110"));
        //assert tester.convertToDecimal("100000000110",2).equals(tester.convertBinaryToDecimal("100000000110")) : "Error: u big dumb";
        System.out.println(tester.convertBaseWithFloat("10.1", 2,10));
        System.out.println("End test of BaseConverter");
    }
    public BaseConverter(double relativeDelta) {
        super(relativeDelta);
    }
    public String convertDecimalToBinary(String decimalNumber){
        return convertFromDecimal(decimalNumber, 2);
    }
    public String convertBinaryToDecimal(String binaryNumber) {
        return convertToDecimal(binaryNumber, 2);
    }
    public String convertBase(String numberToConvert, int currentRadix, int newRadix) {
        if(currentRadix == 10) {
            return convertFromDecimal(numberToConvert, newRadix);
        }else if(newRadix == 10) {
            return convertToDecimal(numberToConvert,newRadix);
        }else{
            return convertFromDecimal(convertToDecimal(numberToConvert,currentRadix), newRadix);
        }
    }
    public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix) {
        int indOfDecimal = numberToConvert.indexOf(".");
        String beforeDec = new String();
        beforeDec = numberToConvert.substring(0, indOfDecimal);
        
       String afterDec = new String();
       afterDec = numberToConvert.substring(indOfDecimal + 1, numberToConvert.length());
         //System.out.println(afterDec);
       afterDec = reverse(afterDec);
       System.out.println(afterDec);
        if(willTerminate(currentRadix, newRadix)) {
            int evenRad = getCommonDenominator(currentRadix, newRadix);
            System.out.println("evenRad: " + evenRad);
            List<Integer> listRemainders = new ArrayList<Integer>();
            
            beforeDec = convertFromDecimal(convertToDecimal(beforeDec, currentRadix), evenRad);
            //System.out.println(convertToDecimal(afterDec, currentRadix));
            afterDec = convertFromDecimal(convertAfterDecimalToDec(afterDec, currentRadix), evenRad);
            
            beforeDec = convertFromDecimal(convertToDecimal(beforeDec, evenRad), newRadix);
            afterDec = convertFromDecimal(convertToDecimal(afterDec, evenRad), newRadix);
            afterDec = reverse(afterDec);
        }else{
            beforeDec = convertFromDecimal(convertToDecimal(numberToConvert.substring(0,indOfDecimal), currentRadix), newRadix);
            afterDec = convertFromDecimal(convertToDecimal(reverse(numberToConvert.substring(indOfDecimal + 1, numberToConvert.length())), currentRadix), newRadix);
            afterDec = reverse(afterDec);
        }
        String returnSTR = new String();
            returnSTR = beforeDec + "." + afterDec;
            return returnSTR;
    }
    public String toString(ArrayList<Object> list) {
        //puts list in string in reverse order
        String str = new String();
        for(int i = list.size() -1; i >= 0; i--) {
            str = str + list.get(i);
        }
        return str;
    }
    public String convertToDecimal(String number, int currentRad) {
        int numToAdd;
        int decNum = 0;
        String str = "";
        for(int i = 0; i < number.length(); i++) {
            str = number.substring(i, i+1);
            int num;
            if(alphabet.indexOf(str) > -1) {
                num = alphabet.indexOf(str) + 10;
            }else{
                num = Integer.parseInt(str);
            }
            numToAdd = num * (int)Math.pow(currentRad, number.length() - i -1);
            decNum += numToAdd;
        }
        String finalSTR = new String();
        finalSTR = "";
        finalSTR = finalSTR + decNum;
        return finalSTR;
    }
    public String convertFromDecimal(String number, int newRad) {
        String endNum = new String();
        ArrayList<Object> endNumList = new ArrayList<>();
        //the arraylist is in reverse order but it is handled in toString
        int dec = Integer.parseInt(number);
        int count = 0;
        while(dec  > 0 && count < 100) {
            if(dec % newRad > 9){
                int i = (dec % newRad) - 9 - 1;
                endNumList.add(alphabet.substring(i, i+1));
            } else {
                endNumList.add(dec % newRad);
            }
            dec /= newRad;
            count++;
        }
        if(dec < newRad && dec != 0) {
            if(dec > 9) {
                endNumList.add(alphabet.substring(dec - 9 - 1, dec -9));
            }
            endNumList.add(dec);
        }
        
        return toString(endNumList);
    }
    public boolean willTerminate(int currentRadix, int newRadix) {
        
        if((currentRadix % newRadix == 0 && (currentRadix / newRadix) % newRadix == 0 ) || (newRadix % currentRadix == 0 && (newRadix/ currentRadix) % currentRadix ==0)) {
          return true;
        } else {
          ArrayList<Integer> factorsOfCurrent = getFactors(currentRadix);
          ArrayList<Integer> factorsOfNew = getFactors(newRadix);
          for(int i = 0; i < factorsOfCurrent.size(); i++) {
              for(int o = 0; o < factorsOfNew.size(); o++){
                  if(factorsOfCurrent.get(i) == factorsOfNew.get(o)) {
                      if(currentRadix <= newRadix)
                        return true;
                    }
                }
            }
          return false;
    }
}
public ArrayList<Integer> getFactors(int number) {
   ArrayList<Integer> factors = new ArrayList<>();
        for(int i = 1; i <= number; i++){
            if(number % i == 0) {
                factors.add(i);
            }
        }
   return factors;
 }

public String reverse(String str){
    String newSTR = new String();
    for(int i = str.length() - 1; i > -1; i--) {
        newSTR += str.substring(i, i+1);
    }
    return newSTR;
}
public int getCommonDenominator(int num1, int num2){
    ArrayList<Integer> factorsOfCurrent = getFactors(num1);
    System.out.println(factorsOfCurrent);
          ArrayList<Integer> factorsOfNew = getFactors(num2);
          System.out.println(factorsOfNew);
          for(int i = factorsOfCurrent.size() -1; i > -1; i--) {
                  if(factorsOfNew.indexOf(factorsOfCurrent.get(i))> -1) {
                      return factorsOfCurrent.get(i);
                    }
                
            }
            return -1;
}
public String convertAfterDecimalToDec(String afterDec, int currentRadix) {
    double numToAdd;
        int decNum = 0;
        String str = "";
        for(int i = 0; i < afterDec.length(); i++) {
            str = afterDec.substring(i, i+1);
            int num;
            if(alphabet.indexOf(str) > -1) {
                num = alphabet.indexOf(str) + 10;
            }else{
                num = Integer.parseInt(str);
            }
            numToAdd = num * (int)(10/ (Math.pow(currentRadix, i + 1)));
            decNum += numToAdd;
        }
        String finalSTR = new String();
        finalSTR = "";
        finalSTR = finalSTR + decNum;
        System.out.println(finalSTR);
        return finalSTR;
}


}


