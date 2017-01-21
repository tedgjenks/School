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
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
