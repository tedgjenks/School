package edu.cb.climbingclub.hastings.nick;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;


public class ClimbInfo extends AbstractClimbInfo {


public ClimbInfo(String peakName, int climbTime) {

super(peakName, climbTime);

// TODO Auto-generated constructor stub

}


@Override

public int compareTo(AbstractClimbInfo o) {

// TODO Auto-generated method stub

String mountain= o.getPeakName();

return this.getPeakName().compareTo(mountain);

}


}