package edu.inheritance.hollingsworth.james;

import edu.jenks.dist.inheritance.Buyable;
import edu.jenks.dist.inheritance.ItemHandler;

public class SelfCheckout implements ItemHandler {

	public final double TAX_RATE;
	private double totalWeight, subTotal, totalTax;
	private boolean IDRequired;
	private Buyable currentItem;
	
	public SelfCheckout(double taxRate) {
		TAX_RATE = taxRate;
	}
	
	@Override
	public boolean addItem(Buyable item) {
		if(!item.initBuyable(this)) {
			currentItem = item;
			totalWeight += item.getWeight();
		}
		subTotal += item.getPrice();
		totalTax += item.getTax(TAX_RATE);
		
		return true;
	}

	@Override
	public boolean addItem(Buyable item, double scaleWeight) {
		if(scaleWeight > 0) {
			if(!item.initBuyable(this)) {
				currentItem = item;
				totalWeight += item.getWeight();
			}
			item.setWeight(scaleWeight);
			subTotal += ((WeighedItem) item).getPricePerPound() * scaleWeight;
			totalTax += item.getTax(TAX_RATE);
		}
		else addItem(item);
		
		return true;
	}

	@Override
	public double getCheckoutTotal() {
		return this.getSubtotal() + this.getTax();
	}

	@Override
	public double getExpectedWeight() {
		if(currentItem != null) return currentItem.getWeight();
		return 0;
	}

	@Override
	public double getScaleWeight() {
		return totalWeight;
	}

	@Override
	public double getSubtotal() {
		return subTotal;
	}

	@Override
	public double getTax() {
		return totalTax;
	}

	@Override
	public boolean isCheckID() {
		return this.IDRequired;
	}

	@Override
	public void setCheckID(boolean checkID) {
		this.IDRequired = checkID;
	}

}
