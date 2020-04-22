package edu.inheritance.tran.don;

import edu.jenks.dist.inheritance.*;

public class SelfCheckout implements ItemHandler{
	private double taxRate;
	private double weight;
	private double subTotal;
	private double totTax;
	private double scaleWeight;
	private boolean needId;
	public SelfCheckout(double taxRate) {
		this.taxRate = taxRate;
	}
	
	public boolean addItem(Buyable arg0) {
		arg0.initBuyable(this);
		if(!arg0.isBulk()) {
			weight += arg0.getWeight();
		}
		subTotal += arg0.getPrice();
		totTax += arg0.getTax(taxRate);
		return false;
	}

	public boolean addItem(Buyable arg0, double scaleWeight) {
		this.scaleWeight = scaleWeight;
		arg0.initBuyable(this);
		if(!arg0.isBulk()) {
			weight += scaleWeight;
		}
		subTotal += arg0.getPrice();
		totTax += arg0.getTax(taxRate);
		return false;
	}

	public double getCheckoutTotal() {
		return subTotal + totTax;
	}

	@Override
	public double getExpectedWeight() {
		return weight;
	}

	@Override
	public double getScaleWeight() {
		return scaleWeight;
	}

	@Override
	public double getSubtotal() {
		return subTotal;
	}

	@Override
	public double getTax() {
		return totTax;
	}

	@Override
	public boolean isCheckID() {
		return needId;
	}

	@Override
	public void setCheckID(boolean arg0) {
		needId = arg0;
		
	}

}
