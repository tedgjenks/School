package edu.cb.climbingclub.whitt.rose;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub {

	public static void main(String[] args) {
		ClimbingClubAlphabetical a = new ClimbingClubAlphabetical();
		
		//rose is 17 greater than anna
		System.out.println(("rose").compareTo("anna"));
		
		a.addClimb("Monadnock", 274);
		a.addClimb("Whiteface", 301);
		
		
//		for (int i = 0; i < a.getClimbList().size(); i++) {
//			System.out.println(a.getClimbList().get(i).getPeakName() + ", " + a.getClimbList().get(i).getClimbTime());
//		}
		
		a.addClimb("Algonquin", 225);
		a.addClimb("Monadnock", 344);
		
		for (int i = 0; i < a.getClimbList().size(); i++) {
			System.out.println(a.getClimbList().get(i).getPeakName() + ", " + a.getClimbList().get(i).getClimbTime());
		}
		
		System.out.println(a.distinctPeakNames());
	}
	
	public ClimbingClubAlphabetical() {
		
	}
	
	@Override
	public void addClimb(String arg0, int arg1) {
		// TODO Auto-generated method stub
		//add
		getClimbList().add(new ClimbInfo(arg0, arg1));
		
		for ( int j = 0; j < getClimbList().size() - 1; j++ )
	    {
		      int min = j;
		      for ( int k=j+1; k < getClimbList().size(); k++ )
		        if (getClimbList().get(k).getPeakName().compareTo(getClimbList().get(min).getPeakName()) < 0 ) {
		        	min = k;  
		        }
	
		      // Swap the reference at j with the reference at min 
		      AbstractClimbInfo temp = getClimbList().get(j);
		      getClimbList().set(j, getClimbList().get(min));
		      getClimbList().set(min, temp);
	    } 
	}

	@Override
	public int distinctPeakNames() {
		List<String> a = new ArrayList<String>();
		for (int i = 0; i < getClimbList().size(); i++) {
			a.add(getClimbList().get(i).getPeakName());
		}
		System.out.println(a);
		int count = 0;
		for (int i = 0; i < a.size(); i++) {
			if (Collections.frequency(a, a.get(i)) == 1) {
				count++;
			}
		}
		return count;
	}

}
