/**
 * 
 */
package edu.jenks.dist.barrons.experiment;

/**
 * @author Ted Jenks
 *
 */
public interface Solution {
	/**
	 * @return an integer value that ranges from 1 (very acidic) to 14
	 */
	int getPH();
	
	/**
	 * Set PH to new Value.
	 * @param newValue the new PH value
	 */
	void setPH(int newValue);
}
