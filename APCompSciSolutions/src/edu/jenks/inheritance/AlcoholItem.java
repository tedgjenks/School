/**
 * 
 */
package edu.jenks.inheritance;

import edu.jenks.inheritance.dist.ItemHandler;
import edu.jenks.inheritance.dist.SinTaxable;

/**
 * An AlcoholItem has a sin tax in addition to the base tax.<br/>
 * An ID check is required upon checkout.<br/>
 * 
 * @author Ted Jenks
 *
 */
public class AlcoholItem extends BarcodeItem implements SinTaxable {
	private double sinTaxRate;

	@Override
	public double getTax(double baseTaxRate) {
		return (baseTaxRate + sinTaxRate) * getPrice();
	}
	
	@Override
	public boolean initBuyable(ItemHandler itemHandler) {
		itemHandler.setCheckID(true);
		return true;
	}

	@Override
	public double getSinTaxRate() {
		return sinTaxRate;
	}

	@Override
	public void setSinTaxRate(double sinTaxRate) {
		this.sinTaxRate = sinTaxRate;
	}
	
}
