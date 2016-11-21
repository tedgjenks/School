package edu.jenks.util;

import java.util.Arrays;
import java.util.Collection;

public class CollectionUtil {

	public static int count(Collection<?> c, Object o) {
		int count = 0;
		if(c != null && o != null) {
			for(Object element : c) {
				if(o.equals(element))
					count++;
			}
		}
		return count;
	}
	
	public static int[][] deepCopy(int[][] arg) {
		int[][] copy = null;
		if(arg != null) {
			copy = new int[arg.length][];
			for(int rowIndex = arg.length - 1; rowIndex >= 0; rowIndex--)
				copy[rowIndex] = Arrays.copyOf(arg[rowIndex], arg[rowIndex].length);
		}
		return copy;
	}
}
