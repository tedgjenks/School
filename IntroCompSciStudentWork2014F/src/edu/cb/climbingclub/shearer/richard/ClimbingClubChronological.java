package edu.cb.climbingclub.shearer.richard;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {}
	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo wow = new ClimbInfo(peakName, climbTime);
		getClimbList().add(wow);
	}
	@Override
	public int distinctPeakNames() {
			ArrayList<String> arrayList = new ArrayList<>();
			for(AbstractClimbInfo climb: getClimbList())
				if (climbList.indexOf(climb.getPeakName())<0){
					arrayList.add(climb.getPeakName());
				}
			return arrayList.size();
	}
}