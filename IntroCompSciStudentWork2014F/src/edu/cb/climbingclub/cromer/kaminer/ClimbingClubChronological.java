package edu.cb.climbingclub.cromer.kaminer;

import java.util.*;
import edu.jenks.dist.cb.climbingclub.*;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		List<AbstractClimbInfo> Goose = new ArrayList<AbstractClimbInfo>();
		setClimbList(Goose);
	}
	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimb = new ClimbInfo(peakName, climbTime);
		getClimbList().add(newClimb);
	}
	@Override
	public int distinctPeakNames() {
		if (getClimbList().size() <= 0 ){
			return 0;
		}
		List<String> peakNames = new ArrayList<String>();
		peakNames.add(getClimbList().get(0).getPeakName());
		for(int i = 0; i < getClimbList().size(); i++){
			Boolean fake = true;
			String currName = getClimbList().get(i).getPeakName();
			for(int q = 0; q < peakNames.size(); q++){
				if(peakNames.get(q).equals(currName)){
					fake = false;	
				}
			}
			if(fake){
				peakNames.add(currName);
			}
		}return peakNames.size();
	}
}