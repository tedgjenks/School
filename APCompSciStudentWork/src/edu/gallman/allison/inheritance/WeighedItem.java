package edu.gallman.allison.inheritance;


import edu.jenks.inheritance.dist.Weighable;
import edu.jenks.inheritance.dist.ItemHandler;

public class WeighedItem
extends Item
implements Weighable{
	private double pricePerPound;
	private double baseTaxRate;
	private double price;
	private Object itemHandler;
	private double scaleWeight;
	public WeighedItem(){
		
	}
	
	public void WeightedItem(double pricePerPound, double baseTaxRate, Object itemHandler){
		this.pricePerPound=pricePerPound;
		this.baseTaxRate=baseTaxRate;
		this.itemHandler=itemHandler;
	}//why void?
	
	public double getPricePerPound(){
		return pricePerPound;
	}
	
	
	public void setPricePerPound(double pricePerPound){
		this.pricePerPound=pricePerPound;
	}
	
	
	public double getPrice(){
		return getPricePerPound()*getScaleWeight();
	}
	
	
	public double getTax(double baseTaxRate){
		return getPrice()*baseTaxRate;
	}
	

	public boolean initBuyable(ItemHandler itemHandler) {
		return false;
	}
	
	public double getScaleWeight(){
		return scaleWeight;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void setScaleWeight(double scaleWeight){
		this.scaleWeight=scaleWeight;
	}

}//end class.
