package edu.cb.climbingclub.smith.rod;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		
	}

	@Override
	public int compareTo(AbstractClimbInfo o) {
		return 0;
	}

}
