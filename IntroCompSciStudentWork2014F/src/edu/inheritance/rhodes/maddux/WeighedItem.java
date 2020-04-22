package edu.inheritance.rhodes.maddux;

import edu.jenks.dist.inheritance.ItemHandler;
import edu.jenks.dist.inheritance.Weighable;

public class WeighedItem extends Item implements Weighable{

	private double price;
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
		return price;
	}
	
	public double getTax(double baseTaxRate) {
		return this.getPrice()*baseTaxRate;
	}
	
	public boolean initBuyable(ItemHandler itemHandler) {
		price = pricePerPound*itemHandler.getScaleWeight();
		setWeight(itemHandler.getScaleWeight());
		return true;
	}
}
