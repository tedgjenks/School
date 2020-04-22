package edu.cb.climbingclub.whitt.rose;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}
	
	public int compareTo(AbstractClimbInfo o) {
		return this.getPeakName().compareTo(o.getPeakName());
	}

}
