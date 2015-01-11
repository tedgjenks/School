package edu.cb.climbingclub.latham.chase;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public ClimbingClubAlphabetical() {
		
	}

	@Override
	public void addClimb(String arg0, int arg1) {
		ClimbingClubChronological hikerClub = new ClimbingClubChronological();
		hikerClub.addClimb("HEY", 110);
		hikerClub.addClimb("WHAT", 312);
		hikerClub.addClimb("PEIGNUIN", 256);
		hikerClub.addClimb("BIG MOMMA", 889);


	}

	@Override
	public int distinctPeakNames() {
		
		return 0;
	}

}
