package edu.unitconversion.ramsey.will;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double amountUnits, String startingUnit, String desiredUnit) {
		// TODO Auto-generated method stub
		double numberMeters = convertToMeters(amountUnits, startingUnit);
		double endingValue = convertFromMeters(numberMeters, desiredUnit);
		return endingValue;
	}

	@Override
	public String convertForDisplay(double amountUnits, String startingUnit, String desiredUnit) {
		// TODO Auto-generated method stub\
		boolean validInput = false;
		if ("nm".equals(startingUnit)|| "mm".equals(startingUnit)|| "mm".equals(startingUnit)|| "cm".equals(startingUnit)|| "dm".equals(startingUnit)|| "m".equals(startingUnit)|| "dam".equals(startingUnit)|| "hm".equals(startingUnit)|| "km".equals(startingUnit)|| "Mm".equals(startingUnit))
			if ("nm".equals(desiredUnit)|| "mm".equals(desiredUnit)|| "mm".equals(desiredUnit)|| "cm".equals(desiredUnit)|| "dm".equals(desiredUnit)|| "m".equals(desiredUnit)|| "dam".equals(desiredUnit)|| "hm".equals(desiredUnit)|| "km".equals(desiredUnit)|| "Mm".equals(desiredUnit))
				validInput = true;
		if (validInput == false)
			return "Unit not supported.";
		double endingValue = convert(amountUnits, startingUnit, desiredUnit);
		String returnString = + amountUnits + " " + startingUnit + " = " + endingValue + " " + desiredUnit + ".";
		
		return returnString;
	}

	@Override
	public double convertFromMeters(double numberMeters, String desiredUnit) {
		// TODO Auto-generated method stub
		double numberOfDesiredUnits = 0;
		if (desiredUnit.equals ("nm"))		
			numberOfDesiredUnits = numberMeters*1000000000;
		else if (desiredUnit.equals ("um"))
			numberOfDesiredUnits = numberMeters*1000000;
		else if (desiredUnit.equals ("mm"))
			numberOfDesiredUnits = numberMeters*1000;
		else if (desiredUnit.equals ("cm"))
			numberOfDesiredUnits = numberMeters*100;
		else if (desiredUnit.equals ("dm"))
			numberOfDesiredUnits = numberMeters*10;
		else if (desiredUnit.equals ("m"))
			numberOfDesiredUnits = numberMeters*1;
		else if (desiredUnit.equals ("dam"))
			numberOfDesiredUnits = numberMeters/10;
		else if (desiredUnit.equals ("hm"))
			numberOfDesiredUnits = numberMeters/100;
		else if (desiredUnit.equals ("km"))
			numberOfDesiredUnits = numberMeters/1000;
		else if (desiredUnit.equals ("Mm"))
			numberOfDesiredUnits = numberMeters/1000000;
			else numberOfDesiredUnits = numberMeters/1000000000;
		return numberOfDesiredUnits;
	}

	@Override
	public double convertToMeters(double amountUnits, String startingUnit) {
		// TODO Auto-generated method stub
		double numberMeters = 0;
		if (startingUnit.equals ("nm"))
			numberMeters = amountUnits/1000000000;
		else if (startingUnit.equals ("um"))
			numberMeters = amountUnits/1000000;
		else if (startingUnit.equals ("mm"))
			numberMeters = amountUnits/1000;
		else if (startingUnit.equals ("cm"))
			numberMeters = amountUnits/100;
		else if (startingUnit.equals ("dm"))
			numberMeters = amountUnits/10;
		else if (startingUnit.equals("m"))
			numberMeters = amountUnits *1;
		else if (startingUnit.equals ("dam"))
			numberMeters = amountUnits*10;
		else if (startingUnit.equals ("hm"))
			numberMeters = amountUnits*100;
		else if (startingUnit.equals ("km"))
			numberMeters = amountUnits*1000;
		else if (startingUnit.equals("Mm"))
			numberMeters = amountUnits*1000000;
			else numberMeters = amountUnits*1000000000;
		return numberMeters;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("ProgramStart");
		System.out.println("Program End");
	}

}
