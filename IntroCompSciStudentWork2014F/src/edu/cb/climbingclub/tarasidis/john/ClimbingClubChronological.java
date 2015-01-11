package edu.cb.climbingclub.tarasidis.john;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String arg0, int arg1) {
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
