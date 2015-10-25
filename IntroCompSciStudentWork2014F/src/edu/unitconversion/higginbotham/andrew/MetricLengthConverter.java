package edu.unitconversion.higginbotham.andrew;

import edu.jenks.dist.unitconversion.Convertible;

public class MetricLengthConverter implements Convertible {
	
	
	
	
	
	@Override
	public double convert(double numberStartingUnits, String startingUnit, String endUnit) {
		double result = convertToMeters(numberStartingUnits, startingUnit);
		return convertFromMeters(result, endUnit);
	}

	@Override
	public String convertForDisplay(double numberUnits, String startingUnit, String endUnit) {
		
		double output = convert( numberUnits, startingUnit, endUnit);
		if(output < 0)
			return ("Unit not supported.");
		
		else
			System.out.println(startingUnit + " " + numberUnits + " = " + output + " " + endUnit + ".");
		
		return numberUnits + " " + startingUnit + " = " + output + " " + endUnit + ".";
	}

	@Override
	public double convertFromMeters(double numberMeters, String unit) {
		double m =  numberMeters;
		double dam = m/10;
		double hm = m/100;
		double km = m/1000;
		double Mm = m/1000000;
		double Gm = m/1000000000;
		double dm = m*10;
		double cm = m*100;
		double mm = m*1000;
		double um = m*1000000;
		double nm = m*1000000000;
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
		
		else if (unit.equals("dm")){
			return dm;
		}
		
		else if (unit.equals("cm")){
			return cm;
		}
		
		else if (unit.equals("mm")){
			return mm;
		}
		
		else if (unit.equals("Gm")){
			return Gm;
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
	public double convertToMeters(double numberOfUnits, String unit) {
		
		double m = numberOfUnits;
		double dam = m*10;
		double hm = m*100;
		double km = m*1000;
		double Mm = m*1000000;
		double Gm = m*1000000000;
		double dm = m/10;
		double cm = m/100;
		double mm = m/1000;
		double um = m/1000000;
		double nm = m/1000000000;
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
		
		else if (unit.equals("dm")){
			return dm;
		}
		
		else if (unit.equals("cm")){
			return cm;
		}
		
		else if (unit.equals("mm")){
			return mm;
		}
		
		else if (unit.equals("Gm")){
			return Gm;
		}
		
		else if (unit.equals("um")){
			return um;
		}
		
		else if (unit.equals("nm")){
			return nm;
		}
		else return -1;
		
	}

}
