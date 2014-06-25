package edu.slimmer.ben.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;

public class WeighedItem
extends Item
implements Weighable {
	private double PriceperPound=0;
	
	public WeighedItem() {
		
	}
	

	@Override
	public double getPrice() {
		return this.getWeight()*PriceperPound;
	}

	@Override
	public double getTax(double arg0) {
		return this.getPrice()*arg0;
	}

	@Override
	public double getPricePerPound() {
		return PriceperPound;
	}

	@Override
	public void setPricePerPound(double arg0) {
		PriceperPound=arg0;
	}


	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
