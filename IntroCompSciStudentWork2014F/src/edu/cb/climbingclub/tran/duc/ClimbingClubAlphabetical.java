package edu.cb.climbingclub.tran.duc;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		List<AbstractClimbInfo> newClimbList = new ArrayList<AbstractClimbInfo>();
		setClimbList(newClimbList);
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimbInfo = new ClimbInfo(peakName, climbTime);
		if(getClimbList().size() <= 0){
			getClimbList().add(newClimbInfo);
		}else{
			for(int index = getClimbList().size() - 1; index >= -1; index--){
				if(index == -1){
					getClimbList().add(0, newClimbInfo);
					break;
				}
				if(getClimbList().get(index).compareTo(newClimbInfo) <= 0){
					getClimbList().add(index + 1, newClimbInfo);
					break;
				}
			}
		}
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
