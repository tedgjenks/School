package edu.unitconversion.busbee.hunter;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {
	

	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnits) {
		// TODO Auto-generated method stub
		double convertedUnit = convertToMeters(numberStartingUnits, startingUnit);
		double unitWanted = convertFromMeters(convertedUnit, desiredUnits);
		return unitWanted;
		
	}

	@Override
	public String convertForDisplay(double numberStartingUnits, String startingUnit , String desiredUnits) {
		// TODO Auto-generated method stub
		
			return null;
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		// TODO Auto-generated method stub
		switch(unit){
		case "nm":
			return numberMeters * 100000000;
		case "um":
			return numberMeters * 100000;
		case "mm":
			return numberMeters * 1000;
		case "cm":
			return numberMeters * 100;
		case "dm":
			return numberMeters * 10;
		case "m":
			return numberMeters;
		case "dam":
			return numberMeters * .1;
		case "hm":
			return numberMeters * .01;
		case "km":
			return numberMeters * .001;
		case "Mm":
			return numberMeters * .000001;
		case "Gm":
			return numberMeters * .000000001;
		default:
			System.out.println("Not a valid unit input.");
			return -50;
		}
		
	}

	@Override
	public double convertToMeters(double numberUnits, String unit) {
		// TODO Auto-generated method stub
		switch(unit){
		case "nm":
			return numberUnits * .000000001;
		case "um":
			return numberUnits * .000001;
		case "mm":
			return numberUnits * .001;
		case "cm":
			return numberUnits * .01;
		case "dm":
			return numberUnits * .1;
		case "m":
			return numberUnits;
		case "dam":
			return numberUnits * 10;
		case "hm":
			return numberUnits * 100;
		case "km":
			return numberUnits * 1000;
		case "Mm":
			return numberUnits * 100000;
		case "Gm":
			return numberUnits * 100000000;
		default:
			
			System.out.println("Not a valid unit input.");
			return -50;
		}
		
	}

}
