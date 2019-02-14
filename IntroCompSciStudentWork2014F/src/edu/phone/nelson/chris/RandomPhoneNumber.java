/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.phone.nelson.chris;

import edu.jenks.dist.phone.*;

        
/**
 *
 * @author chris
 */
public class RandomPhoneNumber extends AbstractRandomPhoneNumber{

    /**
     * @param args the command line arguments
     *  
     */
   
    public static void main(String[] args) {
        RandomPhoneNumber random = new RandomPhoneNumber();
        int numIncorrect = 0;
        for(int i = 0; i<1000; i++){
            numIncorrect++;
            
            String testNum = random.generateRandomPhoneNumber();
            String areaCode = testNum.substring(0,3);
            String exchangeCode = testNum.substring(4,7);
            String stationCode = testNum.substring(8,12);
            
            assert testNum.charAt(3)=='-' &&  testNum.charAt(7)=='-': "Incorrect - " + testNum + " Num: " + numIncorrect;
            assert !areaCode.contains("8") || !areaCode.contains("9"): "8 or 9 in area code" + testNum + " Num: " + numIncorrect;
            
        }
        System.out.println(random.generateRandomPhoneNumber());
    }

    /**
     *
     * @return
     */
    
    @Override
    public String generateRandomPhoneNumber() {
        
        String areaCodeInt = "8";
        while(areaCodeInt.contains("8") || areaCodeInt.contains("9")){
            double areaCode = Math.random() * 778; 
            String tempAreaCode = Integer.toString((int) areaCode);
            
                if(tempAreaCode.length()==2){
                    areaCodeInt = "0" + tempAreaCode;
                }else if(tempAreaCode.length()==1){
                    areaCodeInt = "00" + tempAreaCode;
                }else{
                    areaCodeInt = tempAreaCode; 
                }
        }
         
        double exchangeCodeInt = Math.random() * 743; 
        String tempExchangeCode = Integer.toString((int) exchangeCodeInt);
        String exchangeCode = "0";
        if(tempExchangeCode.length()==2){
                    exchangeCode = "0" + tempExchangeCode;
                }else if(tempExchangeCode.length()==1){
                    exchangeCode = "00" + tempExchangeCode;
                }else{
                    exchangeCode = tempExchangeCode; 
                }
        
        double stationCodeInt = Math.random() * 10000; 
        String stationCode = "0";
        String tempStationCode = "" + Integer.toString((int) stationCodeInt);
        
        if(tempStationCode.length()==2){
                stationCode = "00" + tempStationCode;
            }else if(tempStationCode.length()==1){
                stationCode = "000" + tempStationCode;
            }else if(tempStationCode.length()==3){
                stationCode = "0" + tempStationCode;
            }else{
                stationCode = tempStationCode; 
            }
        
        String phoneNumber = areaCodeInt + "-" + exchangeCode + "-"+ stationCode; 
        return phoneNumber;
    }
    
}
