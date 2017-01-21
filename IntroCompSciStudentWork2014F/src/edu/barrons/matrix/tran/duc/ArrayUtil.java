package edu.barrons.matrix.tran.duc;

import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil{
	
	public ArrayUtil() {}
	
	public void reverseArray(int[] arr) 
	{
		int ori = arr.length - 1; //Gets the length of the inputed array
		int [] rev = new int[ori + 1]; //Creates the reversed array
		for(int index = 0; ori >= 0; index++) //Turns (rev) into a reversed order array of (arr)
		{
			rev[index] = arr[ori];
			ori--;
		}
		for(int count = 0; count < arr.length; count++) //Finally makes (arr) reversed
			arr[count] = rev[count];
	}
}