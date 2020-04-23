package edu.cb.climbingclub.page.javin;
import edu.jenks.dist.cb.climbingclub.*;
public class ClimbInfo extends AbstractClimbInfo{

	public ClimbInfo(String peakName, int climbTime) {
		super(peakName, climbTime);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(AbstractClimbInfo o) {
		// TODO Auto-generated method stub
		return getPeakName().compareTo(o.getPeakName());
	}

}
