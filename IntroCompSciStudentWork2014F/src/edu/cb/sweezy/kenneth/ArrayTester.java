package edu.cb.sweezy.kenneth;

import edu.jenks.dist.cb.TestableArray;

public class ArrayTester implements TestableArray {
	private int[][] array;

	public ArrayTester() {
		array = new int[0][0];
	}

	public ArrayTester(int[][] arr) {
		array = arr;
	}

	public boolean containsDuplicates(int[] arr) {
		for (int i : arr) {
			if (howMany(arr, i) > 1) {
				return true;
			}
		}
		return false;
	}

	public int[] getColumn(int[][] arr2D, int c) {
		int[] temp = new int[arr2D.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = arr2D[i][c];
			// System.out.print(arr2D[i][c] + ",\n");
		}
		return temp;
	}

	public boolean hasAllValues(int[] arr1, int[] arr2) {
		int count = 0;
		for (int i : arr1) {
			if (contains(arr2, i)) {
				count++;
			}
		}
		if (count == arr1.length) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isLatin(int[][] a) {
		if (containsDuplicates(a[0])) {
			return false;
		} else {
			for (int[] element : a) {
				if (hasAllValues(a[0], element)) {
					for (int j = 0; j < a[0].length; j++) {
						if (hasAllValues(a[0], getColumn(a, j))) {
							return true;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		ArrayTester testing = new ArrayTester();
		// getColumn() testing
		/*
		 * int count = 0; int[][] testArr = new int[3][3]; for (int i = 0; i <
		 * testArr.length; i++) { for (int j = 0; j < testArr[i].length; j++) {
		 * testArr[i][j] = count; count++; } } ArrayTester testing = new
		 * ArrayTester(testArr); testing.print2DArr(); int[] columnTest =
		 * testing.getColumn(testArr, 0); for (int i : columnTest) {
		 * System.out.print(i); }
		 */
		// hasAllValues() testing
		/*
		 * testing int[] arr1 = new int[] { 1, 2, 3 }; int[] arr2 = new int[] { 1, 4, 3
		 * }; System.out.println(testing.hasAllValues(arr1, arr2));
		 */
		// containsDuplicates() testing
		/*
		 * int[] testingArr = new int[] { 1, 1, 2 };
		 * System.out.println(testing.containsDuplicates(testingArr));
		 */
		int[][] testingArr = new int[][] { { 1, 2 }, { 1, 2 } };
		System.out.println(testing.isLatin(testingArr));
	}

	private void print2DArr() {
		System.out.print("[");
		for (int[] iO : array) {
			System.out.print("[");
			for (int iT : iO) {
				System.out.print(iT + ", ");
			}
			System.out.print("], ");
		}
		System.out.print("]");
	}

	private boolean contains(int[] arr, int num2Check) {
		for (int i : arr) {
			if (i == num2Check) {
				return true;
			}
		}
		return false;
	}

	private int howMany(int[] a, int toFind) {
		int count = 0;
		for (int i : a) {
			if (i == toFind) {
				++count;
			}
		}
		return count;
	}

}
