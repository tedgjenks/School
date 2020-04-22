package edu.inheritance.rhodes.maddux;

import edu.jenks.dist.inheritance.ItemHandler;
import edu.jenks.dist.inheritance.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable{

	double sinTaxRate;
	
    public AlcoholItem (boolean bulk, double weight, double price, double sinTaxRate) {
		super(bulk, weight, price);
		this.sinTaxRate = sinTaxRate;
	}

	public double getTax(double baseTaxRate) {
		return ((getPrice()*sinTaxRate) + (getPrice()*baseTaxRate));
	}

	public boolean initBuyable(ItemHandler itemHandler) {
		itemHandler.setCheckID(true);
		return true;
	}

	public double getSinTaxRate() {
		return sinTaxRate;
	}

	public void setSinTaxRate(double sinTaxRate) {
		this.sinTaxRate = sinTaxRate;
	}

}
