/**
 * 
 */
package edu.inheritance.jenks.ted;

import edu.jenks.dist.inheritance.*;

/**
 * A BarcodedItem has a fixed price.<br>
 * 
 * @author Ted Jenks
 *
 */
public class BarcodeItem extends Item implements Barcoded {
	private double price;
	
	/**
	 * @param bulk
	 * @param weight
	 * @param price
	 */
	public BarcodeItem(boolean bulk, double weight, double price) {
		super(bulk);
		setWeight(weight);
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Barcoded#setPrice(double)
	 */
	@Override
	public void setPrice(double price) {
		this.price = price;
	}

	/* (non-Javadoc)
	 * @see jenks.inheritance.Item#getPrice()
	 */
	@Override
	public double getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#getTax(double)
	 */
	@Override
	public double getTax(double baseTaxRate) {
		return Item.getStandardTax(baseTaxRate, price);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#initBuyable(edu.jenks.dist.inheritance.ItemHandler)
	 */
	@Override
	public boolean initBuyable(ItemHandler itemHandler) {
		return true;
	}
}
