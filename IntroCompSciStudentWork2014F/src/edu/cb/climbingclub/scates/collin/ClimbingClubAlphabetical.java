package edu.cb.climbingclub.scates.collin;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		for(int i = 0; i < this.climbList.size(); i++){
			if(peakName.compareTo(this.climbList.get(i).getPeakName())<=0){
				this.climbList.add(i, new ClimbInfo(peakName, climbTime));
				break;
			}
		}
	}

	@Override
	public int distinctPeakNames() {
		
		return 0;
	}

}