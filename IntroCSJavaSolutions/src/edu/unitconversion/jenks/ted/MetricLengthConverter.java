package edu.unitconversion.jenks.ted;

//import static java.lang.System.out;
import edu.jenks.dist.unitconversion.Convertible;
//import edu.jenks.util.MathUtil;

import java.util.*;

/**
 * @author Ted Jenks
 *
 */
public class MetricLengthConverter implements Convertible {
	
	private final Map<String, Integer> UNIT_CODES = new HashMap<String, Integer>(20);
	
	/*public static void main(String[] args) {
		MetricLengthConverter mlc = new MetricLengthConverter();
		out.println(mlc.convertForDisplay(10, "m", "mm"));
		out.println(mlc.convertForDisplay(100000, "cm", "km"));
		out.println(mlc.convertForDisplay(100000, "booga", "km"));
		double d1 = Double.parseDouble("2E7");
		double d2 = Double.parseDouble("2E7");
		System.out.println(d1);
		//System.out.println(MathUtil.equalsRelative(d1, d2, .001));
	}*/
	
	public MetricLengthConverter() {
		UNIT_CODES.put("nm", -9);
		UNIT_CODES.put("um", -6);
		UNIT_CODES.put("mm", -3);
		UNIT_CODES.put("cm", -2);
		UNIT_CODES.put("dm", -1);
		UNIT_CODES.put("m", 0);
		UNIT_CODES.put("dam", 1);
		UNIT_CODES.put("hm", 2);
		UNIT_CODES.put("km", 3);
		UNIT_CODES.put("Mm", 6);
		UNIT_CODES.put("Gm", 9);
	}

	@Override
	public double convertToMeters(double numberUnits, String unit) {
		int magnitude = UNIT_CODES.get(unit);
		return numberUnits * Math.pow(10, magnitude);
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		int magnitude = UNIT_CODES.get(unit);
		return numberMeters / Math.pow(10, magnitude);
	}

	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
		double numberMeters = convertToMeters(numberStartingUnits, startingUnit);
		return convertFromMeters(numberMeters, desiredUnit);
	}

	@Override
	public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
		String returnVal;
		if(UNIT_CODES.containsKey(startingUnit) && UNIT_CODES.containsKey(desiredUnit)) {
			double numberDesiredUnits = convert(numberStartingUnits, startingUnit, desiredUnit);
			returnVal = numberStartingUnits + " " + startingUnit + " = " + numberDesiredUnits + " " + desiredUnit + ".";
		} else
			returnVal = "Unit not supported.";
		return returnVal;
	}

}
