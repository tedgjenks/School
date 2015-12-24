package edu.cb.climbingclub.mariscal.juan;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String name, int time) {
		// TODO Auto-generated method stub
		ClimbInfo ci = new ClimbInfo(name, time);
		getClimbList().add(ci);
	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		ArrayList<AbstractClimbInfo> names = new ArrayList<>();
		names.add(getClimbList().get(0));
		
		for(int n = 1; n < getClimbList().size()-1; n++){
			if(!(names.contains(getClimbList().get(n).getPeakName()))){
				names.add(getClimbList().get(n));
			}
		}
		return names.size();
		
	}

}
