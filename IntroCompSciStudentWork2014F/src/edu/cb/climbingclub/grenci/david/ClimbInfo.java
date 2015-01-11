package edu.cb.climbingclub.grenci.david;

//import edu.cb.climbingclub.jenks.ted.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.*;

public class ClimbInfo extends AbstractClimbInfo {

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
	}

	@Override
	public int compareTo(AbstractClimbInfo peakName) {
		String name = getPeakName();
		int runTime = getClimbTime();
		
		return 0;
	}

}
