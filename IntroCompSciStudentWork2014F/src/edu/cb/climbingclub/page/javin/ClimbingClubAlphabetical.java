package edu.cb.climbingclub.page.javin;
import edu.jenks.dist.cb.climbingclub.*;
public class ClimbingClubAlphabetical extends AbstractClimbingClub{

	@Override
	public void addClimb(String arg0, int arg1) {
		ClimbInfo thus = new ClimbInfo(arg0, arg1);
		for(int i = 0; i < getClimbList().size(); i++) {
			if(getClimbList().get(i).compareTo(thus) <= 0 || i == getClimbList().size() -1) {
				getClimbList().add(i-1, thus);
				break;
			}
		}
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

}
