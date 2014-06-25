package edu.lodge.kaleb.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
	private double sinTaxRate;

	public AlcoholItem() {
		super();
		sinTaxRate = 0;
	}
	
	public double getTax(){
		return ((super.getTax(baseTaxRate)) + (price * sinTaxRate));
	}
	
	public boolean initBuyable(ItemHandler arg0) {
		return false;
	}
	
	public double getSinTaxRate(){
		return sinTaxRate;
		
	}
	
	public void setSinTaxRate(double sinTaxRate){
		this.sinTaxRate = sinTaxRate;
	}
}
