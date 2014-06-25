package edu.manalich.patrick.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;

public class WeighedItem extends Item implements Weighable {
	
	private double pricePerPound, price, scaleWeight;
	
	public WeighedItem(){
		
	}
	
	@Override
	public boolean initBuyable(ItemHandler arg0) {
		return true;
	}

	@Override
	public double getPricePerPound() {
		return pricePerPound;
	}

	@Override
	public void setPricePerPound(double arg0) {
		pricePerPound = arg0;
		
	}

	@Override
	public double getPrice() {
		price = pricePerPound * getScaleWeight();
		return price;
	}
	
	@Override
	public double getTax(double baseTaxRate){
		return baseTaxRate;
	}
	
	public double getScaleWeight(){
		return scaleWeight;
	}
	
	public void setScaleWeight(double newScaleWeight){
		scaleWeight = newScaleWeight;
		
	}

}
