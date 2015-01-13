package edu.cb.climbingclub.rhodes.carter;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakName, int climbTime) {
		climbList.add(new ClimbInfo(peakName, climbTime));

	}

	@Override
	public int distinctPeakNames() {
		ArrayList<String> nameList = new ArrayList<String>();
		for(AbstractClimbInfo num: getClimbList()){
			if(nameList.indexOf(num.getPeakName())<0){
				nameList.add(num.getPeakName());	
			}
		}
		return nameList.size();
	}

}
