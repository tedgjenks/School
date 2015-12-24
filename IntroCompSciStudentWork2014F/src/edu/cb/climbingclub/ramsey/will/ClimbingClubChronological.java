package edu.cb.climbingclub.ramsey.will;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String arg0, int arg1) {
		// TODO Auto-generated method stub
		ClimbInfo climb = new ClimbInfo(arg0,arg1);
		getClimbList().add(climb);
	}

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		int distinct = 0;
		ArrayList<AbstractClimbInfo> used = new ArrayList<AbstractClimbInfo>();
		distinct++;
		used.add(getClimbList().get(0));
		for(int index = 1; index < getClimbList().size();index++){;
				if(used.indexOf(getClimbList().get(index)) == -1){
						distinct++;
						used.add(getClimbList().get(index));
				}
		}
		return distinct;
	}

}

