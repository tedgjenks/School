package edu.cb.climbingclub.guareschi.alex;

import edu.jenks.dist.cb.climbingclub.*;


public class ClimbInfo extends AbstractClimbInfo{

	public ClimbInfo(String peakName, int climbTime) {
		super (peakName, climbTime);
	}

	@Override
	public int compareTo(AbstractClimbInfo peakName) {
		return this.getPeakName().compareTo(peakName.getPeakName());


	}
}