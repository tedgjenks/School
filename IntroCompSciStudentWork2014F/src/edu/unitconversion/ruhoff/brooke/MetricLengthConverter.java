package edu.unitconversion.ruhoff.brooke;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String convertForDisplay(double arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double convertFromMeters(double numMeters, String desiredUnit) {
		double newUnit=0;
		if ("dm".equals(desiredUnit)){
			newUnit=numMeters*10;
		}
		else if ("nm".equals(desiredUnit)){
			newUnit=numMeters*1000000000;
		}
		else if ("um".equals(desiredUnit)){
			newUnit=numMeters*1000000;
		}
		
		return newUnit;
	}

	@Override
	public double convertToMeters(double arg0, String arg1) {
		double meters=0;
		if ("dm".equals(arg1)){
			meters=arg0/10;
		}
		else if ("nm".equals(arg1)){
			meters=arg0/1000000000;
		}
		else if ("um".equals(arg1)){
			meters=arg0/1000000;
		}
		else if ("mm".equals(arg1)){
			meters=arg0/1000;
		}
		else if ("cm".equals(arg1)){
			meters=arg0/100;
		}
		else if ("km".equals(arg1)){
			meters=arg0*1000;
		}
		else if ("dam".equals(arg1)){
			meters=arg0*10;
		}
		else if ("Mm".equals(arg1)){
			meters=arg0*1000000;
		}
		else if ("Gm".equals(arg1)){
			meters=arg0*1000000000;
		}
		else if ("hm".equals(arg1)){
			meters=arg0*100;
		}
		return meters;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
