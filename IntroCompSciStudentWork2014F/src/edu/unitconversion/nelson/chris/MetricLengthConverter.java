/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unitconversion.nelson.chris;
import edu.jenks.dist.unitconversion.*;
/**
 *
 * @author chris
 */
public class MetricLengthConverter implements Convertible {

    /**
     * @param args the command line arguments
     */
    private static String[] units = { "nm","um","mm","cm","dm","dam","hm","km" ,"Mm","Gm" };
    private static int[] unitExponent = {-9, -6, -3, -2, -1, 1, 2, 3, 6, 9};
    public static void main(String[] args) {
        MetricLengthConverter test = new MetricLengthConverter();
        System.out.println(test.convertForDisplay(1, "m","m"));
    }
    public double convertToMeters(double numberUnits, String unit){
        int exp = 0;
        for(int i = 0; i<units.length; i++){
            if(unit.equals(units[i])){
                exp = unitExponent[i];
                break;
            }
        }
        double conversion = numberUnits * Math.pow(10, exp);
        return conversion; 
    }
    public double convertFromMeters(double numberMeters, String unit){
        int exp = 0;
        for(int i = 0; i<units.length; i++){
            if(unit.equals(units[i])){
                exp = unitExponent[i];
                break;
            }
        }
        double conversion = numberMeters / Math.pow(10, exp);
        return conversion; 
    }
    public double convert(double numberStartingUnits,String startingUnit,String desiredUnit){
        int startExp = 0;
        int desiredExp = 0;
        
        for(int i = 0; i<units.length; i++){
            if(startingUnit.equals(units[i])){
                startExp = unitExponent[i]; 
            }
            if(desiredUnit.equals(units[i])){
                desiredExp = unitExponent[i];
            }
            
        }
        double convertToMeters = numberStartingUnits * Math.pow(10,startExp);
        double convertFromMeters = convertToMeters / Math.pow(10, desiredExp);
        return convertFromMeters;   
    }
    public String convertForDisplay(double numberStartingUnits,String startingUnit, String desiredUnit){
        int startExp = 0;
        int desiredExp = 0;
        
        for(int i = 0; i<units.length; i++){
            if(startingUnit.equals(units[i])){
                startExp = unitExponent[i]; 
            }
            if(desiredUnit.equals(units[i])){
                desiredExp = unitExponent[i];
            }     
        }
        if((startExp == 0 && !startingUnit.equals("m")) || (desiredExp == 0 &&!desiredUnit.equals("m"))){
                return "Unit not supported.";
        }
        double convertToMeters = numberStartingUnits * Math.pow(10,startExp);
        double convertFromMeters = convertToMeters / Math.pow(10, desiredExp);
        return numberStartingUnits + " " + startingUnit + " = "+ convertFromMeters + " " + desiredUnit + ".";      
    }   
}
