package edu.unitconversion.ball.daniel;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible 
{

	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) 
	{
		double finalconversion = 0.0; //Final return value
		MetricLengthConverter startingMLC = new MetricLengthConverter();
		String unit = startingUnit;			//Arguments to "convertToMeters"							
		double numberUnits = numberStartingUnits;	//Takes number of starting units from supplied "convert" values
		double startingToMeters = startingMLC.convertToMeters(numberUnits, unit);	//Creates a temporary variable to store number of meters from starting unit
		MetricLengthConverter finalMLC = new MetricLengthConverter(); //Takes the previous meters and feeds them into "convertFromMeters"
		finalconversion = finalMLC.convertFromMeters(startingToMeters, desiredUnit);	//Uses the temporary value and "desiredUnit" to create a final answer
		return finalconversion; //Return final value
	}

	@Override
	public String convertForDisplay(double numberStartingUnits, String startingUnit, String desiredUnit) 
	{
		return null;
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit)
	{
		double finalFromMeters = 0.0;
			
			if (unit.equals("Gm")) //If converting to Gigameters, take number of meters and divide by 1000000000
				{
					finalFromMeters = (numberMeters/1000000000);
				}
		else if (unit.equals("Mm"))
				{
					finalFromMeters = (numberMeters/1000000);
				}
		else if (unit.equals("km"))
				{
					finalFromMeters = (numberMeters/1000);
				}
		else if (unit.equals("hm"))
				{
					finalFromMeters = (numberMeters/100);
				}	
		else if (unit.equals("dam"))
				{
					finalFromMeters = (numberMeters/10);
				}
		else if (unit.equals("m"))
				{
					finalFromMeters = (numberMeters);
				}
		else if (unit.equals("dm"))
				{
					finalFromMeters = (numberMeters*10);
				}
		else if (unit.equals("cm"))
				{
					finalFromMeters = (numberMeters*100);
				}
		else if (unit.equals("mm"))
				{
					finalFromMeters = (numberMeters*1000);
				}
		else if (unit.equals("um"))
				{
					finalFromMeters = (numberMeters*1000000);
				}
		else if (unit.equals("nm"))
				{
					finalFromMeters = (numberMeters*1000000000);
				}
			
		return finalFromMeters;
	}

	@Override
	public double convertToMeters(double numberUnits, String unit) 
	{
		double finalToMeters = 0.0;
		
			if (unit.equals("Gm")) //1 Gigameter = 1000000000 meters
				{
					finalToMeters = (numberUnits*1000000000);
				}
		else if (unit.equals("Mm")) //1 Megameter = 1000000 meters
				{
					finalToMeters = (numberUnits*1000000);
				}
		else if (unit.equals("km")) //1 kilometer = 1000 meters
				{
					finalToMeters = (numberUnits*1000);
				}
		else if (unit.equals("hm")) //1 hectometer = 100 meters
				{
					finalToMeters = (numberUnits*100);
				}
		else if (unit.equals("dam")) //1 decameter = 10 meters
				{
					finalToMeters = (numberUnits*10);
				}
		else if (unit.equals("m"))
				{
					finalToMeters = (numberUnits);
				}
		else if (unit.equals("dm")) //1 meter = 10 decimeters
				{
					finalToMeters = (numberUnits/10);
				}
		else if (unit.equals("cm")) //1 meter = 100 centimeters
				{
					finalToMeters = (numberUnits/100);
				}
		else if (unit.equals("mm")) //1 meter = 1000 millimeters
				{
					finalToMeters = (numberUnits/1000);
				}
		else if (unit.equals("um")) //1 meter = 1000000 micrometers
				{
					finalToMeters = (numberUnits/1000000);
				}
		else if (unit.equals("nm")) //1 meter = 1000000000 nanometers
				{
					finalToMeters = (numberUnits/1000000000);
				}

		return finalToMeters;
	}

	public static void main(String[] args) 
	{
		
	}

}
