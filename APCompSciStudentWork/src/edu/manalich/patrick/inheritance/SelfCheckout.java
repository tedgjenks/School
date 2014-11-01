package edu.manalich.patrick.inheritance;

import edu.jenks.inheritance.dist.*;

public class SelfCheckout implements ItemHandler {
	
	public final double TAX_RATE;
	private double subtotal, expectedWeight, sW = 0;
	private boolean ID;
	
	public SelfCheckout(double argTaxRate){
		TAX_RATE = argTaxRate;
	}
	
	@Override
	public boolean addItem(Buyable argItem) { 
		return addItem(argItem, 0.0);
	}

	@Override
	public boolean addItem(Buyable argItem, double scaleWeight) {  
		sW = scaleWeight;
		
		
		if (scaleWeight == 0 && argItem instanceof WeighedItem){  //scaleWeight = 0, illegal arg
			return false;
		}
		
		else if (argItem instanceof WeighedItem) {   //WeighedItem
			WeighedItem item = (WeighedItem) argItem;
			if (!argItem.isBulk()) {
				expectedWeight += scaleWeight;
			}
			subtotal += item.getPrice() + (item.getPrice() * getTaxRate());
			return true;
		}		
		
		else if (argItem instanceof AlcoholItem){   //AlcoholItem
			AlcoholItem item =  (AlcoholItem) argItem;
			setCheckID(true);
			if (!item.isBulk()) {
				expectedWeight += item.getWeight();
			}
			subtotal += item.getPrice() + (item.getPrice() * (TAX_RATE + item.getSinTaxRate()));  
			return true;
		}
		
		else if (argItem instanceof BarcodeItem){   //BarcodeItem
			if(scaleWeight > 0 || scaleWeight < 0){
				return false;
			}
			
			if (!argItem.isBulk()) 
				expectedWeight += argItem.getWeight();
			subtotal += argItem.getPrice() + (argItem.getPrice() * TAX_RATE);
			return true;
		}
		return false;
	}

	@Override
	public double getCheckoutTotal() {
		return subtotal;
	}

	@Override
	public double getExpectedWeight() {
		return expectedWeight;
	}

	@Override
	public double getScaleWeight() {
		return sW;
	}

	@Override
	public boolean isCheckID() {
		return ID;
	}

	@Override
	public void setCheckID(boolean argID) {
		ID = argID;
	}
	
	public double getTaxRate(){
		return TAX_RATE;
	}
		
	

}
