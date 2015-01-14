/**
 * 
 */
package edu.jenks.dist.cb.masterorder;

/**
 * @author JenksT
 *
 */
public class CookieOrder {
	
	private String variety;
	private int numBoxes;

	/**
	 * @param variety
	 * @param numBoxes
	 */
	public CookieOrder(String variety, int numBoxes) {
		this.variety = variety;
		this.numBoxes = numBoxes;
	}

	/**
	 * @return the variety of cookie being ordered
	 */
	public String getVariety() {
		return variety;
	}

	/**
	 * @return the number of boxes being ordered
	 */
	public int getNumBoxes() {
		return numBoxes;
	}

}
