package edu.joseph.juliet.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem extends Item implements Barcoded {
	
	private double itemPrice;
	public BarcodeItem(){
		//itemPrice=0.0;
	}

	public void setPrice(double price){
		itemPrice = price;
	}
	public double getPrice() {
		return itemPrice;
	}
	public double getTax(double taxRate) {
		return getPrice()*taxRate;
    }
	public boolean initBuyable(ItemHandler arg0) {
        return false;
    }
	
}
