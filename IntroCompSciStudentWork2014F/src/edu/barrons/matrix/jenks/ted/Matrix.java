/**
 * 
 */
package edu.barrons.matrix.jenks.ted;

import edu.jenks.dist.barrons.matrix.AbstractMatrix;

/**
 * @author jenkst
 *
 */
public class Matrix extends AbstractMatrix {
	private ArrayUtil arrayUtil = new ArrayUtil();

	/**
	 * @param m
	 */
	public Matrix(int[][] m) {
		super(m);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.matrix.AbstractMatrix#reverseAllRows()
	 */
	@Override
	public void reverseAllRows() {
		for(int rowIndex = 0; rowIndex < mat.length; rowIndex++)
			arrayUtil.reverseArray(mat[rowIndex]);
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.barrons.matrix.AbstractMatrix#reverseMatrix()
	 */
	@Override
	public void reverseMatrix() {
		reverseAllRows();
		for(int bIndex = 0, eIndex = mat.length - 1; bIndex < eIndex; bIndex++, eIndex--) {
			int[] temp = mat[bIndex];
			mat[bIndex] = mat[eIndex];
			mat[eIndex] = temp;
		}
	}

}
