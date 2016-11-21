/**
 * 
 */
package edu.barrons.matrix.jenks.ted;

import edu.jenks.dist.barrons.matrix.AbstractArrayUtil;

/**
 * @author jenkst
 *
 */
public class ArrayUtil extends AbstractArrayUtil {

	/**
	 * 
	 */
	public ArrayUtil() {}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.matrix.AbstractArrayUtil#reverseArray(int[])
	 */
	@Override
	public void reverseArray(int[] arr) {
		for(int leftIndex = 0, rightIndex = arr.length - 1; leftIndex < rightIndex; leftIndex++, rightIndex--) {
			int temp = arr[leftIndex];
			arr[leftIndex] = arr[rightIndex];
			arr[rightIndex] = temp;
		}
	}
}
