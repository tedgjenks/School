package edu.barrons.matrix.smathers.patrick;
import edu.jenks.dist.barrons.matrix.*;
import java.util.*;
public class ArrayUtil extends AbstractArrayUtil{
	public ArrayUtil(){ }
	
	public void reverseArray(int[] arr){
		int[] reversed = new int[arr.length];
		int counter = 0;
		for(int i = arr.length - 1; i >= 0; i--){
			reversed[counter] = arr[i];
			//System.out.println(reversed[counter]);
			counter++;
			
		}
		for(int p = 0; p < reversed.length; p++){
			arr[p] = reversed[p];
		}
	}
}