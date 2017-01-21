package edu.barrons.matrix.brown.buck;
import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil
{
	public ArrayUtil()
	{
		
	}
	
	public void reverseArray(int[] arr)
	{
		int length = arr.length;
		int[] temp = new int[length];
		int count = 0;
		while(count < length)
		{
			temp[count] = arr[count];
			count++;
		}
		count = arr.length - 1;
		int count2 = 0;
		while(count2 < arr.length)
		{
			arr[count2] = temp[count];
			count2++;
			count--;
		}
	}
}
 