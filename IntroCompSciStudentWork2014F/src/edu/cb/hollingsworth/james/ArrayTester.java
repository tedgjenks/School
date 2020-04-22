package edu.cb.hollingsworth.james;

import edu.jenks.dist.cb.TestableArray;

public class ArrayTester implements TestableArray {

	public boolean containsDuplicates(int[] arr) {
		for(int i = 0; i < arr.length - 1; i++) {
			for(int j = i + 1; j < arr.length; j++) {
				if(arr[i] == arr[j]) return true;
			}
		}
		return false;
	}

	public int[] getColumn(int[][] arr2d, int c) {
		int[] tmp = new int[arr2d[c].length];
		for(int i = 0; i < arr2d[c].length; i++) {
			tmp[i] = arr2d[c][i];
		}
		return tmp;
	}

	public boolean hasAllValues(int[] arr1, int[] arr2) {
		boolean currValueFound = false;
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr2.length; j++) {
				if(arr1[i] == arr2[j]) currValueFound = true;
				if(j == arr2.length - 1 && !currValueFound) return false;
			}
			currValueFound = false;
		}
		return true;
	}

	public boolean isLatin(int[][] square) {
		int rows = square[0].length;
		for(int i = 0; i < square.length; i++) {
			if(square[i].length != rows) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		ArrayTester a = new ArrayTester();
		System.out.println(a.containsDuplicates(new int[] {2, 1, 2}));
	}

}
