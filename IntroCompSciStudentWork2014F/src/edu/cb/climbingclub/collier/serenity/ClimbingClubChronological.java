package edu.cb.climbingclub.collier.serenity;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakname, int climbTime) {
		ClimbInfo c = new ClimbInfo(peakname,climbTime);
		climbList.add(c);
		

	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		return 0;
	}

}
