package edu.wicker.marshall.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

public class SelfCheckout implements ItemHandler{
	private final double TAX_RATE;
	private double total, expectedWeight, scaleWeight;

	private boolean checkId = false;

	public double getExpectedWeight() {
		return expectedWeight;
	}
	public boolean isCheckID() {
		return checkId;
	}
	public void setCheckID(boolean checkId) {
		this.checkId = checkId;
	}
	public SelfCheckout(double taxRate) {
		TAX_RATE = taxRate;
	}

	public boolean addItem(Buyable item){
		return addItem(item, 0);
	}

	public boolean addItem(Buyable item , double scaleWeight){
		if (scaleWeight == 0 && item instanceof WeighedItem) return false;
		this.scaleWeight = scaleWeight;
		if (item instanceof AlcoholItem){
			setCheckID(true);
			total += item.getPrice() * (1.0 + ((AlcoholItem)item).getTax(TAX_RATE));
		}
		if (item instanceof WeighedItem){
			expectedWeight += scaleWeight;
			item.setWeight(scaleWeight);
			total += item.getPrice() * (1.0 + TAX_RATE);
		}
		if(item instanceof BarcodeItem && !(item instanceof AlcoholItem)){
			total += item.getPrice() * (1.0 + TAX_RATE);
		}
		if (!item.isBulk() && !(item instanceof WeighedItem)){
			expectedWeight += item.getWeight();
		}
		return true;
	}

	public double getScaleWeight(){
		return scaleWeight;
	}

	public void setScaleWeight(double scaleWeight) {
		this.scaleWeight = scaleWeight;
	}

	public double getCheckoutTotal(){
		return total;
	}
}
