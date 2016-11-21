package edu.jenks.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void test() {
		testBuildString();
		testRandomString();
	}
	
	private void testBuildString() {
		String act = StringUtil.buildString('J', 4);
		assertEquals("testBuildString failed", "JJJJ", act);
	}
	
	private void testRandomString() {
		assertEquals("E", StringUtil.buildRandomString(1, 69, 70));
	}

}
