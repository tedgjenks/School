package edu.cb.climbingclub.jenks.ted.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.cb.climbingclub.jenks.ted.ClimbInfo;
import edu.cb.climbingclub.jenks.ted.ClimbingClubChronological;

public class ClimbingClubChronologicalTest {
	
	private List<ClimbInfo> climbList = new ArrayList<ClimbInfo>();
	private ClimbingClubChronological cc = new ClimbingClubChronological();

	@Before
	public void setUp() throws Exception {
		climbList.add(new ClimbInfo("Alpha", 1));
		climbList.add(new ClimbInfo("Beta", 2));
		climbList.add(new ClimbInfo("Alpha", 3));
		climbList.add(new ClimbInfo("Gamma", 4));
		for(ClimbInfo climbInfo : climbList)
			cc.addClimb(climbInfo.getPeakName(), climbInfo.getClimbTime());
	}

	@Test
	public void testAddClimb() {
		assertEquals(climbList.size(), cc.getClimbList().size());
		for(int index = climbList.size() - 1; index >= 0; index--) {
			assertEquals(climbList.get(index).getPeakName(), cc.getClimbList().get(index).getPeakName());
			assertEquals(climbList.get(index).getClimbTime(), cc.getClimbList().get(index).getClimbTime());
		}
	}

	@Test
	public void testDistinctPeakNames() {
		assertEquals(3, cc.distinctPeakNames());
	}

}
