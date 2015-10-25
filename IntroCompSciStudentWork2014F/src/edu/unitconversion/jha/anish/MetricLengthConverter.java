package edu.unitconversion.jha.anish;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {

	@Override
	public double convert(double amount , String met, String dunit) {
		double meters = convertToMeters(amount, met);
		return convertFromMeters( meters, dunit);
	}

	@Override
	public String convertForDisplay (double amount, String stunit, String dunit) {
		
		boolean validUnit = false;
		
		if (stunit.equals ("Gm")) {
			validUnit=true;
		}
	
		if (stunit.equals("Mm")) {
			validUnit=true;
		}
		
		if (stunit.equals("km")) {
			validUnit=true;
		}
		
		if (stunit.equals("hm")) {
			validUnit=true;
		}
		
		if (stunit.equals("dam")) {
			validUnit=true;
		}
		
		if (stunit.equals("dm")) {
			validUnit=true;
		}
		
		if (stunit.equals("cm")) {
			validUnit=true;
		}
		
		if (stunit.equals("mm")) {
			validUnit=true;
		}
	
		if (stunit.equals("um")) {
			validUnit=true;
		}
	
		if (stunit.equals("nm")) {
			validUnit=true;
		
			}	return null;
	}
	
	@Override
	public double convertFromMeters(double met, String dunit) {
		double amount = 0;
			
		if (dunit.equals("Gm")){
			amount = (met/1000000000);
		}
		
		if (dunit.equals("Mm")){
			amount = (met/1000000);
		}
		
		if (dunit.equals("km")){
			amount = (met/1000);
		}	
		
		if (dunit.equals("hm")){
				amount = (met/100);
		}
			
		if (dunit.equals("dam")){
			amount = (met/10);
		}
		
		if (dunit.equals("m")){
			amount = met;
		}
		
		if (dunit.equals("dm")){
			amount = (met*10);
		}
	
		if (dunit.equals("cm")){
			amount = (met*100);
		}
		
		if (dunit.equals("mm")){
			amount = (met*100);
		}
		
		if (dunit.equals("um")){
			amount = (met*1000000);
		}
	
		if (dunit.equals("nm")){
			amount = (met*1000000000);
		
		}
		
		return amount; 
		
	}
			
	
	
	
		

	@Override
	public double convertToMeters(double stunit, String astunit) {
		double meters = 0;
		
		if ("Gm".equals(astunit)){
			meters = (stunit*1000000000);
	}
		
		if ("Mm".equals(astunit)){
			meters = (stunit*1000000);
	}
		
		if ("km".equals(astunit)){
			meters = (stunit*1000);
	}
			
		if ("hm".equals(astunit)){
			meters = (stunit*100);
	}
		
		if ("dam".equals(astunit)){
			meters = (stunit*10);
	}
		
		if ("m".equals(astunit)){
			meters = stunit;
	}
		
		if ("dm".equals(astunit)){
			meters = (stunit/10);
	}
		
		if ("cm".equals(astunit)){
			meters = (stunit/100);
	}	
		
		if ("mm".equals(astunit)){
			meters = (stunit/1000);
	}	
		
		if ("um".equals(astunit)){
			meters = (stunit/1000000);
	}	
		
		if ("nm".equals(astunit)){
			meters = (stunit/1000000000);
	}	
		
		return meters;
		
		}
		}