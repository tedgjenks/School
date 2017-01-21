package edu.barrons.matrix.bagwell.brady;

import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix {
	
	ArrayUtil arrayUtil = new ArrayUtil();
	
	public Matrix(int[][] m) {
		
		super(m);
	}
	
	public void reverseAllRows() {
		
		for(int i = 0; i < mat.length; i++)
			arrayUtil.reverseArray(mat[i]);
	}
	
	public void reverseMatrix() {
		
		reverseAllRows();
		
		for (int i = 0; i < mat.length / 2; i++) {
			int[] tempArray = mat[mat.length - i - 1];
			mat[mat.length - i - 1] = mat[i];
			mat[i] = tempArray;
		}
	}
	
}

