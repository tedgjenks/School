package edu.cb.climbingclub.gresko.michael;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String name, int time) {
		ClimbInfo newClimb = new ClimbInfo(name, time);
		String sortName = newClimb.getPeakName();
		boolean found = false;
		for(int index = 0; index < climbList.size() && found == false; index++) {
			String tempName = climbList.get(index).getPeakName();
			int place = sortName.compareTo(tempName);
			if(place <= 0) {
				climbList.add(index, newClimb);
				found = true;
			}
		}
		if(found == false) {
			climbList.add(newClimb);
		}
		//System.out.println(climbList);
	}

	@Override
	public int distinctPeakNames() {
		List<String> differentClimbs = new ArrayList<String>();
		if(climbList.size() <= 0) {
			return 0;
		}
		differentClimbs.add(climbList.get(0).getPeakName());
		for(int index = 1; index < climbList.size(); index++) {
			String currentPeak = climbList.get(index).getPeakName();
			boolean found = true;
			for(int innerI = 0; innerI < differentClimbs.size(); innerI++) {
				if(currentPeak.equals(differentClimbs.get(innerI))) {
					found = false;
				}
			}
			if(found) {
				differentClimbs.add(currentPeak);
			}
		}
		return differentClimbs.size();
	}

}
