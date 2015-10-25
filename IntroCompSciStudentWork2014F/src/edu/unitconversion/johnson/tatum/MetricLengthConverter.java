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
		double convertOutput = convert(numberStartingUnits, startingUnit, desiredUnit);
		if (convertOutput <= -1){
			return "Unit not supported.";
		}
		else
			System.out.println("Number of Desired Units: " + convert(numberStartingUnits, startingUnit, desiredUnit));
			
		return numberStartingUnits + " " + startingUnit + " = " + convertOutput + " " + desiredUnit + ".";
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		double m = numberMeters; 
		double numUnits = 0;
		if (unit .equals ("Gm")){
			numUnits = numberMeters / 1000000000;
			return numUnits;
		}
		else if (unit .equals ("Mm")){ 
			numUnits = numberMeters / 1000000;
			return numUnits;
		}
		else if (unit .equals ("km")){ 
			numUnits = numberMeters / 1000;
			return numUnits;
		}	
		else if (unit .equals ("hm")){
			numUnits = numberMeters / 100;
			return numUnits;
		}
		else if (unit .equals ("dam")){
			numUnits = numberMeters / 10;
			return numUnits;
		}
		else if (unit .equals ("m")){ 
			numUnits = numberMeters * 1;
			return numUnits;
		}
		else if (unit .equals ("dm")){ 
			numUnits = numberMeters * 10;
			return numUnits;
		}	
		else if (unit .equals("cm")){
			numUnits = numberMeters * 100;
			return numUnits;
		}
		else if (unit .equals("mm")){
			numUnits = numberMeters * 1000;
			return numUnits;
		}
		else if (unit .equals ("um")){
			numUnits = numberMeters * 1000000;
			return numUnits;
		}
		else if (unit .equals ("nm")){
			numUnits = numberMeters * 1000000000;
			return numUnits;
		}
		else
			return -1; 
	}
		

	@Override
	public double convertToMeters(double numberUnits, String unit) {
		double m = numberUnits;
		double numberMeters;
		if (unit .equals ("Gm")){
			numberMeters = numberUnits * 1000000000;
			return  numberMeters;
		}
		else if (unit .equals ("Mm")){ 
			numberMeters= numberUnits * 1000000;
			return numberMeters;
		}
		else if (unit .equals ("km")){ 
			numberMeters= numberUnits * 1000;
			return numberMeters;
		}
		else if (unit .equals ("hm")){
			numberMeters = numberUnits * 100;
			return numberMeters;
		}
		else if (unit .equals ("dam")){
			numberMeters = numberUnits * 10;
			return numberMeters;
		}
		else if (unit .equals ("m")){ 
			numberUnits = numberUnits * 1;
			return numberUnits;
		}	
		else if (unit .equals ("dm")){ 
			numberMeters = numberUnits / 10;
			return numberMeters;
		}
		else if (unit .equals("cm")){
			numberMeters = numberUnits / 100;
			return numberMeters;
		}
		else if (unit .equals("mm")){
			numberMeters = numberUnits / 1000;
			return numberMeters;
		}
		else if (unit .equals ("um")){
			numberMeters = numberUnits / 1000000;
			return numberMeters;
		}
		else if (unit .equals ("nm")){
			numberMeters = numberUnits / 1000000000;
			return numberMeters;
		}
		else
			return -1;
	}

	public static void main(String[] args) {
		

	}

}
