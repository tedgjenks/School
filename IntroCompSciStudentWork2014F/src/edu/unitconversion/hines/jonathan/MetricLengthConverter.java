package edu.unitconversion.hines.jonathan;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {
// 
	@Override
	public double convert(double numberStartingUnits, String startingUnit, String desiredUnit) {
		double givenUnit = convertToMeters(numberStartingUnits, startingUnit);	
		return convertFromMeters(givenUnit, desiredUnit);
	}

	@Override
	public String convertForDisplay(double numberStartingunits, String startingUnit, String desiredUnit) {
		double convertOutput = convert(numberStartingunits, startingUnit, desiredUnit);
		if (convertOutput < 0)
			return ("Unit not supported.");
		else
			System.out.println(numberStartingunits + " " + startingUnit + " = " + convertOutput + " " + desiredUnit + ".");
			return numberStartingunits + " " + startingUnit + " = " + convertOutput + " " + desiredUnit + ".";

	}

	@Override
	public double convertFromMeters(double meters, String unit) {
		
		double m = meters;
		double dam = (m / 10);
		double hm = (m / 100);
		double km = (m / 1000);
		double Mm = (m / 1000000);
		double Gm = (m / 1000000000);
		double dm = (m * 10);
		double cm = (m * 100);
		double mm = (m * 1000);
		double um = (m * 1000000);
		double nm = (m * 1000000000);	
		

		if (unit.equals("m")){
			return m;
		} 
		else if (unit.equals("dam")){
			return dam; 	
		}	
		else if (unit.equals("hm")){
			return hm;
		}
		else if (unit.equals("km")){
			return km;
		}
		else if (unit.equals("Mm")){
			return Mm;
		}
		else if (unit.equals("Gm")){
			return Gm;
		}
		 else if (unit.equals("dm")){
			return dm;
		}
		 else if (unit.equals("cm")){
			return cm;
		}
		 else if (unit.equals("mm")){
			return mm;
		}
		 else if (unit.equals("um")){
			return um;
		}
		 else if (unit.equals("nm")){
			return nm;
		}
		 else return -1;
		
	}

	@Override
	public double convertToMeters(double givenUnit, String unit) {
		double m = givenUnit;
		double damtom = (m * 10);
		double hmtom = (m * 100);
		double kmtom = (m * 1000);
		double megamtom = (m * 1000000);
		double gmtom = (m * 1000000000);
		double dmtom = (m / 10);
		double cmtom = (m / 100);
		double mmtom = (m / 1000);
		double micromtom = (m / 1000000);
		double nmtom = (m / 1000000000);
		
		if (unit.equals("m")){
			return m;
		} 
		else if (unit.equals("dam")){
			return damtom; 	
		}	
		else if (unit.equals("hm")){
			return hmtom;
		}
		else if (unit.equals("km")){
			return kmtom;
		}
		else if (unit.equals("Mm")){
			return megamtom;
		}
		else if (unit.equals("Gm")){
			return gmtom;
		}
		 else if (unit.equals("dm")){
			return dmtom;
		}
		 else if (unit.equals("cm")){
			return cmtom;
		}
		 else if (unit.equals("mm")){
			return mmtom;
		}
		 else if (unit.equals("um")){
			return micromtom;
		}
		 else if (unit.equals("nm")){
			return nmtom;
		}
		 else return -1;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
