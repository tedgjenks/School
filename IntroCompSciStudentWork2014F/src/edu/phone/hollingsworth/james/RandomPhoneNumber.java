  package edu.phone.hollingsworth.james;
  import edu.jenks.dist.phone.*;

public class RandomPhoneNumber extends AbstractRandomPhoneNumber
{
    public static void main(String[] args) {
        System.out.println("Beginning...");
        RandomPhoneNumber phoneNumber = new RandomPhoneNumber();
        for(int i = 0; i < 10; i++)
            System.out.println(phoneNumber.generateRandomPhoneNumber());
        System.out.println("Finished without errors!");
    }
    
    public String generateRandomPhoneNumber() {
        int areaCode1 = (int) (Math.random() * 8);
        int areaCode2 = (int) (Math.random() * 8);
        int areaCode3 = (int) (Math.random() * 8);
        while(areaCode1 > 7) areaCode1 = (int) (Math.random() * 8);
        while(areaCode2 > 7) areaCode2 = (int) (Math.random() * 8);
        while(areaCode3 > 7) areaCode3 = (int) (Math.random() * 8);
        String areaCode = Integer.toString(areaCode1) + Integer.toString(areaCode2) + Integer.toString(areaCode3);
        if(areaCode.length() < 2) areaCode = "0" + areaCode;
        if(areaCode.length() < 3) areaCode = "0" + areaCode;
        String exchange = Integer.toString((int) (Math.random() * 743));
        if(exchange.length() < 2) exchange = "0" + exchange;
        if(exchange.length() < 3) exchange = "0" + exchange;
        String finalPart = Integer.toString((int) (Math.random() * 10000));
        if(finalPart.length() < 2) finalPart = "0" + finalPart;
        if(finalPart.length() < 3) finalPart = "0" + finalPart;
        if(finalPart.length() < 4) finalPart = "0" + finalPart;
        return areaCode + "-" + exchange + "-" + finalPart;
    }
}
