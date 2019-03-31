package edu.unitconversion.savelyev.denis;

import edu.jenks.dist.unitconversion.*;

public class MetricLengthConverter implements Convertible{
    public static void main(String[] args) {
        MetricLengthConverter instance = new MetricLengthConverter();
        System.out.println(instance.convertForDisplay(45, "km", "cm"));
    }
    public double convertToMeters(double numberUnits, String unit) {
        return toMeterConvert(numberUnits, unit);
    }
    public double convertFromMeters(double numberMeters, String unit) {
        return fromMeterConvert(numberMeters, unit);
    }
    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
        double partOne = toMeterConvert(numberStartingUnits, startingUnit);
        double partTwo = fromMeterConvert(partOne, desiredUnit);
        return partTwo;
    }
    public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
        double convertedNum = convert(numberStartingUnits, startingUnit, desiredUnit);
        return numberStartingUnits + " " + startingUnit + " = " + convertedNum + " " + desiredUnit + ".";
    }
    private double toMeterConvert(double numberUnits, String unit){
        String[] abvArr = {"nm", "um", "mm", "cm", "dm", "m", "dam", "hm", "km", "Mm", "Gm"};
        double[] convertArr = {0.000000001, 0.000001, 0.001, 0.01, 0.1, 1, 10, 100, 1000, 1000000, 1000000000};
        int length = abvArr.length - 1;
        for (int i = 0; i <= length; i++){
            if(unit == abvArr[i]){
                numberUnits *= convertArr[i];
            }
        }
        return numberUnits;
    }
    private double fromMeterConvert(double numberMeters, String unit){
        String[] abvArr = {"nm", "um", "mm", "cm", "dm", "m", "dam", "hm", "km", "Mm", "Gm"};
        double[] convertArr = {0.000000001, 0.000001, 0.001, 0.01, 0.1, 1, 10, 100, 1000, 1000000, 1000000000};
        int length = abvArr.length - 1;
        for (int i = 0; i <= length; i++){
            if(unit == abvArr[i]){
                numberMeters /= convertArr[i];
            }
        }
        return numberMeters;
    }
}
