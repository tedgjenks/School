/**
 * 
 */
package edu.jenks.dist.inheritance;

/**
 * The price of <code>Weighable</code> items is determined by multiplying the scale weight by the price per pound.
 * @author Ted Jenks
 *
 */
public interface Weighable extends Buyable {

	/**
	 * @return
	 */
	double getPricePerPound();
	
	/**
	 * @param pricePerPound
	 */
	void setPricePerPound(double pricePerPound);
}
