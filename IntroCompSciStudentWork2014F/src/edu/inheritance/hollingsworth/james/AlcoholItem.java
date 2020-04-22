package edu.inheritance.hollingsworth.james;

import edu.jenks.dist.inheritance.ItemHandler;
import edu.jenks.dist.inheritance.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable{

	private double sinTaxRate;
	public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) {
		super(bulk, weight, price);
		this.sinTaxRate = sinTaxRate;
	}

	@Override
	public double getSinTaxRate() {
		return sinTaxRate;
	}

	@Override
	public void setSinTaxRate(double sinTaxRate) {
		this.sinTaxRate = sinTaxRate;
	}

	public boolean initBuyable(ItemHandler checkout) {
		checkout.setCheckID(true);
		return isBulk();
	}
	
	@Override
	public double getTax(double baseTaxRate) {
		return super.getTax(baseTaxRate + this.sinTaxRate);
	}
	
}
