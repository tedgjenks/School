package edu.unitconversion.busbee.hunter;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {
	

	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnits) {
		// TODO Auto-generated method stub
		double convertedUnit = convertToMeters(numberStartingUnits, startingUnit);
		return convertFromMeters(convertedUnit, desiredUnits);
	
		
	}

	public String convertForDisplay(double numberStartingUnits, String startingUnit , String desiredUnits) {
		// TODO Auto-generated method stub
		// Test desiredUnit also. Return the right thing.
		boolean validUnit = false;
		switch(startingUnit){
		case "nm":
			validUnit = true;
			break;
		case "um":
			validUnit = true;
			break;
		case "mm":
			validUnit = true;
			break;			
		case "cm":
			validUnit = true;
			break;
		case "dm":
			validUnit = true;
			break;
		case "m":
			validUnit = true;
			break;
		case "dam":
			validUnit = true;
			break;
		case "hm":
			validUnit = true;
			break;
		case "km":
			validUnit = true;
			break;
		case "Mm":
			validUnit = true;
			break;
		case "Gm":
			validUnit = true;
			break;
		default:
			return ("Unit is not supported.") ;
			}
		switch(desiredUnits){
		case "nm":
			validUnit = true;
			break;
		case "um":
			validUnit = true;
			break;
		case "mm":
			validUnit = true;
			break;			
		case "cm":
			validUnit = true;
			break;
		case "dm":
			validUnit = true;
			break;
		case "m":
			validUnit = true;
			break;
		case "dam":
			validUnit = true;
			break;
		case "hm":
			validUnit = true;
			break;
		case "km":
			validUnit = true;
			break;
		case "Mm":
			validUnit = true;
			break;
		case "Gm":
			validUnit = true;
			break;
		default:
			return "Unit is not supported." ;
			}
		if(validUnit)
			return "" + numberStartingUnits + " " + startingUnit + " = " + convert(numberStartingUnits, startingUnit, desiredUnits) + " " + desiredUnits + ".";
		else
			return "Unit is not supported";
		}
	

	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		// TODO Auto-generated method stub
		switch(unit){
		case "nm":
			return numberMeters * 1000000000;
		case "um":
			return numberMeters * 1000000;
		case "mm":
			return numberMeters * 1000;
		case "cm":
			return numberMeters * 100;
		case "dm":
			return numberMeters * 10;
		case "m":
			return numberMeters;
		case "dam":
			return numberMeters / 10;
		case "hm":
			return numberMeters / 100;
		case "km":
			return numberMeters / 1000;
		case "Mm":
			return numberMeters / 1000000;
		case "Gm":
			return numberMeters / 1000000000;
		default:
			System.out.println("Not a valid unit input.");
			return -1;
		}
		
	}

	@Override
	public double convertToMeters(double numberUnits, String unit) {
		// TODO Auto-generated method stub
		switch(unit){
		case "nm":
			return numberUnits / 1000000000;
		case "um":
			return numberUnits / 1000000;
		case "mm":
			return numberUnits / 1000;
		case "cm":
			return numberUnits / 100;
		case "dm":
			return numberUnits / 10;
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
			return -1;
		}
		
	}

}
