package edu.cb.climbingclub.slimmer.ben;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo
extends AbstractClimbInfo{

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	@Override
	public int compareTo(AbstractClimbInfo arg0) {
		return this.getPeakName().compareTo(arg0.getPeakName());
	}

}
