package edu.inheritance.sweezy.kenneth;
import edu.jenks.dist.inheritance.*;

public class SelfCheckout implements ItemHandler {
	private double taxR;
	private double expPrice = 0.0;
	private double expScaleWeight = 0.0;
	private ItemHandler runner = new SelfCheckout(taxR);

	public static void main(String[] args) {
		
	}

	public SelfCheckout(double taxRate) {
		taxR = taxRate;
	}

	public boolean addItem(Buyable buyableItem) {
		if(buyableItem != null) {
			buyableItem.initBuyable(runner);
			if(buyableItem.initBuyable(runner)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean addItem(Buyable buyableItem, double weight) {
		buyableItem.setWeight(weight);
		return true;
	}

	public double getCheckoutTotal() {
		return 0;
	}
	
	public double getExpectedWeight() {
		return 0;
	}

	public double getScaleWeight() {
		return expScaleWeight;
	}

	public double getSubtotal() {
		return expPrice;
	}

	public double getTax() {
		return taxR * getSubtotal();
	}

	public boolean isCheckID() {
		return false;
	}

	public void setCheckID(boolean arg0) {
	
	}

}
