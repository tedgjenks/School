package edu.manalich.patrick.inheritance;

import edu.jenks.inheritance.dist.*;


public abstract class Item implements Buyable {
	
	private double weight, tax;
	private boolean bulk;
	
	public Item(){ 
		
	}
	
	@Override
	public double getWeight(){
		return weight;
	}
	
	
	
	@Override
	public void setWeight(double argWeight){ 
		weight = argWeight;
	}
	
	
	
	@Override
	public boolean isBulk(){ 
		return bulk;
	}
	
	
	
	@Override
	public void setBulk(boolean argBulk){
		bulk = argBulk;
	}
	
	
	@Override
	public abstract double getPrice();
		
	@Override
	public double getTax(double baseTaxRate){
		tax = getPrice() * baseTaxRate;
		return tax;
	}
	
	
	@Override
	public boolean initBuyable(ItemHandler itemHandler){ 
		return true;
	}
}
