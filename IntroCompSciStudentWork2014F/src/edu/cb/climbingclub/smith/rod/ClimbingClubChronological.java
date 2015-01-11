package edu.cb.climbingclub.smith.rod;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbingClubChronological trail = new ClimbingClubChronological();
		trail.addClimb("Monadnock", 274);
		trail.addClimb("Whiteface", 301);
		trail.addClimb("Alginquin", 225);
		trail.addClimb("Monadnock", 344);
		
	}

	@Override
	public int distinctPeakNames() {
		return 0;
	}

}
