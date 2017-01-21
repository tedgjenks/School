package edu.barrons.matrix.turner.john;
import edu.jenks.dist.barrons.matrix.*;

public class ArrayUtil extends AbstractArrayUtil{
	public ArrayUtil(){
		super();
	}
	
	public void reverseArray(int[] arr){
		for(int i = 0; i < arr.length/2; i++){
			int temp;
			temp = arr[i];
			int back = arr.length - 1 - i;
			arr[i] = arr[back];
			arr[back] = temp;
		}
	}
}