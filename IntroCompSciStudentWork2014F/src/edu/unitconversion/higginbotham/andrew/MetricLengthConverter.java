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
			System.out.println("Unit not supported.");
		
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
		double meters = 0;
		if (unit.equals("m")){
			meters =  m;
		}
		
		else if (unit.equals("dam")){
			meters = dam;
		}
		
		else if (unit.equals("hm")){
			meters = hm;
		}
		
		else if (unit.equals("km")){
			meters = km;
		}
		
		else if (unit.equals("Mm")){
			meters = Mm;
		}
		
		else if (unit.equals("dm")){
			meters = dm;
		}
		
		else if (unit.equals("cm")){
			meters = cm;
		}
		
		else if (unit.equals("mm")){
			meters = mm;
		}
		
		else if (unit.equals("Gm")){
			meters = Gm;
		}
		
		else if (unit.equals("um")){
			meters = um;
		}
		
		else if (unit.equals("nm")){
			meters = nm;
		}
		return meters;
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
		double meters = 0; 
		if (unit.equals("m")){
			meters = m;
		}
		
		else if (unit.equals("dam")){
			meters = dam;
		}
		
		else if (unit.equals("hm")){
			meters = hm;
		}
		
		else if (unit.equals("km")){
			meters = km;
		}
		
		else if (unit.equals("Mm")){
			meters = Mm;
		}
		
		else if (unit.equals("dm")){
			meters = dm;
		}
		
		else if (unit.equals("cm")){
			meters = cm;
		}
		
		else if (unit.equals("mm")){
			meters = mm;
		}
		
		else if (unit.equals("Gm")){
			meters = Gm;
		}
		
		else if (unit.equals("um")){
			meters = um;
		}
		
		else if (unit.equals("nm")){
			meters = nm;
		}
		
		return meters;
	}

}
