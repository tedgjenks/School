package edu.inheritance.macias.marcus;

import edu.jenks.dist.inheritance.Barcoded;
import edu.jenks.dist.inheritance.ItemHandler;

public class BarcodeItem extends Item implements Barcoded {
	private double price;
	private double weight;
	public BarcodeItem(boolean bulk,double weight,double price) {
		super(bulk);
		
		this.setWeight(weight);
		//this.weight = weight;
		setPrice(price);
		
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getWeight() {
		return weight;
	}
	public static void main(String[] args) {
		BarcodeItem run = new BarcodeItem(true, 10,10) ;
		run.setPrice(1.00);
		System.out.println(run.getWeight());
		//System.out.println(run.getTax(.09));
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	public double getTax(double baseTaxRate) {
		return getPrice()*baseTaxRate;
	}
	public boolean initBuyable(ItemHandler itemHandler) {
		//itemHandler.getCheckoutTotal();
		
		return true;
	}
	
	
	
}
