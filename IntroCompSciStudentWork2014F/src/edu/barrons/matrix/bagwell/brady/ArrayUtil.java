package edu.barrons.matrix.bagwell.brady;

import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil {
	
	public ArrayUtil() {}
	
	public void reverseArray(int[] arr) {
		
		int[] tempArray = new int[arr.length];
		
		for(int i = 0; i < arr.length; i++)
			tempArray[i] = arr[arr.length - i - 1];
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = tempArray[i];
	}
}