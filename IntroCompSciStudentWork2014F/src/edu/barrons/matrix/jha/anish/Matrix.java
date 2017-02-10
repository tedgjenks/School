package edu.barrons.matrix.jha.anish;

import edu.jenks.dist.barrons.matrix.*;



public class Matrix extends AbstractMatrix
{
	
	
	public Matrix(int[][] m)
	{
		super(m);
	}
	
	public void reverseAllRows()
	{
		
		int s = mat.length;
		
		ArrayUtil a = new ArrayUtil();
			
		for(int i = 0; i < s; i++)
		{
		
			a.reverseArray(mat[i]);
			
			
		}
		
		
	}

	public void reverseMatrix()
	{
		reverseAllRows();
		
		int s = mat.length;
		
		int ss = mat[0].length;
		
		int[][] rC = new int [s][ss - 1];
		
		for(int i = 0; i < s; i++)
		{
			rC[rC.length - i - 1] = mat[i];	
			
		}
		
		for(int i = 0; i < s; i++)
		{
			mat[i] = rC[i];
						
		}
			
			
			
			
			
			
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
