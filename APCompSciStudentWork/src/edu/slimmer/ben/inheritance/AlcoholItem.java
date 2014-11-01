package edu.slimmer.ben.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

public class AlcoholItem
extends BarcodeItem
implements SinTaxable {
	private double baseTax=0;
	private double sinTax=0;
	private boolean bulk= false;
	
	public AlcoholItem(){
		
	}
	
	public double getTax(double baseTaxRate) {
		return (this.getPrice()*baseTaxRate)+(this.getPrice()*this.getSinTaxRate());
	}

	@Override
	public double getSinTaxRate() {
		return sinTax;
	}

	@Override
	public void setSinTaxRate(double arg0) {
		sinTax=arg0;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
