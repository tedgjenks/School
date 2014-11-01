/**
 * 
 */
package edu.jenks.inheritance;

import edu.jenks.inheritance.dist.Barcoded;
import edu.jenks.inheritance.dist.ItemHandler;

/**
 * A BarcodedItem has a fixed price.<br/>
 * 
 * @author Ted Jenks
 *
 */
public class BarcodeItem extends Item implements Barcoded {
	private double price;

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

	@Override
	public double getTax(double baseTaxRate) {
		return ItemHelper.getStandardTax(baseTaxRate, price);
	}

	@Override
	public boolean initBuyable(ItemHandler itemHandler) {
		return true;
	}
}
