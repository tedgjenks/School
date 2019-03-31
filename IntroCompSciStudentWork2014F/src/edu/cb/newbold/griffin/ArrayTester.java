package edu.cb.newbold.griffin;
import edu.jenks.dist.cb.*;

public class ArrayTester implements TestableArray{

	public ArrayTester(){
		super();
	}
	
	public boolean containsDuplicates(int[] arr){
		boolean duplicates = false; 
		for(int i = 0; i < (arr.length); i++){
			for(int j = i+1; j < arr.length; j++){ 
				if(arr[i] == arr[j]){
					duplicates = true;
				}
			}
		}
		return duplicates;
	}
	
	public int[] getColumn(int[][] arr2D, int c){
		int[] column = new int[arr2D.length];
		for(int r = 0; r < column.length; r++){
			column[r] = arr2D[r][c];
		}
		return column;
	}
	
	public boolean hasAllValues(int[] arr1, int[] arr2){
		if(arr1.length == 0){
			return true;
		}else{
			int i = 0;
			for(int j = 0; j < arr2.length; j += 0){
				if(i != arr1.length){
					if(arr1[i] == arr2[j]){
						i++;
						j = 0;
					}else{
						j++;
					}
				}else{
					return true;
				}
			}
			return false;
		}
	}

	public boolean isLatin(int[][] square){
		if(containsDuplicates(square[0])){
			return false;
		}
		
		for(int i = 1; i < square.length; i++){
			if(hasAllValues(square[0], square[1]) == false){
				return false;
			}
		}
		
		for(int j = 0; j < square.length; j++){
			int[] columnTest = getColumn(square, j);
			if(hasAllValues(square[0], columnTest) == false){
				return false;
			}
		}
		
		return true;
		
		
	}
	public static void main(String[] args){
		ArrayTester arrTest = new ArrayTester();
		int[] arr = {0, 2};
		int[] arr2 = {1, 0};
		int[][] square = {{1, 2, 1}, 
						  {2, 3, 1}, 
						  {3, 1, 2}};
		System.out.println(arrTest.isLatin(square));
		
	}



}