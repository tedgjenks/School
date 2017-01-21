package edu.barrons.matrix.tran.duc;

import edu.jenks.dist.barrons.matrix.*;

public class Matrix extends AbstractMatrix
{
	ArrayUtil helper = new ArrayUtil(); //Creates an ArrayUtil object to help with reverseAllRows
	
	public Matrix(int[][] m)
	{
		super (m);
	}
	
	public void reverseAllRows()
	{
		for (int rows = mat.length - 1;rows >= 0; rows--)//Gets the number of rows in (mat) and sets up a for loop until (rows) => 0
			helper.reverseArray(mat[rows]);				 //(helper) reverses all rows in the (mat) 2-D array
	}
	
	public void reverseMatrix()
	{
		this.reverseAllRows(); //Reverses all rows
		int [][] copy = new int [mat.length][]; //Creates a temp array
		int rows = mat.length; //Gets the number of rows of (mat)
		for (int count = 0; count < mat.length; count++) //Copies (mat) into (copy)
		{
			copy[count] = mat[count];
		}
		for (int count = 0; count < mat.length; count++) //replaces the first row of (mat) with the last row of (copy) and then goes towards the center
		{
			rows--;
			mat[count] = copy[rows];
		}
	}
}