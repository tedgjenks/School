package edu.cb.climbingclub.burroughs.lauren;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);	
	}
	

	@Override
	public int compareTo(AbstractClimbInfo climb) {
		
		return getPeakName().compareTo(climb.getPeakName());
	}

}
