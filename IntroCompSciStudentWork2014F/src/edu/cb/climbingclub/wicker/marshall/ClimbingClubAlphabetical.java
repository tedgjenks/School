package edu.cb.climbingclub.wicker.marshall;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {
	
	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo ci = new ClimbInfo(peakName, climbTime);
		climbList.add(ci);
		climbList.sort(null);
		/*
		int position = -1;
		for (int i = 0; i < climbList.size(); i++){
			if (climbList.get(i).compareTo(ci) <= 0){
				position = i;
			}
		}
		if (position < 0){
			climbList.add(ci);
		}
		else{
			climbList.add(position, ci);
		}
		*/
	}

	@Override
	public int distinctPeakNames() {
		ArrayList<String> names = new ArrayList<String>(climbList.size());
		for (AbstractClimbInfo c : climbList){
			if (names.indexOf(c.getPeakName()) < 0){
				names.add(c.getPeakName());
			}
		}
		return names.size();
	}

}
