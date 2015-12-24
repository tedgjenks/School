package edu.cb.climbingclub.balentine.gryphon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo info=new ClimbInfo(peakName, climbTime);
		getClimbList().add(info);
		Collections.sort(getClimbList());
	}

	@Override
	public int distinctPeakNames() {
		/*ArrayList<AbstractClimbInfo> names=new ArrayList<>();
		names.add(getClimbList().get(0));
		for(int a=1; a<getClimbList().size()-1; a++){
			if(!(names.contains(getClimbList().get(a).getPeakName())))
				names.add(getClimbList().get(a));
		}
		return names.size();*/
		
		ArrayList<AbstractClimbInfo> array=new ArrayList<>();
		HashSet<AbstractClimbInfo> unique=new HashSet<>();
		unique.addAll(array);
		return unique.size();
	}

	public static void main(String[] args) {
	}

}
