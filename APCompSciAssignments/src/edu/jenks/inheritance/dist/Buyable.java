package edu.jenks.inheritance.dist;

public interface Buyable {

	double getWeight();
	void setWeight(double weight);
	boolean isBulk();
	void setBulk(boolean bulk);
	double getPrice();
	
	/**
	 * @param baseTaxRate the decimal value of the base tax rate
	 * @return the amount of the tax charge added to the price
	 */
	double getTax(double baseTaxRate);
	
	/**
	 * Initialize Buyable (NOT TESTED)
	 */
	boolean initBuyable(ItemHandler itemHandler);
}
