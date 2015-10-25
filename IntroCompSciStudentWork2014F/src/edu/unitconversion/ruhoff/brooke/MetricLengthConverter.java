package edu.unitconversion.ruhoff.brooke;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double numMeters, String arg1, String desiredUnit) {
		double meters=convertToMeters(numMeters, desiredUnit);
		
		return convertFromMeters(meters, desiredUnit);
	}

	@Override
	public String convertForDisplay(double newUnit, String startingUnit, String desiredUnit) {
		boolean validUnit=false;
		if (startingUnit.equals("dm")){
			validUnit=true;
		}
		else if (startingUnit.equals("nm")){
			validUnit=true;
		}
		else if (startingUnit.equals("um")){
			validUnit=true;
		}
		else if (startingUnit.equals("cm")){
			validUnit=true;
		}
		else if (startingUnit.equals("mm")){
			validUnit=true;
		}
		else if (startingUnit.equals("km")){
			validUnit=true;
		}
		else if (startingUnit.equals("dam")){
			validUnit=true;
		}
		else if (startingUnit.equals("Mm")){
			validUnit=true;
		}
		else if (startingUnit.equals("Gm")){
			validUnit=true;
		}
		else if (startingUnit.equals("hm")){
			validUnit=true;
		}
		if (desiredUnit.equals("dm")){
		validUnit=true;
		}
		else if (desiredUnit.equals("nm")){
			validUnit=true;
		}
		else if (desiredUnit.equals("um")){
			validUnit=true;
		}
		else if (desiredUnit.equals("cm")){
			validUnit=true;
		}
		else if (desiredUnit.equals("mm")){
			validUnit=true;
		}
		else if (desiredUnit.equals("km")){
			validUnit=true;
		}
		else if (desiredUnit.equals("dam")){
			validUnit=true;
		}
		else if (desiredUnit.equals("Mm")){
			validUnit=true;
		}
		else if (desiredUnit.equals("Gm")){
			validUnit=true;
		}
		else if (desiredUnit.equals("hm")){
			validUnit=true;
		}
		return "unit not supported";
	}

	@Override
	public double convertFromMeters(double numMeters, String desiredUnit) {
		double newUnit=numMeters;
		if ("dm".equals(desiredUnit)){
			newUnit=numMeters*10;
		}
		else if ("nm".equals(desiredUnit)){
			newUnit=numMeters*1000000000;
		}
		else if ("um".equals(desiredUnit)){
			newUnit=numMeters*1000000;
		}
		else if ("mm".equals(desiredUnit)){
			newUnit=numMeters*1000;
		}
		else if ("cm".equals(desiredUnit)){
			newUnit=numMeters*100;
		}
		else if ("km".equals(desiredUnit)){
			newUnit=numMeters/1000;
		}
		else if ("dam".equals(desiredUnit)){
			newUnit=numMeters/10;
		}
		else if ("Mm".equals(desiredUnit)){
			newUnit=numMeters/1000000;
		}
		else if ("Gm".equals(desiredUnit)){
			newUnit=numMeters/1000000000;
		}
		else if ("hm".equals(desiredUnit)){
			newUnit=numMeters/100;
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
