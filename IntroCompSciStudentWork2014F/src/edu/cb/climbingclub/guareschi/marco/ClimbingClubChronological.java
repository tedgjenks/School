package edu.cb.climbingclub.guareschi.marco;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub{

	@Override
	public void addClimb(String peakName, int climbTime) {
		for (int i = 0; i < this.climbList.size(); i++) {
			 if (peakName.compareTo(this.climbList.get(i).getPeakName()) <= 0) {
			 this.climbList.add(i, new ClimbInfo(peakName, climbTime));
			 return;
			 }
		}
	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		return 0;
	}
}
