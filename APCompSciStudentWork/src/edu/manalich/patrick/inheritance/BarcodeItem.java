package edu.manalich.patrick.inheritance;

import edu.jenks.inheritance.dist.ItemHandler; 
import  edu.jenks.inheritance.dist.*; 

public class BarcodeItem extends Item implements Barcoded {
	
	private double numPrice;
	
	public BarcodeItem(){
		
	}
	
	
	@Override
	public void setPrice(double price){
		numPrice = price;
	}
	
	
	@Override
	public double getPrice() {
		return numPrice;
	}


	@Override
	public boolean initBuyable(ItemHandler arg0) {
		return true;
	}
	
	@Override
	public double getTax(double baseTaxRate){
		return super.getTax(baseTaxRate);
	}
	
	
}
