package edu.cb.climbingclub.patterson.andrew;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String arg0, int arg1) {
		for (int i = 0; i < this.climbList.size(); i++) {
			 if (arg0.compareTo(this.climbList.get(i).getPeakName()) <= 0) {
			 this.climbList.add(i, new ClimbInfo(arg0, arg1));
			    return;
			 }
		}
		this.climbList.add(new ClimbInfo(arg0, arg1)); 
	}

	@Override
	public int distinctPeakNames() {
		List<String> tempClimbList1 = new ArrayList<String>();
		for (AbstractClimbInfo name : this.climbList) {
			tempClimbList1.add(name.getPeakName());
		}
		Set<String> tempClimbList2 = new HashSet<String>(tempClimbList1);
    		 return tempClimbList2.size();
	}

}
