package edu.inheritance.rhodes.maddux;

import edu.jenks.dist.inheritance.Buyable;
import edu.jenks.dist.inheritance.ItemHandler;

public class Item implements Buyable{

	private boolean bulk;
	private double weight;
	
	public Item() {
		
	}
	
	public Item(boolean bulk) {
		this.bulk = bulk;
	}
	
	public double getPrice() {
		return this.getPrice();
	}

	public double getTax(double baseTaxRate) {
		return (this.getPrice()*baseTaxRate);
	}

	public double getWeight() {
		return weight;
	}

	public boolean initBuyable(ItemHandler arg0) {
		return true;
	}

	public boolean isBulk() {
		return bulk;
	}

	public void setBulk(boolean bulk) {
		this.bulk = bulk;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
