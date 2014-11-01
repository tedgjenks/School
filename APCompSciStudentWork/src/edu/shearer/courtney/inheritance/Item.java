package edu.shearer.courtney.inheritance;

import edu.jenks.inheritance.dist.Buyable;

public abstract class Item
extends java.lang.Object
implements Buyable {
	private double weight;
	private boolean bulk;
	//public double expectedweight;
	public Item(){
		weight = 0.0;
		
	}
	public double getWeight(){
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

