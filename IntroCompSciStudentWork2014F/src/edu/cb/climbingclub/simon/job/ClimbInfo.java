package edu.cb.climbingclub.simon.job;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo 
extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractClimbInfo arg0) {
		// TODO Auto-generated method stub
		return this.getPeakName().compareTo(arg0.getPeakName());
	}

}
