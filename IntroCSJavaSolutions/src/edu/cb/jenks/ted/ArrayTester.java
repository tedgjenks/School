/**
 * 
 */
package edu.cb.jenks.ted;

import edu.jenks.dist.cb.TestableArray;

/**
 * @author Jenks
 *
 */
public class ArrayTester implements TestableArray {

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.TestableArray#getColumn(int[][], int)
	 */
	@Override
	public int[] getColumn(int[][] arr2D, int c) {
		int[] col = new int[arr2D.length];
		for(int rowIndex = arr2D.length - 1; rowIndex >= 0; rowIndex--) {
			col[rowIndex] = arr2D[rowIndex][c];
		}
		return col;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.TestableArray#hasAllValues(int[], int[])
	 */
	@Override
	public boolean hasAllValues(int[] arr1, int[] arr2) {
		arr1Label:
		for(int i1 = arr1.length - 1; i1 >= 0; i1--) {
			for(int i2 = arr2.length - 1; i2 >= 0; i2--) {
				if(arr1[i1] == arr2[i2])
					continue arr1Label;
			}
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.TestableArray#containsDuplicates(int[])
	 */
	@Override
	public boolean containsDuplicates(int[] arr) {
		boolean dups = false;
		for(int first = arr.length - 1; first > 0; first--) {
			for(int test = first - 1; test >= 0; test--) {
				if(arr[first] == arr[test])
					return true;
			}
		}
		return dups;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.TestableArray#isLatin(int[][])
	 */
	@Override
	public boolean isLatin(int[][] square) {
		if(containsDuplicates(square[0])) // no duplicates in first row
			return false;
		for(int rowIndex = square.length - 1; rowIndex > 0; rowIndex--) { // every row contains the same values of the first row
			if(!hasAllValues(square[0], square[rowIndex]))
				return false;
		}
		for(int colIndex = square.length - 1; colIndex >= 0; colIndex--) {
			if(!hasAllValues(square[0], getColumn(square, colIndex)))
				return false;
		}
		return true;
	}

}
