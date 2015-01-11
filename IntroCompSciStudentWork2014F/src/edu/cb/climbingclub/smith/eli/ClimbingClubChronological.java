package edu.cb.climbingclub.smith.eli;

import java.util.*;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public ClimbingClubChronological() {
		
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		climbList.add(new ClimbInfo(peakName, climbTime));
		

	}

	@Override
	public int distinctPeakNames() {
		int sameCount = 0;
		int same = 0;
		List<String> comparedToList = new ArrayList<String>();
		for(int i = 0; i < climbList.size(); i++){
			comparedToList.add(climbList.get(i).getPeakName());
		}
		for(int comparerIndex = climbList.size() - 1; comparerIndex >= 0; comparerIndex --){
			sameCount = 1;
			for(int scannerIndex = comparedToList.size() - 1; scannerIndex >= 0; scannerIndex--){
				String comparer = climbList.get(comparerIndex).getPeakName();
				String comparedTo = climbList.get(scannerIndex).getPeakName();
				if(comparer.equals(comparedTo )){
					sameCount = sameCount - 1;
				}
				if(sameCount < 0){
					same = sameCount * -1;
				}
				
			}
		}
		return climbList.size() - same;
	}

}
