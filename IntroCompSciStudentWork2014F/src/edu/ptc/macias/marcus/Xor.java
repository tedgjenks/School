package edu.ptc.macias.marcus;

import edu.jenks.dist.ptc.Xorable;

public class Xor implements Xorable {
	public static void main(String[] args) {
		Xor run = new Xor();
		System.out.println(run.xorBinaryString("1"));
	}

	public boolean xorBinaryString(String arg0) {
		boolean answer = false;
		if(arg0.length() == 1) {
			if(arg0 == "1") {
				return true;
			}
		}
		for (int i = 0; i < arg0.length() - 1; i++) {
			boolean firstB;
			if (i == 0) {
				String first = arg0.substring(i, i + 1);
				firstB = first.equals("1");
			} else {
				firstB = answer;
			}

			String second = arg0.substring(i + 1, i + 2);

			boolean secondB = second.equals("1");
			answer = test(firstB, secondB);

		}
		return answer;
	}

	public static boolean test(boolean first, boolean second) {
		if (first != second) {
			return true;
		}
		return false;
	}
}
