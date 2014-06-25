package edu.barrett.joshua.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;
public class WeighedItem extends Item implements Weighable {
	
	private double ScaleWeight;
	private double PricePerPound;
	
	public WeighedItem () {
		
	}

	@Override
	public double getPrice() {
		return getPricePerPound()*getScaleWeight();
	}
	
	public double getScaleWeight() {
		return ScaleWeight;
		
	}
	
	public void setScaleWeight(double Scale) {
		ScaleWeight=Scale;
	}

	@Override
	public double getTax(double arg0) {
		return getPrice()*arg0;
	}

	

	@Override
	public double getPricePerPound() {
		// TODO Auto-generated method stub
		return PricePerPound;
	}

	@Override
	public void setPricePerPound(double arg0) {
		PricePerPound=arg0;
		
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}



