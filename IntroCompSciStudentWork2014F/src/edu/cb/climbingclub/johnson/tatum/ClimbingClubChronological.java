package edu.cb.climbingclub.johnson.tatum;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakName, int climbTime) {
		AbstractClimbInfo c = new ClimbInfo(peakName, climbTime);
		getClimbList().add(c);
	}

	@Override
	public int distinctPeakNames() {
		ArrayList <String> cl = new ArrayList<String>();
		for(AbstractClimbInfo c : getClimbList()){
			if(cl.indexOf(c.getPeakName()) < 0){
				cl.add(c.getPeakName());
			}
		}
		return cl.size();
	}

}
