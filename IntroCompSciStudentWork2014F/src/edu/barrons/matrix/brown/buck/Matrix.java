package edu.barrons.matrix.brown.buck;
import edu.jenks.dist.barrons.matrix.*;


public class Matrix extends AbstractMatrix
{
	int[][] copy;
	ArrayUtil au = new ArrayUtil();
	public Matrix(int[][] m)
	{
		super(m);
		copy = m;
	}
	
	public void reverseAllRows()
	{
		int length = copy.length;
		int count = 0;
		while(count < length)
		{
			au.reverseArray(copy[count]);
			count++;
		}
	}
	
	public void reverseMatrix()
	{
		int length = copy.length;
		int length2 = copy[0].length;
		int count = 0;
		int count2 = 0;
		this.reverseAllRows();
		int[][] temp = new int[length][length2];
		while(count < length)
		{
			count2 = 0;
			while(count2 < length2)
			{
				temp[count][count2] = copy[count][count2];
				count2++;
			}
			count++;
		}
		count = 0;
		int end = copy.length - 1;
		while(count < length)
		{
			copy[count] = temp[end];
			count++;
			end--;
		}
	}
}
