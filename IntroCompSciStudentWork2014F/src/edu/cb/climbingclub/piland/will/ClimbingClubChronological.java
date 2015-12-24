package edu.cb.climbingclub.piland.will;

import java.util.ArrayList;
import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		// TODO Auto-generated method stub
		
		ClimbInfo climb = new ClimbInfo(peakName, climbTime);
		getClimbList().add(climb);
		
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
