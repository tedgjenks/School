package edu.jenks.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void test() {
		testBuildString();
	}
	
	private void testBuildString() {
		
		assertSame("testBuildString failed", "JJJJ", StringUtil.buildString('J', 4));
	}

}
