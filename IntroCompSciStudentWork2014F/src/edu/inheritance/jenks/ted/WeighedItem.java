/**
 * 
 */
package edu.inheritance.jenks.ted;

import edu.jenks.dist.inheritance.*;
/**
 * A WeighedItem has a price per pound, not a fixed price.<br>
 * Its price should be calculated from the price per pound and scale weight.<br>
 * 
 * @author Ted Jenks
 *
 */
public class WeighedItem extends Item implements Weighable {
	private double pricePerPound;
	
	/**
	 * @param bulk
	 * @param pricePerPound
	 */
	public WeighedItem(boolean bulk, double pricePerPound) {
		super(bulk);
		this.pricePerPound = pricePerPound;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Weighable#getPricePerPound()
	 */
	@Override
	public double getPricePerPound() {
		return pricePerPound;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Weighable#setPricePerPound(double)
	 */
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

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#getTax(double)
	 */
	@Override
	public double getTax(double baseTaxRate) {
		return Item.getStandardTax(baseTaxRate, getPrice());
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#initBuyable(edu.jenks.dist.inheritance.ItemHandler)
	 */
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
