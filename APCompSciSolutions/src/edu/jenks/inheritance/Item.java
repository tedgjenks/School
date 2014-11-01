/**
 * 
 */
package edu.jenks.inheritance;

import edu.jenks.inheritance.dist.Buyable;

/**
 * @author Ted Jenks
 *
 */
public abstract class Item implements Buyable {
	private double weight;
	private boolean bulk;
	
	@Override
	public double getWeight() {
		return weight;
	}
	
	@Override
	public void setWeight(double weight) {
		if(weight < 0)
			throw new IllegalArgumentException("Weight must be >= 0: " + weight);
		this.weight = weight;
	}
	
	@Override
	public boolean isBulk() {
		return bulk;
	}
	
	@Override
	public void setBulk(boolean bulk) {
		this.bulk = bulk;
	}
}
