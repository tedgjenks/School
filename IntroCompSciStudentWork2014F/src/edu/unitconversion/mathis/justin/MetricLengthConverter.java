package edu.unitconversion.mathis.justin;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double amount, String unit, String desired) {
		double meters = convertToMeters(amount, unit);
		return convertFromMeters(meters, desired);
	}

	@Override
	public String convertForDisplay(double amount, String unit, String desired) {
		boolean validUnit = false;
		if (unit.equals("Gm")){
			validUnit = true;
		}
		else if (unit.equals("Mm")){
			validUnit = true;
		}
		else if (unit.equals("km")){
			validUnit = true;
		}
		else if (unit.equals("hm")){
			validUnit = true;
		}
		else if (unit.equals("dam")){
			validUnit = true;
		}
		else if (unit.equals("dm")){
			validUnit = true;
		}
		else if (unit.equals("cm")){
			validUnit = true;
		}
		else if (unit.equals("mm")){
			validUnit = true;
		}
		else if (unit.equals("um")){
			validUnit = true;
		}
		else if (unit.equals("nm")){
			validUnit = true;
		}
		if (validUnit){
			
		}
		else {
			return "Unit not supported.";
		}
		boolean validUnit2 = false;
		if (desired.equals("Gm")){
			validUnit2 = true;
		}
		else if (desired.equals("Mm")){
			validUnit2 = true;
		}
		else if (desired.equals("km")){
			validUnit2 = true;
		}
		else if (desired.equals("hm")){
			validUnit2 = true;
		}
		else if (desired.equals("dam")){
			validUnit2 = true;
		}
		else if (desired.equals("dm")){
			validUnit2 = true;
		}
		else if (desired.equals("cm")){
			validUnit2 = true;
		}
		else if (desired.equals("mm")){
			validUnit2 = true;
		}
		else if (desired.equals("um")){
			validUnit2 = true;
		}
		else if (desired.equals("nm")){
			validUnit2 = true;
		}
		if (validUnit2) {
			
		}
		else {
			return "Unit not supported.";
		}
		double amountofdesired = convert(amount,unit,desired);
		String result = amount + unit + (" = ") + amountofdesired + desired;
		System.out.print(result);
		return result;
	}

	@Override
	public double convertFromMeters(double amount, String unit) {
		if (unit.equals("Gm")){
			amount = (amount / 1000000000);
		}
		
		if (unit.equals("Mm")){
			amount = (amount / 1000000);
		}
		
		if (unit.equals("km")){
			amount = (amount / 1000);
		}
		
		if (unit.equals("hm")){
			amount = (amount / 100);
		}
		if (unit.equals("dam")){
			amount = (amount / 10);
		}
		
		if (unit.equals("dm")){
			amount = (amount * 10);
			
		}
		if (unit.equals("cm")){
			amount = (amount * 100);
		}
		
		if (unit.equals("mm")){
			amount = (amount * 1000);
			
		}
		if (unit.equals("um")){
			amount = (amount * 1000000);
			
		}
		if (unit.equals("nm")){
			amount = (amount * 1000000000);
		}
		return amount;
	}

	@Override
	public double convertToMeters(double amount, String unit) {
		if (unit.equals("Gm")){
			amount = (amount * 1000000000);
		}
		
		if (unit.equals("Mm")){
			amount = (amount * 1000000);
		}
		
		if (unit.equals("km")){
			amount = (amount * 1000);
		}
		
		if (unit.equals("hm")){
			amount = (amount * 100);
		}
		if (unit.equals("dam")){
			amount = (amount * 10);
		}
		
		if (unit.equals("dm")){
			amount = (amount / 10);
			
		}
		if (unit.equals("cm")){
			amount = (amount / 100);
		}
		
		if (unit.equals("mm")){
			amount = (amount / 1000);
			
		}
		if (unit.equals("um")){
			amount = (amount / 1000000);
			
		}
		if (unit.equals("nm")){
			amount = (amount / 1000000000);
		}
		return amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
