package edu.slimmer.ben.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

public class SelfCheckout
extends java.lang.Object
implements ItemHandler {
	public final double TAX_RATE;
	private double totalWeight=0;
	private double totalPrice=0;
	private double taxTotal=0;
	private boolean hasID=false;
	private double currentWeight=0;
	
	public SelfCheckout(double taxRate) {
		TAX_RATE=taxRate;
		
	}
	@Override
	public boolean addItem(Buyable item) {
		currentWeight=0;
		if (item instanceof SinTaxable){
			if (purchaseAlcohol(item)==true) {
				return true;
			}
			else
				return false;
		}
		if (item.isBulk()==false) {
			currentWeight=item.getWeight();
		}
		totalPrice+=item.getPrice();
		taxTotal+=item.getTax(TAX_RATE);
		if (item.isBulk()==false) {
			totalWeight+= item.getWeight();
		}
		return true;
	}

	@Override
	public boolean addItem(Buyable item, double scaleWeight) {
		item.setWeight(scaleWeight);
		if (item instanceof SinTaxable){
			if (purchaseAlcohol(item)==true) {
				return true;
			}
			else
				return false;
		}
		totalPrice+=item.getPrice();
		taxTotal+=item.getTax(TAX_RATE);
		if (item.isBulk()==false) {
		totalWeight+=(item.getWeight());
		}
		return true;
	}

	@Override
	public double getCheckoutTotal() {
		return totalPrice+taxTotal;
	}

	@Override
	public double getExpectedWeight() {
		return totalWeight;
	}

	@Override
	public double getScaleWeight() {
		return currentWeight;
	}

	@Override
	public boolean isCheckID() {
		return hasID;
	}

	@Override
	public void setCheckID(boolean arg0) {
		hasID=arg0;
	}
	
	private boolean purchaseAlcohol(Buyable item) {
		if (hasID==true) {
			totalPrice+=item.getPrice();
			taxTotal+=item.getTax(TAX_RATE);
			currentWeight=item.getWeight();
			if (item.isBulk()==false) {
				totalWeight+= item.getWeight();	
			}
			return true;
		}
		else {
			return false;
		}
	}

}
