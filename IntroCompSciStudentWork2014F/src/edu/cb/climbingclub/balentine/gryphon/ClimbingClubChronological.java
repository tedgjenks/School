package edu.cb.climbingclub.balentine.gryphon;

import java.util.ArrayList;
import java.util.HashSet;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo info=new ClimbInfo(peakName, climbTime);
		getClimbList().add(info);
	}

	@Override
	public int distinctPeakNames() {
		ArrayList<AbstractClimbInfo> array=new ArrayList<>();
		HashSet<AbstractClimbInfo> unique=new HashSet<>();
		unique.addAll(array);
		return unique.size();
	}

	public static void main(String[] args) {
	}

}
