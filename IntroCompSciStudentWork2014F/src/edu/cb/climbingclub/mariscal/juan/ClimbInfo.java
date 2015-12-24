package edu.cb.climbingclub.mariscal.juan;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo {
	
	
	public ClimbInfo(String peakName, int climbTime){
		super(peakName, climbTime);
	}
	@Override
	public int compareTo(AbstractClimbInfo a) {
		// TODO Auto-generated method stub
		
		return this.getPeakName().compareTo(a.getPeakName());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
