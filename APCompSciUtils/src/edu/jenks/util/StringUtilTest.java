package edu.jenks.util;

import static org.junit.Assert.*;
import static java.lang.System.out;

import org.junit.Test;

public class StringUtilTest {
	
	public static void main(String[] args) {
		out.println("Begin");
		for(int index = 100; index > 0; index--) {
			char c = StringUtil.getRandomAsciiCharacterExcludeRange(32, 127, StringUtil.FIRST_ASCII_DIGIT, StringUtil.FIRST_ASCII_DIGIT + 10);
			if(Character.isDigit(c)) 
				out.println("ERROR:  " + c);
		}
		out.println("End");
	}

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
