package edu.cb.climbingclub.smithdeal.thomas;

import java.util.*;

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
		//List <AbstractClimbingClub> climb2 = new ArrayList() <AbstractClimbingClub>;
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
