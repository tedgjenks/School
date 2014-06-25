package edu.wicker.marshall.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
	private double sinTaxRate;
	
	public AlcoholItem() {
	}
	
	public void setSinTaxRate(double sinTaxRate){
		this.sinTaxRate = sinTaxRate;
	}
	
	public double getSinTaxRate(){
		return sinTaxRate;
	}

	@Override
	public double getTax(double baseTaxRate) {
		return baseTaxRate + sinTaxRate;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
