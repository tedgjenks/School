/**
 * 
 */
package edu.jenks.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.Weighable;

/**
 * A WeighedItem has a price per pound, not a fixed price.<br/>
 * Its price should be calculated from the price per pound and scale weight.<br/>
 * 
 * @author Ted Jenks
 *
 */
public class WeighedItem extends Item implements Weighable {
	private double pricePerPound;

	@Override
	public double getPricePerPound() {
		return pricePerPound;
	}

	@Override
	public void setPricePerPound(double pricePerPound) {
		this.pricePerPound = pricePerPound;
	}

	/* (non-Javadoc)
	 * @see jenks.inheritance.Item#getPrice()
	 */
	@Override
	public double getPrice() {
		return getWeight() * pricePerPound;
	}

	@Override
	public double getTax(double baseTaxRate) {
		return ItemHelper.getStandardTax(baseTaxRate, getPrice());
	}

	@Override
	public boolean initBuyable(ItemHandler itemHandler) {
		boolean weightValid = false;
		double weight = itemHandler.getScaleWeight();
		if(weight > 0) {
			setWeight(weight);
			weightValid = true;
		}
		return weightValid;
	}

}
