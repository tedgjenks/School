package edu.jenks.dist.inheritance;

/**
 * <code>Barcoded</code> items have a price independent of their weight.<br>
 * Therefore, they do not need to be weighed on the scale.<br>
 * They are placed in the staging area for weight check, unless they are bulk.
 * @author Ted Jenks
 *
 */
public interface Barcoded extends Buyable {

	/**
	 * Set the price of the barcoded item.<br>
	 * 
	 * <b>Precondition: </b> <code>price</code> is not negative.
	 * @param price
	 */
	void setPrice(double price);
}
