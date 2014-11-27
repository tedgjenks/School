/**
 * 
 */
package edu.jenks.dist.cb.lunch;

/**
 * @author Ted
 *
 */
public abstract class SingleMenuItem implements MenuItem {
	private String name;
	private double price;

	/**
	 * 
	 */
	public SingleMenuItem(String name, double price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
