package edu.unitconversion.crosby.amber;

import edu.jenks.dist.unitconversion.*;


public class MetricLengthConverter implements Convertible
{
    public static void main(String[] args){
        System.out.println("Begin");
        
        System.out.println("End without error");
    }
    
    public double convertToMeters(double numberUnits, String unit){
        double converted = 0;
        if (unit == "Gm"){
            converted = numberUnits * Math.pow(10,9);
        }else if (unit == "Mm"){
            converted = numberUnits * Math.pow(10,6);
        }else if (unit == "km"){
            converted = numberUnits * Math.pow(10,3);
        }else if (unit == "hm"){
            converted = numberUnits * Math.pow(10,2);
        }else if (unit == "dam"){
            converted = numberUnits * Math.pow(10,1);
        }else if (unit == "m"){
            converted = numberUnits;
        }else if (unit == "dm"){
            converted = numberUnits * Math.pow(10,-1);
        }else if (unit == "cm"){
            converted = numberUnits * Math.pow(10,-2);
        }else if (unit == "mm"){
            converted = numberUnits * Math.pow(10,-3);
        }else if (unit == "um"){
            converted = numberUnits * Math.pow(10,-6);
        }else if (unit == "nm"){
            converted = numberUnits * Math.pow(10,-9);
        }else{
            converted = 0;
        }
        return converted;
    }
    
    public double convertFromMeters(double numberUnits, String unit){
        double converted = 0;
        if (unit == "Gm"){
            converted = numberUnits / Math.pow(10,9);
        }else if (unit == "Mm"){
            converted = numberUnits / Math.pow(10,6);
        }else if (unit == "km"){
            converted = numberUnits / Math.pow(10,3);
        }else if (unit == "hm"){
            converted = numberUnits / Math.pow(10,2);
        }else if (unit == "dam"){
            converted = numberUnits / Math.pow(10,1);
        }else if (unit == "m"){
            converted = numberUnits;
        }else if (unit == "dm"){
            converted = numberUnits / Math.pow(10,-1);
        }else if (unit == "cm"){
            converted = numberUnits / Math.pow(10,-2);
        }else if (unit == "mm"){
            converted = numberUnits / Math.pow(10,-3);
        }else if (unit == "um"){
            converted = numberUnits / Math.pow(10,-6);
        }else if (unit == "nm"){
            converted = numberUnits / Math.pow(10,-9);
        }else{
            converted = 0;
        }
        return converted;
    }
    
    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit){
        double numMeters = convertToMeters(numberStartingUnits, startingUnit);
        return convertFromMeters(numMeters, desiredUnit);
    }
    
    private boolean unitFound(String unit){
        String[] arr = {"Gm","Mm","km","hm","dam","m","dm","cm","mm","um","nm"};
        for(int index = 0;index <= arr.length - 1; index++){
            if(unit.equals(arr[index])){
                return true;
            }
        }
        return false;
    }
    
    public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit){
        if(unitFound(startingUnit) && unitFound(desiredUnit)){
            return numberStartingUnits + " " + startingUnit + " = " +convert(numberStartingUnits, startingUnit, desiredUnit) + " " + desiredUnit + ".";
        }else{
            return "Unit not supported.";
        }
    }
}
