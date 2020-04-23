package edu.cb.climbingclub.macias.marcus;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo{

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		
	}
	public static void main(String[] args) {
		ClimbInfo run = new ClimbInfo("Batman",10);
		System.out.println(run.compareTo(new ClimbInfo("Marcus",10)));
		
	}
	
	public int compareTo(AbstractClimbInfo o) {
		String first = this.getPeakName();
		String second = o.getPeakName();
		
		return first.compareTo(second);
	}
	
}
