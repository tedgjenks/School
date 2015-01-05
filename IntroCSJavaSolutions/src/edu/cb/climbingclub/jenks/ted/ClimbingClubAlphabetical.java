/**
 * 
 */
package edu.cb.climbingclub.jenks.ted;

import java.util.Collections;

/**
 * @author Ted
 *
 */
public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	/**
	 * 
	 */
	public ClimbingClubAlphabetical() {}

	/* (non-Javadoc)
	 * @see edu.cb.climbingclub.jenks.ted.AbstractClimbingClub#addClimb(java.lang.String, int)
	 */
	@Override
	public void addClimb(String peakName, int climbTime) {
		climbList.add(new ClimbInfo(peakName, climbTime));
		Collections.sort(climbList);
	}

	/* (non-Javadoc)
	 * @see edu.cb.climbingclub.jenks.ted.AbstractClimbingClub#distinctPeakNames()
	 */
	@Override
	public int distinctPeakNames() {
		int peakNames = 0;
		String previous = null, next = null;
		for(AbstractClimbInfo climbInfo : climbList) {
			if(previous == null) {
				previous = climbInfo.getPeakName();
				peakNames++;
			} else {
				next = climbInfo.getPeakName();
				if(!next.equals(previous))
					peakNames++;
				previous = next;
			}
		}
		return peakNames;
	}

}
