package edu.rogers.payton.inheritance;

import edu.jenks.inheritance.dist.*;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
	
	public double sinTax;
	
	public AlcoholItem() {
		super();
	}
	
	
	@Override
	public double getTax(double baseTaxRate) {
		return this.getPrice() * (baseTaxRate + sinTax);
	}
	
	
	@Override
	public double getSinTaxRate() {
		return sinTax;
	}

	@Override
	public void setSinTaxRate(double sinTax) {
		this.sinTax = sinTax;
		
	}

}
