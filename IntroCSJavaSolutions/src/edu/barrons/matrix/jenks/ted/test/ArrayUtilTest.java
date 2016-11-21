package edu.barrons.matrix.jenks.ted.test;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.barrons.matrix.jenks.ted.ArrayUtil;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		ArrayUtil au = new ArrayUtil();
		int[] arr = {1};
		int[] exp = {1};
		au.reverseArray(arr);
		assertArrayEquals(exp, arr);

		int[] arr2 = {1, 2};
		int[] exp2 = {2, 1};
		au.reverseArray(arr2);
		assertArrayEquals(exp2, arr2);
		
		int[] arr3 = {1, 2, 3};
		int[] exp3 = {3, 2, 1};
		au.reverseArray(arr3);
		assertArrayEquals(exp3, arr3);
		
		int[] arr4 = {1, 2, 3, 4, 5, 6, 7, 8};
		int[] exp4 = {8, 7, 6, 5, 4, 3, 2, 1};
		au.reverseArray(arr4);
		assertArrayEquals(exp4, arr4);
		
		int[] arr5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] exp5 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
		au.reverseArray(arr5);
		assertArrayEquals(exp5, arr5);
	}
}
