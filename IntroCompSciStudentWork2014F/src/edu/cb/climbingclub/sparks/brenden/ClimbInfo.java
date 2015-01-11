package edu.cb.climbingclub.sparks.brenden;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractClimbInfo peakName) {
		return this.getPeakName().compareTo(peakName.getPeakName());
	}

}
