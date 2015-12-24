package edu.cb.climbingclub.higginbotham.andrew;


import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {


	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		getClimbList().add(new ClimbInfo(peakName, climbTime));
		getClimbList().sort(null);
	}

	@Override
	public int distinctPeakNames() {
		ArrayList<String> arrayList = new ArrayList<>();
		for(AbstractClimbInfo climb: getClimbList())
			if(climbList.indexOf(climb.getPeakName()) < 0){
				arrayList.add(climb.getPeakName());
			}
			return arrayList.size();
	
	}
}
