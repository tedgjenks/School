package edu.cb.climbingclub.jenks.ted.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.cb.climbingclub.jenks.ted.ClimbInfo;

public class ClimbInfoTest {

	@Test
	public void test() {
		ClimbInfo ci1 = new ClimbInfo("Alpha", 1);
		ClimbInfo ci2 = new ClimbInfo("Alpha", 2);
		ClimbInfo ci3 = new ClimbInfo("Omega", 3);
		assertTrue(ci1.compareTo(ci2) == 0);
		assertTrue(ci1.compareTo(ci3) < 0);
		assertTrue(ci3.compareTo(ci1) > 0);
	}

}
