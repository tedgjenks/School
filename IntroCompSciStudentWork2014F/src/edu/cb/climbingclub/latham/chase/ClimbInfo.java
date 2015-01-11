package edu.cb.climbingclub.latham.chase;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;


public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		peakName = ("HEY");
		climbTime = (10);
	}

	@Override
	public int compareTo(AbstractClimbInfo o) {
		
		return 0;
	}

}
