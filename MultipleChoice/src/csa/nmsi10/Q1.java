package csa.nmsi10;

import java.util.Arrays;

public class Q1 {

	private static  int[][] mat = {{4,1,4,3,2}, {1,8,7,5,3}, {7,4,6,9,2}, {3,8,1,2,4}, {5,6,7,0,3}};
	public static void main(String[] args) {
		mystery();
		System.out.println(Arrays.deepToString(mat));
	}
	
	public static void mystery() {
		for(int row = 1; row < mat.length; row++) {
			for(int col = 0; col < mat[0].length; col++) {
				if(row != col)
					mat[row][col] = mat[row - 1][col];
			}
		}
	}

}
