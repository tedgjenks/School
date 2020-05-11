package edu.cb.climbingclub.hollingsworth.james;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	@Override
	public void addClimb(String name, int climbTime) {
		List<AbstractClimbInfo> list = this.getClimbList();
		int index = 0, prevIndex = -1;
		while(index != prevIndex && index < list.size()) {
			prevIndex = index;
			if(list.get(index).getPeakName().compareTo(name) < 0) index--;
			else if(list.get(index).getPeakName().compareTo(name) > 0) index++;
			else {
				list.add(index, new ClimbInfo(name, climbTime));
				return;
			}
			
			if(index < 0) {
				list.add(0, new ClimbInfo(name, climbTime));
				return;
			}
			else if(index == prevIndex) {
				list.add(index, new ClimbInfo(name, climbTime));
				return;
			}
			
		}
		if(index == list.size()) list.add(new ClimbInfo(name, climbTime));
	}

	@Override
	public int distinctPeakNames() {
		List<AbstractClimbInfo> list = this.getClimbList();
		List<String> names = new ArrayList<String>();
		int numNames = 0;
		
		for(AbstractClimbInfo c : list) {
			boolean duplicate = false;
			for(String name : names) {
				if(name.equals(c.getPeakName())) {
					duplicate = true;
					break;
				}
			}
			if(!duplicate) {
				numNames++;
				names.add(c.getPeakName());
			}
		}
		
		return numNames;
	}
	
	public static void main(String[] args) {
		ClimbingClubAlphabetical c = new ClimbingClubAlphabetical();
		c.addClimb("Peak1", 0);
		c.addClimb("Testing", 2);
		c.addClimb("Peak1", 0);
		c.addClimb("Testing", 2);
		for(AbstractClimbInfo ci : c.getClimbList()) {
			System.out.println(ci.getPeakName() + ", ");
		}
		System.out.println(c.distinctPeakNames());
	}

}
