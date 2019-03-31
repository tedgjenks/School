package edu.unitconversion.whitt.rose;

import edu.jenks.dist.unitconversion.*;
/**
 * This program will convert metric units of length.
 * Use the standard metric symbols to match user input.
 * For example ‘mm’ is millimeters and ‘Mm’ is Megameters.
 * Prompt the user to enter a number of units as a float,
 * the metric symbol for the current unit,
 * and the metric symbol for the desired unit.
 * Your program should handle nanometers through gigameters.
 */
public class MetricLengthConverter implements Convertible
{
    public static void main(String[] args) {
        MetricLengthConverter pc = new MetricLengthConverter();
        
        
        String nano = "nm";
        String micro = "um";
        String milli = "mm";
        String centi = "cm";
        String deci = "dm";
        String meter = "m";
        String deka = "dam";
        String hecto = "hm";
        String kilo = "km";
        String mega = "Mm";
        String giga = "Gm";
        
        double numberStartingUnits = 2.5;
        String startingUnit = "nm";
        String desiredUnit = "boo";
        double thing = pc.convertToMeters(numberStartingUnits, startingUnit);
        System.out.println(thing);
        System.out.println(pc.convertFromMeters(thing, desiredUnit));
        System.out.println(pc.convert(numberStartingUnits, startingUnit, desiredUnit));
        System.out.println(pc.convertForDisplay(numberStartingUnits, startingUnit, desiredUnit));
    }
    public double convertToMeters(double numberUnits, String unit) {
        if (unit == "nm") {
            return numberUnits * Math.pow(10, -9);
        } else if (unit == "um") {
            return numberUnits * Math.pow(10, -6);
        } else if (unit == "mm") {
            return numberUnits * Math.pow(10, -3);
        } else if (unit == "cm") {
            return numberUnits * Math.pow(10, -2);
        } else if (unit == "dm") {
            return numberUnits * Math.pow(10, -1);
        } else if (unit == "m") {
            return numberUnits * Math.pow(10, 0);
        } else if (unit == "dam") {
            return numberUnits * Math.pow(10, 1);
        } else if (unit == "hm") {
            return numberUnits * Math.pow(10, 2);
        } else if (unit == "km") {
            return numberUnits * Math.pow(10, 3);
        } else if (unit == "Mm") {
            return numberUnits * Math.pow(10, 6);
        } else {
            return numberUnits * Math.pow(10, 9);
        }
    }
    public double convertFromMeters(double numberMeters, String unit) {
        if (unit == "nm") {
            return numberMeters * Math.pow(10, 9);
        } else if (unit == "um") {
            return numberMeters * Math.pow(10, 6);
        } else if (unit == "mm") {
            return numberMeters * Math.pow(10, 3);
        } else if (unit == "cm") {
            return numberMeters * Math.pow(10, 2);
        } else if (unit == "dm") {
            return numberMeters * Math.pow(10, 1);
        } else if (unit == "m") {
            return numberMeters * Math.pow(10, 0);
        } else if (unit == "dam") {
            return numberMeters * Math.pow(10, -1);
        } else if (unit == "hm") {
            return numberMeters * Math.pow(10, -2);
        } else if (unit == "km") {
            return numberMeters * Math.pow(10, -3);
        } else if (unit == "Mm") {
            return numberMeters * Math.pow(10, -6);
        } else {
            return numberMeters * Math.pow(10, -9);
        }
    }
    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
        double meters = convertToMeters(numberStartingUnits, startingUnit);
        return convertFromMeters(meters, desiredUnit);
    }
    public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
        String nano = "nm";
        String micro = "um";
        String milli = "mm";
        String centi = "cm";
        String deci = "dm";
        String meter = "m";
        String deka = "dam";
        String hecto = "hm";
        String kilo = "km";
        String mega = "Mm";
        String giga = "Gm";
        
        String[] possibleUnits = {nano, micro, milli, centi, deci, meter, deka, hecto, kilo, mega, giga};
        int count = 0;
        for(int i = 0; i < possibleUnits.length; i++) {
            if (startingUnit == possibleUnits[i]) {
                count++;
            } else {
                count = count;
            }
            if (desiredUnit == possibleUnits[i]) {
                count++;
            } else {
                count = count;
            }
        }
        if (count == 2) {
            return numberStartingUnits + " " + startingUnit + " = " + convert(numberStartingUnits, startingUnit, desiredUnit) + " " + desiredUnit + ".";
        } else {
            return "Unit not supported.";
        }
    }
}
