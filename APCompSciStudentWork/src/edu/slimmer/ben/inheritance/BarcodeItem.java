package edu.slimmer.ben.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem
extends Item
implements Barcoded {
	private double Price=0;
	
	public BarcodeItem() {
		
	}

	@Override
	public double getPrice() {
		return Price;
	}

	@Override
	public double getTax(double arg0) {
		return Price*arg0;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPrice(double arg0) {
		Price=arg0;
	}

}
