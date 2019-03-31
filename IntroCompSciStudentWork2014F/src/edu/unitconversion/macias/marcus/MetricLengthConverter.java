package edu.unitconversion.macias.marcus;

import edu.jenks.dist.unitconversion.*;

public class MetricLengthConverter implements Convertible
{
    
    public static void main(String[] arg){
        MetricLengthConverter metric = new MetricLengthConverter();
        System.out.println(metric.convertForDisplay(1000,"cm","mm"));
        //System.out.println(metric.convertFromMeters(7503,"dm"));
    }
    
    public double convertToMeters(double numberUnits,String unit){
        String[] unitList = {"Gm","Mm","km","hm","dam","m","dm","cm","mm","um","nm"};
        double[] unitValues = {1000000000,1000000,1000,100,10,1,.1,.01,.001,.000001,.000000001};
        for(int i = 0 ; i < unitList.length;i++){
            if(unit.equals(unitList[i])){
                numberUnits *= unitValues[i];
                break;
            }
        }
        return numberUnits;
    }
    
    public double convertFromMeters(double numberMeters,String unit){
        String[] unitList = {"Gm","Mm","km","hm","dam","m","dm","cm","mm","um","nm"};
        double[] unitValues = {1000000000,1000000,1000,100,10,1,.1,.01,.001,.000001,.000000001};
        for(int i = 0 ; i < unitList.length;i++){
            if(unit.equals(unitList[i])){
                numberMeters /= unitValues[i];
                break;
            }
        }
        return numberMeters;
    }
    
    public double convert(double numberStartingUnits,String startingUnit,String desiredUnit){
        double meters = convertToMeters(numberStartingUnits,startingUnit);
        double numberDesiredUnits = convertFromMeters(meters,desiredUnit);
        //String display = convertForDisplay(numberStartingUnits,startingUnit,desiredUnit);
        return numberDesiredUnits;
    }
    
    public String convertForDisplay(double numberStartingUnits,String startingUnit,String desiredUnit){
        String[] unitList = {"Gm","Mm","km","hm","dam","m","dm","cm","mm","um","nm"};
        int test = 0;
        int test2 = 0;
        for(int i = 0 ; i < unitList.length ; i++){
            if(startingUnit.equals(unitList[i])){
                test += 1;
            }
            if(desiredUnit.equals(unitList[i])){
                test2 += 1;
            }
            if(test == 1 && test2 == 1){
                break;
            }
        }
        if(test != 1 || test2 != 1){
            return "Unit not supported.";
        }
        double numberDesiredUnits = convert(numberStartingUnits,startingUnit,desiredUnit);
        String numberStartingUnitsString = Double.toString(numberStartingUnits);
        String numberDesiredUnitsString = Double.toString(numberDesiredUnits);
        String display = numberStartingUnitsString + " " + startingUnit + " = " + numberDesiredUnitsString + " " + desiredUnit + ".";
        return display;
    }
}
