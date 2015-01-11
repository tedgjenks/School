package edu.cb.climbingclub.warner.hampton;

import edu.jenks.dist.cb.climbingclub.*;


public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		
	}

	@Override
	public void addClimb(String peakName, int clubTime) {
		this.climbList.add(new ClimbInfo(peakName, clubTime));
		
		
	}

	@Override
	public int distinctPeakNames() {
		
		return 0;
	}

}
