/**
 * 
 */
package edu.cb.climbingclub.jenks.ted;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

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
	/*public int distinctPeakNames() {
		Set<String> peakNames = new HashSet<String>();
		for(AbstractClimbInfo climbInfo : climbList)
			peakNames.add(climbInfo.getPeakName());
		return peakNames.size();
	}*/
	
	public int distinctPeakNames() {
		List<String> distinctPeakNames = new ArrayList<String>();
		for(AbstractClimbInfo climbInfo : climbList) {
			String peakName = climbInfo.getPeakName();
			if(!findPeakName(distinctPeakNames, peakName))
				distinctPeakNames.add(peakName);
		}
		return distinctPeakNames.size();
	}
	
	private boolean findPeakName(List<String> peakNames, String peakName) {
		boolean found = false;
		for(int index = 0; index < peakNames.size() && !found; index++) {
			found = peakName.compareTo(peakNames.get(index)) == 0;
		}
		return found;
	}

}
