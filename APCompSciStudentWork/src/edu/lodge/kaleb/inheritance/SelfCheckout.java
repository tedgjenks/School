package edu.lodge.kaleb.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

public class SelfCheckout implements ItemHandler{
	public final double TAX_RATE;
	private boolean checkID;
	private double totalPrice;
	private double totalWeight;

	public SelfCheckout(double taxRate){
		TAX_RATE = taxRate;
	}
	
	public double getScaleWeight(){
		return totalWeight;
	}
	
	public boolean isCheckID(){
		return checkID;
	}
	
	public void setCheckID(boolean checkID){
		this.checkID = checkID;
	}
	
	public double getExpectedWeight(){
		return totalWeight;
	}
	
	@Override
	public boolean addItem(Buyable item) {
		totalPrice += (item.getPrice() + item.getTax(TAX_RATE));
		if (!item.isBulk()){
			totalWeight += 0;
		}
			
		return true;
	}
	
	@Override
	public boolean addItem(Buyable item, double scaleWeight) {
		if (scaleWeight >= 0){
			totalPrice +=  (item.getPrice() + item.getTax(TAX_RATE));
			if (!item.isBulk()){
				totalWeight += scaleWeight;
			}
			return true;
		}
		return false;
	}
	
	public double getCheckoutTotal(){
		return totalPrice;
	}
}
