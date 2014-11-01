package edu.shearer.courtney.inheritance;

import edu.jenks.inheritance.dist.SinTaxable;

public class AlcoholItem
extends BarcodeItem
implements SinTaxable{
	private double sinTaxRate;
	public AlcoholItem(){
		sinTaxRate = 0.0;
		
	}
	public double getSinTaxRate(){
		return sinTaxRate;
		
	}
	public void setSinTaxRate(double sinTaxRate){
		this.sinTaxRate = sinTaxRate;
		
	}

}
