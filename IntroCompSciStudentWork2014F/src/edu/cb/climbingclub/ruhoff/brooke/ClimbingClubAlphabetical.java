package edu.cb.climbingclub.ruhoff.brooke;

import java.util.Collections;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {
	public ClimbingClubAlphabetical(){
		
	}
	
	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo a = new ClimbInfo(peakName, climbTime);
		climbList.add(a);
		Collections.sort(climbList);
	}

	@Override
	public int distinctPeakNames() {
		int numPeaks=0;
		for(int index=0; index<climbList.size(); index++){
			if(climbList.contains(climbList.indexOf(index))){
				numPeaks+=1;
			}
		}
		return numPeaks;
	}

}
