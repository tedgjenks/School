package edu.cb.climbingclub.wilbanks.tanner;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String name, int time) {
		ClimbInfo item = new ClimbInfo(name, time); 
		climbList.add(item);

	}

	@Override
	public int distinctPeakNames() {
		if (getClimbList().size() <= 0){
			return 0;
		}
		List<String> names = new ArrayList<String>();
		names.add(getClimbList().get(0).getPeakName());
		for(int index = 0; index<getClimbList().size(); index++){
			boolean yes = true;
			String curname = getClimbList().get(index).getPeakName();
			for(int counter = 0; counter<names.size(); counter++){
				if(curname.equals(names.get(counter))){
					yes = false;
				}
			}
			if(yes){
				names.add(curname);		
			}
		}
		return names.size();
	}

}
