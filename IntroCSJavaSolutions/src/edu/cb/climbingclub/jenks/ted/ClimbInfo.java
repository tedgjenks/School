/**
 * 
 */
package edu.cb.climbingclub.jenks.ted;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

/**
 * @author Ted
 *
 */
/**
 * @author Ted
 *
 */
public class ClimbInfo extends AbstractClimbInfo {

	/**
	 * @param peakName
	 * @param climbTime
	 */
	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	
	/**
	 * Compares peak names alphabetically.
	 */
	@Override
	public int compareTo(AbstractClimbInfo o) {
		return this.getPeakName().compareTo(o.getPeakName());
	}

}
