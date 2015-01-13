package edu.cb.climbingclub.rhodes.carter;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimbInfo= new ClimbInfo(peakName, climbTime);
		if(getClimbList().size()<= 0){
			getClimbList().add(newClimbInfo);
		}else{
			for(int index = getClimbList().size()-1; index>=-1;index--){
				if(index == - 1){
					getClimbList().add(0, newClimbInfo);
					break;
				}
				if(getClimbList().get(index).compareTo(newClimbInfo)<=0){
					getClimbList().add(index+1, newClimbInfo);
					break;
				}
			}
		}
		

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
