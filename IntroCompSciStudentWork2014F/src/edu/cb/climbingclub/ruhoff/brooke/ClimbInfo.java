package edu.cb.climbingclub.ruhoff.brooke;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {
	public ClimbInfo(java.lang.String peakName, int climbTime){
		super(peakName, climbTime);
	}
	@Override
	public int compareTo(AbstractClimbInfo o) {
		return getPeakName().compareTo(o.getPeakName());
	}

}
