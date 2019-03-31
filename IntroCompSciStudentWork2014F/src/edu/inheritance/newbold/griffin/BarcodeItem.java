package edu.inheritance.newbold.griffin;
import edu.jenks.dist.inheritance.*;

public class BarcodeItem extends Item implements Barcoded{

	public BarcodeItem(boolean bulk, double weight, double price){
		super(bulk);
		this.price = price;
		this.weight = weight;
		this.bulk = bulk;
	}
	public boolean initBuyable(ItemHandler itemHandler){
		return false;
	}
}