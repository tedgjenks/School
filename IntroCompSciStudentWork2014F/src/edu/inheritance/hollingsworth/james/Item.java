package edu.inheritance.hollingsworth.james;

import edu.jenks.dist.inheritance.Buyable;
import edu.jenks.dist.inheritance.ItemHandler;

public class Item implements Buyable {
	
	private double price, weight;
	private boolean bulk;
	
	public Item(boolean bulk) {
		setBulk(bulk);
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public boolean initBuyable(ItemHandler itemHandler) {
		return isBulk();
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
	public double getTax(double baseTaxRate) {
		return getPrice() * baseTaxRate;
	}

}
