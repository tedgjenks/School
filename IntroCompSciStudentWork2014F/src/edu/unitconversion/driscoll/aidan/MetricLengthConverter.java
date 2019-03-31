package edu.unitconversion.driscoll.aidan;

import edu.jenks.dist.unitconversion.*;

/**
 * Write a description of class MetricLengthConverter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MetricLengthConverter implements Convertible
{
    public static void main (String[] args){
        System.out.println("Begin");
        MetricLengthConverter pc = new MetricLengthConverter();
        System.out.println(pc.convertToMeters(3, "dam"));
        System.out.println(pc.convertFromMeters(3, "Gm"));
        System.out.println(pc.convert(3, "Gm", "dm"));
        System.out.println(pc.convertForDisplay(3, "bm", "Gm"));
        System.out.println("End");
    }
    
    public double convertToMeters(double numberUnits, String unit){
        double result = 0;
        if(unit == "Gm"){
          result = Math.pow(10, 9) * numberUnits;  
        }
        if(unit == "Mm"){
          result = Math.pow(10, 6) * numberUnits;  
        }
        if(unit == "km"){
          result = Math.pow(10, 3) * numberUnits;  
        }
        if(unit == "hm"){
          result = Math.pow(10, 2) * numberUnits;  
        }
        if(unit == "dam"){
          result = Math.pow(10, 1) * numberUnits;  
        }
        if(unit == "m"){
          result = numberUnits;
        }
        if(unit == "dm"){
          result = Math.pow(10, -1) * numberUnits;  
        }
        if(unit == "cm"){
          result = Math.pow(10, -2) * numberUnits;  
        }
        if(unit == "mm"){
          result = Math.pow(10, -3) * numberUnits;  
        }
        if(unit == "um"){
          result = Math.pow(10, -6) * numberUnits;  
        }
        if(unit == "nm"){
          result = Math.pow(10, -9) * numberUnits;  
        }
        return result;
    }
    
    public double convertFromMeters(double numberUnits, String unit){
        double result = 0;
        if(unit == "Gm"){
          result = numberUnits / Math.pow(10, 9);  
        }
        if(unit == "Mm"){
          result = numberUnits / Math.pow(10, 6);  
        }
        if(unit == "km"){
          result = numberUnits / Math.pow(10, 3);  
        }
        if(unit == "hm"){
          result = numberUnits / Math.pow(10, 2);  
        }
        if(unit == "dam"){
          result = numberUnits / Math.pow(10, 1);  
        }
        if(unit == "m"){
          result = numberUnits;
        }
        if(unit == "dm"){
          result = numberUnits / Math.pow(10, -1);  
        }
        if(unit == "cm"){
          result = numberUnits / Math.pow(10, -2);  
        }
        if(unit == "mm"){
          result = numberUnits / Math.pow(10, -3);  
        }
        if(unit == "um"){
          result = numberUnits / Math.pow(10, -6);  
        }
        if(unit == "nm"){
          result = numberUnits / Math.pow(10, -9);  
        }
        return result;
    }
    
    public double convert(double numberStartingUnits, String startingunit, String desiredUnit){
       double units = convertToMeters(numberStartingUnits, startingunit);
       double meter = convertFromMeters(units, desiredUnit);
       return meter; 
    }
    
    public String convertForDisplay(double numberStartingUnits, String startingunit, String desiredUnit){
       double returnValue = convert(numberStartingUnits, startingunit, desiredUnit);
       String num = Double.toString(numberStartingUnits);
       String nums = Double.toString(returnValue);
       String[] unit = {"Gm", "Mm", "km", "hm", "dam", "m", "dm", "cm", "mm", "um", "nm"};
       int count = 0;
       int counts = 0;
       for(int i = 0; i <= unit.length - 1; i++){
           if(!unit[i].equals(startingunit)){
               count = count + 1;
           }
           if(!unit[i].equals(desiredUnit)){
               counts = counts + 1;
           }
       }
       if(count == unit.length || counts == unit.length){
           return "Unit not supported.";
       }else{
           return num + " " + startingunit + " = " + nums + " " + desiredUnit + ".";
       }
    }
}
