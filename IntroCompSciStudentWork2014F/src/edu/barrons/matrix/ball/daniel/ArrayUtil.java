package edu.barrons.matrix.ball.daniel;

import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil
{
	public ArrayUtil(){}
	
	public void reverseArray(int[] arr)
	{
		int[] tempArray = new int[arr.length];

		int temp = 0;
		for (int i = 0; i < arr.length; i++)
		{
			temp = arr[i];
			tempArray[tempArray.length-1-i] = temp;
		}
		for (int z = 0; z < arr.length; z++)
		{
			arr[z] = tempArray[z];
		}
	}
}