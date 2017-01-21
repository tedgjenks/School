package edu.barrons.matrix.ball.daniel;

import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix
{
	ArrayUtil aux = new ArrayUtil();
	public Matrix(int[][] m)
	{
		super(m);
	}
	
	public void reverseAllRows()
	{
		for (int i = 0; i < mat.length; i++)
		{
			//System.out.println(mat[i]);
			aux.reverseArray(mat[i]);
		}
	}
	public void reverseMatrix()
	{
		reverseAllRows();
		int[][] tempMatrix = new int[mat.length][mat[0].length];
		int[] tempArray;
		for (int a = 0; a < mat.length; a++)
		{
			
			tempArray = mat[a];
			//System.out.println(mat[a]);
			//System.out.println("");
			tempMatrix[mat.length-1-a] = tempArray;
			//System.out.println(mat[mat.length-1-a] + " " + mat[mat.length-1-a][0]);
			System.out.println(tempMatrix[tempMatrix.length-1-a] + " " + tempMatrix[tempMatrix.length-1-a][0]);
			//tempMatrix[tempMatrix.length-1-a] = tempArray;
			//tempArray = mat[a];
			//tempMatrix[tempMatrix.length-a-1] = tempArray;
			//System.out.println(tempMatrix[a][0] + " ");
			//System.out.println(tempArray);
		}
		for (int a = 0; a < mat.length; a++)
		{
			mat[a] = tempMatrix[a];
		}
	}
}