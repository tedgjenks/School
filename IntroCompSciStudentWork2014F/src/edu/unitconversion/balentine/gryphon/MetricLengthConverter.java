package edu.unitconversion.balentine.gryphon;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
		//
		//Convert numberStartingUnits of startingUnit to desiredUnit. <----------------------------------------
		//Part 3: 40%
		//Write a function to convert a number of current units into the desired units.
		//
		return 0;
	}

	@Override
	public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
		//
		//Convert numberStartingUnits of startingUnit to desiredUnit. <----------------------------------------
		//A String with the following format is used:
		//numberStartingUnits startingUnit = returned_value desiredUnit.
		//If startingUnit or desiredUnit is not valid, return: Unit not supported.
		//Part 4: 10%
		//The conversion should report the original number and units, and the converted number and units.
		return null;
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		//
		//Convert numberUnits of meters to unit. <-------------------------------------------------------------
		//Part 2: 25%
		//Write a function to convert a number of meters into the desired units.
		//
		double numDesUnits=0;
		if (unit.equals("nm")){
			numDesUnits=numberMeters * 1000000000;
		}
		else if (unit.equals("um")){
			numDesUnits=numberMeters * 1000000;
		}
		else if (unit.equals("mm")){
			numDesUnits=numberMeters * 1000;
		}
		else if (unit.equals("cm")){
			numDesUnits=numberMeters * 100;
		}
		else if (unit.equals("dm")){
			numDesUnits=numberMeters * 10;
		}
		else if (unit.equals("m")){
			numDesUnits=numberMeters;
		}
		else if (unit.equals("dam")){
			numDesUnits=numberMeters / 10;
		}
		else if (unit.equals("hm")){
			numDesUnits=numberMeters / 100;
		}
		else if (unit.equals("km")){
			numDesUnits=numberMeters / 1000;
		}
		else if (unit.equals("Mm")){
			numDesUnits=numberMeters / 1000000;
		}
		else if (unit.equals("Gm")){
			numDesUnits=numberMeters / 1000000000;
		}
		return numDesUnits;
	}

	@Override
	public double convertToMeters(double numberUnits, String unit) {
		//
		//Convert numberUnits of unit to meters. <------------------------------------------------------------
		//Part 1: 25%
		//Write a function to convert the number of current units into meters.
		//
		double meters=0;
		if (unit.equals("nm")){
			meters=numberUnits / 1000000000;
		}
		else if (unit.equals("um")){
			meters=numberUnits / 1000000;
		}
		else if (unit.equals("mm")){
			meters=numberUnits / 1000;
		}
		else if (unit.equals("cm")){
			meters=numberUnits / 100;
		}
		else if (unit.equals("dm")){
			meters=numberUnits / 10;
		}
		else if (unit.equals("m")){
			meters=numberUnits;
		}
		else if (unit.equals("dam")){
			meters=numberUnits * 10;
		}
		else if (unit.equals("hm")){
			meters=numberUnits * 100;
		}
		else if (unit.equals("km")){
			meters=numberUnits * 1000;
		}
		else if (unit.equals("Mm")){
			meters=numberUnits * 1000000;
		}
		else if (unit.equals("Gm")){
			meters=numberUnits * 1000000000;
		}
		return meters;
	}

	public static void main(String[] args) {
		double convertedMeters=0;
		MetricLengthConverter mlc=new MetricLengthConverter();
		int numberUnits = 297; //inputs for convertToMeters <-----------
		String units = "km"; //inputs for convertToMeters <-----------
		convertedMeters = mlc.convertToMeters(numberUnits, units);
		System.out.println("Convert to Meters:");
		System.out.println(numberUnits + units + " is " + convertedMeters + " meters.");
		

	}

}
