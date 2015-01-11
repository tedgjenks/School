package edu.cb.climbingclub.sparks.brenden;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;


public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		this.climbList.add(new ClimbInfo(peakName, climbTime));
		
	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		return 0;
	}

}
