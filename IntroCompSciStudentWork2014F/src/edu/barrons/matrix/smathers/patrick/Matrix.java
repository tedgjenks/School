package edu.barrons.matrix.smathers.patrick;
import edu.jenks.dist.barrons.matrix.*;
import java.util.*;
public class Matrix extends AbstractMatrix{
	ArrayUtil au = new ArrayUtil();
	
	public Matrix(int[][] m){
		//this.array = m;
		super(m);
	}
	public void reverseAllRows(){
		int[][] reversedRowes = new int[mat.length][mat[0].length];
		/* int counterRows = 0;
		for(int i = 0; i < mat.length; i++){
			for(int o = mat[i].length - 1; o >= 0;o--){
				reversedRowes[i][(mat[i].length - 1) - o] = mat[i][o];
			}
			counterRows++;
		} */
		
		for(int i = 0; i < mat.length; i++){
			au.reverseArray(mat[i]);
		}
	
		
	}
	public void reverseMatrix(){
		
		int temp;
		for(int i = 0; i < mat.length / 2; i++){
			for(int col = 0; col < mat[i].length; col++){
				temp = mat[i][col];
				mat[i][col] = mat[mat.length - i - 1][col];
				mat[mat.length - i - 1][col] = temp;
				
			}
		}
		reverseAllRows();
		/* for(int c = 0; c < mat.length; c++){
			reverseAllRows();
		} */
	}
	
}