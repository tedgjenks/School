package edu.inheritance.jenks.ted;

import edu.jenks.dist.inheritance.*;

/**
 * SelfCheckout allows customers to scan Item objects during checkout.<br>
 * An expected weight is kept, which is the sum of the Item weights.<br>
 * It sets a flag if any item requires an ID check.<br>
 * At the end of the checkout process, the total price is calculated.<br>
 * Total price is calculated from the individual Item prices and the associated tax.<br>
 * The scale weight is recorded for any Item that requires weighing.<br>
 * 
 * @author Ted Jenks
 *
 */
public class SelfCheckout implements ItemHandler {
	/**
	 * The standard tax rate applied to all items.<br>
	 * This value should be initialized in the constructor.
	 */
	public final double TAX_RATE;
	
	private double expectedWeight;
	private boolean checkID;
	private double subTotal;
	private double tax;
	private double scaleWeight;

	/**
	 * @param taxRate
	 */
	public SelfCheckout(double taxRate) {
		TAX_RATE = taxRate;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#getScaleWeight()
	 */
	@Override
	public double getScaleWeight() {
		return scaleWeight;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#isCheckID()
	 */
	@Override
	public boolean isCheckID() {
		return checkID;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#setCheckID(boolean)
	 */
	@Override
	public void setCheckID(boolean checkID) {
		this.checkID = checkID;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#getExpectedWeight()
	 */
	@Override
	public double getExpectedWeight() {
		return expectedWeight;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#addItem(edu.jenks.dist.inheritance.Buyable)
	 */
	@Override
	public boolean addItem(Buyable item) {
		return addItem(item, 0);
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#addItem(edu.jenks.dist.inheritance.Buyable, double)
	 */
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
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#getCheckoutTotal()
	 */
	@Override
	public double getCheckoutTotal() {
		return subTotal + tax;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#getSubtotal()
	 */
	@Override
	public double getSubtotal() {
		return subTotal;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.ItemHandler#getTax()
	 */
	@Override
	public double getTax() {
		return tax;
	}
	
}
