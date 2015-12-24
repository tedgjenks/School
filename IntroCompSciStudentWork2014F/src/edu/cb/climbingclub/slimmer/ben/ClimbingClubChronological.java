package edu.cb.climbingclub.slimmer.ben;

import java.util.ArrayList;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological
extends AbstractClimbingClub{

	@Override
	public void addClimb(String arg0, int arg1) {
		ClimbInfo newclimb= new ClimbInfo(arg0,arg1);
		this.getClimbList().add(newclimb);
		
	}

	@Override
	public int distinctPeakNames() {
		ArrayList<String> distincts= new ArrayList<String>();
		if(this.getClimbList().size()==0)
			return 0;
		distincts.add(this.getClimbList().get(0).getPeakName());
		for(int i=1;i<this.getClimbList().size();i++){
			if(distincts.indexOf(getClimbList().get(i).getPeakName())==-1)
				distincts.add(getClimbList().get(i).getPeakName());
		}
		return distincts.size();
	}

}
