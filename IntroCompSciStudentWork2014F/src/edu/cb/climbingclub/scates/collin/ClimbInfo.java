package edu.cb.climbingclub.scates.collin;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	@Override
	public int compareTo(AbstractClimbInfo peakName) {
		return this.getPeakName().compareTo(peakName.getPeakName());
	}

	public static void add(ClimbInfo climbInfo) {
		
	}

}