package edu.cb.climbingclub.collier.serenity;

import java.util.Collections;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakname, int climbTime) {
		ClimbInfo c = new ClimbInfo(peakname,climbTime);
		climbList.add(c);
		Collections.sort(climbList);
	}

	@Override
	public int distinctPeakNames() {
		int  numNames= 0;
		for(int index = 0;index<climbList.size() ;index++){
			if(numNames>=0){
				
			}
		}
		return 0;
	}

}
