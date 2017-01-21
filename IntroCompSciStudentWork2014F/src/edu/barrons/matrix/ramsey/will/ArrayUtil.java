package edu.barrons.matrix.ramsey.will;

import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil {
	public ArrayUtil() {}
	
	public void reverseArray(int[] arr) {
		int [] reverse = new int [arr.length];
		for(int index = 0; index < arr.length; index++)
			reverse[reverse.length-index-1] = arr[index];
		for(int index = 0; index < arr.length; index++)
			arr[index] = reverse[index];
	}
}