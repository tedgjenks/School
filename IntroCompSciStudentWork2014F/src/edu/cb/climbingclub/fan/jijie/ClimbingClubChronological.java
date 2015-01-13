package edu.cb.climbingclub.fan.jijie;

import java.util.*;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		// TODO Auto-generated method stub
		climbList.add(new ClimbInfo(peakName, climbTime));


	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub

		if (getClimbList().size() <= 0){
			return 0;
		}

		List<String> names= new ArrayList<String>();

		List<AbstractClimbInfo> cat= getClimbList();
		for(AbstractClimbInfo currCLimbInfo: cat){
			if(names.indexOf(currCLimbInfo.getPeakName())<0){
				names.add(currCLimbInfo.getPeakName());

			}
		}
	
		return names.size();
	}
}

