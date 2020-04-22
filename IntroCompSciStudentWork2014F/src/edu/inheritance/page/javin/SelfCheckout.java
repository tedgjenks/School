package edu.inheritance.page.javin;

import edu.jenks.dist.inheritance.*;
public class SelfCheckout extends java.lang.Object implements ItemHandler{
	public final double TAX_RATE;
	private double expWeight = 0;
	private double subtotal = 0;
	private double tax = 0;
	private double scale = 0;
	private boolean checkID = false;
	public SelfCheckout(double taxRate) {
		TAX_RATE = taxRate;
	}
	public boolean addItem(Buyable arg0) {
		arg0.initBuyable(this);
		if(!arg0.isBulk()) expWeight += arg0.getWeight();
		subtotal += arg0.getPrice();
		tax += arg0.getTax(TAX_RATE);
		return true;
	}

	@Override
	public boolean addItem(Buyable arg0, double weight) {
		arg0.setWeight(weight);
		addItem(arg0);
		if(!arg0.isBulk()) scale += weight;
		return true;
	}

	@Override
	public double getCheckoutTotal() {
		// TODO Auto-generated method stub
		return getSubtotal() + getTax();
	}

	@Override
	public double getExpectedWeight() {
		return expWeight;
	}

	@Override
	public double getScaleWeight() {
		// TODO Auto-generated method stub
		return scale;
	}

	@Override
	public double getSubtotal() {
		return subtotal;
	}

	@Override
	public double getTax() {
		return tax;
	}

	@Override
	public boolean isCheckID() {
		// TODO Auto-generated method stub
		return checkID;
	}

	@Override
	public void setCheckID(boolean arg0) {
		// TODO Auto-generated method stub
		checkID = arg0;
	}
	public static void main(String args[]) {
		SelfCheckout instance = new SelfCheckout(.08);
		BarcodeItem cereal = new BarcodeItem(true, 20000000, 12.09);
		instance.addItem(cereal);
		System.out.println(instance.getCheckoutTotal());
		
	}
}
