package edu.cb.climbingclub.burroughs.lauren;

import java.util.Collection;
import java.util.Collections;

import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakName, int time) {
		ClimbInfo climb = new ClimbInfo(peakName, time);
		climbList.add(climb);
		Collections.sort(climbList);
	}

	@Override
	public int distinctPeakNames() {
		List<AbstractClimbInfo> climb = climbList;
		for(AbstractClimbInfo a : climb ){
			String actClimb = a.getPeakName();
			int count = Collections.frequency(climbList, actClimb);
			return count;
		
		}
		return 0;
	}
}
