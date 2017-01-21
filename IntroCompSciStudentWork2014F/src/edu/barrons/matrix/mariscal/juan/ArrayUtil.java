package edu.barrons.matrix.mariscal.juan;

import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil {
	public ArrayUtil() {
		
	}
	
	public void reverseArray(int[] arr) {
		int arrLength = arr.length;
		int [] arr2= new int [arrLength];
		for (int index = 0; index < arrLength; index ++){
			arr2 [index]= arr [arr.length - index - 1];
		}
		for (int inde = 0; inde < arrLength; inde ++){
			arr[inde]=arr2[inde]; 
		}
	}
	
}



