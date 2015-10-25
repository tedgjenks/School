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
		String returnString = new String("");
		double runConvert = convert(numberStartingUnits, startingUnit, desiredUnit);
		
		if (runConvert > 0)
		{
			returnString = (numberStartingUnits + " " + startingUnit + " = " + runConvert + " " + desiredUnit + ".");
			return returnString;
		}
		else
		{
			returnString = ("Unit not supported.");
			return returnString;
		}
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit)
	{	
			if (unit.equals("Gm")) //If converting to Gigameters, take number of meters and divide by 1000000000
				{
					return (numberMeters/1000000000);
				}
		else if (unit.equals("Mm"))
				{
					return (numberMeters/1000000);
				}
		else if (unit.equals("km"))
				{
					return (numberMeters/1000);
				}
		else if (unit.equals("hm"))
				{
					return (numberMeters/100);
				}	
		else if (unit.equals("dam"))
				{
					return (numberMeters/10);
				}
		else if (unit.equals("m"))
				{
					return (numberMeters);
				}
		else if (unit.equals("dm"))
				{
					return (numberMeters*10);
				}
		else if (unit.equals("cm"))
				{
					return (numberMeters*100);
				}
		else if (unit.equals("mm"))
				{
					return (numberMeters*1000);
				}
		else if (unit.equals("um"))
				{
					return (numberMeters*1000000);
				}
		else if (unit.equals("nm"))
				{
					return (numberMeters*1000000000);
				}
		else
				{
					return -1;
				}
	}

	@Override
	public double convertToMeters(double numberUnits, String unit) 
	{
			if (unit.equals("Gm")) //1 Gigameter = 1000000000 meters
				{
					return (numberUnits*1000000000);
				}
		else if (unit.equals("Mm")) //1 Megameter = 1000000 meters
				{
					return (numberUnits*1000000);
				}
		else if (unit.equals("km")) //1 kilometer = 1000 meters
				{
					return (numberUnits*1000);
				}
		else if (unit.equals("hm")) //1 hectometer = 100 meters
				{
					return (numberUnits*100);
				}
		else if (unit.equals("dam")) //1 decameter = 10 meters
				{
					return (numberUnits*10);
				}
		else if (unit.equals("m"))
				{
					return (numberUnits);
				}
		else if (unit.equals("dm")) //1 meter = 10 decimeters
				{
					return (numberUnits/10);
				}
		else if (unit.equals("cm")) //1 meter = 100 centimeters
				{
					return (numberUnits/100);
				}
		else if (unit.equals("mm")) //1 meter = 1000 millimeters
				{
					return (numberUnits/1000);
				}
		else if (unit.equals("um")) //1 meter = 1000000 micrometers
				{
					return (numberUnits/1000000);
				}
		else if (unit.equals("nm")) //1 meter = 1000000000 nanometers
				{
					return (numberUnits/1000000000);
				}
		else
				{
					return -1;
				}

	}

	public static void main(String[] args) 
	{
		
	}

}
