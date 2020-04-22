package edu.inheritance.savelyev.denis;

import edu.jenks.dist.inheritance.*;

public class SelfCheckout implements ItemHandler{
	private double TAX_RATE;
	private double curSubTotal = 0;
	private double curWeight = 0;

	
	public SelfCheckout(double taxRate){
		TAX_RATE = taxRate;
	}
	
	public boolean addItem(Buyable item) {
		if(item != null) {
			curSubTotal += item.getPrice();
			return true;
		}
		return false;
	}

	public boolean addItem(Buyable item, double scaleWeight) {
		if(item != null) {
			if (item.isBulk()) {
				curSubTotal += item.getPrice();
			} else {
				curSubTotal += item.getPrice();
				curWeight += scaleWeight;
			}
			return true;
		}
		return false;
	}

	public double getCheckoutTotal() {
		double total = curSubTotal + (curSubTotal * TAX_RATE);
		return total;
	}

	public double getExpectedWeight() {
		return curWeight;
	}

	public double getScaleWeight() {
		return curWeight;
	}

	public double getSubtotal() {
		return curSubTotal;
	}

	public double getTax() {
		return 1 * TAX_RATE;
	}

	public boolean isCheckID() {
		return false;
	}

	public void setCheckID(boolean checkID) {
		
	}

}
