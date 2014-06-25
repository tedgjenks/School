package edu.jenks.inheritance;

import edu.jenks.inheritance.dist.Buyable;
import edu.jenks.inheritance.dist.ItemHandler;

/**
 * SelfCheckout allows customers to scan Item objects during checkout.<br/>
 * An expected weight is kept, which is the sum of the Item weights.<br/>
 * It sets a flag if any item requires an ID check.<br/>
 * At the end of the checkout process, the total price is calculated.<br/>
 * Total price is calculated from the individual Item prices and the associated tax.<br/>
 * The scale weight is recorded for any Item that requires weighing.<br/>
 * 
 * @author Ted Jenks
 *
 */
public class SelfCheckout implements ItemHandler {
	public final double TAX_RATE;
	
	private double expectedWeight;
	private boolean checkID;
	private double subTotal;
	private double tax;
	private double scaleWeight;

	public SelfCheckout(double taxRate) {
		TAX_RATE = taxRate;
	}
	
	@Override
	public double getScaleWeight() {
		return scaleWeight;
	}
	
	protected void setScaleWeight(double scaleWeight) {
		this.scaleWeight = scaleWeight;
	}
	
	@Override
	public boolean isCheckID() {
		return checkID;
	}
	
	@Override
	public void setCheckID(boolean checkID) {
		this.checkID = checkID;
	}
	
	@Override
	public double getExpectedWeight() {
		return expectedWeight;
	}
	
	@Override
	public boolean addItem(Buyable item) {
		return addItem(item, 0);
	}
	
	@Override
	public boolean addItem(Buyable item, double scaleWeight) {
		boolean itemAdded = false;
		this.scaleWeight = scaleWeight;
		if(item.initBuyable(this)) {
			subTotal += item.getPrice();
			tax += item.getTax(TAX_RATE);
			if(!item.isBulk())
				expectedWeight += item.getWeight();
			itemAdded = true;
		}
		return itemAdded;
	}
	
	@Override
	public double getCheckoutTotal() {
		return subTotal + tax;
	}
	
}
