package edu.cb.tran.don;

import edu.jenks.dist.cb.*;

public class ArrayTester implements TestableArray {

	public ArrayTester() {

	}

	public static void main(String[] args) {
		ArrayTester a = new ArrayTester();
		int[] arrr = new int[] {1, 2, 3, 5,};
		int[] ab = new int[] {1,5,3,2,};
		int[][] ar = new int[][] { {1,2,3,}, {4, 5, 6,}, {7,  8, 9, }, };
		//int[][] ar = new int[][] { {1,2,3,}, {2, 3, 1,}, {3,  1, 2, }, };
		//System.out.println(a.containsDuplicates(arrr));
		//arrr = a.getColumn(ar, 3);
		for(int i = 0; i < arrr.length; i++) {
			//System.out.print(arrr[i] + ", ");
		}
		System.out.println(a.isLatin(ar));
		//System.out.println(a.hasAllValues(arrr, ab));
		
	}
	
	
	public boolean containsDuplicates(int[] arr) {
		int first = arr[0];
		for(int i = 0; i < arr.length - 1; i++) {
			first = arr[i];
			for(int j = i + 1; j < arr.length; j++) {
				if(first == arr[j]) {
					return true;
				}
			}
		}
		return false;
	}

	public int[] getColumn(int[][] arr1, int c) {
		int[] arr = new int[arr1.length];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = arr1[i][c];
		}
		return arr;
	}

	public boolean hasAllValues(int[] arr1, int[] arr2) {
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr2.length; j++) {
				if(arr1[i] == arr2[j]) {
					break;
				} else if(j == arr2.length - 1) {
					return false;
				}
			}
		}
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr1.length; j++) {
				if(arr2[i] == arr1[j]) {
					break;
				} else if(j == arr1.length - 1) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isLatin(int[][] arr) {
		for(int i = 0; i < arr[0].length; i++) {
			if(containsDuplicates(getColumn(arr, i))) {
				return false;
			}
		}
		for(int i = 0; i < arr.length; i++) {
			if(containsDuplicates(arr[i])) {
				return false;
			}
		}
		for(int i = 0; i < arr[0].length - 1; i++) {
			if(!hasAllValues(getColumn(arr, i), getColumn(arr, i + 1))) {
				return false;
			}
		}
		for(int i = 0; i < arr.length - 1; i++) {
			if(!hasAllValues(arr[i], arr[i + 1])) {
				return false;
			}
		}
		return true;
	}

}
