package jenks2018;

import java.io.IOException;

public class Day2b {
	
	private static final String TEST_INPUT = "abcde,fghij,klmno,pqrst,fguij,axcye,wvxyz";

	public static void main(String[] args) throws IOException {
		assert "fgij".equals(findCommon(TEST_INPUT.split(",")));
		String input = Day2a.loadInput();
		System.out.println(findCommon(input.split(",")));
	}
	
	private static String findCommon(String[] input) {
		String common = null;
		findCommon:
			for(int oIndex = input.length - 1; oIndex >= 1; oIndex--) {
				for(int iIndex = oIndex - 1; iIndex >= 0; iIndex--) {
					if(offByOne(input[oIndex], input[iIndex])) {
						common = buildCommon(input[oIndex], input[iIndex]);
						break findCommon;
					}
				}
			}
		return common;
	}
	
	private static String buildCommon(String a, String b) {
		int diffIndex = -1;
		for(int index = a.length() - 1; index >= 0 && diffIndex < 0; index--) {
			if(a.charAt(index) != b.charAt(index))
				diffIndex = index;
		}
		return a.substring(0, diffIndex) + a.substring(diffIndex + 1);
	}
	
	private static boolean offByOne(String a, String b) {
		boolean match = false;
		if(a.length() == b.length()) {
			int diff = 0;
			for(int index = a.length() - 1; index >= 0 && diff <= 1; index--) {
				if(a.charAt(index) != b.charAt(index))
					diff++;
			}
			match = diff == 1;
		}
		return match;
	}

}
