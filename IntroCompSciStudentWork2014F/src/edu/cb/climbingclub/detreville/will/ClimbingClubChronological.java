package edu.cb.climbingclub.detreville.will;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int clubTime) {
		this.climbList.add(new ClimbInfo(peakName, clubTime));
		

	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		return 0;
	}

}
