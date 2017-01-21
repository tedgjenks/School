package edu.barrons.matrix.jha.anish;

import edu.jenks.dist.barrons.matrix.*;
 

public class ArrayUtil extends AbstractArrayUtil
{
	
	public ArrayUtil(){}
	
	public void reverseArray(int[] arr)
	{
		int s = arr.length;
		
		int[] a2 = new int[s];
		
		for(int i = 0; i < s; i++)
		{
			
			a2[i] = arr[s - (i+1)];
								
		}
		
		for(int ii = 0; ii < s; ii++)
		{
			
			arr[ii] = a2[ii];
			
		}
		
		
		
		
		
	}
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
