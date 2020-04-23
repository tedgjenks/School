package edu.cb.climbingclub.page.javin;
import edu.jenks.dist.cb.climbingclub.*;
public class ClimbingClubChronological extends AbstractClimbingClub{

	@Override
	public void addClimb(String arg0, int arg1) {
		getClimbList().add(new ClimbInfo(arg0, arg1));
	}

	@Override
	public int distinctPeakNames() {
		int thismany = 0;
		thefirst: for(int i = 0; i < getClimbList().size();i++) {
			AbstractClimbInfo info = getClimbList().get(i);
			for(int j = 0; j < i; j++) {
				if(info.compareTo(getClimbList().get(j)) == 0) {
					continue thefirst;
				}
			}
			thismany++;
		}
		return thismany;
	}
	public static void main(String[] args) {
		ClimbingClubChronological instance = new ClimbingClubChronological();
		instance.addClimb("hi", 233);
		instance.addClimb("baby", 233);
		instance.addClimb("boy", 233);
		instance.addClimb("hi", 233);
		System.out.println(instance.distinctPeakNames());
	}
}
