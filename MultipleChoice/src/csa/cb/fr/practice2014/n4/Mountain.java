package csa.cb.fr.practice2014.n4;

public class Mountain {

	public static void main(String[] args) {
		System.out.println("Begin");
		int[] i1 = {11, 22, 33, 22, 11};
		assert getPeakIndex(i1) == 2 : 1;
		int[] i2 = {11, 22, 11, 22, 11};
		assert getPeakIndex(i2) == 1 : 2;
		int[] i3 = {11, 22, 33, 55, 77};
		assert getPeakIndex(i3) == -1 : 3;
		int[] i4 = {99, 33, 55, 77, 120};
		assert getPeakIndex(i4) == -1 : 4;
		int[] i5 = {99, 33, 55, 77, 55};
		assert getPeakIndex(i5) == 3 : 5;
		int[] i6 = {33, 22, 11};
		assert getPeakIndex(i6) == -1 : 6;
		System.out.println("End without error");
	}
	
	public static boolean isIncreasing(int[] array, int stop) {
		for(int i = 0; i < stop; i++) {
			if(array[i] >= array[i + 1])
				return false;
		}
		return true;
	}
	
	public static boolean isDecreasing(int[] array, int start) {
		for(int i = start; i < array.length - 1; i++) {
			if(array[i] <= array[i + 1])
				return false;
		}
		return true;
	}
	
	public static int getPeakIndex(int[] array) {
		if(isIncreasing(array, array.length))
			return -1;
		else if(isDecreasing(array, array.length))
			return -1;
		else {
			int high = 0;
			for(int i = array.length - 1; i > 0; i--) {
				if(isIncreasing(array, i))
					return i;
			}
		}
		return -1;
	}

}
