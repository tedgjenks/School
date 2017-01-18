/**
 * 
 */
package edu.inheritance.jenks.ted;

import edu.jenks.dist.inheritance.*;

/**
 * @author Ted Jenks
 *
 */
public abstract class Item implements Buyable {
	private double weight;
	private boolean bulk;
	
	static double getStandardTax(double baseTaxRate, double price) {
		return baseTaxRate * price;
	}
	
	/**
	 * @param bulk
	 */
	public Item(boolean bulk) {
		this.bulk = bulk;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#getWeight()
	 */
	@Override
	public double getWeight() {
		return weight;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#setWeight(double)
	 */
	@Override
	public void setWeight(double weight) {
		if(weight < 0)
			throw new IllegalArgumentException("Weight must be >= 0: " + weight);
		this.weight = weight;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#isBulk()
	 */
	@Override
	public boolean isBulk() {
		return bulk;
	}
	
	/* (non-Javadoc)
	 * @see edu.jenks.dist.inheritance.Buyable#setBulk(boolean)
	 */
	@Override
	public void setBulk(boolean bulk) {
		this.bulk = bulk;
	}
}
