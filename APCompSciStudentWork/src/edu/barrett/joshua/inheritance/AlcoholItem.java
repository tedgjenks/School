package edu.barrett.joshua.inheritance;


import edu.jenks.inheritance.dist.SinTaxable; 

public class AlcoholItem extends BarcodeItem implements SinTaxable {
	private double SinTaxRate;
	
	public double getTax(double baseTaxRate) {
		return getPrice()*(baseTaxRate + getSinTaxRate());
	}

	
	public double getSinTaxRate() {
		
		return SinTaxRate;
	}

	@Override
	public void setSinTaxRate(double arg0) {
		SinTaxRate = arg0;
		
	}

}
