package edu.cb.climbingclub.tran.don;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbInfo extends AbstractClimbInfo{

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractClimbInfo arg0) {
		return this.getPeakName().compareTo(arg0.getPeakName());
	}

}
