package edu.barrons.matrix.ramsey.will;

import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix {
	public Matrix(int[][] m) {
		super(m);
	}
	public void reverseAllRows() {
		ArrayUtil au = new ArrayUtil();
		for(int index = 0; index < mat.length; index++)
			au.reverseArray(mat[index]);
	}
	
	public void reverseMatrix() {
		this.reverseAllRows();
		int[][] reverse = new int[mat.length][mat[0].length];
		for(int index = 0; index < mat.length; index++)
			reverse[reverse.length-index-1] = mat[index];
		for(int index = 0; index < mat.length; index++)
			mat[index] = reverse[index];
	}
	
	
}