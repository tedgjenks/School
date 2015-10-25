package edu.unitconversion.mariscal.juan;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double amount, String stUnit, String desUnit) {
		// TODO Auto-generated method stub
		double meters = convertToMeters(amount, stUnit);		
		return convertFromMeters(meters, desUnit);
		
	}

	@Override
	public String convertForDisplay(double amount, String stUnit, String desUnit) {
		// TODO Auto-generated method stub
		boolean validUnit = false;
		
		if (stUnit.equals ("Gm")) {	
			validUnit=true;
		
		}
		else if (stUnit.equals("Mm")) {
			validUnit=true;
		}
		else if (stUnit.equals("km")) {
			validUnit=true;
		}
		else if (stUnit.equals("hm")) {
			validUnit=true;
		}
		else if (stUnit.equals("dam")) {
			validUnit=true;
		}
		else if (stUnit.equals("dm")) {
			validUnit=true;
		}
		else if (stUnit.equals("cm")) {
			validUnit=true;
		}
		else if (stUnit.equals("mm")) {
			validUnit=true;
		}
		else if (stUnit.equals("um")) {
			validUnit=true;
		}
		else if (stUnit.equals("nm")) {
			validUnit=true;
		}
		if (validUnit){
			
		}
		else{
		
			return "Unit not supported";
			
		}
		
		boolean svalidUnit=false;
		if (desUnit.equals ("Gm")) {	
			svalidUnit=true;
		}
		else if (desUnit.equals("Mm")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("km")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("hm")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("dam")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("dm")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("cm")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("mm")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("um")) {
			svalidUnit=true;
		}
		else if (desUnit.equals("nm")) {
			svalidUnit=true;
		}
		if (svalidUnit){
			
		}
		else{
			return "Unit not supported";
		}
			double amtdesired= convert(amount, stUnit, desUnit); 
			String result = (amount) + (stUnit) + ("=") + (amtdesired) + (desUnit);
			return result;
			
		
			
		
	}

	@Override
	public double convertFromMeters(double amount, String desUnit) {
		// TODO Auto-generated method stub
		
		if (desUnit.equals("Gm")){
			amount =(amount/1000000000);
		}
		else if (desUnit.equals("Mm")){
			amount =(amount/1000000);}
		
		else if (desUnit.equals("km")){
			amount =(amount/1000);}
		
		else if (desUnit.equals("hm")){
			amount =(amount/100);}
		
		else if (desUnit.equals("dam")){
			amount =(amount/10);}
		
		else if (desUnit.equals("dm")){
			amount =(amount*10);}
		
		else if (desUnit.equals("cm")){
			amount =(amount*100);}
		
		else if (desUnit.equals("mm")){
			amount =(amount*1000);}
		
		else if (desUnit.equals("um")){
			amount =(amount*1000000);}
		
		else if (desUnit.equals("nm")){
			amount =(amount*1000000000);}
		return amount;
	}

	@Override
	public double convertToMeters(double amount, String stUnit) {
		// TODO Auto-generated method stub
		if (stUnit.equals("nm")){
			amount=(amount/1000000000);
			
		}
		else if 	(stUnit.equals("um")){
			amount=(amount/1000000);
		}
		else if (stUnit.equals("mm")){
			amount=(amount/1000);
		}
		else if (stUnit.equals("cm")){
			amount=(amount/100);
		}
		else if(stUnit.equals("dm")){
			amount=(amount/10);
		}
		else if (stUnit.equals("dam")){
			amount=(amount*10);
		}
		else if(stUnit.equals("hm")){
			amount=(amount * 100);
		}
		else if (stUnit.equals("km")){
			amount=(amount*1000);
		}
		else if (stUnit.equals("Mm")){
			amount=(amount *1000000);
		}
		else if (stUnit.equals("Gm")){
			amount=(amount*1000000000);
		}
		return amount;
	}



}
