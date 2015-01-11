package edu.cb.climbingclub.tran.duc;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		
	}

	@Override
	public int compareTo(AbstractClimbInfo o) {
		int returnNumber = getPeakName().compareTo(o.getPeakName());
		return returnNumber;
	}

}
