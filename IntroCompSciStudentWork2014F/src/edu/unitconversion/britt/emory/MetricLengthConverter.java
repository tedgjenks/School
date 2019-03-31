package edu.unitconversion.britt.emory;
import edu.jenks.dist.unitconversion.*; 


public class MetricLengthConverter implements Convertible{
    public static void main(String[] args){
        System.out.println("Begin");
        MetricLengthConverter mlc = new MetricLengthConverter();
        double act = mlc.convertToMeters(1000.0, "km");
        double exp = 1.0; 
        assert act == exp : "convertToMeters exp: " + exp + "; actual: " + act; 
        double act2 = mlc.convertToMeters(0.0, "m");
        double exp2 = 0.0; 
        assert act2 == exp2 : "convertToMeters exp: " + exp2 + "; actual: " + act2; 
        double act3 = mlc.convertFromMeters(0.0, "m");
        double exp3 = 0.0; 
        assert act3 == exp3 : "convertFromMeters exp: " + exp3 + "; actual: " + act3; 
        double act4 = mlc.convertFromMeters(1.0, "km");
        double exp4 = 1000.0; 
        assert act4 == exp4 : "convertFromMeters exp: " + exp4 + "; actual: " + act4;
        System.out.println("End Without Error");
    }
    public double convertToMeters(double numberUnits, String unit){
        double conversionFactor = getCF(unit); 
        double conMeter = 0.0; 
        if(conversionFactor == 0){
            return conMeter; 
        }
        if(conversionFactor > 1){
            conMeter = numberUnits * conversionFactor; 
        } else if(conversionFactor < 1){
            conMeter = numberUnits * conversionFactor; 
        } else if(conversionFactor == 1){
            return numberUnits; 
        } 
        return conMeter; 
    }
    public double convertFromMeters(double numberMeters, String unit){
        double conversionFactor = getCF(unit); 
        double conNum = 0.0; 
        if(conversionFactor == 0){
            return conNum; 
        }
        if(conversionFactor > 1){
            conNum = numberMeters / conversionFactor; 
        } else if(conversionFactor < 1){
            conNum = numberMeters / conversionFactor; 
        } else if(conversionFactor == 1){
            return numberMeters; 
        }
        return conNum; 
    }
    public double getCF(String unit){
        if(unit.equals("Gm")){
            return 1000000000.0;
        } else if(unit.equals("Mm")){
            return 1000000.0; 
        } else if(unit.equals("km")){
            return 1000.0; 
        } else if(unit.equals("hm")){
            return 100.0; 
        } else if(unit.equals("dam")){
            return 10.0; 
        } else if(unit.equals("m")){
            return 1.0; 
        } else if (unit.equals("dm")){
            return .1; 
        } else if(unit.equals("cm")){
            return .01; 
        } else if(unit.equals("mm")){
            return .001; 
        } else if(unit.equals("um")){
            return .000001; 
        } else if(unit.equals("nm")){
            return .000000001; 
        }
        return 0.0; 
    }
    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit){
        double conNum = 0.0;
        conNum = convertToMeters(numberStartingUnits, startingUnit);
        conNum = convertFromMeters(conNum, desiredUnit);
        return conNum; 
    }
    public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit){
        double check = getCF(startingUnit);
        double check2 = getCF(desiredUnit);
        if(check == 0 || check2 == 0){
            return "Unit not supported.";
        }
        double conNum = convert(numberStartingUnits, startingUnit, desiredUnit);
        return numberStartingUnits + " " + startingUnit + " = " + conNum + " " + desiredUnit + "."; 
    }
}
