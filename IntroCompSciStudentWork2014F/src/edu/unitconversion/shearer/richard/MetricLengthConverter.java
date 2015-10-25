package edu.unitconversion.shearer.richard;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
		// Convert numberStartingUnits of startingUnit to desiredUnit.
		double converter = convertToMeters(numberStartingUnits, startingUnit);
		return convertFromMeters(converter, desiredUnit);
		}

	@Override
	public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
		// Convert numberStartingUnits of startingUnit to desiredUnit.
		double output = convert(numberStartingUnits, startingUnit, desiredUnit);
		if(output < 0) { 
			return ("Unit not supported.");
		}
		else
			return numberStartingUnits + " " + startingUnit + " = " + output + " " + desiredUnit + ".";
	}
	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		// Convert numberMeters of meters to unit.
		double meters = 0;
		if (unit.equals("m")) {
			meters = numberMeters;
			return meters;
		}
		else if (unit.equals("Gm")){
			meters = numberMeters/1000000000;
			return meters;
		}
		else if (unit.equals("Mm")){
			meters = numberMeters/1000000;
			return meters;
		}
		else if (unit.equals("km")){
			meters = numberMeters/1000;
			return meters;
		}
		else if (unit.equals("hm")){
			meters = numberMeters/100;
			return meters;
		}
		else if (unit.equals("dam")){
			meters = numberMeters/10;
			return meters;
		}
		else if (unit.equals("dm")){
			meters = numberMeters*10;
			return meters;
		}
		else if (unit.equals("cm")){
			meters = numberMeters*100;
			return meters;
		}
		else if (unit.equals("mm")){
			meters = numberMeters*1000;
			return meters;
		}
		else if (unit.equals("um")){
			meters = numberMeters*1000000;
			return meters;
		}
		else if (unit.equals("nm")){
			meters = numberMeters*1000000000;
			return meters;
		}
		else return -1;
	}
	
	@Override
	public double convertToMeters(double numberUnits, String unit) {
		// Convert numberUnits of unit to meters.
		double meters = 0;
		if (unit.equals("m")) {
			meters = numberUnits;
			return meters;
		}
		else if (unit.equals("Gm")){
			meters = numberUnits*1000000000;
			return meters;
		}
		else if (unit.equals("Mm")){
			meters = numberUnits*1000000;
			return meters;
		}
		else if (unit.equals("km")){
			meters = numberUnits*1000;
			return meters;
		}
		else if (unit.equals("hm")){
			meters = numberUnits*100;
			return meters;
		}
		else if (unit.equals("dam")){
			meters = numberUnits*10;
			return meters; 
		}
		else if (unit.equals("dm")){
			meters = numberUnits/10;
			return meters;
		}
		else if (unit.equals("cm")){
			meters = numberUnits/100;
			return meters;
		}
		else if (unit.equals("mm")){
			meters = numberUnits/1000;
			return meters;
		}
		else if (unit.equals("um")){
			meters = numberUnits/1000000;
			return meters;
		}
		else if (unit.equals("nm")){
			meters = numberUnits/1000000000;
			return meters;
		}
		else return -1;
	}
}