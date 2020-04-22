package edu.cb.whitt.rose;


import edu.jenks.dist.cb.TestableArray;

public class ArrayTester extends Object implements TestableArray {
	public static void main(String[] args) {
		ArrayTester t = new ArrayTester();
		int[][] arr = {{1, 2}, {2, 1}};
		System.out.println(arr.length);
		System.out.println(t.isLatin(arr));
//		int[] ar = {3, 2, 1, 5, 6, 37, 40, 2};
//		t.printArr(ar);
//		t.sort(ar);
//		t.printArr(ar);
	}
	public ArrayTester() {
		
	}
	
	public int[] getColumn(int[][] arr2D, int c) {
		int[] arr = new int[arr2D.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr2D[i][c];
		}
		return arr;
	}
	
	public boolean hasAllValues(int[] arr1, int[] arr2) {
		for (int one = 0; one < arr1.length; one++) {
			int check = 0;
			for (int two = 0; two < arr2.length; two++) {
				
				if (arr1[one] == arr2[two]) {
					check++;
				}
			}
			if (check == 0) {
				return false;
			}
		}
		return true;
	}
	
	public boolean containsDuplicates(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int check = 0;
			for (int k = 0; k < arr.length; k++) {
				if (arr[i] == arr[k]) {
					check++;
				}
			}
			if (check > 1) {
				return true;
			}
		}
		return false;
	}
	
	public int[] sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[i]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return arr;
	}
	
	public boolean isLatin(int[][] square) {
		//contains duplicates on first row
		if (containsDuplicates(square[0]) == true) {
			return false;
		}
		//loop of every row
			for (int i = 0; i < square.length; i++) {
				//has all values
				if (hasAllValues(square[0], square[i]) == false) {
					return false;
				}
			}
			
		//contains duplicates on first column
		if (containsDuplicates(getColumn(square, 0)) == true) {
			return false;
		}
		//loop of every row (get column)
			for (int i = 0; i < square[0].length; i++) {
				//has all values
				if (hasAllValues(getColumn(square, 0), getColumn(square, i)) == false) {
					return false;
				}
			}
			
		return true;
	}
	
	public boolean areEqual(int[] arr1, int[] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			if ((arr1[i] == arr2[i]) == false) {
				return false;
			}
		}
		return true;
	}
	
	public void printArr(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	public void printTwoArr(int[][] arr) {
		for (int row = 0; row < arr.length; row++) {
			for (int col = 0; col < arr[row].length; col++) {
				System.out.print(arr[row][col] + " ");
			}
			System.out.println("");
		}
	}
}
