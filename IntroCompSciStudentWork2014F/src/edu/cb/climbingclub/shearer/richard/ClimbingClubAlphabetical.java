package edu.cb.climbingclub.shearer.richard;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;
import java.util.ArrayList;

public class ClimbingClubAlphabetical extends AbstractClimbingClub { 

	public ClimbingClubAlphabetical() {}
	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo blah = new ClimbInfo(peakName, climbTime);
		getClimbList().add(blah);
		getClimbList().sort(null);
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
