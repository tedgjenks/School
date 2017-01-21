package edu.barrons.matrix.mathis.justin;

import edu.jenks.dist.barrons.matrix.*;
public class ArrayUtil extends AbstractArrayUtil
{
	public ArrayUtil() {}
	
	public void reverseArray(int[] a1) {
		int le = a1.length;
		int holder = 0;
		int number = (le/2);
		for (int ind = 0; ind < number;ind++){
			holder = a1[ind];
			a1[ind] = a1[le - ind - 1];
			a1[le - ind - 1] = holder;
		}
		
		
		}
}