package edu.cb.rhodes.maddux;

import edu.jenks.dist.cb.*;

public class ArrayTester implements TestableArray{

	public ArrayTester() {
		
	}
	
	public static void main(String[] args) {
		ArrayTester test = new ArrayTester();
		int[][] arrD2 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 2, 3}};
		test.toArray(test.getColumn(arrD2,  0));
		System.out.println(test.containsDuplicates(new int[] {1, 2, 3, 4, 5, 6, 7}));
		System.out.println(test.hasAllValues(new int[] {1, 2, 3}, new int[] {1, 2, 3, 4}));
		System.out.println(test.isLatin(new int[][] {{1, 2, 3}, {3, 1, 2}, {2, 3, 1}}));
		
	}
	
	public void toArray(int[] print) {
		System.out.print("[");
		for(int i = 0; i < print.length-1; i++) {
			System.out.print(print[i] + ", ");
		}
		System.out.print(print[print.length-1] + "]");
		System.out.println();
	}
	
	public boolean containsDuplicates(int[] arr) {
		for(int i = 0; i < arr.length-1; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i] == arr[j]) {
					return true;
				}
			}
		}
		return false;
	}

	public int[] getColumn(int[][] arr2D, int c) {
		int[] toBReturned = new int[arr2D.length];
		for(int i = 0; i < arr2D.length; i++) {
			toBReturned[i] = arr2D[i][c];
		}
		return toBReturned;
	}

	public boolean hasAllValues(int[] arr1, int[] arr2) {
		if(arr2.length < arr1.length) {
			return false;
		}
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr2.length; j++) {
				if(arr1[i] == arr2[j]) {
					break;
				}
				if(j == arr2.length-1) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isLatin(int[][] square) {
		if(containsDuplicates(square[1])) {
			return false;
		}
		for(int i = 0; i < square[1].length-1; i++) {
			if(!hasAllValues(square[i], square[i+1])) {
				return false;
			}
		}
		int[] firstRow = square[1];
		for(int i = 0; i < square[1].length; i++) {
			int[] column2BTested = getColumn(square, i);
			if(!hasAllValues(firstRow, column2BTested)) {
				return false;
			}
		}
		return true;
	}

}
