package edu.unitconversion.simon.job;

import edu.jenks.dist.unitconversion.*;
public class MetricLengthConverter 
implements Convertible {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

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
	public double convertFromMeters(double nunmeters, String desireunit) {
		// TODO Auto-generated method stub
		double newunit=0;
		if ("Gm" .equals(desireunit)){
			newunit=nunmeters/1000000000;
		}
		if ("Mm" .equals(desireunit)){
			newunit=nunmeters/1000000;
		}
		if ("km" .equals(desireunit)){
			newunit=nunmeters/1000;
		}
		if ("hm" .equals(desireunit)){
			newunit=nunmeters/100;
		}
		if ("dam" .equals(desireunit)){
			newunit=nunmeters/10;
		}
		if ("cm".equals(desireunit)){
			newunit=nunmeters*100;}
		
		if("mm".equals(desireunit)){
			newunit=nunmeters*1000;}
		
		if("um".equals(desireunit)){
			newunit=nunmeters*1000000;}
		
		if("nm" .equals(desireunit)){
			newunit=nunmeters*1000000000;}
		
		if("dm" .equals(desireunit)){
			newunit=nunmeters*10;
		}
	return newunit;
}
	@Override
	public double convertToMeters(double arg0, String arg1) {
		// TODO Auto-generated method stub
		double meters=0;
		if ("cm".equals(arg1)){
			meters=arg0/100;}
		
		if("mm".equals(arg1)){
			meters=arg0/1000;}
		
		if("um".equals(arg1)){
			meters=arg0/1000000;}
		
		if("nm" .equals(arg1)){
			meters=arg0/1000000000;}
		
		if("dm" .equals(arg1)){
			meters=arg0/10;	
		}
		if ("Gm" .equals(arg1)){
			meters=arg0*1000000000;
		}
		if ("Mm" .equals(arg1)){
			meters=arg0*1000000;
		}
		if ("km" .equals(arg1)){
			meters=arg0*1000;
		}
		if ("hm" .equals(arg1)){
			meters=arg0*100;
		}
		if ("dam" .equals(arg1)){
			meters=arg0*10;
		}

		return meters;
	}

}
