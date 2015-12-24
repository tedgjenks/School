package edu.cb.climbingclub.hines.jonathan;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimb = new ClimbInfo(peakName, climbTime);
		getClimbList().add(newClimb);
	}

	@Override
	public int distinctPeakNames() {

		ArrayList <String> climbList = new ArrayList<>(); 

		for(AbstractClimbInfo ci : getClimbList())
		{
			if(climbList.indexOf(ci.getPeakName())<0)
			{
				climbList.add(ci.getPeakName());
			}
		}
		return climbList.size();
	}
}

