package edu.barrons.matrix.higginbotham.andrew;

import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil
{
	public ArrayUtil() {}
	
	public void reverseArray(int[] arr)
	{
		int[] temp = new int [arr.length];
		int tempPlace = 0;
		int arrPlace = arr.length-1;
		int count = 0;
		if(arr.length - 1 != 0){
		while(count <= arr.length-1)
		{
			temp[count] = arr[count];
			count++;
		}
			
		while(arrPlace >= 0)
		{
			arr[arrPlace] = temp[tempPlace];
			tempPlace++;
			arrPlace--;
		}
		}
		
	}
}