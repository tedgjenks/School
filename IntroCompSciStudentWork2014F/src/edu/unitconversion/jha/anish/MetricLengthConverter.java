package edu.unitconversion.jha.anish;

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
	public double convertFromMeters(double met, String dunit) {
		double amount = 0;
		
		if (dunit.equals("Gm")){
			amount = (met/1000000000);
		}
		
		if (dunit.equals("Mm")){
			amount = (met/1000000);
		}
		
		if (dunit.equals("Km")){
			amount = (met/1000);
		
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
		
	
		}
		return amount;	
		
		
		}

	@Override
	public double convertToMeters(double arg0, String arg1) {
		// TODO Auto-generated method stub
		return 0;

		}
		}