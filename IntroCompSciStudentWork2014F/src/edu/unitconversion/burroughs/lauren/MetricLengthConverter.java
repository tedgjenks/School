package edu.unitconversion.burroughs.lauren;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double sNumUnit, String sUnit, String eUnit) {
		double numMeters = convertToMeters(sNumUnit, sUnit);
		
		eUnit = "cm"; 
		double numEUnit = convertFromMeters(numMeters, eUnit) ;		
		return numEUnit;
	}

	@Override
	public String convertForDisplay(double numSUnit, String sUnit, String eUnit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double convertFromMeters(double numMeters, String eUnit) {
		double endnum=0;
		if("nm".equals(eUnit))
			endnum = numMeters * 1000000000;
		else if("um".equals(eUnit))
			endnum = numMeters * 1000000;
		else if("mm".equals(eUnit))
			endnum = numMeters * 1000;
		else if("cm".equals(eUnit))
			endnum = numMeters * 100;
		else if("dm".equals(eUnit))
			endnum = numMeters * 10;
		else if("dam".equals(eUnit))
			endnum = numMeters / 10;
		else if("hm".equals(eUnit))
			endnum = numMeters / 100;
		else if("km".equals(eUnit))
			endnum = numMeters / 1000;
		else if("Mm".equals(eUnit))
			endnum = numMeters / 1000000;
		else if("Gm".equals(eUnit))
			endnum = numMeters / 1000000000;
		else if("m".equals(eUnit))
			endnum = numMeters * 1;
		return endnum;
		
	}

	@Override
	public double convertToMeters(double sNumUnit, String sUnit) {
		double meters = 0;
		if ("cm".equals(sUnit))
			meters = sNumUnit / 100 ;	
		else if("mm".equals(sUnit))
			meters = sNumUnit / 1000;
		else if("dm".equals(sUnit))
			meters = sNumUnit / 10;	
		else if("dam".equals(sUnit))
			meters = sNumUnit * 10;
		else if("Gm".equals(sUnit))
			meters = sNumUnit * 1000000000;
		else if("hm".equals(sUnit))
			meters = sNumUnit * 100;
		else if("Mm".equals(sUnit))
			meters = sNumUnit * 1000000;
		else if("km".equals(sUnit))
			meters = sNumUnit * 1000;
		else if("um".equals(sUnit))
			meters = sNumUnit / 1000000;
		else if("nm".equals(sUnit))
			meters = sNumUnit / 1000000000;
		else if("m".equals(sUnit));
			meters = sNumUnit / 1;
		return meters;
	}

}

