package edu.inheritance.hollingsworth.james;

import edu.jenks.dist.inheritance.Weighable;

public class WeighedItem extends Item implements Weighable {

	private double pricePerPound;
	public WeighedItem(boolean bulk, double pricePerPound) {
		super(bulk);
		this.pricePerPound = pricePerPound;
	}

	@Override
	public double getPricePerPound() {
		return pricePerPound;
	}

	@Override
	public void setPricePerPound(double pricePerPound) {
		this.pricePerPound = pricePerPound;
	}

}
