package edu.cb.climbingclub.simon.job;
import java.util.ArrayList;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological 
extends  AbstractClimbingClub {
 
	@Override
	public void addClimb(String peakName, int climbTime) {
		// TODO Auto-generated method stub
		ClimbInfo object = new ClimbInfo(peakName, climbTime);
		this.getClimbList().add(object);
	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		ArrayList<String> distinctPeakNames = new ArrayList<String>();
		if(this.getClimbList().size() == 0) {
			return 0;
		}
		distinctPeakNames.add(this.getClimbList().get(0).getPeakName());
		for (int e = 1; e<this.getClimbList().size(); e++){
			if(distinctPeakNames.indexOf(getClimbList().get(e).getPeakName())<0){
				distinctPeakNames.add(getClimbList().get(e).getPeakName());
			}
		}
		return distinctPeakNames.size();	
	}

}
