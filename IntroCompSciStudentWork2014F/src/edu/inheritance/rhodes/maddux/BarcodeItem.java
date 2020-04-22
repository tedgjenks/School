package edu.inheritance.rhodes.maddux;

import edu.jenks.dist.inheritance.Barcoded;
import edu.jenks.dist.inheritance.ItemHandler;

public class BarcodeItem extends Item implements Barcoded{

	private double price;
	
	public BarcodeItem(boolean bulk, double weight, double price) {
		super(bulk);
		setWeight(weight);
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getTax(double baseTaxRate) {
		return (price*baseTaxRate);
	}

	public boolean initBuyable(ItemHandler itemHandler) {
		return true;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
