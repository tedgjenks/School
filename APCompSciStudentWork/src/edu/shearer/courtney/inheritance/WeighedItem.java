package edu.shearer.courtney.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;


public class WeighedItem
extends Item
implements Weighable
 {
	private double scaleWeight;
	private double pricePerPound;
	//private double weight;
	public WeighedItem(){
		//price = 0.0;
		pricePerPound = 0.0;
		//weight = 0.0;
		
	}
	public double getScaleWeight() {
		return scaleWeight;
	}
	public void setScaleWeight(double scaleWeight) {
		this.scaleWeight = scaleWeight;
	}
	
	public double getPricePerPound(){
		return pricePerPound;
		
	}
	public void setPricePerPound(double pricePerPound){
		this.pricePerPound = pricePerPound;
		
	}
	public double getTax(double baseTaxRate) {
		 return baseTaxRate;
	}
	

	@Override
	public double getPrice() {
		
		double price = getScaleWeight() * getPricePerPound();
		return price;
	}
	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
