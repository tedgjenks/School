package edu.rogers.payton.inheritance;

import edu.jenks.inheritance.dist.*;

public class Item implements Buyable{
	public double price = 0;
	public double weight = 0;
	public boolean bulk = false;
	public boolean IDFlag = false;
	
	public Item() {
		super();
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public double getTax(double tax) {
		return price * tax;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public boolean isBulk() {
		return bulk;
	}

	@Override
	public void setBulk(boolean bulk) {
		this.bulk = bulk;
	}

	@Override
	public void setWeight(double weight) {
		this.weight = weight;
		
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		return false;
	}


}
