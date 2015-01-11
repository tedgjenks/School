/**
 * 
 */
package edu.cb.climbingclub.jenks.ted;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

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

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AbstractClimbInfo o) {
		return this.getPeakName().compareTo(o.getPeakName());
	}

}
