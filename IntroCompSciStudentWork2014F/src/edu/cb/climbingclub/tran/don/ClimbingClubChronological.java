package edu.cb.climbingclub.tran.don;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbingClubChronological extends AbstractClimbingClub{

	public static void main(String[] args) {
		ClimbingClubChronological c = new ClimbingClubChronological();
		c.addClimb("Mount A", 100);
		c.addClimb("Mount C", 100);
		c.addClimb("Mount C", 100);
		c.addClimb("Mount D", 100);
		c.prant();
	}
	
	public void addClimb(String mount, int time) {
		getClimbList().add(new ClimbInfo(mount, time));
	}
	
	public void prant() {
		for(AbstractClimbInfo c : getClimbList()) {
			System.out.println(c.getPeakName() + ", " + c.getClimbTime());
		}
	}

	@Override
	public int distinctPeakNames() {
		List<String> sepList = new ArrayList<String>();
		for(int i = 0; i < getClimbList().size(); i++) {
			if(sepList.contains(getClimbList().get(i).getPeakName())) {
				continue;
			} else {
				sepList.add(getClimbList().get(i).getPeakName());
			}
		}
		return sepList.size();
	}

}
