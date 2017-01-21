package edu.barrons.matrix.higginbotham.andrew;

import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix
{
	int[][] temp;
	public Matrix(int[][] m)
	{	
		super(m);
		temp = m; //m.length returns the amount of collumns 
				  //m[x].length returns the length of the row
	}
	public void reverseAllRows()
	{
		ArrayUtil au = new ArrayUtil();
		int rowCount = 0;
		while(rowCount < temp.length){
			au.reverseArray(temp[rowCount]);
			rowCount++;
		}
		
	}
	public void reverseMatrix()
	{
		reverseAllRows();
		int rowCount = 0;
	while(rowCount < (temp.length / 2))
		{
			int[] tempRow = temp[rowCount];
			int[] storedRow = temp[temp.length - 1];
			temp[temp.length - 1] = tempRow;
			temp[rowCount] = storedRow;
			rowCount++;
		}
	}
}