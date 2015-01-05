/**
 * 
 */
package edu.cb.climbingclub.jenks.ted;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ted
 *
 */
public class ClimbingClubChronological extends AbstractClimbingClub {

	/**
	 * 
	 */
	public ClimbingClubChronological() {}

	/* (non-Javadoc)
	 * @see edu.cb.climbingclub.jenks.ted.AbstractClimbingClub#addClimb(java.lang.String, int)
	 */
	@Override
	public void addClimb(String peakName, int climbTime) {
		climbList.add(new ClimbInfo(peakName, climbTime));
	}

	/* (non-Javadoc)
	 * @see edu.cb.climbingclub.jenks.ted.AbstractClimbingClub#distinctPeakNames()
	 */
	@Override
	public int distinctPeakNames() {
		Set<String> peakNames = new HashSet<String>();
		for(AbstractClimbInfo climbInfo : climbList)
			peakNames.add(climbInfo.getPeakName());
		return peakNames.size();
	}

}
