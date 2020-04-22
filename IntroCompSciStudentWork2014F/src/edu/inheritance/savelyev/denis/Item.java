package edu.inheritance.savelyev.denis;

import edu.jenks.dist.inheritance.*;

public abstract class Item implements Buyable{

	public Item(boolean bulk) {
		setBulk(bulk);
	}
	
	public double getPrice() {
		return 0;
	}

	public double getTax(double arg0) {
		return 0;
	}

	public double getWeight() {
		return 0;
	}

	public boolean initBuyable(ItemHandler thing) {
		return false;
	}

	public boolean isBulk() {
		return false;
	}

	public void setBulk(boolean bulk) {
	}

	public void setWeight(double weight) {
	}

}
