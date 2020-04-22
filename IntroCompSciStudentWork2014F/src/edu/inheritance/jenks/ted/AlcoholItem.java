/**
 * 
 */
package edu.inheritance.jenks.ted;

import edu.jenks.dist.inheritance.*;

/**
 * An AlcoholItem has a sin tax in addition to the base tax.<br>
 * An ID check is required upon checkout.<br>
 * 
 * @author Ted Jenks
 *
 */
public class AlcoholItem extends BarcodeItem implements SinTaxable {
	private double sinTaxRate;
	
	/**
	 * @param bulk
	 * @param weight
	 * @param price
	 * @param sinTaxRate
	 */
	public AlcoholItem(boolean bulk, double weight, double price, double sinTaxRate) {
		super(bulk, weight, price);
		this.sinTaxRate = sinTaxRate;
	}

	/* (non-Javadoc)
	 * @see edu.inheritance.jenks.ted.BarcodeItem#getTax(double)
	 */
	@Override
	public double getTax(double baseTaxRate) {
		return (baseTaxRate + sinTaxRate) * getPrice();
	}
	
	/* (non-Javadoc)
	 * @see edu.inheritance.jenks.ted.BarcodeItem#initBuyable(edu.jenks.dist.inheritance.ItemHandler)
	 */
	@Override
	public boolean initBuyable(ItemHandler itemHandler) {
		itemHandler.setCheckID(true);
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.SinTaxable#getSinTaxRate()
	 */
	@Override
	public double getSinTaxRate() {
		return sinTaxRate;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.SinTaxable#setSinTaxRate(double)
	 */
	@Override
	public void setSinTaxRate(double sinTaxRate) {
		this.sinTaxRate = sinTaxRate;
	}
	
}
