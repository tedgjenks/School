package edu.unitconversion.ruhoff.brooke;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double numUnit, String unit, String desiredUnit) {
		double meters=convertToMeters(numUnit, desiredUnit);
		double numDesiredUnits=convertFromMeters(meters, desiredUnit);
		return numDesiredUnits;
	}
	

	
	@Override
	public String convertForDisplay(double newUnit, String unit, String desiredUnit) {
		
		boolean validUnit=false;
		if (unit.equals("dm")){
			validUnit=true;
		}
		else if (unit.equals("nm")){
			validUnit=true;
		}
		else if (unit.equals("um")){
			validUnit=true;
		}
		else if (unit.equals("cm")){
			validUnit=true;
		}
		else if (unit.equals("mm")){
			validUnit=true;
		}
		else if (unit.equals("km")){
			validUnit=true;
		}
		else if (unit.equals("dam")){
			validUnit=true;
		}
		else if (unit.equals("Mm")){
			validUnit=true;
		}
		else if (unit.equals("Gm")){
			validUnit=true;
		}
		else if (unit.equals("hm")){
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
		if (validUnit==true){
		 double numberDesiredUnits = convert(newUnit, unit, desiredUnit);
		 String result=(newUnit + " " + unit + " = " + numberDesiredUnits + " " + desiredUnit);
		 return result;
		}
		else return "unit not supported";
	}

	@Override
	public double convertFromMeters(double meters, String desiredUnit) {
		double numDesiredUnit = 0;
		if ("dm".equals(desiredUnit)){
			numDesiredUnit=meters*10;
		}
		else if ("nm".equals(desiredUnit)){
			numDesiredUnit=meters*1000000000;
		}
		else if ("um".equals(desiredUnit)){
			numDesiredUnit=meters*1000000;
		}
		else if ("mm".equals(desiredUnit)){
			numDesiredUnit=meters*1000;
		}
		else if ("cm".equals(desiredUnit)){
			numDesiredUnit=meters*100;
		}
		else if ("km".equals(desiredUnit)){
			numDesiredUnit=meters/1000;
		}
		else if ("dam".equals(desiredUnit)){
			numDesiredUnit=meters/10;
		}
		else if ("Mm".equals(desiredUnit)){
			numDesiredUnit=meters/1000000;
		}
		else if ("Gm".equals(desiredUnit)){
			numDesiredUnit=meters/1000000000;
		}
		else if ("hm".equals(desiredUnit)){
			numDesiredUnit=meters/100;
		}
		else if ("m".equals(desiredUnit)){
			numDesiredUnit=meters;
		}
		return numDesiredUnit;
	}

	@Override
	public double convertToMeters(double numUnits, String unit) {
		double meters = 0;
		if ("dm".equals(unit)){
			meters=numUnits/10;
		}
		else if ("nm".equals(unit)){
			meters=numUnits/1000000000;
		}
		else if ("um".equals(unit)){
			meters=numUnits/1000000;
		}
		else if ("mm".equals(unit)){
			meters=numUnits/1000;
		}
		else if ("cm".equals(unit)){
			meters=numUnits/100;
		}
		else if ("km".equals(unit)){
			meters=numUnits*1000;
		}
		else if ("dam".equals(unit)){
			meters=numUnits*10;
		}
		else if ("Mm".equals(unit)){
			meters=numUnits*1000000;
		}
		else if ("Gm".equals(unit)){
			meters=numUnits*1000000000;
		}
		else if ("hm".equals(unit)){
			meters=numUnits*100;
		}
		else if ("m".equals(unit)){
			meters=numUnits;
		}
		return meters;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}