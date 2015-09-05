package edu.unitconversion.johnson.tatum;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
		double numberMeters = convertToMeters(numberStartingUnits, startingUnit);
		return convertFromMeters(numberMeters, desiredUnit);
	}

	@Override
	public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) {
		return null;
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		double numberUnits = 0; 
		if (unit .equals ("Gm")){
			numberUnits = numberMeters / 1000000000;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals ("Mm")){ 
			numberUnits= numberMeters / 1000000;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals ("km")){ 
			numberUnits= numberMeters / 1000;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals ("hm")){
			numberUnits = numberMeters / 100;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals ("dam")){
			numberUnits = numberMeters / 10;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals ("dm")){ 
			numberUnits = numberMeters * 10;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals("cm")){
			numberUnits = numberMeters * 100;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals("mm")){
			numberUnits = numberMeters * 1000;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals ("um")){
			numberUnits = numberMeters * 1000000;
			System.out.println("Number of Meters: " + numberUnits);
		}
		else if (unit .equals ("nm")){
			numberUnits = numberMeters * 1000000000;
			System.out.println("Number of Meters: " + numberUnits);
		}
		return numberUnits;
	}
		

	@Override
	public double convertToMeters(double numberUnits, String unit) {
		double numberMeters = 0;
		if (unit .equals ("Gm")){
			numberMeters = numberUnits * 1000000000;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals ("Mm")){ 
			numberMeters= numberUnits * 1000000;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals ("km")){ 
			numberMeters= numberUnits * 1000;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals ("hm")){
			numberMeters = numberUnits * 100;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals ("dam")){
			numberMeters = numberUnits * 10;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals ("dm")){ 
			numberMeters = numberUnits / 10;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals("cm")){
			numberMeters = numberUnits / 100;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals("mm")){
			numberMeters = numberUnits / 1000;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals ("um")){
			numberMeters = numberUnits / 1000000;
			System.out.println("Number of Meters: " + numberMeters);
		}
		else if (unit .equals ("nm")){
			numberMeters = numberUnits / 1000000000;
			System.out.println("Number of Meters: " + numberMeters);
		}
		return numberMeters;
	}

	public static void main(String[] args) {
		

	}

}
