package edu.inheritance.hollingsworth.james;

import edu.jenks.dist.inheritance.Barcoded;

public class BarcodeItem extends Item implements Barcoded {

	public BarcodeItem(boolean bulk, double weight, double price) {
		super(bulk);
		this.setWeight(weight);
		this.setPrice(price);
	}

}
