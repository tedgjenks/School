package edu.cb.climbingclub.shearer.richard;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	@Override
	public int compareTo(AbstractClimbInfo object) {
		return this.getPeakName().compareTo(object.getPeakName());
	}

}
