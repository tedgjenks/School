package edu.cb.climbingclub.scates.collin;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		this.climbList.add(new ClimbInfo(peakName, climbTime));
	}

	@Override
	public int distinctPeakNames() {
		
		return 0;
	}

}