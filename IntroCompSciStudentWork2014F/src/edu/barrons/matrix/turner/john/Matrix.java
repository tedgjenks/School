package edu.barrons.matrix.turner.john;
import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix{
	ArrayUtil util = new ArrayUtil();
	
	public Matrix(int[][] m){
		super(m);
	}
	
	public void reverseAllRows(){
		for(int i = 0; i < mat.length; i++){
			util.reverseArray(mat[i]);
		}
	}
	
	public void reverseMatrix(){
		
		reverseAllRows();
		for(int j = 0; j < mat.length/2; j++){
			int[] temp;
			temp = mat[j];
			int back = mat.length - 1 - j;
			mat[j] = mat[back];
			mat[back] = temp;
		}
	}
}