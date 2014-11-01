package edu.wicker.marshall.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;

public class WeighedItem extends Item implements Weighable{
	private double pricePerPound;

	public double getPricePerPound() {
		return pricePerPound;
	}

	public void setPricePerPound(double pricePerPound) {
		if (pricePerPound >= 0)
			this.pricePerPound = pricePerPound;
	}

	public WeighedItem() {
	}

	public double getPrice() {
		return this.getWeight() * getPricePerPound();
	}

	@Override
	public double getTax(double baseTaxRate) {
		return baseTaxRate;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
