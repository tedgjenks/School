package edu.barrons.matrix.mathis.justin;

import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix {
	public Matrix(int[][] m) {
		super(m);

	}
	public void reverseAllRows() {
		ArrayUtil au = new ArrayUtil();
		for (int i = 0; i<mat.length; i++){
			au.reverseArray(mat [i]);
		}
	}

	public void reverseMatrix() {
		reverseAllRows();
		int a = 0;
		int num = (mat.length/2);
		for (int i = 0; i < mat[0].length;i++){
			for(int i2 = 0; i2<num;i2++){
				a = mat[mat.length-1-i2][i];
				mat[mat.length-1-i2][i] = mat[i2][i];
				mat[i2][i] = a;
			}
		}
	}
}