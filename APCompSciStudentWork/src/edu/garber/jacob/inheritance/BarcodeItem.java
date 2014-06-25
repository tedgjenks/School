package edu.garber.jacob.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem extends Item implements Barcoded {
	private double price;
	
	
	public BarcodeItem () {
		
	}
	@Override
	public double getPrice() {
		
		return price;
	}

	@Override
	public double getTax(double arg0) {
		
		return getPrice()*arg0;
	}

	
	@Override
	public void setPrice(double arg0) {
		price=arg0;
		
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
