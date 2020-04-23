package edu.cb.climbingclub.rhodes.maddux;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbInfo extends AbstractClimbInfo{

	public static void main(String[] args) {
		ClimbInfo test = new ClimbInfo("new peak", 200);
		System.out.println(test.compareTo(new ClimbInfo("new pea", 500)));
	}
	
	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	public int compareTo(AbstractClimbInfo o) {
		return this.getPeakName().compareTo(o.getPeakName());
	}

}
