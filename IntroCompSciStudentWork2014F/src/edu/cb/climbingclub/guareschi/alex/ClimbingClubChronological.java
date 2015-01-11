package edu.cb.climbingclub.guareschi.alex;
import edu.jenks.dist.cb.climbingclub.*;
public class ClimbingClubChronological extends AbstractClimbingClub{

	public ClimbingClubChronological() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addClimb(String peakName, int climbTime) {
		this.climbList.add(new ClimbInfo(peakName, climbTime));

			
		}
		
	

	@Override
	public int distinctPeakNames() {
		// TODO Auto-generated method stub
		return 0;
	}

}
