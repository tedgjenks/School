package edu.cb.climbingclub.rhodes.maddux;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.climbingclub.AbstractClimbInfo;
import edu.jenks.dist.cb.climbingclub.AbstractClimbingClub;

public class ClimbingClubAlphabetical extends AbstractClimbingClub{

	public static void main(String[] args) {
		ClimbingClubAlphabetical test = new ClimbingClubAlphabetical();
		List<AbstractClimbInfo> list = new ArrayList<AbstractClimbInfo>();
		list.add(new ClimbInfo("a", 200));
		list.add(new ClimbInfo("b", 250));
		list.add(new ClimbInfo("c", 100));
		list.add(new ClimbInfo("e", 400));
		list.add(new ClimbInfo("e", 300));
		test.setClimbList(list);
		test.printArr(test.getClimbList());
		test.addClimb("a", 500);
		test.printArr(test.getClimbList());
		System.out.println("Num distinct peaks: " + test.distinctPeakNames());
	}

	public void addClimb(String peakName, int climbTime) {
		int len = getClimbList().size();
		for(int i = 0; i < getClimbList().size(); i++) {
			if(peakName.compareTo(getClimbList().get(i).getPeakName()) > 0) {
				continue;
			}else if(peakName.compareTo(getClimbList().get(i).getPeakName()) == 0) {
				getClimbList().add(i+1, new ClimbInfo(peakName, climbTime));
			}else if(peakName.compareTo(getClimbList().get(i).getPeakName()) < 0) {
				getClimbList().add(i, new ClimbInfo(peakName, climbTime));
			}
			break;
		}
		if(getClimbList().size() == len) {
			getClimbList().add(len, new ClimbInfo(peakName, climbTime));
		}
		if(getClimbList().size() < 1) {
			getClimbList().add(0, new ClimbInfo(peakName, climbTime));
		}
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
		if(arr.size() > 1) {
			for(int i = 1; i < arr.size()-1; i++) {
				System.out.print(arr.get(i).getPeakName() + "=" + arr.get(i).getClimbTime() + ", ");
			}
			System.out.print(arr.get(arr.size()-1).getPeakName() + "=" + arr.get(arr.size()-1).getClimbTime() + "]");
			System.out.println();
		}
	}
}
