package edu.cb.climbingclub.hines.jonathan;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) 
	{
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractClimbInfo newClimbInfo) 
	{
		return getPeakName().compareTo(newClimbInfo.getPeakName());
	}
}
