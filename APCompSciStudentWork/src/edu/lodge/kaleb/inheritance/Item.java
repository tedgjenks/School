package edu.lodge.kaleb.inheritance;

import edu.jenks.inheritance.dist.Buyable;

public abstract class Item implements Buyable{
	double weight;
	boolean bulk;
	double baseTaxRate;

	public Item() {
		weight = 0;
		bulk = false;
	}
	
	public double getWeight() {
		return weight;
		
	}
	
	public void setWeight(double weight){
		this.weight = weight;
	}
	
	public boolean isBulk(){
		return bulk;
		
	}
	
	public void setBulk(boolean bulk){
		this.bulk = bulk;
	}
	
	public abstract double getPrice();
}
