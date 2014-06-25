package edu.rogers.payton.inheritance;

import edu.jenks.inheritance.dist.*;

public class BarcodeItem extends Item implements Barcoded{
	
	public BarcodeItem() {
		super();
	}

	
	@Override
	public void setPrice(double price) {
		super.price = price;
	}

}
