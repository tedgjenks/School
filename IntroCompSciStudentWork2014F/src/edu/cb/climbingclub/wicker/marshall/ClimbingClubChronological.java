package edu.cb.climbingclub.wicker.marshall;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String name, int time) {
		climbList.add(new ClimbInfo(name, time));
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
