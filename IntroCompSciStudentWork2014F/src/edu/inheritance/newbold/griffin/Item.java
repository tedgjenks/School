package edu.inheritance.newbold.griffin;
import edu.jenks.dist.inheritance.*;

public class Item implements Buyable{
	public boolean bulk;
	public double weight;
	public double price;
	public double totalTax;
	public double totalScaleWeight;
	public double pricePerPound;
	
	public Item(boolean bulk){
		this.bulk = bulk; 
	}
	
	public double getWeight(){
		return weight;
	}
	
	public boolean isBulk(){
		return bulk;
		
	}
	public boolean initBuyable(ItemHandler itemHandler){
		return false;
	}
	
	public void setBulk(boolean bulk){
		this.bulk = bulk;
	}
	
	public void setWeight(double weight){
		this.weight = weight; 
	}
	
	public double getTax(double baseTaxRate){
		return baseTaxRate*price;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	
	public double retrievePrice(double scaleWeight){
		weight = scaleWeight;
		return scaleWeight * pricePerPound;
    }
	
	
	
	
	
	
	
	
	
}