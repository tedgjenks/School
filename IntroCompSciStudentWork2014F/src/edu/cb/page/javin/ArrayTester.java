package edu.cb.page.javin;
import edu.jenks.dist.cb.*;
public class ArrayTester extends Object implements TestableArray{

	@Override
	public boolean containsDuplicates(int[] arg0) {
		for(int i = 0; i < arg0.length; i++) {
			for(int j = i; j < arg0.length; j++) {
				if(arg0[i] == arg0[j]) return true;
			}
		}
		return false;
	}

	@Override
	public int[] getColumn(int[][] arg0, int arg1) {
		// TODO Auto-generated method stub
		int[] arr = new int[arg0.length];
		for(int i = 0; i < arr.length; i++) {
			arr[i] = arg0[i][arg1];
		}
		return arr;
	}

	@Override
	public boolean hasAllValues(int[] arg0, int[] arg1) {
		// TODO Auto-generated method stub
		for(int i = 0; i < arg0.length; i++)
			for(int j = 0; j < arg1.length;j++) {
				if(arg0[i] == arg1[j]) break;
				if(j == arg1.length - 1) return false;
			}
		return true;
	}

	@Override
	public boolean isLatin(int[][] arg0) {
		for(int i = 0; i < arg0.length - 1; i++) {
			if(!hasAllValues(arg0[i], arg0[i+1])) return false;
		}
		if(containsDuplicates(arg0[0])) return false;
		for(int j =0; j< arg0[0].length; j++) {
			if(containsDuplicates(getColumn(arg0, j))) return false;
		}
		return true;
	}
}
