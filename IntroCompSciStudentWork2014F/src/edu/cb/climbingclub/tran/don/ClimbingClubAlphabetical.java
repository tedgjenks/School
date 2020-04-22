package edu.cb.climbingclub.tran.don;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbingClubAlphabetical extends AbstractClimbingClub{

	public static void main(String[] args) {
		ClimbingClubAlphabetical c = new ClimbingClubAlphabetical();
		c.addClimb("Mount B", 100);
		c.addClimb("Mount B", 100);
		c.addClimb("Mount C", 100);
		c.addClimb("Mount D", 100);
		c.addClimb("Mount A", 100);
		c.addClimb("Mount A", 100);
		c.addClimb("Mount Z", 100);
		c.addClimb("Mount C", 5);
		c.addClimb("Mount Y", 5);
		c.prant();
		System.out.println(c.distinctPeakNames());
	}
	
	public void addClimb(String mount, int time) {
		ClimbInfo c = new ClimbInfo(mount, time);
		int len = getClimbList().size();
		if(getClimbList().size() == 0) {
			getClimbList().add(c);
		} else {
			for(int i = 0; i < len; i++) {
				if(getClimbList().get(i).compareTo(c) > 0) {
					getClimbList().add(i, c);
					break;
				}
				if(i == getClimbList().size() - 1) {
					getClimbList().add(c);
				}
			}
		}
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
