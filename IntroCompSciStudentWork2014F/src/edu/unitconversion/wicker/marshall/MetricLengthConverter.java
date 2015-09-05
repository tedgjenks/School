package edu.unitconversion.wicker.marshall;

import java.util.Arrays;
import java.util.List;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {
	
	private List<String> metricSymbols = Arrays.asList(new String[] {"nm", "um", "mm", "cm", "dm", "m", "dam", "hm", "km", "Mm", "Gm"});
	private double[] multipliers = new double[] {.000000001, .000001, .001, .01, .1, 1, 10, 100, 1000, 1000000, 1000000000};

	@Override
	public double convert(double toConvert, String startingUnit, String endingUnit) {
		return toConvert * multiplier(startingUnit, endingUnit);
	}

	@Override
	public String convertForDisplay(double toConvert, String startingUnit, String endingUnit) {
		double multiplierVal = multiplier(startingUnit, endingUnit);
		
		if (multiplierVal < 0)
			return "Unit not supported.";
		else
			return toConvert + " " + startingUnit + " = " + toConvert * multiplierVal + " " + endingUnit + ".";
	}

	@Override
	public double convertFromMeters(double toConvert, String endingUnit) {
		return toConvert * multiplier("m", endingUnit);
	}

	@Override
	public double convertToMeters(double toConvert, String startingUnit) {
		return toConvert * multiplier(startingUnit, "m");
	}
	
	private double multiplier (String startAbbreviation, String endAbbreviation) {
		
		//Gets index of the abbreviation
		int indexOfStartAbbreviation = metricSymbols.indexOf(startAbbreviation);
		int indexOfEndAbbreviation = metricSymbols.indexOf(endAbbreviation);
		
		if (indexOfStartAbbreviation < 0 || indexOfEndAbbreviation < 0)
			return -1;
		else
			return multipliers[indexOfStartAbbreviation] / multipliers[indexOfEndAbbreviation];
	}

}
