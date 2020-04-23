package edu.cb.climbingclub.macias.marcus;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub{

	
	public void addClimb(String peakName, int climbTime) {
		ClimbInfo info = new ClimbInfo(peakName,climbTime);
		boolean addQuestionMark = false;
		for(int i = 0 ; i < getClimbList().size();i++) {
			String currentName = getClimbList().get(i).getPeakName();
			int test = peakName.compareTo(currentName);
			if(test <= 0) {
				getClimbList().add(i, info);
				addQuestionMark = true;
				break;
			}
			
		}
		if(!addQuestionMark) {
			getClimbList().add(info);
		}
	}
	public static void main(String[] args) {
		ClimbingClubAlphabetical run = new ClimbingClubAlphabetical();
		run.addClimb("Monadnock", 274);
		run.addClimb("Whiteface", 301);
		run.addClimb("Algonquin", 225);
		run.addClimb("Monadnock", 344);
		
		for(int i = 0 ; i < run.getClimbList().size();i++) {
			System.out.println(run.getClimbList().get(i).getPeakName());
		}
		System.out.println(run.distinctPeakNames());
	}
	
	public int distinctPeakNames() {
		//List<String> list = new ArrayList<String>();
		List<String> names = new ArrayList<>();
		
		
		for(int i = 0 ; i < getClimbList().size();i++) {
			String currentName = getClimbList().get(i).getPeakName();
			boolean maybeAdd = true;
			if(names.size() == 0 ) {
				names.add(currentName);
				continue;
			}
			
			for(int x = 0 ; x < names.size();x++) {
				String compareName = names.get(x);
				if(currentName.equals(compareName)) {
					maybeAdd = false;
					break;
				}
				
			}
			if(maybeAdd) {
				names.add(currentName);
			}
			
			
		}
		
		return names.size();
	}

}
