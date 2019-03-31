package edu.unitconversion.page.javin;

import edu.jenks.dist.unitconversion.*;
/**
 * Write a description of class MetricLengthConverter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MetricLengthConverter implements Convertible
{
    public static void main(String[] args)
    {
        MetricLengthConverter instance = new MetricLengthConverter();
       System.out.println(instance.convertForDisplay(12973, "Gm", "nom"));
    }
    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit){
        double num = convertToMeters(numberStartingUnits, startingUnit);
        num = convertFromMeters(num, desiredUnit);
        return num;
    }
    public double convertToMeters(double numberUnits, String unit){
        String[] lesserValues = new String[]{"m", "dm", "cm", "mm", "ten-thou", "hund-thou", "um", "ten-mill", "hund-mill", "nm"};
        String[] greaterValues = new String[]{"m", "dam", "hm", "km", "ten-thou", "hund-thou", "Mm", "ten-mill", "hund-mill", "Gm"};
        double meters = 0.0;
        for(int i = 0; i < lesserValues.length; i++){   
            if(lesserValues[i].equals(unit)){
                meters = numberUnits * Math.pow(10, i * -1);
            }else if(greaterValues[i].equals(unit)){
                meters = numberUnits * Math.pow(10, i);
            }
        }
        return meters;
    }
    public double convertFromMeters(double numberMeters, String desiredUnit){
        String[] lesserValues = new String[]{"m", "dm", "cm", "mm", "ten-thou", "hund-thou", "um", "ten-mill", "hund-mill", "nm"};
        String[] greaterValues = new String[]{"m", "dam", "hm", "km", "ten-thou", "hund-thou", "Mm", "ten-mill", "hund-mill", "Gm"};
        double units = 0.0;
        for(int i = 0; i < lesserValues.length; i++){
            if(lesserValues[i].equals(desiredUnit)){
                units = numberMeters * Math.pow(10, i);
            }else if(greaterValues[i].equals(desiredUnit)){
                units = numberMeters / Math.pow(10, i);
            }
        }
        return units;
    }
    public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit){
        String[] lesserValues = new String[]{"m", "dm", "cm", "mm", "ten-thou", "hund-thou", "um", "ten-mill", "hund-mill", "nm"};
        String[] greaterValues = new String[]{"m", "dam", "hm", "km", "ten-thou", "hund-thou", "Mm", "ten-mill", "hund-mill", "Gm"};
        boolean isMeasurement = false;
        for(int i = 0; i < lesserValues.length; i++){
            if(lesserValues[i].equals(desiredUnit)){
                for(int n = 0; n < lesserValues.length; n++){
                    if(lesserValues[n].equals(startingUnit)){
                        isMeasurement = true;
                    }else if(greaterValues[n].equals(startingUnit)){
                        isMeasurement = true;
                    }
                }
            }else if(greaterValues[i].equals(desiredUnit)){
                for(int a = 0; a < lesserValues.length; a++){
                    if(lesserValues[a].equals(startingUnit)){
                        isMeasurement = true;
                    }else if(greaterValues[a].equals(startingUnit)){
                        isMeasurement = true;
                    }
                }
            }
        }
        if(isMeasurement){
            double number = convert(numberStartingUnits, startingUnit, desiredUnit);
            return Double.toString(numberStartingUnits) + " " + startingUnit + " = " + Double.toString(number) + " " + desiredUnit + ".";
        }else {
            return "Unit not supported.";
        }
    }
}
