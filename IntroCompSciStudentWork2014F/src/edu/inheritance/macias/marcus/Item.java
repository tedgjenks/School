package edu.inheritance.macias.marcus;

import edu.jenks.dist.inheritance.Buyable;

public abstract class Item implements Buyable {
	private double weight;
	private boolean bulk;
	public Item(boolean bulk) {
		this.bulk = bulk;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isBulk() {
		return bulk;
	}
	
	public void setBulk(boolean bulk) {
		this.bulk = bulk;
	}
	

		

	

	

}
