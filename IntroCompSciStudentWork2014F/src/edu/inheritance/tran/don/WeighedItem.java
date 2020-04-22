package edu.inheritance.tran.don;

import edu.jenks.dist.inheritance.*;


public class WeighedItem extends Item implements Weighable{
	double pricePerPound;
	double scale;
	public WeighedItem(boolean bulk, double pricePerPound) {
		super(bulk);
		this.pricePerPound = pricePerPound;
	}
	
	public double getPricePerPound() {
		return pricePerPound * scale;
	}

	@Override
	public void setPricePerPound(double arg0) {
		pricePerPound = arg0;
		
	}
	
	public double getPrice() {
		return getPricePerPound();
	}
	
	public double getTax(double baseTaxRate) {
		return getPricePerPound() * baseTaxRate;
	}
	
	public boolean initBuyable(ItemHandler itemHandler) {
		scale = itemHandler.getScaleWeight();
		return false;
	}

}
