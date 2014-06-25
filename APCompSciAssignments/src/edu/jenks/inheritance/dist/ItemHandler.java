/**
 * 
 */
package edu.jenks.inheritance.dist;

/**
 * @author Ted Jenks
 *
 */
public interface ItemHandler {

	/**
	 * @return true if an ID check is required, otherwise false
	 */
	boolean isCheckID();
	
	void setCheckID(boolean checkID);
	
	/**
	 * @return the expected weight of scanned items in the staging area
	 */
	double getExpectedWeight();
	
	
	/**
	 * @return the weight as measured by the ItemHandler scale
	 */
	double getScaleWeight();
	
	/**
	 * scaleWeight should be set to zero<br/>
	 * 
	 * @see edu.jenks.inheritance.dist.ItemHandler#addItem(Buyable item, double scaleWeight)
	 */
	boolean addItem(Buyable item);
	
	/**
	 * Scan an item during the checkout process.<br/>
	 * The item price should be added to the subtotal.<br/>
	 * The item weight should be added to the expected weight, except for bulk items.<br/>
	 * Any other operations specific to special items should be performed.<br/>
	 * 
	 * @param item - the item being scanned
	 * @param scaleWeight - the weight of a WeighedItem
	 * @return true if the item is successfully added
	 */
	boolean addItem(Buyable item, double scaleWeight);
	
	/**
	 * @return sum of the subtotal and tax
	 */
	double getCheckoutTotal();
}
