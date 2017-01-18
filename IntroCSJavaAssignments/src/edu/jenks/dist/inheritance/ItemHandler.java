/**
 * 
 */
package edu.jenks.dist.inheritance;

/**
 * @author Ted Jenks
 *
 */
public interface ItemHandler {

	/**
	 * @return true if an ID check is required for the purchase of one or more items, otherwise false
	 */
	boolean isCheckID();
	
	void setCheckID(boolean checkID);
	
	/**
	 * @return the expected weight of scanned items in the staging area
	 */
	double getExpectedWeight();
	
	
	/**
	 * <code>Weighable</code> items are placed on a scale to determine their price.
	 * @return the weight as measured by the <code>ItemHandler</code> scale
	 */
	double getScaleWeight();
	
	/**
	 * @see edu.jenks.dist.inheritance.ItemHandler#addItem(Buyable item, double scaleWeight)
	 */
	boolean addItem(Buyable item);
	
	/**
	 * Scan an item during the checkout process.<br>
	 * The item price should be added to the subtotal.<br>
	 * The item weight should be added to the expected weight, except for bulk items.<br>
	 * Any other operations specific to special items should be performed.<br>
	 * <b>Preconditions: </b>
	 *  - <code>scaleWeight</code> will be greater than zero for <code>Weighable</code> items.<br>
	 *  - <code>scaleWeight</code> will be 0 (or can be ignored) for items with a predetermined price.<br>
	 * 
	 * @param item - the item being scanned
	 * @param scaleWeight - the weight of a <code>Weighable</code> item.
	 * @return true if the item is successfully added
	 */
	boolean addItem(Buyable item, double scaleWeight);
	
	/**
	 * @return the cost of all items in checkout (excluding taxes)
	 */
	double getSubtotal();
	
	/**
	 * @return the tax charge of all items in checkout
	 */
	double getTax();
	
	/**
	 * @return sum of the subtotal and tax
	 */
	double getCheckoutTotal();
}
