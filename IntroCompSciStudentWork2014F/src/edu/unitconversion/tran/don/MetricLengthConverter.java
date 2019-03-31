package edu.unitconversion.tran.don;

import edu.jenks.dist.unitconversion.*;

public class MetricLengthConverter implements Convertible
{
    public static void main (String[] args) {
        MetricLengthConverter pc = new MetricLengthConverter();
        double act = pc.convertToMeters(1.0, "dm");
        System.out.println(act);
    }
    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
        String[] units = new String[]{"nm","um","mm","cm","dm","m","dam","hm","km","Mm","Gm"};
        double[] unitConv = new double[]{.000000001, .000001, .001, .01, .1, 1, 10, 100, 1000, 1000000, 1000000000};
        //if(supportedOrNot == false || supportedOrNotTwo == false) {
            //convertForDisplay(0, "nah man", "nah");
        //}
        double meters = convertToMeters(numberStartingUnits, startingUnit);
        double desired = convertFromMeters(meters, desiredUnit);
        //convertForDisplay(desired, startingUnit, desiredUnit);
        return desired;
    }
    public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
        String[] units = new String[]{"nm","um","mm","cm","dm","m","dam","hm","km","Mm","Gm"};
        boolean supportedOrNot = false;
        boolean supportedOrNotTwo = false;
        for(int i = 0; i < units.length; i++) {
            String curr = units[i];
            if(startingUnit.equals(curr)) {
                supportedOrNot = true;
            }
        }
        for(int i = 0; i < units.length; i++) {
            String currTwo = units[i];
            if(desiredUnit.equals(currTwo)) {
                supportedOrNotTwo = true;
            }
        }
        if(supportedOrNot == false || supportedOrNotTwo == false) {
            return "Unit not supported.";
        }
        double answer = convert(numberStartingUnits, startingUnit, desiredUnit);
        //if(startingUnit.equals("nah man")) {
            //System.out.println("works");
            //return "Unit is not supported";
        //}
        return numberStartingUnits + " " + startingUnit + " = " + answer + " " + desiredUnit + ".";
    }
    public double convertToMeters(double numberUnits, String unit) {
        String[] units = new String[]{"nm","um","mm","cm","dm","m","dam","hm","km","Mm","Gm"};
        double[] unitConv = new double[]{.000000001, .000001, .001, .01, .1, 1, 10, 100, 1000, 1000000, 1000000000};
        double meters = 0.0;
        for(int i = 0; i < units.length; i++) {
            String curr = units[i];
            System.out.println(curr);
            if(curr.equals(unit)) {
                 meters = numberUnits * unitConv[i];
            }
        }
        if(unit.equals("m")) {
            return numberUnits;
        }
        return meters;
    }
    public double convertFromMeters(double numberMeters, String unit) { 
        String[] units = new String[]{"nm","um","mm","cm","dm","m","dam","hm","km","Mm","Gm"};
        double[] unitConv = new double[]{.000000001, .000001, .001, .01, .1, 1, 10, 100, 1000, 1000000, 1000000000};
        double finalAnswer = 0.0;
        for(int i = 0; i < units.length; i++) {
            String curr = units[i];
            if(curr.equals(unit)) {
                finalAnswer = numberMeters / unitConv[i];
            }
        }
        return finalAnswer;
    }
}
