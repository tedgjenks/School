package edu.inheritance.newbold.griffin;
import edu.jenks.dist.inheritance.*;

public class WeighedItem extends Item implements Weighable{
	
	private double pricePerPound;
	public double weight;
	
	public WeighedItem(boolean bulk, double pricePerPound){
		super(bulk);
		this.pricePerPound = pricePerPound;
	}
	
	public double getPricePerPound(){
		return pricePerPound;
	}
	public void setPrice(double price){
		this.price = price;
	}
	
	public void setPricePerPound(double pricePerPound){
		this.pricePerPound = pricePerPound;
	}
	
	public boolean initBuyable(ItemHandler itemHandler){
		this.price = pricePerPound;
		return false;
	}
	public double getTax(double baseTaxRate){
		return baseTaxRate * (price * pricePerPound);
	}
	
	public double getWeight(){
		return weight;
	}
	
}