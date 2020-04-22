package edu.cb.macias.marcus;

import edu.jenks.dist.cb.TestableArray;

public class ArrayTester implements TestableArray{
	public static void main(String[] args) {
		ArrayTester run = new ArrayTester();
		int[][] arr = { {10,30,20,0},
						 {0,20,30,10},
						 {30,0,10,20},
						 {20,10,0,30}};
		int[] test = run.getColumn(arr, 1);
		//System.out.println(run.containsDuplicates(test));
		System.out.println(run.isLatin(arr));
		
		
		
	}
	public void arrayToString(int[] arr) {
		int i = 0 ;
		System.out.print("[");
		for(int a : arr) {
			if(i == arr.length - 1) {
				System.out.print(a+"]");
			}else{
				System.out.print(a+", ");
			}
			i++;
			
		}
	}
	public ArrayTester() {
		
	}
	
	
	public boolean containsDuplicates(int[] arr) {
		boolean answer = true;
		
		for(int i = 0 ; i < arr.length;i++) {
			for(int y = 0 ; y < arr.length;y++) {
				if(i == y) {
					continue;
				}else if(arr[i] == arr[y]) {
					return answer;
				}
			}
		}
		
		return !answer;
	}

	
	public int[] getColumn(int[][] arr2D, int c) {
		int[] answer = new int[arr2D.length];
		int i = 0 ;
		for(int[] a: arr2D) {
			answer[i] = a[c];
			i++;
		}
		return answer;
	}

	
	public boolean hasAllValues(int[] arr1, int[] arr2) {
		
		for(int i = 0 ; i < arr1.length;i++) {
			for(int y = 0 ; y < arr2.length;y++) {
				if(arr1[i] == arr2[y]){
					break;
				}
				if(y == arr2.length - 1) {
					return false;
				}
				
			}
		}
		return true;
	}

	
	public boolean isLatin(int[][] square) {
		int[] firstRow = square[0];
		boolean firstRule = !containsDuplicates(firstRow);
		//boolean secondRule = true;
		
		for(int i = 0 ; i  < square.length;i++) {
			int[] currentRow = square[i];
			boolean test = hasAllValues(firstRow,currentRow);
			if(test) {
				continue;
			}else {
				return false;
			}
		}
		//boolean thirdRule = true;
		for(int i = 0 ; i < square[0].length;i++) {
			int[] currentColumn = getColumn(square,i);
			boolean test = hasAllValues(firstRow,currentColumn);
			if(test) {
				continue;
			}else {
				return false;
			}
		}
		//System.out.println("hello");
		return firstRule;
	}

}
