package edu.cb.climbingclub.tran.duc;

import java.util.*;

import edu.jenks.dist.cb.climbingclub.*;


public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		List<AbstractClimbInfo> newClimbList = new ArrayList<AbstractClimbInfo>();
		setClimbList(newClimbList);
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimb = new ClimbInfo(peakName, climbTime);
		getClimbList().add(newClimb);
	}

	@Override
	public int distinctPeakNames() {
		if(getClimbList().size() <= 0){
			return 0;
		}
		List<String> compNames = new ArrayList<String>();
		compNames.add(getClimbList().get(0).getPeakName());
		for (int index = 0; index < getClimbList().size(); index ++){
			Boolean add = true;
			String currName = getClimbList().get(index).getPeakName();
			for(int nameIndex = 0; nameIndex < compNames.size(); nameIndex++){
				if(compNames.get(nameIndex).equals(currName)){
					add = false;
				}
			}
			if(add){
				compNames.add(currName);
			}
		}
		return compNames.size();
	}

}
