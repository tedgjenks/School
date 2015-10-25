package edu.unitconversion.piland.will;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double amount, String unit, String desiredUnit) {
		// TODO Auto-generated method stub
		double declareMeters = convertToMeters(amount, unit);
		return convertFromMeters(declareMeters, desiredUnit);
		
	}

	@Override
	public String convertForDisplay(double amount, String unit, String desiredUnit) {
		// TODO Auto-generated method stub
		boolean validUnit = false;
		if (unit.equals("Gm")){
			validUnit = true;
		}	
		if (unit.equals("Mm")){
			validUnit = true;
		}
		if (unit.equals("km")){
			validUnit = true;
		}
		if (unit.equals("m")){
			validUnit = true;
		}
		if (unit.equals("dm")){
			validUnit = true;
		}
		if (unit.equals("cm")){
			validUnit = true;
		}
		if (unit.equals("mm")){
			validUnit = true;
		}
		if (unit.equals("um")){
			validUnit = true;
		}
		if (unit.equals("nm")){
			validUnit = true;
		}
		if (validUnit) {
			
		}
		else 
			
			return "Unit Not Supported";
		
		if (desiredUnit.equals("Gm")){
			validUnit = true;
		}	
		if (desiredUnit.equals("Mm")){
			validUnit = true;
		}
		if (desiredUnit.equals("km")){
			validUnit = true;
		}
		if (desiredUnit.equals("m")){
			validUnit = true;
		}
		if (desiredUnit.equals("dm")){
			validUnit = true;
		}
		if (desiredUnit.equals("cm")){
			validUnit = true;
		}
		if (desiredUnit.equals("mm")){
			validUnit = true;
		}
		if (desiredUnit.equals("um")){
			validUnit = true;
		}
		if (desiredUnit.equals("nm")){
			validUnit = true;
		if (validUnit) {
				
		}
		else 
				
			return "Unit Not Supported";	
		}
		double amountOfDesired = convert(amount, unit, desiredUnit);
		String result = amount + unit + " = " + amountOfDesired + desiredUnit;
		return result;
	}

	@Override
	public double convertFromMeters(double amount, String unit) {
		// TODO Auto-generated method stub
		if (unit.equals("nm")){
			amount = (amount * 1000000000);
		}
		if (unit.equals("um")){
			amount = (amount * 1000000);
		}
		if (unit.equals("mm")){
			amount = (amount * 1000);
		}
		if (unit.equals("cm")){
			amount = (amount * 100);
		}
		if (unit.equals("dm")){
			amount = (amount * 10);
		}
		if (unit.equals("m")){
			return amount;
		}
		if (unit.equals("dam")){
			amount = (amount / 10);
		}
		if (unit.equals("hm")){
			amount = (amount / 100);
		}
		if (unit.equals("km")){
			amount = (amount / 1000);
		}
		if (unit.equals("Mm")){
			amount = (amount / 1000000);
		}
		if (unit.equals("Gm")){
			amount = (amount / 1000000000);
		}
		return amount;
	}

	@Override
	public double convertToMeters(double amount, String unit) {
		// TODO Auto-generated method stub
		if (unit.equals("nm")){
			amount = (amount / 1000000000);
		}
		if (unit.equals("um")){
			amount = (amount / 1000000);
		}
		if (unit.equals("mm")){
			amount = (amount / 1000);
		}
		if (unit.equals("cm")){
			amount = (amount / 100);
		}
		if (unit.equals("dm")){
			amount = (amount / 10);
		}
		if (unit.equals("dam")){
			amount = (amount * 10);
		}
		if (unit.equals("hm")){
			amount = (amount * 100);
		}
		if (unit.equals("km")){
			amount = (amount * 1000);
		}
		if (unit.equals("Mm")){
			amount = (amount * 1000000);
		}
		if (unit.equals("Gm")){
			amount = (amount * 1000000000);
		}
		return amount;
		
	}

	
		// TODO Auto-generated method stub

	

}
