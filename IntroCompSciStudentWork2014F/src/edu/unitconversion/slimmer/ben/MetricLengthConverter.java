package edu.unitconversion.slimmer.ben;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter
implements Convertible {
	String[] prefix = new String[]{"nm","um", "mm", "cm","dm","m","dam","hm","km","Mm","Gm"};
	double[] multipliers = new double[]{.000000001,.000001,.001,.01,.1,1,10,100,1000,1000000,1000000000};
	public static void main(String[] args){
		
	}
	public double convertToMeters(double numberunits, String unit) {
		double returnvalue=0;
		int found=5;
		for (int i=0; i<=10; i++) {
			if (unit.equals(prefix[i])) {
				found=i;
			}
		}
		returnvalue= numberunits*multipliers[found];
		return returnvalue;
	}
	@Override
	public double convert(double arg0, String starting, String desired) {
		double returnvalue= convertToMeters(arg0,starting);
		returnvalue= convertFromMeters(returnvalue,desired);
		return returnvalue;
	}
	@Override
	public String convertForDisplay(double arg0, String arg1, String arg2) {
		double returnvalue=0;
		int i=0;
		while(arg1.equals(prefix[i])==false){
			if(i<10)
				i++;
			else
				return "Unit not supported.";
		}
		i=0;
		while(arg2.equals(prefix[i])==false){
			if(i<10)
				i++;
			else
				return "Unit not supported.";
		}
		returnvalue= convert(arg0,arg1,arg2);
		String returnstring= arg0+" "+arg1+" = "+returnvalue+" "+arg2+".";
		return returnstring;
	}
	@Override
	public double convertFromMeters(double arg0, String arg1) {
		double returnvalue=0;
		int found=5;
		for(int i=0;i<=10;i++){
			if (arg1.equals(prefix[i]))
				found=i;
		}
		returnvalue= arg0/multipliers[found];
		return returnvalue;
	}

}
