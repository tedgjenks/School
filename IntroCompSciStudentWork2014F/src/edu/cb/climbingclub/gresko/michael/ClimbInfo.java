package edu.cb.climbingclub.gresko.michael;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	@Override
	public int compareTo(AbstractClimbInfo argument) {
		int compare = this.getPeakName().compareTo(argument.getPeakName());
		return compare;
	}
}
