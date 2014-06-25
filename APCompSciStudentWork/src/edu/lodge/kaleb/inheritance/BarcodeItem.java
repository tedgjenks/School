package edu.lodge.kaleb.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem extends Item implements Barcoded{
	double price;
	
	public BarcodeItem() {
		super();
		price = 0;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public double getTax(double baseTaxRate) {
		this.baseTaxRate = baseTaxRate;
		return price * baseTaxRate;
	}
	
	@Override
	public boolean initBuyable(ItemHandler arg0) {
		return false;
	}
}
