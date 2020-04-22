package edu.inheritance.savelyev.denis;

import edu.jenks.dist.inheritance.*;

public class BarcodeItem extends Item implements Barcoded{

	public BarcodeItem(boolean bulk, double weight, double price) {
		super(bulk);
		super.setBulk(bulk);
		super.setWeight(weight);
		setWeight(weight);
		setPrice(price);
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

	public boolean initBuyable(ItemHandler arg0) {
		return false;
	}

	public boolean isBulk() {
		return false;
	}

	public void setBulk(boolean arg0) {
	}

	public void setWeight(double arg0) {
	}

	public void setPrice(double arg0) {
	}

}
