package edu.inheritance.macias.marcus;

import edu.jenks.dist.inheritance.Buyable;
import edu.jenks.dist.inheritance.ItemHandler;



public class SelfCheckout implements ItemHandler{
	private double subTotal;
	public final double TAX_RATE;
	private double scaleWeight;
	private boolean ID;
	private double totalWeight;
	private double TotalTax;
	public SelfCheckout(double taxRate) {
		TAX_RATE = taxRate;
		
	}
	/*
	 * Alcohol Item paramater --> (Bulk , Weight , price , liquor tax)
	 * Barcode Item parameter --> (Bulk , Weight , price)
	 * Weighed Item parameter --> (Bulk , PricePerPound)
	 */
	public static void main(String[] args) {
		SelfCheckout run = new SelfCheckout(.06);
		//Tax 26.4
		run.addItem(new AlcoholItem(false,13,110.00,.05));
		run.addItem(new AlcoholItem(false,10,130.00,0.05));
	
		//Tax rest 11.76
		run.addItem(new BarcodeItem(false,29,39.00));
		
		run.addItem(new WeighedItem(false,3.00),39);
		run.addItem(new WeighedItem(false,2.00),20);
		
		System.out.println("Sub Total: -->  $" + run.getSubtotal());
		System.out.println("Tax Total: -->  $" + run.getTax());
		System.out.println("Checkout: -->  $" + run.getCheckoutTotal());
		System.out.println("Weight: " + run.getExpectedWeight());
	}
	public double getScaleWeight() {
		return scaleWeight;
	}
	
	public boolean isCheckID() {
		return ID;
	}
	
	public void setCheckID(boolean checkID) {
		ID = checkID;
	}
	
	public double getExpectedWeight() {
		return totalWeight;
	}
	
	
	public boolean addItem(Buyable item) {
		
		if(!item.isBulk()) {
			totalWeight += item.getWeight();
		}
		
		item.initBuyable(this);
		TotalTax += item.getTax(TAX_RATE);
		subTotal += item.getPrice();
		
		return true;
	}
	
	
	public boolean addItem(Buyable item, double scaleWeight) {
		
		this.scaleWeight = scaleWeight;
		item.initBuyable(this);
		if(!item.isBulk()) {
			totalWeight += item.getWeight();
			
		}
		
		
		
		
		TotalTax += item.getTax(TAX_RATE);
		subTotal += item.getPrice();
		
		return true;
	}

	
	public double getCheckoutTotal() {
		return subTotal + getTax();
	}
	
	public double getSubtotal() {
		return subTotal;
	}

	
	public double getTax() {
		
		return TotalTax;
	}

	
	

	
	

}
