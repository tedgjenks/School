package edu.lodge.kaleb.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;

public class WeighedItem extends Item implements Weighable{
	double pricePerPound;

	public WeighedItem() {
		super();
		pricePerPound = 0;
	}
	
	public double getPricePerPound(){
		return pricePerPound;
	}
	
	public void setPricePerPound(double pricePerPound){
		this.pricePerPound = pricePerPound;
	}
	
	public double getPrice() {
		return pricePerPound * weight;
	}
	
	@Override
	public double getTax(double baseTaxRate) {
		this.baseTaxRate = baseTaxRate;
		return (getPrice() * baseTaxRate);
	}
	
	@Override
	public boolean initBuyable(ItemHandler arg0) {
		return false;
	}
}
