package edu.gallman.allison.inheritance;


import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

public class BarcodeItem
extends Item
implements Barcoded{
	private double price;
	private double baseTaxRate;
	private Object itemHandler;
	public void BarcodedItem(){
		
	}
	
	public void BarcodedItem(double price, double baseTaxRate, Object itemHandler){
		this.price=price;
		this.baseTaxRate=baseTaxRate;
		this.itemHandler=itemHandler;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
	
	public double getPrice(){
		return price;
	}
	
	
	public double getTax(double baseTaxRate){
		return getPrice()*baseTaxRate;
	}
	
	
	public boolean initBuyable(ItemHandler itemHandler) {
		return false;
	}

}//end class.
