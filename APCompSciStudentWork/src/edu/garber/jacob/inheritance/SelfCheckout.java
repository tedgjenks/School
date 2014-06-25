package edu.garber.jacob.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

public class SelfCheckout implements ItemHandler {
	
	private double TAX_RATE;
	private double total;
	private double weight;
	private boolean checkId;
	private double ScaleWeight;
	
	public SelfCheckout(double TAX_RATE) {
		this.TAX_RATE = TAX_RATE;
	}
	
	
	public boolean addItem(Buyable arg0) {
		if (arg0 instanceof WeighedItem) {
			return false;
		}
		else if (arg0 instanceof AlcoholItem) {
			AlcoholItem a1 = (AlcoholItem) arg0;
			setCheckID(true);
			if (!a1.isBulk()) {
				weight+=a1.getWeight();
			}
			total+=a1.getPrice() + a1.getTax(TAX_RATE);
		}
		
		else if (arg0 instanceof BarcodeItem) {
			BarcodeItem b1 = (BarcodeItem) arg0;
			if (!b1.isBulk()) {
				weight+=b1.getWeight();
			}
			total+=b1.getPrice() + b1.getTax(TAX_RATE);
		}
		
		return true;
	}

	@Override
	public boolean addItem(Buyable arg0, double arg1) {
		if (arg1<=0 && arg0 instanceof WeighedItem) {
			return false;
			
		}
		if (arg0 instanceof WeighedItem) {
			WeighedItem w1 = (WeighedItem) arg0;
			setScaleWeight(arg1);
			w1.setScaleWeight(arg1);
			if (!w1.isBulk()) {
				weight+=w1.getScaleWeight();
			}
			total+=w1.getPrice() + w1.getTax(TAX_RATE);
		}
		else if (arg0 instanceof AlcoholItem) {
			AlcoholItem a1 = (AlcoholItem) arg0;
			setCheckID(true);
			if (!a1.isBulk()) {
				weight+=a1.getWeight();
			}
			total+=a1.getPrice() + a1.getTax(TAX_RATE);
		}
		
		else if (arg0 instanceof BarcodeItem) {
			BarcodeItem b1 = (BarcodeItem) arg0;
			if (!b1.isBulk()) {
				weight+=b1.getWeight();
			}
			total+=b1.getPrice() + b1.getTax(TAX_RATE);
		}
		return true;
	}

	@Override
	public double getCheckoutTotal() {
		return total;
	}

	@Override
	public double getExpectedWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	@Override
	public double getScaleWeight() {
		
		return ScaleWeight;
	}
	
	public void setScaleWeight(double Scale) {
		ScaleWeight = Scale;
	}

	@Override
	public boolean isCheckID() {
		// TODO Auto-generated method stub
		return checkId;
	}

	@Override
	public void setCheckID(boolean arg0) {
		checkId=arg0;
		
	}

}
