/**
 * 
 */
package edu.jenks.dist.cb;

/**
 * @author Jenks
 *
 */
public interface TestableArray {

	/**
	 * @param arr2D must not be changed
	 * @param c valid column index in <code>arr2D</code>
	 * @return an array containing the elements of column <code>c</code> of <code>arr2D</code> in the same order as they appear in <code>arr2D</code>.
	 */
	int[] getColumn(int[][] arr2D, int c);
	
	/**
	 * @param arr1 same length as <code>arr2</code> and does not change.
	 * @param arr2 same length as <code>arr1</code> and does not change.
	 * @return <code>true</code> if and only if every value in <code>arr1</code> appears in <code>arr2</code>.
	 */
	boolean hasAllValues(int[] arr1, int[] arr2);
	
	/**
	 * @param arr should not change
	 * @return <code>true</code> if <code>arr</code> contains any duplicate values; <code>false</code> otherwise.
	 */
	boolean containsDuplicates(int[] arr);
	
	/**
	 * @param square has an equal number of rows and columns; has at least one row.
	 * @return <code>true</code> if <code>square</code> is a Latin square; <code>false</code> otherwise.
	 */
	boolean isLatin(int[][] square);
}
