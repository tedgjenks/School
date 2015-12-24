package edu.cb.climbingclub.piland.will;

import java.util.ArrayList;
import java.util.Collections;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
	}

	@Override
	public void addClimb(String peakName, int climbtime) {
		// TODO Auto-generated method stub
		
		ClimbInfo climb = new ClimbInfo(peakName, climbtime);
		getClimbList().add(climb);
		Collections.sort(climbList);
	}

	@Override
	public int distinctPeakNames() {
		ArrayList<String> peakname = new ArrayList<>();
		if(climbList != null)
			for(AbstractClimbInfo climb : getClimbList()){

				for(int n = 0;  n < climbList.size(); n++ ){
					if(climb.getPeakName() != peakname.get(n)){
						peakname.add(climb.getPeakName());
					}
				}
			}


		return peakname.size();
	}

}
