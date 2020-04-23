package edu.cb.climbingclub.macias.marcus;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub {

	public void addClimb(String peakName, int climbTime) {
		ClimbInfo info = new ClimbInfo(peakName, climbTime);
		// List<AbstractClimbingClub> list = getClimbList();
		this.getClimbList().add(info);

	}

	public static void main(String[] args) {
		ClimbingClubChronological run = new ClimbingClubChronological();
		run.addClimb("Cooll", 3);
		run.addClimb("Cooall", 3);
		run.addClimb("Marcus", 3);
		run.addClimb("Batman", 3);
		run.addClimb("Cooll", 3);
		run.addClimb("Cooll", 3);
		System.out.println(run.distinctPeakNames());
		for (int i = 0; i < run.getClimbList().size(); i++) {
			System.out.println(run.getClimbList().get(i).getPeakName());
		}

	}

	public int distinctPeakNames() {
		List<String> names = new ArrayList<>();

		for (int i = 0; i < getClimbList().size(); i++) {
			String currentName = getClimbList().get(i).getPeakName();
			boolean maybeAdd = true;
			if (names.size() == 0) {
				names.add(currentName);
				continue;
			}

			for (int x = 0; x < names.size(); x++) {
				String compareName = names.get(x);
				if (currentName.equals(compareName)) {
					maybeAdd = false;
					break;
				}

			}
			if (maybeAdd) {
				names.add(currentName);
			}

		}

		return names.size();
	}

}
