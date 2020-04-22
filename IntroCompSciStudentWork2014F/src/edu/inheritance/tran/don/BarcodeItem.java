package edu.inheritance.tran.don;

import edu.jenks.dist.inheritance.*;

public class BarcodeItem

extends Item implements Barcoded{
	//private boolean bulk;
	//private double weight;
	private double price;
	public BarcodeItem(boolean bulk, double weight, double price) {
		super(bulk);
		super.setWeight(weight);
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}

	public double getTax(double baseTaxRate) {
		return price*baseTaxRate;
	}

	@Override
	public double getWeight() {
		return super.getWeight();
	}

	@Override
	public boolean initBuyable(ItemHandler itemHandler) {
		
		return false;
	}

	@Override
	public boolean isBulk() {
		return super.isBulk();
	}

	@Override
	public void setBulk(boolean newBulk) {
		super.setBulk(newBulk);
		
	}

	@Override
	public void setWeight(double newWeight) {
		super.setWeight(newWeight);
		
	}

	@Override
	public void setPrice(double newPrice) {
		this.price = newPrice;
		
	}

}
