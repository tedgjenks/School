package edu.cb.climbingclub.wicker.marshall;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;

public class ClimbInfo extends AbstractClimbInfo{	
	public ClimbInfo(String name, int time){
		super(name, time);
	}

	public int compareTo(AbstractClimbInfo comparedInfo) {
		//if (this.getPeakName().compareToIgnoreCase((comparedInfo.getPeakName())) != 0)
			return this.getPeakName().compareTo(comparedInfo.getPeakName());
		/*else if (this.getClimbTime() > comparedInfo.getClimbTime())
			return -1;
		else if (this.getClimbTime() < comparedInfo.getClimbTime())
			return 1;
		else
			return 0;
		*/	
	}}
