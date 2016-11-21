/**
 * 
 */
package edu.jenks.dist.barrons.matrix;

/**
 * @author Ted Jenks
 *
 */
public abstract class AbstractMatrix {
	protected int[][] mat;
	
	public AbstractMatrix(int[][] m) {
		mat = m;
	}
	
	/**
	 * <p>Reverses the elements in each row of <code>mat</code>.</p>
	 * <b>Postcondition:</b> The elements in each row have been reversed.
	 */
	public abstract void reverseAllRows();
	
	/**
	 * <p>Reverses the elements of <code>mat</code>.</p>
	 * <b>Postcondition:</b><br>
	 *  - The final elements of mat, when read in row-major order,<br>
	 *    are the same as the original elements of <code>mat</code> when read<br>
	 *    from the bottom corner, right to left, going upward.<br>
	 *  - <code>mat[0][0]</code> contains what was originally the last element.<br>
	 *  - <code>mat[mat.length-1][mat[0].length-1</code> contains what was<br>
	 *    originally the first element.
	 */
	public abstract void reverseMatrix();
	
}
