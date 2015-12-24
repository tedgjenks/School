package edu.cb.climbingclub.mathis.justin;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractClimbInfo climb2) {
		return this.getPeakName().compareTo(climb2.getPeakName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
