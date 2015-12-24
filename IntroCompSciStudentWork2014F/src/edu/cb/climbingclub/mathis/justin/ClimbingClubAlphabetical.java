package edu.cb.climbingclub.mathis.justin;

import java.util.Collections;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	@Override
	public void addClimb(String peak, int time) {
		ClimbInfo a = new ClimbInfo(peak, time);
		this.getClimbList().add(a);
		Collections.sort(this.getClimbList());
		

	}

	@Override
	public int distinctPeakNames() {
		Collections.sort(this.getClimbList());
		int sum = 0;
		List<AbstractClimbInfo> a = getClimbList();
		for(int i = 1; i < a.size()-1; i++){
			if(a.get(i) != a.get(i-1)){
				sum+=1;
			}
			if(a.get(i) != a.get(i+1)){
				sum+=1;
			}
		}
		return sum;
	}

}
