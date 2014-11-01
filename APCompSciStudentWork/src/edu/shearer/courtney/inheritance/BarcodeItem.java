package edu.shearer.courtney.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;


public class BarcodeItem
extends Item
implements Barcoded {
	private double price;
	//private double baseTaxRate;
	public BarcodeItem(){
		price = 0.0;
	}
	public void setPrice(double price){
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}
	@Override
	public double getTax(double baseTaxRate) {
		//this.baseTaxRate = baseTaxRate;
		return baseTaxRate;
	}
	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
