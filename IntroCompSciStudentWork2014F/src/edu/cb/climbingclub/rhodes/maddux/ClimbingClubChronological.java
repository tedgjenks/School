package edu.cb.climbingclub.rhodes.maddux;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubChronological extends AbstractClimbingClub{

	public static void main(String[] args) {
		ClimbingClubChronological test = new ClimbingClubChronological();
		List<AbstractClimbInfo> list = new ArrayList<AbstractClimbInfo>();
		list.add(new ClimbInfo("1", 200));
		list.add(new ClimbInfo("2", 250));
		list.add(new ClimbInfo("2", 100));
		list.add(new ClimbInfo("4", 400));
		list.add(new ClimbInfo("5", 300));
		test.setClimbList(list);
		test.printArr(test.getClimbList());
		test.addClimb("6", 500);
		test.printArr(test.getClimbList());
		System.out.println(test.distinctPeakNames());
	}
	
	public void addClimb(String peakName, int climbTime) {
		getClimbList().add(new ClimbInfo(peakName, climbTime));
	}

	public int distinctPeakNames() {
		List<String> names = new ArrayList<String>();
		for(AbstractClimbInfo a : getClimbList()) {
			if(!names.contains(a.getPeakName())) {
				names.add(a.getPeakName());
			}
		}
		return names.size();
	}

	public void printArr(List<AbstractClimbInfo> arr) {
		System.out.print("[" + arr.get(0).getPeakName() + "=" + arr.get(0).getClimbTime() + ", ");
		for(int i = 1; i < arr.size()-1; i++) {
			System.out.print(arr.get(i).getPeakName() + "=" + arr.get(i).getClimbTime() + ", ");
		}
		System.out.print(arr.get(arr.size()-1).getPeakName() + "=" + arr.get(arr.size()-1).getClimbTime() + "]");
		System.out.println();
	}
}
