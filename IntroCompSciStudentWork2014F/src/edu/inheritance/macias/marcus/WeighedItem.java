package edu.inheritance.macias.marcus;

import edu.jenks.dist.inheritance.ItemHandler;
import edu.jenks.dist.inheritance.Weighable;

public class WeighedItem extends Item implements Weighable{
	private double pricePerPound;
	
	public WeighedItem(boolean bulk, double pricePerPound) {
		super(bulk);
		this.pricePerPound = pricePerPound;
	}

	
	public double getPricePerPound() {
		return pricePerPound;
	}

	public void setPricePerPound(double pricePerPound) {
		this.pricePerPound = pricePerPound;
	}

	
	public double getPrice() {
		return getWeight() * pricePerPound;
	}

	
	public double getTax(double baseTaxRate) {
		return getPrice() * baseTaxRate;
	}

	public boolean initBuyable(ItemHandler item) {
		setWeight(item.getScaleWeight());
		return true;
	}

}
