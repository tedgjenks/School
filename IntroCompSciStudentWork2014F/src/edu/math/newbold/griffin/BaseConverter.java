package edu.math.newbold.griffin;
import edu.jenks.dist.math.*;

public class BaseConverter extends AbstractBaseConverter{
  private String alphabet = "abcdefghijklmnopqrstuvwxyz";
  private final String decimal = ".";
  public BaseConverter(double relativeDelta){
    super(relativeDelta);
  }
  public String convertBase(String numberToConvert, int currentRadix, int newRadix){
    int def = 36;
    int sum = 0;
    int c = 0;
    String result = "";
    int length = numberToConvert.length()-1;
    for(int i = 0; i < numberToConvert.length(); i++){
      String cur = numberToConvert.substring(i,i+1);
      if(alphabet.indexOf(cur) >= 0){
        int val = alphabet.indexOf(cur) + 10;
        sum += val * Math.pow(currentRadix,length);
      }else{
        int value = Integer.parseInt(cur);
        sum += value * Math.pow(currentRadix,length);
      }
      length--;
    }
    while(Math.pow(newRadix, c) <= sum){
      c++;
    }
    int d = c -1;
    for(int j = 0; j < c; j++){
      while(sum - (def * Math.pow(newRadix, d)) < 0 ){
        def--;
      }
      if(def >= 10){
        def -= 10;
        result += alphabet.substring(def,def+1);
        def += 10;
        sum -= (def * Math.pow(newRadix,d));
        def = 36;
        d--;
      }else{
        result += def;
        sum -= (def * Math.pow(newRadix,d));
        def = 36;
        d--;
      }
    }
    return result;
  }
  private double convertToBase10(String decimalPortion, int currentRadix){
    if(currentRadix == 10){
      return Double.parseDouble(decimalPortion);
    }
    double valueInBase10 = 0.0;
    int placeValue = 1;
    for(int i = 1; i < decimalPortion.length(); i++){
      String cur = decimalPortion.substring(i,i+1);
      if(alphabet.indexOf(cur) >= 0){
        valueInBase10 += (alphabet.indexOf(cur)+10) * (1/Math.pow(currentRadix, placeValue));
      }else{
        valueInBase10 += Integer.parseInt(cur) * (1/Math.pow(currentRadix, placeValue));
      }
      placeValue++;
    }
    return valueInBase10;
  }
  public String convertBaseWithFloat(String numberToConvert, int currentRadix, int newRadix){
    String wholeNumber = convertBase(numberToConvert.substring(0,numberToConvert.indexOf(".")), currentRadix, newRadix);
    String decimalPortion = numberToConvert.substring(numberToConvert.indexOf("."));
    String result = "";
    if(!willTerminate(currentRadix,newRadix)){
      double deci = convertToBase10(decimalPortion, currentRadix);
      result = finishConversion(deci,newRadix);
    }else{
      System.out.println("EXACT");
      if(!doesOverflow(decimalPortion,currentRadix,newRadix)){
        System.out.println("Does not overflow");
        result = exactConversion(decimalPortion, currentRadix, newRadix);
      }else{
        double deci = convertToBase10(decimalPortion, currentRadix);
        result = finishConversion(deci,newRadix);
      }
    }
    for(int i = result.length(); i >= 0; i--){
      String cur = result.substring(i-1,i);
      if(cur.equals("0")){
        result = result.substring(0,i-1);
      }else{
        break;
      }
    }
    return wholeNumber + decimal + result;
  }
  private boolean doesOverflow(String num, int curr, int newR){
    String deciBase10 = convertBase(num.substring(num.indexOf(".")+1), curr, 10);
    int demnominatorCurr = (int) (Math.pow(curr, num.length()-1));
    String factorsofDemCurr = findFactor((double) demnominatorCurr);
    int count = 0;
    for(int i = 1; i <= 25; i++){
      String newFactors = findFactor(Math.pow(newR, i));
      if(newFactors.indexOf(factorsofDemCurr) != -1){
        count = i;
        break;
      }
    }
    if(Integer.parseInt(deciBase10) <= Integer.MAX_VALUE/Math.pow(newR, count)){
      return false;
    }else{
      return true;
    }
  }
  private String exactConversion(String decimal, int currentRadix, int newRadix){
    //step one: convert decimal into base 10 over currentRadix raised to its length;
    String deciBase10 = convertBase(decimal.substring(1), currentRadix, 10);
    double currentRadixDenominator = Math.pow(currentRadix, decimal.length()-1);
    //step two find newRadix demnominator
    String factorsofDemCurr = findFactor(currentRadixDenominator);
    int count = 0;
    for(int i = 1; i <= 10; i++){
      String newFactors = findFactor(Math.pow(newRadix, i));
      if(newFactors.indexOf(factorsofDemCurr) != -1){
        count = i;
        break;
      }
    }
    double newRadixDenominator = Math.pow(newRadix, count);
    int x = (int)(Double.parseDouble(deciBase10) * newRadixDenominator)/(int) currentRadixDenominator;
    System.out.println("Here is your value: " + x);
    String answer = convertBase(Integer.toString(x), 10, newRadix);
    if(Math.abs(answer.length()- count) > 0){
      int missingZero = Math.abs(answer.length()- count);
      for(int i = 0; i < missingZero; i++){
        answer = "0" + answer;
      }
    }
    return answer;
  }
  private String finishConversion(double deci, int newRadix){
    String result = "";
    int def = newRadix-1;
    int x = 1;
    while(deci > 0 && x <= 10){
      if(deci - (def *(1/(Math.pow(newRadix,x)))) >= 0){
        deci -= (def *(1/(Math.pow(newRadix,x))));
        if(def > 9){
          result += alphabet.substring(def-10, def-9);
        }else{
          result += def;
        }
        x++;
        def = newRadix -1;
      }else{
        def--;
        if(def == 0){
          result += 0;
        }
      }
    }
    return result;
  }
  public String convertBinaryToDecimal(String binaryNumber){
    int length = binaryNumber.length();
    int sum = 0;
    String answer;
    for(int i = 0; i < binaryNumber.length();i++){
      if(binaryNumber.substring(i,i+1).equals("1")){
        sum += Math.pow(2, length-1);
      }
      length--;
    }
    answer = Integer.toString(sum);
    return answer;
  }
  public boolean willTerminate(int currentRadix, int newRadix){
    int counter = 2;
    boolean found = false;
    String oldFactors = findFactor(currentRadix);
    String newFactors = findFactor(newRadix);
    if(newFactors.indexOf(oldFactors) >= 0){
      return true;
    }else{
      while(counter < 10 && found == false){
        String newString = findFactor(Math.pow(newRadix,counter));
        if(newString.indexOf(oldFactors) >= 0){
          found = true;
        }else{
          counter++;
        }
      }
      return found;
    }
  }
  private String findFactor(double number){
    String result = "";
    while (number % 2 == 0) {
        result += 2;
        number = number / 2;
    }

    double sqrtNum = Math.sqrt(number);
    for (int i = 3; i <= sqrtNum; i++) {
        while (number % i == 0) {
            result += i;
            number = number / i;
        }
    }
    if (number > 2) {
        result += (int) number;
    }
    if(result.substring(0,1).equals("1")){
      result = result.substring(1);
    }
    return result;
  }
  public String convertDecimalToBinary(String decimalNumber){
    int x = 0;
    int deci = Integer.parseInt(decimalNumber);
    String result ="";
    while(Math.pow(2, x) <= deci){
      x++;
    }
    int j = x-1;
    for(int i = 0; i < x; i++){
      if(deci - Math.pow(2, j) >= 0){
        result += "1";
        deci -= Math.pow(2,j);
      }else{
        result += "0";
      }
      j--;
    }

    if(result.substring(0,1).equals("0") && result.length() > 1){
      result = result.substring(1);
    }
    return result;
  }
  public static void main(String[] args){
    BaseConverter base = new BaseConverter(.004);
    System.out.println(base.convertBaseWithFloat("8.7527",18,6));

  }
}
