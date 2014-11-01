package edu.jenks.unit1;

import edu.jenks.util.StringUtil;

public class PrintInitials {
	
	private static void printRowNoGap(int numSpace0, int numT, int numSpace1, int numG, int numSpace2, int numJ) {
		System.out.print(StringUtil.buildString(' ', numSpace0));
		System.out.print(StringUtil.buildString('T', numT));
		System.out.print(StringUtil.buildString(' ', numSpace1));
		System.out.print(StringUtil.buildString('G', numG));
		System.out.print(StringUtil.buildString(' ', numSpace2));
		System.out.println(StringUtil.buildString('J', numJ));
	}
	
	private static void printRowWithGap(int numSpace0, int numT, int numSpace1, int numG1, int numSpaceG, int numG2, int numSpace2, int numJ) {
		System.out.print(StringUtil.buildString(' ', numSpace0));
		System.out.print(StringUtil.buildString('T', numT));
		System.out.print(StringUtil.buildString(' ', numSpace1));
		System.out.print(StringUtil.buildString('G', numG1));
		System.out.print(StringUtil.buildString(' ', numSpaceG));
		System.out.print(StringUtil.buildString('G', numG2));
		System.out.print(StringUtil.buildString(' ', numSpace2));
		System.out.println(StringUtil.buildString('J', numJ));
	}

	public static void main(String[] args) {
		printRowNoGap(0, 9, 3, 9, 3, 9);
		printRowNoGap(4, 1, 7, 1, 15, 1);
		printRowNoGap(4, 1, 7, 1, 15, 1);
		printRowWithGap(4, 1, 7, 1, 6, 2, 7, 1);
		printRowWithGap(4, 1, 7, 1, 7, 1, 7, 1);
		printRowNoGap(4, 1, 7, 9, 3, 5);
	}

}
