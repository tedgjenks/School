package edu.wicker.marshall.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem extends Item implements Barcoded{
	private double price;
	public BarcodeItem() {
		
	}
	
	public void setPrice(double price){
		if (price >= 0)
			this.price = price;
		
	}

	@Override
	public double getPrice() {
		return price;
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
