package edu.inheritance.rhodes.maddux;

import edu.jenks.dist.inheritance.Buyable;
import edu.jenks.dist.inheritance.ItemHandler;

public class SelfCheckout implements ItemHandler{

	private double taxRate;
	private boolean ID;
	private double subtotal;
	private double totalTax;
	private double totalWeight;
	private double scaleWeight;
	
	public SelfCheckout(double taxRate) {
		this.taxRate = taxRate;
	}
	
	public static void main(String[] args) {
		WeighedItem item = new WeighedItem(true, 5.00);
		SelfCheckout test = new SelfCheckout(0.05);
		System.out.println(test.addItem(item, 5.00));
	}
	
	public boolean addItem(Buyable item) {
		item.initBuyable(this);
		subtotal += item.getPrice();
		totalTax += item.getTax(taxRate);
		if(!item.isBulk()) {
			totalWeight += item.getWeight();
		}
		return false;
	}
	
	public boolean addItem(Buyable item, double scaleWeight) {
		this.scaleWeight = scaleWeight;
		item.initBuyable(this);
		subtotal += item.getPrice();
		totalTax += item.getTax(taxRate);
		if(!item.isBulk()) {
			totalWeight += item.getWeight();
		}
		return false;
	}

	public double getCheckoutTotal() {
		return subtotal + totalTax;
	}

	public double getExpectedWeight() {
		return totalWeight;
	}

	public double getScaleWeight() {
		return scaleWeight;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public double getTax() {
		return totalTax;
	}

	public boolean isCheckID() {
		return ID;
	}

	public void setCheckID(boolean checkID) {
		ID = checkID;
	}

	

}
