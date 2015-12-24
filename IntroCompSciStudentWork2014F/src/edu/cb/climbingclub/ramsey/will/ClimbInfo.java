package edu.cb.climbingclub.ramsey.will;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractClimbInfo o) {
		// TODO Auto-generated method stub
		int compared = this.getPeakName().compareTo(o.getPeakName());
		return compared;
	}

}
