package edu.cb.climbingclub.carter.noah;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimbInfo = new ClimbInfo(peakName, climbTime);
		if (getClimbList().size() <= 0){
			getClimbList().add(newClimbInfo);
		}else{
			for(int i = getClimbList().size() - 1; i >= -1; i--){
				if(i == -1){
					getClimbList().add(0, newClimbInfo);
					break;
				}
				if(getClimbList().get(i).compareTo(newClimbInfo) <= 0){
					getClimbList().add(i + 1, newClimbInfo);
					break;
				}
			}
		}

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
		}
		return peakNames.size();
}
}
