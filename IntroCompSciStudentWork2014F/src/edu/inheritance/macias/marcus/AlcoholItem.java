package edu.inheritance.macias.marcus;

import edu.jenks.dist.inheritance.ItemHandler;
import edu.jenks.dist.inheritance.SinTaxable;

public class AlcoholItem extends BarcodeItem implements SinTaxable{
	//private double price;
	private double sinTaxRate;
	public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) {
		super(bulk, weight, price);
		
		this.sinTaxRate = sinTaxRate;
	}

	public double getTax(double baseTaxRate) {
		
		return getPrice() * (baseTaxRate +  sinTaxRate);
	}
	
	public double getSinTaxRate() {
		
		return sinTaxRate;
	}
	
	public void setSinTaxRate(double sinTaxRate) {
		this.sinTaxRate = sinTaxRate;
	}
	
	public boolean initBuyable(ItemHandler itemHandler) {
		
		
		itemHandler.setCheckID(true);
		//itemHandler.getTax();
		
		
		return true;
	}

}
