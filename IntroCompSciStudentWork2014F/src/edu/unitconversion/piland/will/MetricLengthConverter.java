package edu.unitconversion.piland.will;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double amount, String unit, String desiredUnit) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String convertForDisplay(double amount, String unit, String desiredUnit) {
		// TODO Auto-generated method stub
		return null;
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
			
		}
		
		
		
		return 0;
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
