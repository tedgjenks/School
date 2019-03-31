package edu.cb.gabriel.mitchell;
import edu.jenks.dist.cb.*;

public class ArrayTester implements TestableArray{
    public ArrayTester(){
    }
    public int[] getColumn(int[][] arr2D, int c){
        int[] vals = new int[arr2D[0].length + 1];
        int index = 0;
        for (int row = 0; row < arr2D[0].length + 1; row++){
            vals[index] = arr2D[row][c];
            index++;
        }
        return vals;
    }
    public boolean hasAllValues(int[] arr1,int[] arr2){
        boolean matches = false;
        int sameCount = 0;
        int i = 0;
        for ( int y = 0; y < arr1.length; y++){
            for(i = 0; i < arr1.length; i++){
                if(arr1[y] == arr2[i]){
                    sameCount++;
                }
            }
        }
        if(sameCount == arr1.length){
            matches = true;
        }
        return matches;
    }
    public boolean containsDuplicates(int[] arr){
        boolean hasDupe = false;
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++){
            copy[i] = arr[i];
            if (copy[i] != arr[i]){
                hasDupe = true;
            }
        }
        return hasDupe;
    }
    public boolean isLatin(int[][] square){
        boolean answer = false;
        if( !containsDuplicates(square[0])){
            int i = 0;
            answer = true;
            for(i = 0; i < square.length; i++){
                if (!hasAllValues(square[i],square[0]) || !hasAllValues(square[0], getColumn(square, i))){
                    answer = false;
                }
            }
        }
        return answer;
    }
}
