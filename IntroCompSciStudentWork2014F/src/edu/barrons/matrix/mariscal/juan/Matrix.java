package edu.barrons.matrix.mariscal.juan;

import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix{
	public Matrix(int[][] m) { 
		super (m);
	}
	
	public void reverseAllRows(){
		
		ArrayUtil au = new ArrayUtil();
		for(int index = 0; index<mat.length; index++){
			au.reverseArray(mat[index]);
		}
			
	}
	
	public void reverseMatrix(){
		reverseAllRows();
		ArrayUtil au = new ArrayUtil();
		int len = mat.length;
		int wid = mat[0].length;
		int [][] mat2= new int [len][wid];
		for(int index = 0; index < wid; index++){
			for(int index2 = 0; index2 < len; index2++)
				mat2[index2][index] = mat[len -index2-1][index];
		}
		for(int index3 = 0; index3 < len; index3++){
			for(int index4 = 0; index4 < wid; index4++)
				mat[index3][index4] = mat2[index3][index4];
		}
	
	
	
	}







	
	
	
	
}




