package edu.inheritance.tran.don;

import edu.jenks.dist.inheritance.*;

public abstract class Item implements Buyable{
	private boolean bulk;
	private double weight; 
	
	public Item(boolean bulk) {
		this.bulk = bulk;
	}
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTax(double arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public boolean initBuyable(ItemHandler arg0) {
		// TODO Auto-generated method stub
		return false;
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
	public void setWeight(double arg0) {
		weight = arg0;
		
	}

}
