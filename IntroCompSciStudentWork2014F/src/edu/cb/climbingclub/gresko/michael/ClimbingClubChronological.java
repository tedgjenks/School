package edu.cb.climbingclub.gresko.michael;

import java.util.*;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String name, int time) {
		ClimbInfo newClimb = new ClimbInfo(name, time);
		climbList.add(newClimb);

	}

	@Override
	public int distinctPeakNames() {
		List<String> differentClimbs = new ArrayList<String>();
		if(climbList.size() <= 0) {
			return 0;
		}
		differentClimbs.add(climbList.get(0).getPeakName());
		for(int index = 0; index < climbList.size(); index++) {
			String currentPeak = climbList.get(index).getPeakName();
			boolean found = true;
			for(int innerI = 0; innerI < differentClimbs.size(); innerI++) {
				if(currentPeak.equals(differentClimbs.get(innerI))) {
					found = false;
				}
			}
			if(found) {
				differentClimbs.add(currentPeak);
			}
		}
		return differentClimbs.size();
	}

}
