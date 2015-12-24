package edu.cb.climbingclub.balentine.gryphon;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	@Override
	public int compareTo(AbstractClimbInfo newInfo) {
		return this.getPeakName().compareTo(newInfo.getPeakName());
	}

	public static void main(String[] args) {
	}

}
