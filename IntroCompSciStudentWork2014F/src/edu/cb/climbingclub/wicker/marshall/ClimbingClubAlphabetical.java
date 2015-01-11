package edu.cb.climbingclub.wicker.marshall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub{

	@Override
	public void addClimb(String name, int time) {
		List<AbstractClimbInfo> newList = getClimbList();
		newList.add(new ClimbInfo(name, time));
		Collections.sort(newList);
		this.setClimbList(newList);
	}

	@Override
	public int distinctPeakNames() {
		List<AbstractClimbInfo> allPeaks = getClimbList();
		ArrayList<String> usedNames = new ArrayList<String>(allPeaks.size());
		for (AbstractClimbInfo currentClimbInfo : allPeaks)
			if (usedNames.indexOf(currentClimbInfo.getPeakName()) < 0)
				usedNames.add(currentClimbInfo.getPeakName());
		return usedNames.size();
	}
	
}
