package edu.cb.climbingclub.smith.eli;

import java.util.*;

import edu.jenks.dist.cb.climbingclub.*;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo newClimb = new ClimbInfo(peakName, climbTime);
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
