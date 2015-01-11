package edu.cb.climbingclub.warner.hampton;

import edu.jenks.dist.cb.climbingclub.*;


public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		
	}
	
	
	public void addClimb(String peakName, int climbTime) {
		for (int i = 0; i < this.climbList.size(); i++){
				if(peakName.compareTo(this.climbList.get(i).getPeakName())<=0);{
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
