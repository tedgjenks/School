package edu.cb.climbingclub.li.zhilin;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

import java.util.*;

public class ClimbingClubChronological extends AbstractClimbingClub {
	
	private ClimbingClubChronological ccc = new ClimbingClubChronological();

	public ClimbingClubChronological() {
		setClimbList(new ArrayList<AbstractClimbInfo>());
	}

	@Override
	public void addClimb(String peakName, int time) {
		ClimbInfo series = new ClimbInfo(peakName, time);
		climbList.add(series);
		
	}

	@Override
	public int distinctPeakNames() {
		int distinct = 0;
		
		return 0;
	}

}																									
