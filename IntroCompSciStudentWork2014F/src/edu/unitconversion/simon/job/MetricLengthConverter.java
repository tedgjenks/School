package edu.unitconversion.simon.job;

import edu.jenks.dist.unitconversion.*;
public class MetricLengthConverter 
implements Convertible {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public double convert(double amount, String met, String desireunit) {
		// TODO Auto-generated method stub
		
		double meters = convertToMeters(amount, met);
		return convertFromMeters(meters, desireunit);
		
	}

	@Override
	public String convertForDisplay(double numberStartingUnits, String stunit, String desireunit) {
		// TODO Auto-generated method stub
		double numberDesireunits = convert( numberStartingUnits, stunit, desireunit);
		
		boolean vaildunit= false;
			
			if (stunit.equals("Gm")) {
				vaildunit = true;
			}
			else if (stunit.equals("Mm")){
				vaildunit = true;
			}
			else if (stunit.equals("km")){
				vaildunit = true;
			}
			else if (stunit.equals("hm")){
				vaildunit = true;
			}
			else if (stunit.equals("dam")){
				vaildunit = true;
			}
			else if (stunit.equals("cm")){
				vaildunit = true;
			}
			else if (stunit.equals("mm")){
				vaildunit = true;
			}
			else if (stunit.equals("um")){
				vaildunit = true;
			}
			else if (stunit.equals("nm")){
				vaildunit = true;
			}
			else if (desireunit.equals("nm")){
				vaildunit = true;
			}
			else if (desireunit.equals("um")){
				vaildunit = true;
			}
			else if (desireunit.equals("mm")){
				vaildunit = true;
			}
			else if (desireunit.equals("cm")){
				vaildunit = true;
			}
			else if (desireunit.equals("dam")){
				vaildunit = true;
			}
			else if (desireunit.equals("hm")){
				vaildunit = true;
			}
			else if (desireunit.equals("km")){
				vaildunit = true;
			}
			else if (desireunit.equals("Mm")){
				vaildunit = true;
			}
			else if (desireunit.equals("Gm")){
				vaildunit = true;
			}
 
				return ("unit not supporterd");
	}	
	@Override
	public double convertFromMeters(double amount, String desireunit) {
		// TODO Auto-generated method stub
		double newunit= amount;
		if ("Gm" .equals(desireunit)){
			newunit=amount/1000000000;
		}
		if ("Mm" .equals(desireunit)){
			newunit=amount/1000000;
		}
		if ("km" .equals(desireunit)){
			newunit=amount/1000;
		}
		if ("hm" .equals(desireunit)){
			newunit=amount/100;
		}
		if ("dam" .equals(desireunit)){
			newunit=amount/10;
		}
		if ("cm".equals(desireunit)){
			newunit=amount*100;}
		
		if("mm".equals(desireunit)){
			newunit=amount*1000;}
		
		if("um".equals(desireunit)){
			newunit=amount*1000000;}
		
		if("nm" .equals(desireunit)){
			newunit=amount*1000000000;}
		
		if("dm" .equals(desireunit)){
			newunit=amount*10;

		}
	return newunit;
}
	@Override
	public double convertToMeters(double amount, String strUnit) {
		// TODO Auto-generated method stub
		double meters=amount;
		if ("cm".equals(strUnit)){
			meters=amount/100;}
		
		if("mm".equals(strUnit)){
			meters=amount/1000;}
		
		if("um".equals(strUnit)){
			meters=amount/1000000;}
		
		if("nm" .equals(strUnit)){
			meters=amount/1000000000;}
		
		if("dm" .equals(strUnit)){
			meters=amount/10;	
		}
		if ("Gm" .equals(strUnit)){
			meters=amount*1000000000;
		}
		if ("Mm" .equals(strUnit)){
			meters=amount*1000000;
		}
		if ("km" .equals(strUnit)){
			meters=amount*1000;
		}
		if ("hm" .equals(strUnit)){
			meters=amount*100;
		}
		if ("dam" .equals(strUnit)){
			meters=amount*10;
		}
		
		return meters;
	}

}
