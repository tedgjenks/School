package edu.cb.climbingclub.whitt.rose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public static void main(String[] args) {
		ClimbingClubChronological c = new ClimbingClubChronological();
		c.addClimb("Monadnock", 274);
		c.addClimb("Whiteface", 301);
		c.addClimb("Algonquin", 225);
//		c.addClimb("Rose", 345);
		c.addClimb("Monadnock", 344);
		c.addClimb("Monadnock", 234);
//		c.addClimb("Algonquin", 532);
		
//		System.out.println(Collections.frequency(c.getClimbList(), "Rose"));
		
		for (int i = 0; i < c.getClimbList().size(); i++) {
			System.out.println(c.getClimbList().get(i).getPeakName() + ", " + c.getClimbList().get(i).getClimbTime());
		}

		System.out.println(c.distinctPeakNames());
	}
	
	public ClimbingClubChronological() {
		
	}
	
	@Override
	public void addClimb(String arg0, int arg1) {
		// TODO Auto-generated method stub
		getClimbList().add(new ClimbInfo(arg0, arg1));
	}

	@Override
	public int distinctPeakNames() {
		List<String> a = new ArrayList<String>();
		for (int i = 0; i < getClimbList().size(); i++) {
			if (a.contains(getClimbList().get(i).getPeakName()) == false) {
				a.add(getClimbList().get(i).getPeakName());
			}
		}
		return a.size();
	}
	
	

}
