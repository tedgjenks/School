package edu.rogers.payton.inheritance;

import edu.jenks.inheritance.dist.*;

public class WeighedItem extends Item implements Weighable{

	public double pricePerPound;
	
	public WeighedItem() {
		super();
	}

	@Override
	public double getPricePerPound() {
		return this.pricePerPound;
	}

	@Override
	public void setPricePerPound(double PPP) {
		this.pricePerPound = PPP;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
