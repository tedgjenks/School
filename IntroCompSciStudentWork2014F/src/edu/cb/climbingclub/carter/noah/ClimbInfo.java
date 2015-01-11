package edu.cb.climbingclub.carter.noah;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbInfo extends AbstractClimbInfo implements Comparable<AbstractClimbInfo> {

	  
	  public ClimbInfo(String peakName, int climbTime) 
	  { 
	    super(peakName,climbTime);
	  }
	  
	  public static void main(String[] args)
	  {
	    ClimbInfo info = new ClimbInfo("Whiteface", 301);
	    System.out.println(info);
	    info = new ClimbInfo("Algonquin", 225);
	    System.out.println(info);
	  }

	@Override
	public int compareTo(AbstractClimbInfo InputClimbInfo) {
		return getPeakName().compareTo(InputClimbInfo.getPeakName());
			
		
	}

	}

