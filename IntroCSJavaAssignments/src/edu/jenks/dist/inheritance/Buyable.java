package edu.jenks.dist.inheritance;

/**
 * @author Ted Jenks
 *
 */
public interface Buyable {

	/**
	 * @return the weight of this item expected in the staging area.
	 */
	double getWeight();
	
	/**
	 * @param weight
	 */
	void setWeight(double weight);
	
	/**
	 * Bulk items are not placed in the staging area.<br>
	 * Their weight is not used to determine total expected weight in the staging area.
	 * @return
	 */
	boolean isBulk();
	
	/**
	 * @param bulk
	 */
	void setBulk(boolean bulk);
	
	/**
	 * @return
	 */
	double getPrice();
	
	/**
	 * @param baseTaxRate the decimal value of the base tax rate
	 * @return the amount of the tax charge added to the price
	 */
	double getTax(double baseTaxRate);
	
	/**
	 * Initialize Buyable (NOT TESTED).<br>
	 * Recommended implementation: call this when an item is added to communicate between the item and ItemHandler.
	 * @param itemHandler
	 * @return
	 */
	boolean initBuyable(ItemHandler itemHandler);
}
