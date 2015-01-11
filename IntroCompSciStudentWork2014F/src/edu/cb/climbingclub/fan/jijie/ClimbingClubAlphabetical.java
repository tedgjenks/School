package edu.cb.climbingclub.fan.jijie;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimbInfo= new ClimbInfo(peakName, climbTime);
			if(getClimbList().size() <= 0) {
				getClimbList().add(newClimbInfo);
			}else{
				for(int index = getClimbList().size()-1; index>=-1; index--){
					if(index == -1){
						getClimbList().add(0, newClimbInfo);
						break;
					}
					if (getClimbList().get(index).compareTo(newClimbInfo)<=0){
						getClimbList().add(index+1, newClimbInfo);
						break;
					}
				}
			}
	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		if (getClimbList().size() <= 0){
			return 0;
		}
		List<String> names= new ArrayList<String>();
		names.add(getClimbList().get(0).getPeakName());
		for(int index = 0; index < climbList.size(); index++){
			Boolean add = true;
			String currName= getClimbList().get(index).getPeakName();
		
			for(int indexName = 0; indexName < names.size(); indexName++){
				if(names.get(indexName).equals(currName)){
					add = false;
				}
				if(add) {
					names.add(currName);
				}
			}
		}
		return names.size();
	}
		
}
		
	
