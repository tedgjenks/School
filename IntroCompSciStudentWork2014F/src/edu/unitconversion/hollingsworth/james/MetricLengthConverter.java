package edu.unitconversion.hollingsworth.james;
import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {
    public static void main(String[] args) {
        MetricLengthConverter m = new MetricLengthConverter();
        System.out.println(m.convert(1, "Mm", "nm"));
        //System.out.println(m.convert(1, "Gm", "nm"));
    }
    
    public double convertToMeters(double numberUnits, String unit) {
        String[] units = {"n", "1", "1", "u", "1", "1", "m", "c", "d", "", "da", "h", "k", "1", "1", "M", "1", "1", "G"};
        String startingUnits = unit.substring(0, unit.length() - 1);
        double result = numberUnits;
        int end = -1;
        for(int i = 0; i < units.length; i++) if(units[i].equals(startingUnits)) end = i;
        System.out.println(end);
        if(end > 9) for(int i = 9; i < end; i++) result *= 10;
        else for(int i = end; i < 9; i++) result /= 10;
        return result;
    }

    public double convertFromMeters(double numberMeters, String unit) {
        String[] units = {"n", "1", "1", "u", "1", "1", "m", "c", "d", "", "da", "h", "k", "1", "1", "M", "1", "1", "G"};
        String endingUnits = unit.substring(0, unit.length() - 1);
        double result = numberMeters;
        int end = -1;
        for(int i = 0; i < units.length; i++) if(units[i].equals(endingUnits)) end = i;
        if(end > 9) for(int i = 9; i < end; i++) result /= 10;
        else for(int i = end; i < 9; i++) result *= 10;
        return result;
    }

    public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
        double convertedToMeters = convertToMeters(numberStartingUnits, startingUnit);
        double result = convertFromMeters(convertedToMeters, desiredUnit);
        return result;
    }

    public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
        String[] units = {"n", "1", "1", "u", "1", "1", "m", "c", "d", "", "da", "h", "k", "1", "1", "M", "1", "1", "G"};
        String startingUnits = startingUnit.substring(0, startingUnit.length() - 1);
        String endingUnits = desiredUnit.substring(0, desiredUnit.length() - 1);
        int start = -1,
            end = -1;
        for(int i = 0; i < units.length; i++) {
            if(units[i].equals(endingUnits)) end = i;
            if(units[i].equals(startingUnits)) start = i;
        }
        if(end == -1 || start == -1) return "Unit not supported.";
        double converted = convert(numberStartingUnits, startingUnit, desiredUnit);
        return Double.toString(numberStartingUnits) + " " + startingUnit + " = " + Double.toString(converted) + " " + desiredUnit + ".";
    }
}
