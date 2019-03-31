package edu.unitconversion.sweezy.kenneth;

import edu.jenks.dist.unitconversion.*;

public class MetricLengthConverter implements Convertible {
	public static void main(String[] args) {
		MetricLengthConverter instance = new MetricLengthConverter();
		double testNum = instance.convertToMeters(58, "cm");
		System.out.println("Expected: " + 0.58 + " Actual: " + testNum);
	}

	public double convertToMeters(double numberUnits, String unit) {
		return numberUnits * convertToNum(unit);
	}

	public double convertFromMeters(double numberMeters, String unit) {
		return numberMeters / convertToNum(unit);
	}

	public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
		return convertFromMeters(convertToMeters(numberStartingUnits, startingUnit), desiredUnit);
	}

	public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
		if((convertToNum(startingUnit) == -1) || (convertToNum(desiredUnit) == -1)) {
			return "Unit not supported.";
		}
		double convertedAmount = convert(numberStartingUnits, startingUnit, desiredUnit);
		return String.valueOf(numberStartingUnits) + " " + startingUnit + " = " + String.valueOf(convertedAmount) + " " + desiredUnit + ".";
	}

	private double convertToNum(String unitName) {
		if(unitName == "Gm") {
			return 1000000000;
		} else if(unitName == "Mm") {
			return 1000000;
		} else if(unitName == "km") {
			return 1000;
		} else if(unitName == "hm") {
			return 100;
		} else if(unitName == "dam") {
			return 10;
		} else if(unitName == "m") {
			return 1;
		} else if(unitName == "dm") {
			return 0.1;
		} else if(unitName == "cm") {
			return 0.01;
		} else if(unitName == "mm") {
			return 0.001;
		} else if(unitName == "um") {
			return 0.000001;
		} else if(unitName == "nm") {
			return 0.000000001;
		} else {
			return -1;
		}
	}
}
